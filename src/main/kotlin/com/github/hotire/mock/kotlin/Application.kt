package com.github.hotire.mock.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MockKotlinApplication

fun main(args: Array<String>) {
    runApplication<MockKotlinApplication>(*args)
}
