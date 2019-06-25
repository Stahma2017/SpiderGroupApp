package com.example.spidergroupapp.presenter.gallery

import com.example.spidergroupapp.data.entity.Image
import com.example.spidergroupapp.data.entity.ImagesResponseEntity
import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.view.gallery.GalleryContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GalleryPresenter(
    private val compositeDisposable: CompositeDisposable,
    private val imgurApi: ImgurApi
) : GalleryContract.Presenter {

    private var view: GalleryContract.View? = null

    override fun attachView(view: GalleryContract.View) {
        this.view = view
    }

    override fun getImages() {
        val imagesDisp = imgurApi.getImages("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { imagesResponse -> view!!.onShowImages(extractImages(imagesResponse)) }
        compositeDisposable.add(imagesDisp)
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

    private fun extractImages(response: ImagesResponseEntity): List<Image>{
        val list : MutableList<Image> = ArrayList()
        for (data in response.data!!){
            data.images?.let { list.addAll(it) }
        }
        return list
    }
}