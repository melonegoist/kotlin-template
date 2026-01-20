package com.edu.operations.query.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.paging")
class PagingProperties(
    val defaultSize: Int = 100,
    val maxSize: Int = 500
)
