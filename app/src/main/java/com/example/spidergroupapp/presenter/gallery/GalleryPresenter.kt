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
    private var currentPage = 0

    override fun attachView(view: GalleryContract.View) {
        this.view = view
    }

    override fun loadNextPage() {
        currentPage++
        view?.setRefreshing(true)
        getImages()
    }

    override fun refresh() {
        currentPage = 0
        view?.setRefreshing(true)
        getImages()
    }

    override fun getImages(page: Int) {
        val imagesDisp = imgurApi.getImages("$currentPage")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ imagesResponse ->
                if (imagesResponse.data.isNullOrEmpty()) {
                    view!!.stopPagination()
                } else {
                    view!!.onShowImages(extractImages(imagesResponse))
                    view!!.setRefreshing(false)
                }
            },
                {
                    view!!.setRefreshing(false)
                    currentPage--
                })
        compositeDisposable.add(imagesDisp)
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }

    private fun extractImages(response: ImagesResponseEntity): List<Image> {
        val list: MutableList<Image> = ArrayList()
        for (data in response.data!!) {
            data.images?.let { list.addAll(it) }
        }
        return list
    }

}