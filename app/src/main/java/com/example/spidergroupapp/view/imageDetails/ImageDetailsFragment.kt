package com.example.spidergroupapp.view.imageDetails


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.spidergroupapp.App

import com.example.spidergroupapp.R
import com.example.spidergroupapp.data.entity.GalleryInfoResponseEntity
import com.example.spidergroupapp.view.imageDetails.adapter.CommentAdapter
import kotlinx.android.synthetic.main.fragment_image_details.*
import javax.inject.Inject


class ImageDetailsFragment : Fragment(), ImageDetailsContract.View {

    @Inject
    lateinit var presenter: ImageDetailsContract.Presenter
    @Inject
    lateinit var commentAdapter: CommentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image_details, container, false)
        (activity!!.application as App).createImageDetailsComponent().injectImageDetailsFragment(this)
        presenter.attachView(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        commentsRec.layoutManager = LinearLayoutManager(context)
        commentsRec.adapter = commentAdapter
        super.onViewCreated(view, savedInstanceState)
        val extras = arguments
        val galleryId = extras?.getString(getString(R.string.intent_message))?: ""
        presenter.getComments(galleryId)
        presenter.getImageInfo(galleryId)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showImageInfo(info: GalleryInfoResponseEntity) {
        val data = info.data
        title.text = data?.title ?: ""
        Glide.with(this)
            .load(data?.images?.get(0)?.link)
            .placeholder(R.drawable.ic_placeholder)
            .fitCenter()
            .into(image)
        ups.text = data?.ups.toString()
        downs.text = data?.downs.toString()
    }

    override fun showComments(comments: List<String?>) {
        commentAdapter.setComments(comments)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }
}
