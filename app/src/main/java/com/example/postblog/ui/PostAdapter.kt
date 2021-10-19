package com.example.postblog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postblog.R
import com.example.postblog.model.Post


class PostAdapter (val listener: OnItemClickListener): RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    var data= emptyList<Post>()

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        var postTitle: TextView =  view.findViewById(R.id.tv_postTitle_PostItem)
        var postBody: TextView =  view.findViewById(R.id.tv_postBody_PostItem)
        var commentIcon: ImageView =  view.findViewById(R.id.iv_commentIcon)
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.post_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentPost : Post = data[position]
        holder.postTitle.text = currentPost.title
        holder.postBody.text =currentPost.body

        /**Setting click listener to the item in the recyclerview*/
        holder.commentIcon.setOnClickListener {
            listener.OnItemClick(currentPost,it)
        }
    }


    /**This function returns the size of the elements in the recylcer view*/
    override fun getItemCount(): Int {
        return data.size
    }


    /**This function adds pokemon to the recycler view*/
    fun setListOfPost ( listOfPost: List<Post>) {
        this.data = listOfPost
        notifyDataSetChanged()
    }


//    fun setPost(post: Post){
//        data.add(post)
//        notifyDataSetChanged()
//    }


    /**The interface class for the on click listener, the function(s) inside
     * should be overridden in the class implementing this class*/
    interface OnItemClickListener {
        fun OnItemClick(post: Post,view: View)
    }
}

