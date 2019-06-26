package com.example.spidergroupapp.view.imageDetails.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spidergroupapp.R
import kotlinx.android.synthetic.main.recycler_comment_item.view.*

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private var comments: List<String> = ArrayList()

    fun setComments(comments: List<String?>){
        this.comments = comments as List<String>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CommentViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.recycler_comment_item, viewGroup, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount() = comments.size


    override fun onBindViewHolder(vh: CommentViewHolder, i: Int) {
      vh.bind(comments[i])
    }

    class CommentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(comment: String){
            itemView.comment.text = comment
        }
    }
}