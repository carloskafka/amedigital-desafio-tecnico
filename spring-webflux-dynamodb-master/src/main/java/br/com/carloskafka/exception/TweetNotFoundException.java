package br.com.carloskafka.exception;

public class TweetNotFoundException extends RuntimeException {

    public TweetNotFoundException(final String tweetId) {
        super("Tweet not found with id " + tweetId);
    }
}
