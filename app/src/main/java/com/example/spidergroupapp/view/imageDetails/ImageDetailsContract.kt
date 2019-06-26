package com.example.spidergroupapp.view.imageDetails

import com.example.spidergroupapp.data.entity.GalleryInfoResponseEntity
import com.example.spidergroupapp.view.base.CanShowError

interface ImageDetailsContract {
    interface View: CanShowError {
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