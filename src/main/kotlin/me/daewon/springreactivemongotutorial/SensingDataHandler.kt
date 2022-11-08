package me.daewon.springreactivemongotutorial

import java.net.URI
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class SensingDataHandler(
    private val repository: SensingDataRepository,
) {
    suspend fun register(request: ServerRequest): ServerResponse =
        request.awaitBody<SensingData>().let { sensingData ->
            repository.save(sensingData).let {
                created(URI.create("/sensing-data/${it.id}"))
                    .buildAndAwait()
            }
        }
}
