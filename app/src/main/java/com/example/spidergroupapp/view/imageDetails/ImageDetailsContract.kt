package com.example.spidergroupapp.view.imageDetails

interface ImageDetailsContract {
    interface View {

    }

    interface Presenter{
        fun attachView(view: View)
        fun detachView()
    }
}