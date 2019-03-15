package br.com.carloskafka.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.carloskafka.dto.TweetDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TweetRepository {
    public Mono<TweetDTO> save(TweetDTO tweetDto);
    public Mono<TweetDTO> findById(String id);
    public Mono<Void> delete(String id);
    public Flux<List<TweetDTO>> findAll();
}
