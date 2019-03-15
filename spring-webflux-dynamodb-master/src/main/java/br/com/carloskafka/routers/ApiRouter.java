package br.com.carloskafka.routers;

import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import br.com.carloskafka.handlers.ApiHandler;
import br.com.carloskafka.handlers.ErrorHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

class ApiRouter {

    private static final String TWEETS = "/tweets";
    private static final String ID = "/{id}";

    static RouterFunction<?> doRoute(final ApiHandler apiHandler, final ErrorHandler errorHandler) {
        return
                nest(path(TWEETS),
                        nest(accept(APPLICATION_JSON),
                                route(GET(ID), apiHandler::getTweetDTO)
                                        .andRoute(DELETE(ID), apiHandler::delete)
                                        .andRoute(GET("/"), apiHandler::findAll)
                                        .andRoute(POST("/"), apiHandler::createTweetDTO)
                        ).andOther(route(RequestPredicates.all(), errorHandler::notFound))
                );
    }
}
