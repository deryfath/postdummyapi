package com.cobamvvm.project.data.model

data class postResponse(
    val `data`: MutableList<Data>,
    val limit: Int,
    val page: Int,
    val total: Int
)

data class Data(
    val id: String,
    val image: String,
    val likes: Int,
    val owner: Owner,
    val publishDate: String,
    val tags: List<String>,
    val text: String
)

data class Owner(
    val firstName: String,
    val id: String,
    val lastName: String,
    val picture: String,
    val title: String
)