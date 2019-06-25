package com.example.spidergroupapp.view.gallery

import com.example.spidergroupapp.data.entity.Image

interface GalleryContract {
    interface View {
        fun onShowImages(images: List<Image>)
    }

    interface Presenter{
        fun attachView(view: View)
        fun detachView()
        fun getImages()
    }
}