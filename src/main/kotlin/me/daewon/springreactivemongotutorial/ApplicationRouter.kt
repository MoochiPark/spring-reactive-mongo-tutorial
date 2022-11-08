package me.daewon.springreactivemongotutorial

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ApplicationRouter(
    private val sensingDataHandler: SensingDataHandler,
) {
    @Bean
    fun route(): RouterFunction<ServerResponse> =
        coRouter {
            "/api".nest {
                accept(APPLICATION_JSON).nest {
                    "/sensing-data".nest {
                        POST("", sensingDataHandler::register)
                    }
                }
            }
        }
}
