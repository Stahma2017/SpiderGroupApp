package com.example.spidergroupapp.view.gallery

import com.example.spidergroupapp.data.entity.Datum
import com.example.spidergroupapp.view.base.CanShowError

interface GalleryContract {
    interface View: CanShowError {
        fun onShowImages(images: List<Datum>)
        fun setRefreshing(flag: Boolean)
        fun stopPagination()
    }

    interface Presenter{
        fun attachView(view: View)
        fun detachView()
        fun getImages(page: Int = 0)
        fun loadNextPage()
        fun refresh()
    }
}