package com.example.desafioandroidigorciqueira.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        render()
    }

    private fun render() {
        mainViewModel.characterList.observe(this, Observer {
            findViewById<RecyclerView>(R.id.rvListCharacters).apply {
                setHasFixedSize(true)

                layoutManager = LinearLayoutManager(this@MainActivity)

                adapter = CharacterAdapter(it)
            }
        })
    }

}
