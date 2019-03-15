package br.com.carloskafka.config;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;

import br.com.carloskafka.handlers.ApiHandler;
import br.com.carloskafka.handlers.ErrorHandler;
import br.com.carloskafka.repository.TweetRepository;
import br.com.carloskafka.repository.TweetRepositoryImpl;
import br.com.carloskafka.routers.MainRouter;
import software.amazon.awssdk.core.auth.AwsCredentials;
import software.amazon.awssdk.core.auth.AwsCredentialsProvider;
import software.amazon.awssdk.core.auth.StaticCredentialsProvider;
import software.amazon.awssdk.core.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDBAsyncClient;

@Configuration
@EnableWebFlux
public class DynamoAsyncDBConfig {

	@Value("${amazon.dynamodb.endpoint}")
	private String dBEndpoint;

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	private AwsCredentialsProvider amazonAWSCredentialsProvider() {
		return StaticCredentialsProvider.create(amazonAWSCredentials());
	}

	private AwsCredentials amazonAWSCredentials() {
		return AwsCredentials.create(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	@Bean
	public DynamoDBAsyncClient dynamoDBAsyncClient() {
		return DynamoDBAsyncClient.builder().region(Region.EU_WEST_1).endpointOverride(URI.create(dBEndpoint))
				.credentialsProvider(amazonAWSCredentialsProvider()).build();
	}

	@Bean
	public ApiHandler apiHandler(TweetRepository awsDynamoService, ErrorHandler errorHandler) {
		return new ApiHandler(awsDynamoService, errorHandler);
	}

	@Bean
	public TweetRepository awsDynamoDbRepository(DynamoDBAsyncClient dynamoDBAsyncClient) {
		return new TweetRepositoryImpl(dynamoDBAsyncClient);
	}

	@Bean
	public ErrorHandler errorHandler() {
		return new ErrorHandler();
	}

	@Bean
	public RouterFunction<?> mainRouterFunction(ApiHandler apiHandler, ErrorHandler errorHandler) {
		return MainRouter.doRoute(apiHandler, errorHandler);
	}
}
