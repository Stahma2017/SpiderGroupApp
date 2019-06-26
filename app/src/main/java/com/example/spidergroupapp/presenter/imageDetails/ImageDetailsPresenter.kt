package com.example.spidergroupapp.presenter.imageDetails

import com.example.spidergroupapp.data.entity.CommentsResponseEntity
import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.view.imageDetails.ImageDetailsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

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

    override fun getImageInfo(galleryId: String) {
        val infoDisp = imgurApi.getGalleryInfo(galleryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({infoResponse ->  view!!.showImageInfo(infoResponse)})
        compositeDisposable.add(infoDisp)
    }

    override fun getComments(galleryId: String) {
       val commentsDisp = imgurApi.getComments(galleryId)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe({commentResponse ->view!!.showComments(filterTopComments(commentResponse))  }
               )
        compositeDisposable.add(commentsDisp)
    }

    private fun filterTopComments(commentResponse: CommentsResponseEntity): List<String?>{
      return commentResponse.data!!.filter { it.ups!! >= 4 }?.map { it.comment}
    }

}