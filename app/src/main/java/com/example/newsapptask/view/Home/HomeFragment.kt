package com.example.newsapptask.view.Home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newsapptask.databinding.FragmentHomeBinding
import com.example.newsapptask.model.ApiState
import com.example.newsapptask.model.NewsResponse
import com.example.newsapptask.model.Repo
import com.example.newsapptask.model.remote.RemoteData
import com.example.newsapptask.view.Category.CategoryAdapter
import com.example.newsapptask.viewModel.HomeFactory
import com.example.newsapptask.viewModel.HomeViewmodel
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var factory: HomeFactory
    lateinit var viewModel: HomeViewmodel
    lateinit var adapter: TrendingRecycleAdapter
    lateinit var categoryAdapter: CategoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        factory=HomeFactory(Repo.getInstance(RemoteData()))
        viewModel= ViewModelProvider(this,factory).get(HomeViewmodel::class.java)

        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryAdapter = CategoryAdapter {
            val action =HomeFragmentDirections.actionHomeFragmentToCategoryFragment(it)
            findNavController().navigate(action)
        }
        binding.categoriesRecyclerView.adapter = categoryAdapter

        adapter = TrendingRecycleAdapter {
            val action =HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.trendingNewsRecyclerView.adapter = adapter

        viewModel.getTopHeadlinesByCategory("general")

        lifecycleScope.launch{
            viewModel.articles.collect{ recource ->
                when(recource){
                    is ApiState.Loading -> {
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    is  ApiState.Success<NewsResponse> -> {
                        binding.progressBar.visibility=View.GONE
                        adapter.submitList(recource.data.articles)
                    }
                    is ApiState.Error -> {
                        binding.progressBar.visibility=View.GONE
                        Log.d("Amr Test", "error: ${recource.message}") // <-- log the full message
                    }
                }
            }
        }


    }


}