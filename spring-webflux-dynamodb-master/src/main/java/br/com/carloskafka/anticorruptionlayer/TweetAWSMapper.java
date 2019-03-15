package br.com.carloskafka.anticorruptionlayer;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import br.com.carloskafka.dto.TweetDTO;
import br.com.carloskafka.factory.TweetFactory;
import br.com.carloskafka.model.Tweet;
import br.com.carloskafka.utils.JsonUtils;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ComparisonOperator;
import software.amazon.awssdk.services.dynamodb.model.Condition;

public class TweetAWSMapper {
	public static final String ID = "Id";
	
	public static Map<String, AttributeValue> convertMapToTweetDTO(final TweetDTO tweetDto) {
		Tweet tweet = TweetFactory.convertDTOToDomain(tweetDto);
		
		Map<String, AttributeValue> attributeValueHashMap = new HashMap<>();
		
		String tweetJson = JsonUtils.toJson(tweet);
		
		AttributeValue text = AttributeValue.builder().s(tweetJson).build();
		AttributeValue id = AttributeValue.builder().s(tweet.getId()).build();
		AttributeValue createdAt = AttributeValue.builder().s(tweet.getCreatedAt()).build();
		
		attributeValueHashMap.put(ID, id);
        attributeValueHashMap.put(Tweet.TEXT, text);
        attributeValueHashMap.put(Tweet.CREATED_AT, createdAt);
        
		return attributeValueHashMap;
	}
    
	public static TweetDTO createTweetDTO(Map<String, AttributeValue> map) {
    	TweetDTO tweetDto = null;
    	
        if (map != null) {
        	String id = map.get(ID).s();
        	String text = map.get(Tweet.TEXT).s();
        	String createdAt = map.get(Tweet.CREATED_AT).s();
        	
        	Tweet tweet = new Tweet();
        	 
        	tweet.setId(id);
        	tweet.setCreatedAt(createdAt);
        	tweet.setText(text);
        	
			tweetDto = TweetFactory.convertDomainToDTO(tweet);        	
        }
        
        return tweetDto;
    }
    
	public static  TweetDTO createTweetDTOFromJson(Map<String, AttributeValue> map) {
    	TweetDTO tweetDto = null;
    	
        if (map != null) {
        	String json = map.get(Tweet.TEXT).s();
            tweetDto = (TweetDTO) JsonUtils.fromJson(json);
            if (tweetDto != null) {
            	tweetDto.setText(json);
            }
        }
        return tweetDto;
    }
    
	public static  Map<String, Condition> convertTweetDTOCreatedAtToConditionalMap() {
		Map<String, Condition> conditionHashMap = new HashMap<>();
		
        Condition condition = Condition.builder()
                .comparisonOperator(ComparisonOperator.LT)
                .attributeValueList(AttributeValue.builder().s(LocalDateTime.now().toString()).build())
                .build();

        conditionHashMap.put(Tweet.CREATED_AT, condition);
        
		return conditionHashMap;
	}

	public static Map<String, AttributeValue> convertIdToMap(final String id) {
		Map<String, AttributeValue> attributeValueHashMap = new HashMap<>();
		
        attributeValueHashMap.put(ID, AttributeValue.builder().s(id).build());
        
		return attributeValueHashMap;
	}
}
