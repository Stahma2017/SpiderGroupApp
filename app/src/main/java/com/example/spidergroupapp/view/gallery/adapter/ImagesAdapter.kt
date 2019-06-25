package com.example.spidergroupapp.view.gallery.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.spidergroupapp.R
import com.example.spidergroupapp.data.entity.Image
import kotlinx.android.synthetic.main.recycler_image_item.view.*
import java.util.ArrayList

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private var images: List<Image> = ArrayList()

    fun setList(images: List<Image>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_image_item, viewGroup, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(vh: ImageViewHolder, i: Int) {
        vh.bind(images[i])
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: Image) {
            Glide.with(itemView)
                .load(image.link)
                .fitCenter()
                .into(itemView.imageView)

        }
    }
}