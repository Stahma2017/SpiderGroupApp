package com.example.spidergroupapp.data.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GalleryInfoResponseEntity(
    @SerializedName("data")
    @Expose
    val data: GalleryData?,
    val success: Boolean?,
    val status: Int?
)

data class GalleryImage(
    val id: String?,
    val title: Any?,
    val description: String?,
    val datetime: Int,
    val type: String?,
    val animated: Boolean?,
    val width: Int?,
    val height: Int?,
    val size: Int?,
    val views: Int?,
    val vote: Any?,
    val favorite: Boolean?,
    val nsfw: Any?,
    val section: Any?,
    val accountUrl: Any?,
    val accountId: Any?,
    val isAd: Boolean?,
    val inMostViral: Boolean?,
    val hasSound: Boolean?,
    val tags: List<Any>?,
    val adType: Int?,
    val adUrl: String?,
    val edited: String?,
    val inGallery: Boolean?,
    val link: String?,
    val commentCount: Any?,
    val favoriteCount: Any?,
    val ups: Any?,
    val downs: Any?,
    val points: Any?,
    val score: Any?
)

data class GalleryData(
    val id: String?,
    val title: String?,
    val description: Any?,
    val datetime: Int?,
    val cover: String?,
    val coverWidth: Int?,
    val coverHeight: Int?,
    val accountUrl: String?,
    val accountId: Int?,
    val privacy: String?,
    val layout: String?,
    val views: Int?,
    val link: String?,
    val ups: Int?,
    val downs: Int?,
    val points: Int?,
    val score: Int?,
    val isAlbum: Boolean?,
    val vote: Any?,
    val favorite: Boolean?,
    val nsfw: Boolean?,
    val section: String?,
    val commentCount: Int?,
    val favoriteCount: Int?,
    val topic: String?,
    val topicId: Int?,
    val imagesCount: Int?,
    val inGallery: Boolean?,
    val isAd: Boolean?,
    val tags: List<Any>?,
    val adType: Int?,
    val adUrl: String?,
    val inMostViral: Boolean?,
    val includeAlbumAds: Boolean?,
    @SerializedName("images")
    @Expose
    val images: List<GalleryImage>?
)