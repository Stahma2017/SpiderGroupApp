package com.example.spidergroupapp.view.gallery.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.spidergroupapp.R
import com.example.spidergroupapp.data.entity.Datum
import kotlinx.android.synthetic.main.recycler_image_item.view.*
import java.util.ArrayList

class ImagesAdapter : RecyclerView.Adapter<ImagesAdapter.ImageViewHolder>() {

    private var galleries: MutableList<Datum> = ArrayList()
    var imageClickListener: (String) -> Unit = {}

   fun clear(){
       galleries.clear()
       notifyDataSetChanged()
   }

    fun addImages(galleries: List<Datum>){
        this.galleries.addAll(galleries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ImageViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_image_item, viewGroup, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount() = galleries.size

    override fun onBindViewHolder(vh: ImageViewHolder, i: Int) {
        vh.bind(galleries[i])
    }

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(gallery: Datum) {
            Glide.with(itemView)
                .load(gallery.images?.get(0)?.link)
                .placeholder(R.drawable.ic_placeholder)
                .fitCenter()
                .into(itemView.imageView)
            itemView.imageCard.setOnClickListener { imageClickListener.invoke(gallery.id!!) }
        }
    }
}