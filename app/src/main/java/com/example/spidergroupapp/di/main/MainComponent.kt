package com.example.spidergroupapp.di.main

import com.example.spidergroupapp.di.gallery.GalleryComponent
import com.example.spidergroupapp.di.imageDetails.ImageDetailsComponent
import com.example.spidergroupapp.presenter.main.MainPresenter
import com.example.spidergroupapp.view.main.MainActivity
import com.example.spidergroupapp.view.main.MainContract
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent{
    fun injectMainActivity(activity: MainActivity)
    fun addGalleryComponent() : GalleryComponent
    fun addImageDetailsComponent() : ImageDetailsComponent
}


@Module
class MainModule {
    @Provides
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()
}

