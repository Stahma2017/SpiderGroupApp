package com.example.spidergroupapp.view.imageDetails

import com.example.spidergroupapp.data.entity.GalleryInfoResponseEntity

interface ImageDetailsContract {
    interface View {
        fun showComments(comments: List<String?>)
        fun showImageInfo(info: GalleryInfoResponseEntity)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun getComments(galleryId: String)
        fun getImageInfo(galleryId: String)
    }
}