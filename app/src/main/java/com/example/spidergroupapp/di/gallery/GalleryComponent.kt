package com.example.spidergroupapp.di.gallery

import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.presenter.gallery.GalleryPresenter
import com.example.spidergroupapp.view.base.ErrorHandler
import com.example.spidergroupapp.view.gallery.GalleryContract
import com.example.spidergroupapp.view.gallery.GalleryFragment
import com.example.spidergroupapp.view.gallery.adapter.ImagesAdapter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable

@Subcomponent(modules = [GalleryModule::class])
interface GalleryComponent {
    fun injectGalleryFragment(galleryFragment: GalleryFragment)
}

@Module
class GalleryModule {
    @Provides
    fun provideGalleryPresenter(
        compositeDisposable: CompositeDisposable,
        imgurApi: ImgurApi,
        errorHandler: ErrorHandler
    ): GalleryContract.Presenter = GalleryPresenter(compositeDisposable, imgurApi, errorHandler)

    @Provides
    fun provideImagesAdapter(): ImagesAdapter =
        ImagesAdapter()
}