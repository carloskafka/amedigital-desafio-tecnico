package br.com.carloskafka.repository;

import java.util.Map;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.Condition;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public class RequestBuilder {

	public static PutItemRequest buildPutItemRequest(String tableName, Map<String, AttributeValue> attributeValueHashMap) {
		return PutItemRequest.builder().tableName(tableName).item(attributeValueHashMap).build();
	}

	public static GetItemRequest buildGetItemRequest(String tableName, Map<String, AttributeValue> attributeValueHashMap) {
		return GetItemRequest.builder().tableName(tableName).key(attributeValueHashMap).build();
	}

	public static ScanRequest buildScanRequest(String tableName,  Map<String, Condition> conditionHashMap) {
		return ScanRequest.builder().tableName(tableName).scanFilter(conditionHashMap).build();
	}
	
	public static DeleteItemRequest buildDeleteItemRequest(String tableName, Map<String, AttributeValue> attributeValueHashMap) {
		return DeleteItemRequest.builder().tableName(tableName).key(attributeValueHashMap).build();
	}
	
}
