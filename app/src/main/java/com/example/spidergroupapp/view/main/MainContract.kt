package com.example.spidergroupapp.view.main

interface MainContract {
    interface View {

    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}