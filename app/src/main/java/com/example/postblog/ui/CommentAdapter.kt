package com.example.postblog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.postblog.R
import com.example.postblog.model.Comment


class CommentAdapter () : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    var data= emptyList<Comment>()
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        var commenterName: TextView =  view.findViewById(R.id.tv_CommenterUserName)
        var commenterBody: TextView =  view.findViewById(R.id.tv_commentBody)
      //  var commentIcon: ImageView =  view.findViewById(R.id.iv_commentIcon)
    }

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int): ViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.comment_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentComment : Comment = data[position]
        holder.commenterName.text = currentComment.name
        holder.commenterBody.text =currentComment.body


        /**Setting click listener to the item in the recyclerview*/
//        holder.commentIcon.setOnClickListener {
//            listener.OnItemClick(currentPost,it)
//        }
    }


    /**This function returns the size of the elements in the recylcer view*/
    override fun getItemCount(): Int {
        return data.size
    }


    /**This function adds pokemon to the recycler view*/
    fun setListOfComments ( listOfComment: List<Comment>) {
        this.data = listOfComment
        notifyDataSetChanged()
    }


//    fun setPost(post: Post){
//        data.add(post)
//        notifyDataSetChanged()
//    }


    /**The interface class for the on click listener, the function(s) inside
     * should be overridden in the class implementing this class*/
//    interface OnItemClickListener {
//        fun OnItemClick(post: Post, view: View)
//    }
}

