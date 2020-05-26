package com.example.desafioandroidigorciqueira.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.extensions.gone
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val imageProvider: ImageProvider by inject()

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: CharacterAdapter
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_list_characters)

        listenViewModel()
        populateData()
        initAdapter()
    }

    private fun listenViewModel() {
        mainViewModel.lastCharacterList.observe(this, Observer {
            if(isLoading)
                recyclerViewAdapter.removeItem()
            hideLoading()
            recyclerViewAdapter.updateData(it)
        })
    }

    private fun populateData(offset: Int = 0) {
        mainViewModel.loadMoreCharacters(offset)
    }

    private fun initAdapter() {
        recyclerViewAdapter = CharacterAdapter(imageProvider, mainViewModel.getEmptyList() )
        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(this@MainActivity)

            adapter = recyclerViewAdapter

            var offset = 0

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    (recyclerView.layoutManager as LinearLayoutManager).apply {
                        mainViewModel.characterList.value?.also {
                            if(!isLoading && findLastCompletelyVisibleItemPosition() == it.size - 1) {
                                recyclerViewAdapter.addItem(null)
                                showLoading()
                                offset+=20
                                populateData(offset)
                            }
                        }
                    }
                }
            })
        }

        //Todo passar tudo para fragment com animação
    }

    private fun showLoading() {
        isLoading = true
    }

    private fun hideLoading() {
        findViewById<ProgressBar>(R.id.progress_circular).gone()
        isLoading = false
    }

}
