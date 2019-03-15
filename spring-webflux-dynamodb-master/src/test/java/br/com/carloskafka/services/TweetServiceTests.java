package br.com.carloskafka.services;

import java.util.Collections;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.carloskafka.dto.TweetDTO;
import br.com.carloskafka.repository.TweetRepository;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TweetServiceTests {

	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired
	private TweetRepository awsDynamoService;

	@Test
	public void testCreateTweetDTO() {
		String randomId = UUID.randomUUID().toString();
		
		TweetDTO tweetDto = new TweetDTO();
		
		tweetDto.setId(randomId);
		tweetDto.setTeste("This is a Test TweetDTO");

		webTestClient.post().uri("/tweets")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(tweetDto), TweetDTO.class)
				.exchange()
				.expectStatus().isOk();
	}

	@Test
    public void testGetAllTweetDTOs() {
	    webTestClient.get().uri("/tweets")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(TweetDTO.class)
                .consumeWith(response -> 
                		Assertions.assertThat(response.getResponseBody()).isNotNull());
    }

    @Test
    public void testGetSingleTweetDTO() {
    	TweetDTO tweetDto = new TweetDTO();
    	
    	tweetDto.setId("4321");
		tweetDto.setTeste("Hello, World!");
		
		awsDynamoService.save(tweetDto);
	    
		webTestClient.get()
                .uri("/tweets/{id}", Collections.singletonMap("id", tweetDto.getId()) )
				.accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange()
				.expectStatus().isOk()
				.expectBody()
				.consumeWith(response ->
						Assertions.assertThat(response.getResponseBody()).isNotNull());
    }


    @Test
    public void testDeleteTweetDTO() {
    	TweetDTO tweetDto = new TweetDTO();
    	
    	tweetDto.setId("1234");
		tweetDto.setTeste("TweetDTO to delete!");
		
		awsDynamoService.save(tweetDto);
		
		webTestClient.delete()
                .uri("/tweets/{id}", Collections.singletonMap("id",  tweetDto.getId()))
                .exchange()
                .expectStatus().isOk();
    }
}
