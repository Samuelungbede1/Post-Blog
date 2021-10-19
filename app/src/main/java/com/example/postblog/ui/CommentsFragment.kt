package com.example.postblog.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postblog.R
import com.example.postblog.model.*
import kotlin.properties.Delegates

class CommentsFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var dbViewModel: DbViewModel
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var recyclerView: RecyclerView
    private var postId by Delegates.notNull<Int>()
  //  var listOfComment = ArrayList<Comment>()

    private val args by navArgs<CommentsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_comments, container, false)

        var postBody = view.findViewById<TextView>(R.id.tv_postBodyCommentActivity)
        var postTitle = view.findViewById<TextView>(R.id.tv_CommentActivityPostTitle)

        postBody!!.text = args.passedPostTocommentFrag.body
        postTitle!!.text = args.passedPostTocommentFrag.title
        postId = args.passedPostTocommentFrag.id
        Log.d(TAG, "onCreateView: $postId")


        commentAdapter =  CommentAdapter()
        recyclerView = view?.findViewById(R.id.rv_recyclerViewCommentActivity)!!
        recyclerView.layoutManager = LinearLayoutManager(context)
        dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        viewModel.getPostComments(postId)
        viewModel.commentResponse.observe(viewLifecycleOwner, Observer {response->
            if(response.isSuccessful){
                response.body()?.forEach {
                    dbViewModel.addListOfComment(response.body() as ArrayList<Comment>)
                }
            }
        })


        dbViewModel.readAllComment.observe(viewLifecycleOwner, Observer { response->
            commentAdapter.setListOfComments(response)
            recyclerView.adapter = commentAdapter
//           Log.d(TAG, "onCreateView: $response")
        })

        return view
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}




