package br.com.carloskafka.handlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import br.com.carloskafka.dto.TweetDTO;
import br.com.carloskafka.repository.TweetRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ApiHandler {

    private static final String id = "id";

    private final ErrorHandler errorHandler;

    private final TweetRepository tweetRepository;

    public ApiHandler(final TweetRepository tweetRepository, final ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        this.tweetRepository = tweetRepository;
    }

    public Mono<ServerResponse> getTweetDTO(final ServerRequest request) {
        String tweetDtoId = request.pathVariable(id);
        Mono<TweetDTO> tweetDtoResponseMono = tweetRepository.findById(tweetDtoId);
        return tweetDtoResponseMono
                .flatMap(tweetDto -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(tweetDto)))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(errorHandler::throwableError);
    }

    public Mono<ServerResponse> createTweetDTO(final ServerRequest request) {
        Mono<TweetDTO> tweetDtoMono = request.bodyToMono(TweetDTO.class);
        return tweetDtoMono.doOnNext(tweetRepository::save)
                .flatMap(tweetDto -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(tweetDto)))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(errorHandler::throwableError);
    }

    public Mono<ServerResponse> findAll(final ServerRequest request) {
        Flux<List<TweetDTO>> tweets = this.tweetRepository.findAll();
        ParameterizedTypeReference<List<TweetDTO>> typeRef = new ParameterizedTypeReference<List<TweetDTO>>() {
        };
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(tweets, typeRef);
    }

    public Mono<ServerResponse> delete(final ServerRequest request) {
        String tweetDtoId = request.pathVariable(id);

        return ServerResponse.ok().build(tweetRepository.delete(tweetDtoId))
                .onErrorResume(errorHandler::throwableError);

    }
}