package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.ActivityMainBinding
import net.simplifiedcoding.data.network.ApiInteface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var  viewModel: viewModel
    lateinit var  binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupObserver()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.retryButton.setOnClickListener{
            viewModel.fetchData()
            setupObserver()
        }

    }

    private fun setupObserver() {
        viewModel.apiResult.observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerView.adapter = Adapter(it.data!!)
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.INVISIBLE
                }
                Status.LOADING -> {
                    binding.recyclerView.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.retryButton.visibility = View.INVISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.retryButton.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun setupViewModel() {
        val api = ApiInteface()
        val repo = Repository(api)
        val factory = ModelFactory(repo)
        viewModel = ViewModelProvider(this,factory).get(com.example.movieapp.viewModel::class.java)
    }

}