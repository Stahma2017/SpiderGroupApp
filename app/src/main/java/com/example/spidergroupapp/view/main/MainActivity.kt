package com.example.spidergroupapp.view.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.spidergroupapp.App
import com.example.spidergroupapp.R
import com.example.spidergroupapp.view.gallery.GalleryFragment
import com.example.spidergroupapp.view.imageDetails.ImageDetailsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).createMaincomponent().injectMainActivity(this)
        presenter.attachView(this)
        init()
    }

    private fun init() {
        doFragmentTransaction(GalleryFragment(), getString(R.string.fragment_gallery), false, "")
    }

    private fun doFragmentTransaction(fragment: Fragment, tag: String, addToBackStack: Boolean, message: String) {
        val transaction = supportFragmentManager.beginTransaction()
        if (message != "") {
            val bundle = Bundle()
            bundle.putString(getString(R.string.intent_message), message)
            fragment.arguments = bundle
        }
        transaction.replace(R.id.container, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.commit()
    }

    fun navigateToImageDetails(galleryId: String){
        doFragmentTransaction(ImageDetailsFragment(), "", true, galleryId)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
