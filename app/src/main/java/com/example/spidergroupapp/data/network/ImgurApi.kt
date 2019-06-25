package com.example.spidergroupapp.data.network

import com.example.spidergroupapp.data.entity.ImagesResponseEntity
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurApi {

    @GET("gallery/top/top/day/{page}?showViral=true&mature=true&album_previews=false")
    fun getImages(@Path("page") page: String): Flowable<ImagesResponseEntity>


}