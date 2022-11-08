package me.daewon.springreactivemongotutorial

import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SensingDataRepository : CoroutineCrudRepository<SensingData, String>
