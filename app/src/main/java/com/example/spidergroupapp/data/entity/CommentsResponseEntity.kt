package com.example.spidergroupapp.data.entity



data class CommentsResponseEntity(
    val data: List<Data>?,
    val success: Boolean?,
    val status: Int?
)

data class Data(
    val id: Int?,
    val imageId: String?,
    val comment: String?,
    val author: String?,
    val authorId: Int?,
    val onAlbum: Boolean?,
    val albumCover: String?,
    val ups: Int?,
    val downs: Int?,
    val points: Int?,
    val datetime: Int?,
    val parentId: Int?,
    val deleted: Boolean?,
    val vote: Any?,
    val platform: String?,
    val children: List<Child>?
)

data class Child(
    val id: Int?,
    val imageId: String?,
    val comment: String?,
    val author: String?,
    val authorId: Int?,
    val onAlbum: Boolean?,
    val albumCover: String?,
    val ups: Int?,
    val downs: Int?,
    val points: Int?,
    val datetime: Int?,
    val parentId: Int?,
    val deleted: Boolean?,
    val vote: Any?,
    val platform: String?,
    val children: List<SubChild>?
)

data class SubChild(
    val id: Int?,
    val imageId: String?,
    val comment: String?,
    val author: String?,
    val authorId: Int?,
    val onAlbum: Boolean?,
    val albumCover: String?,
    val ups: Int?,
    val downs: Int?,
    val points: Int?,
    val datetime: Int?,
    val parentId: Int?,
    val deleted: Boolean?,
    val vote: Any?,
    val platform: String?,
    val children: List<Any>?
)