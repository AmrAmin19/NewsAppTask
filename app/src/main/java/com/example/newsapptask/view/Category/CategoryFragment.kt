package com.example.newsapptask.view.Category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.newsapptask.databinding.FragmentCategoryBinding
import com.example.newsapptask.model.ApiState
import com.example.newsapptask.model.NewsResponse
import com.example.newsapptask.model.Repo
import com.example.newsapptask.model.remote.RemoteData
import com.example.newsapptask.viewModel.CategoryFactory
import com.example.newsapptask.viewModel.CategoryViewmodel
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {

    lateinit var binding: FragmentCategoryBinding
    lateinit var viewModel: CategoryViewmodel
    lateinit var factory: CategoryFactory
    lateinit var adapter: CategoryArticleAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        factory= CategoryFactory(Repo.getInstance(RemoteData()))
        viewModel= ViewModelProvider(this,factory).get(CategoryViewmodel::class.java)

        binding=FragmentCategoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  args: CategoryFragmentArgs by navArgs()
        val category = args.category

        adapter = CategoryArticleAdapter{
            val action =CategoryFragmentDirections.actionCategoryFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.categoryRecyclerView.adapter = adapter
        binding.titleText.text = category

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.getTopHeadlinesByCategory(category)

        lifecycleScope.launch{
            viewModel.articles.collect{ recource ->
                when(recource){
                    is ApiState.Loading -> {
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    is  ApiState.Success<NewsResponse> -> {
                       binding.progressBar.visibility=View.GONE
                        Log.d("AmrTAG", "${recource.data.articles.first().title}")
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