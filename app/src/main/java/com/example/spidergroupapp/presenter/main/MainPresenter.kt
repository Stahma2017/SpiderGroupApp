package com.example.spidergroupapp.presenter.main

import com.example.spidergroupapp.view.main.MainContract

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}