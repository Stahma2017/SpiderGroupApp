package com.example.spidergroupapp.view.gallery

interface GalleryContract {
    interface View {

    }

    interface Presenter{
        fun attachView(view: View)
        fun detachView()
    }
}