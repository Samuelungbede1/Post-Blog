package com.example.postblog.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.postblog.ConnectivityManager.ConnectivityLiveData
import com.example.postblog.R
import com.example.postblog.model.*
import okhttp3.internal.platform.Jdk9Platform
import okhttp3.internal.platform.Jdk9Platform.Companion.isAvailable
import java.util.*
import kotlin.collections.ArrayList


class PostListFragment : Fragment(), PostAdapter.OnItemClickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var dbViewModel: DbViewModel
    private lateinit var postAdapter: PostAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var connectivityLiveData: ConnectivityLiveData


    override fun onCreateView (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        

        val application = requireActivity().application
        connectivityLiveData = ConnectivityLiveData(application)
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
        postAdapter =  PostAdapter(this)
        recyclerView = view?.findViewById(R.id.rv_RecyclerView)!!
        recyclerView.adapter = postAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        viewModel.getPost()
        viewModel.postResponse.observe(viewLifecycleOwner, Observer {response->
            if(response.isSuccessful){
                response.body()?.forEach {
                    dbViewModel.addListOfPost(response.body() as ArrayList<Post>)
                }
            }
        })

        dbViewModel.readAllPost.observe(viewLifecycleOwner, Observer { response->
            postAdapter.setListOfPost(response)
        })


        //connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
       //     when (isAvailable) {
        //        true -> {
//                    viewModel.getPost()
//                    viewModel.postResponse.observe(viewLifecycleOwner, Observer {response->
//                        if(response.isSuccessful){
//                            response.body()?.forEach {
//                                dbViewModel.addListOfPost(response.body() as ArrayList<Post>)
//                            }
//                        }
//                    })
            //    }
         //       false -> {
//                    dbViewModel.readAllPost.observe(viewLifecycleOwner, Observer { response->
//                        postAdapter.setListOfPost(response)
//                    })
             //   }
          //  }
       // })


//        val application = requireActivity().application
//        connectivityLiveData = ConnectivityLiveData(application)
//        val view = inflater.inflate(R.layout.fragment_post_list, container, false)
//        postAdapter =  PostAdapter(this)
//        recyclerView = view?.findViewById(R.id.rv_RecyclerView)!!
//        recyclerView.adapter = postAdapter
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        dbViewModel = ViewModelProvider(this).get(DbViewModel::class.java)
//
//        val repository = Repository()
//        val viewModelFactory = MainViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
//        viewModel.getPost()
//        viewModel.postResponse.observe(viewLifecycleOwner, Observer {response->
//            if(response.isSuccessful){
//                response.body()?.forEach {
//                    dbViewModel.addListOfPost(response.body() as ArrayList<Post>)
//                }
//            }
//        })


//        dbViewModel.readAllPost.observe(viewLifecycleOwner, Observer { response->
//            postAdapter.setListOfPost(response)
//        })
        return view

    }




//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        Log.d("mainActivity", "onCreateOptionsMenu: gtyfre")
//        menuInflater.inflate(R.menu.menu,menu)
//        val item = menu?.findItem(R.id.search_item)
//        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
//
//        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//               // listOfPost.clear()
//                val searchText = newText!!.lowercase(Locale.getDefault())
//                viewModel.search(searchText)
//                viewModel.tempPostList.observe(this@PostListFragment, Observer { response ->
//                    tempListOfPost.addAll(response.body() as ArrayList<Post>)
//
//                    tempListOfPost.forEach{
//                        if(it.title.lowercase(Locale.getDefault()).contains(searchText)){
//                            //  tempListOfPost.add(it)
//                            postAdapter.addPost(it)
//                        }
//                    }
//
//                })
//
//                return false
//            }
//
//        })
//
//        return super.onCreateOptionsMenu(menu)
//
//    }



    /**Connectivity live data observer*/
//    connectivityLiveData.observe(this, Observer { isAvailable ->
//        when (isAvailable) {
//            true -> {
////                Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show()
////                progressBar.visibility = View.GONE
////                recyclerView.visibility = View.VISIBLE
////                obtainData(limitNum,offsetNum)
//            }
//            false -> {
////                Toast.makeText(this, "Please put ON your internet", Toast.LENGTH_LONG).show()
////                progressBar.visibility = View.VISIBLE
////                recyclerView.visibility = View.GONE
//            }
//        }
//    })


//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//    }

    override fun OnItemClick(post: Post, view: View) {
        var action = PostListFragmentDirections.toComments(post)
        Navigation.findNavController(view).navigate(action)
    }
}