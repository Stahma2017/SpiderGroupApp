package com.example.spidergroupapp.di.imageDetails

import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.presenter.imageDetails.ImageDetailsPresenter
import com.example.spidergroupapp.view.imageDetails.ImageDetailsContract
import com.example.spidergroupapp.view.imageDetails.ImageDetailsFragment
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable

@Subcomponent(modules = [ImageDetailsModule::class])
interface ImageDetailsComponent {
    fun injectImageDetailsFragment(imageDetailsFragment: ImageDetailsFragment)
}

@Module
class ImageDetailsModule {
    @Provides
    fun provideImageDetailsPresenter(
        compositeDisposable: CompositeDisposable,
        imgurApi: ImgurApi
    ): ImageDetailsContract.Presenter = ImageDetailsPresenter(compositeDisposable, imgurApi)
}