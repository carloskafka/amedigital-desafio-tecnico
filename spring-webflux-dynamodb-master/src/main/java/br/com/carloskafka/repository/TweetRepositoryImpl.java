package br.com.carloskafka.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import br.com.carloskafka.anticorruptionlayer.TweetAWSMapper;
import br.com.carloskafka.dto.TweetDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDBAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.Condition;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

public class TweetRepositoryImpl implements TweetRepository {

	private static final String tableName = "Tweet";

	private DynamoDBAsyncClient dynamoDBAsyncClient;

	public TweetRepositoryImpl(DynamoDBAsyncClient client) {
		this.dynamoDBAsyncClient = client;
	}

	@Override
	public Mono<TweetDTO> save(final TweetDTO tweetDto) {
		Map<String, AttributeValue> attributeValueHashMap = TweetAWSMapper.convertMapToTweetDTO(tweetDto);

		PutItemRequest putItemRequest = RequestBuilder.buildPutItemRequest(tableName, attributeValueHashMap);

		CompletableFuture<PutItemResponse> completableFuture = dynamoDBAsyncClient.putItem(putItemRequest);

		CompletableFuture<TweetDTO> tweetCompletableFuture = completableFuture
				.thenApplyAsync(PutItemResponse::attributes).thenApplyAsync(map -> TweetAWSMapper.createTweetDTO(map));

		return Mono.fromFuture(tweetCompletableFuture);
	}

	@Override
	public Mono<TweetDTO> findById(String id) {
		Map<String, AttributeValue> attributeValueHashMap = TweetAWSMapper.convertIdToMap(id);

		GetItemRequest getItemRequest = RequestBuilder.buildGetItemRequest(tableName, attributeValueHashMap);

		CompletableFuture<GetItemResponse> completableFuture = dynamoDBAsyncClient.getItem(getItemRequest);

		CompletableFuture<TweetDTO> tweetCompletableFuture = completableFuture.thenApplyAsync(GetItemResponse::item)
				.thenApplyAsync(map -> TweetAWSMapper.createTweetDTOFromJson(map));

		return Mono.fromFuture(tweetCompletableFuture);
	}

	@Override
	public Flux<List<TweetDTO>> findAll() {
		Map<String, Condition> conditionalMap = TweetAWSMapper.convertTweetDTOCreatedAtToConditionalMap();

		ScanRequest scanRequest = RequestBuilder.buildScanRequest(tableName, conditionalMap);

		CompletableFuture<ScanResponse> future = dynamoDBAsyncClient.scan(scanRequest);

		CompletableFuture<List<TweetDTO>> response = future.thenApplyAsync(ScanResponse::items)
				.thenApplyAsync(list -> list.parallelStream().map(map -> TweetAWSMapper.createTweetDTOFromJson(map))
						.collect(Collectors.toList()));

		return Flux.from(Mono.fromFuture(response));
	}

	@Override
	public Mono<Void> delete(final String id) {
		DeleteItemRequest deleteItemRequest = RequestBuilder.buildDeleteItemRequest(tableName,
				TweetAWSMapper.convertIdToMap(id));

		dynamoDBAsyncClient.deleteItem(deleteItemRequest);

		return Mono.empty();
	}

}
