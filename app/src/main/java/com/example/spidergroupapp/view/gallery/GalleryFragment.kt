package com.example.spidergroupapp.view.gallery

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.spidergroupapp.App
import com.example.spidergroupapp.data.entity.Datum
import com.example.spidergroupapp.view.gallery.adapter.ImagesAdapter
import com.example.spidergroupapp.view.main.MainActivity
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_gallery.*
import javax.inject.Inject

class GalleryFragment : Fragment(), GalleryContract.View, SwipyRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: GalleryContract.Presenter
    @Inject
    lateinit var imagesAdapter: ImagesAdapter

    //pagination details
    private var isLastPage = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.example.spidergroupapp.R.layout.fragment_gallery, container, false)
        (activity!!.application as App).createGalleryComponent().injectGalleryFragment(this)
        presenter.attachView(this)
        presenter.getImages()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener(this)
        imagesRec.setHasFixedSize(true)
        val layoutManager = StaggeredGridLayoutManager(3, 1)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        imagesRec.layoutManager = layoutManager
        imagesRec.adapter = imagesAdapter
        imagesRec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = imagesRec.layoutManager?.childCount
                val totalItemCount = imagesRec.layoutManager?.itemCount
                val firstVisibleItemPosition =
                    (imagesRec.layoutManager as StaggeredGridLayoutManager).findFirstVisibleItemPositions(IntArray(3))[0]
                if (!swipeRefresh.isRefreshing && !isLastPage) {
                    if ((visibleItemCount!! + firstVisibleItemPosition) >= totalItemCount!!
                        && firstVisibleItemPosition >= 0
                    ) {
                        presenter.loadNextPage()
                    }
                }
            }
        })
        imagesAdapter.imageClickListener = {
            (activity as MainActivity).navigateToImageDetails(it)
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    override fun setRefreshing(flag: Boolean) {
        swipeRefresh.isRefreshing = flag
    }

    override fun onShowImages(images: List<Datum>) {
        imagesAdapter.addImages(images)
    }

    override fun onRefresh(direction: SwipyRefreshLayoutDirection?) {
        presenter.refresh()
        isLastPage = false
        imagesAdapter.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun stopPagination() {
        isLastPage = true
        setRefreshing(false)
    }
}
