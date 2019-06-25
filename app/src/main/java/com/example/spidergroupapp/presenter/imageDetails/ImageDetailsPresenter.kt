package com.example.spidergroupapp.presenter.imageDetails

import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.view.imageDetails.ImageDetailsContract
import io.reactivex.disposables.CompositeDisposable

class ImageDetailsPresenter(
    private val compositeDisposable: CompositeDisposable,
    private val imgurApi: ImgurApi
) : ImageDetailsContract.Presenter {

    private var view: ImageDetailsContract.View? = null

    override fun attachView(view: ImageDetailsContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()

    }
}