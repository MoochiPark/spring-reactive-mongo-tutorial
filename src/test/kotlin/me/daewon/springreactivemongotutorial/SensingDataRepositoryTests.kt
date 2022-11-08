package me.daewon.springreactivemongotutorial

import java.time.LocalDateTime
import java.util.UUID
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DataMongoTest
class SensingDataRepositoryTests(
    @Autowired
    private val repository: SensingDataRepository,
) {
    @BeforeEach
    fun setUp(): Unit = runBlocking {
        repository.deleteAll()
    }

    @AfterEach
    fun tearDown(): Unit = runBlocking {
        repository.deleteAll()
    }

    @Test
    fun `verifying sensing data is stored correctly`(): Unit = runBlocking {
        // Given
        val data = UUID.randomUUID().toString()
        val sensingTime = LocalDateTime.now()
        val sensingData = SensingData(
            data = data,
            sensingTime = sensingTime
        )

        // When & Then
        repository.save(sensingData).let {
            assertThat(it.id).isNotNull
            assertThat(it.data).isEqualTo(data)
            assertThat(it.sensingTime).isEqualTo(sensingTime)
        }
    }

    @Test
    fun `verifying number of sensing data in findAll`(): Unit = runBlocking {
        // Given
        val randomSensingData = SensingData(
            data = UUID.randomUUID().toString(),
            sensingTime = LocalDateTime.now()
        )

        // When
        repository.saveAll(
            flowOf(
                randomSensingData,
                randomSensingData,
                randomSensingData
            )
        ).collect()

        // Then
        assertThat(repository.count()).isEqualTo(3)
    }
}
