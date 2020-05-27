package com.example.desafioandroidigorciqueira.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CharacterListFragment.newInstance())
                .commitNow()
        }

        listeners()
    }

    private fun listeners() {
        mainViewModel.charactersDetail.observe(this, Observer {
            val ft = supportFragmentManager.beginTransaction()
            ft.addToBackStack(null)
            val newFragment = CharacterDetailFragment.newInstance()
            newFragment.show(ft, "character_dialog")
        })

        mainViewModel.comicsList.observe(this, Observer {
            val ft = supportFragmentManager.beginTransaction()
            ft.addToBackStack(null)
            val newFragment = ComicsFragment.newInstance()
            newFragment.show(ft, "comics_dialog")
        })
    }

}
