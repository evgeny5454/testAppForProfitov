package com.evgeny_m.testapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.evgeny_m.testapp.databinding.FragmentListBinding

const val LOG: Boolean = false

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val recyclerView = binding.recyclerView
        val adapter = ListAdapter(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        mainViewModel.getListPosts().observe(viewLifecycleOwner, { list ->
            val data = list.body()
            if (LOG) Log.d("LOG", data.toString())
            data?.forEach { postId ->
                mainViewModel.getPost(postId)
            }
        })

        mainViewModel.post.observe(viewLifecycleOwner,{
            val data = it.body()
            if (LOG) Log.d("LOG", data.toString())
            if (data != null) {
                adapter.addPosts(data)
            }
        })
        return binding.root
    }
}

