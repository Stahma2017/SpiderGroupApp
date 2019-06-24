package com.example.spidergroupapp.presenter.gallery

import com.example.spidergroupapp.view.gallery.GalleryContract

class GalleryPresenter : GalleryContract.Presenter {

    private var view: GalleryContract.View? = null

    override fun attachView(view: GalleryContract.View) {
        this.view = view
    }

    override fun detachView() {
       view = null
    }
}