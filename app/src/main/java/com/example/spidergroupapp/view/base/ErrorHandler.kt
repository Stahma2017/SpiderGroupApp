package com.example.spidergroupapp.view.base

interface ErrorHandler {
    fun proceed(error: Throwable)
    fun attachView(view: CanShowError)
    fun detachView()
}