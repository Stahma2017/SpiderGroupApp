package com.example.spidergroupapp

import android.app.Application
import com.example.spidergroupapp.di.app.ApplicationComponent
import com.example.spidergroupapp.di.app.ApplicationModule
import com.example.spidergroupapp.di.app.DaggerApplicationComponent
import com.example.spidergroupapp.di.gallery.GalleryComponent
import com.example.spidergroupapp.di.main.MainComponent

class App : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    private var mainComponent: MainComponent? = null
    private var galleryComponent: GalleryComponent? = null

    fun createMaincomponent(): MainComponent {
        if (mainComponent == null) {
            mainComponent = applicationComponent.addMainComponent()
        }
        return mainComponent!!
    }

    fun createGalleryComponent(): GalleryComponent{
        if (galleryComponent == null){
            galleryComponent = mainComponent?.addGalleryComponent()
        }
            return galleryComponent!!
    }
}