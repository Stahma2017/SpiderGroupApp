package com.example.spidergroupapp.view.imageDetails


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spidergroupapp.App

import com.example.spidergroupapp.R
import javax.inject.Inject


class ImageDetailsFragment : Fragment(), ImageDetailsContract.View {

    @Inject
    lateinit var presenter: ImageDetailsContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_details, container, false)
        (activity!!.application as App).createImageDetailsComponent().injectImageDetailsFragment(this)
        presenter.attachView(this)

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }


}
