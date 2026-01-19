package com.edu.operations.query

import com.edu.operations.query.config.PagingProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(PagingProperties::class)
class OperationsQueryApplication

fun main(args: Array<String>) {
    runApplication<OperationsQueryApplication>(*args)
}
