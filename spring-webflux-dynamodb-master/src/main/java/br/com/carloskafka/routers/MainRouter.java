package br.com.carloskafka.routers;

import org.springframework.web.reactive.function.server.RouterFunction;

import br.com.carloskafka.handlers.ApiHandler;
import br.com.carloskafka.handlers.ErrorHandler;

public class MainRouter {

    public static RouterFunction<?> doRoute(final ApiHandler handler, final ErrorHandler errorHandler) {
        return ApiRouter
                .doRoute(handler, errorHandler)
                .andOther(StaticRouter.doRoute());
    }
}
