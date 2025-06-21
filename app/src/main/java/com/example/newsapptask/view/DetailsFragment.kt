package com.example.newsapptask.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapptask.R
import com.example.newsapptask.databinding.FragmentDetailsBinding
import com.example.newsapptask.databinding.FragmentHomeBinding

class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  args: DetailsFragmentArgs by navArgs()
        val article = args.article

        binding.writtenBy.text =article.source.name
        binding.newsTitle.text = article.title
        binding.newsDescription.text = article.description
        binding.contentText.text = article.content
        binding.publishedAt.text = article.publishedAt
        binding.readMoreLink.text = article.url


        Glide.with(this)
            .load(article.image)
            .into(binding.newsImage)



            binding.backButton.setOnClickListener {
                findNavController().popBackStack()
            }


    }

}