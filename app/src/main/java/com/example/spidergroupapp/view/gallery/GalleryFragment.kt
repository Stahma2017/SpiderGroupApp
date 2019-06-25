package com.example.spidergroupapp.view.gallery


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spidergroupapp.App
import com.example.spidergroupapp.R
import com.example.spidergroupapp.data.entity.Image
import com.example.spidergroupapp.view.gallery.adapter.ImagesAdapter
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

class GalleryFragment : Fragment(), GalleryContract.View {

    @Inject
    lateinit var presenter: GalleryContract.Presenter
    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)
        (activity!!.application as App).createGalleryComponent().injectGalleryFragment(this)
        presenter.attachView(this)
        presenter.getImages()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesRec.setHasFixedSize(true)
        val layoutManager = StaggeredGridLayoutManager(3, 1)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        imagesRec.layoutManager = layoutManager
        imagesRec.adapter = imagesAdapter
    }

    override fun onShowImages(images: List<Image>) {
        imagesAdapter.setList(images)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }


}
