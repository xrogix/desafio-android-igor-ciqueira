package com.example.desafioandroidigorciqueira.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CharacterDetailFragment : DialogFragment() {

    private val imageProvider: ImageProvider by inject()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.dialog_theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listeners()
    }

    override fun onResume() {
        super.onResume()
        dialog?.apply {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            window?.setLayout(width, height)
            window?.setWindowAnimations(R.style.dialog_animation_slide)
        }
    }

    private fun listeners() {
        mainViewModel.charactersDetail.observe(viewLifecycleOwner, Observer {
            view?.apply {
                imageProvider.renderImage(it.thumbnail.path, findViewById(R.id.iv_character_detail_image))
                findViewById<AppCompatTextView>(R.id.tv_character_detail_name).text = it.name
                findViewById<AppCompatTextView>(R.id.tv_character_detail_description).text = it.description
            }
        })
    }

    companion object {
        fun newInstance() = CharacterDetailFragment()
    }
}
