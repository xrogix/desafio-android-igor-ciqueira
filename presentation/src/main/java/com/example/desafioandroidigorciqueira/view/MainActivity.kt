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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        render()
    }

    private fun render() {
        mainViewModel.characterList.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progress_circular).gone()

            findViewById<RecyclerView>(R.id.rv_list_characters).apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(this@MainActivity)

                adapter = CharacterAdapter(imageProvider, it)
            }
        })
    }

}
