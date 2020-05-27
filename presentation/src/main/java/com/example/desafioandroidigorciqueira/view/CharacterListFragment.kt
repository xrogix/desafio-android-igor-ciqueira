package com.example.desafioandroidigorciqueira.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.extensions.gone
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterListFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var recyclerViewAdapter: CharacterAdapter? = null

    private val mainViewModel by sharedViewModel<MainViewModel>()
    private val imageProvider: ImageProvider by inject()

    private var isLoading = false
    private var offset = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.rv_list_characters)

        listenViewModel()
        populateData()
        initAdapter()
    }

    private fun listenViewModel() {
        mainViewModel.lastCharacterList.observe(viewLifecycleOwner, Observer {
            if(isLoading)
                recyclerViewAdapter?.removeItem()

            recyclerViewAdapter?.updateData(it)
            hideLoading()
        })
    }

    private fun populateData() {
        mainViewModel.loadMoreCharacters(offset)
        offset+=20
    }

    private fun initAdapter() {
        if(recyclerViewAdapter == null) {
            recyclerViewAdapter = CharacterAdapter(imageProvider, mainViewModel.getCharacterList()) {
                mainViewModel.onSelectedCharacter(it)
            }
        }
        recyclerView?.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = recyclerViewAdapter

            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    (recyclerView.layoutManager as LinearLayoutManager).apply {
                        mainViewModel.characterList.value?.also {
                            if(!isLoading && findLastCompletelyVisibleItemPosition() == it.size - 1) {
                                recyclerViewAdapter?.addItem(null)
                                showLoading()
                                populateData()
                            }
                        }
                    }
                }
            })
        }
    }

    private fun showLoading() {
        isLoading = true
    }

    private fun hideLoading() {
        view?.findViewById<ProgressBar>(R.id.progress_circular)?.gone()
        isLoading = false
    }

    companion object {
        fun newInstance() = CharacterListFragment()
    }
}
