package br.com.carloskafka.factory;

import br.com.carloskafka.dto.TweetDTO;
import br.com.carloskafka.model.Tweet;

public class TweetFactory {

	public static Tweet convertDTOToDomain(TweetDTO tweetDto) {
		Tweet tweet = null;
		
		if (tweetDto != null) {
			tweet = new Tweet();
			
			tweet.setId(tweetDto.getId());
			tweet.setCreatedAt(tweetDto.getCreatedAt());
			tweet.setTeste(tweetDto.getTeste());
		}
		
		return tweet;
	}
	
	public static TweetDTO convertDomainToDTO(Tweet tweet) {
		TweetDTO tweetDto = null;
		
		if (tweet != null) {
			tweetDto = new TweetDTO();
			
			tweetDto.setId(tweet.getId());
        	tweetDto.setCreatedAt(tweet.getCreatedAt());
        	tweetDto.setText(tweet.getText());
		}
		
		return tweetDto;
	}
}
