package com.example.desafioandroidigorciqueira.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.data.services.ImageProvider
import com.example.desafioandroidigorciqueira.R
import com.example.desafioandroidigorciqueira.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.NumberFormat

class ComicsFragment : DialogFragment() {

    private val imageProvider: ImageProvider by inject()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.comicsList.observe(viewLifecycleOwner, Observer {
            val comics = it.maxBy {commics ->
                commics.prices.filter {price ->
                    price.type == "printPrice"
                }[0].price
            }
            comics?.apply {
                imageProvider.renderImage(thumbnail.path, view.findViewById(R.id.iv_comics_image), PORTRAIT_IMAGE)
                view.findViewById<AppCompatTextView>(R.id.tv_comics_name).text = title
                view.findViewById<AppCompatTextView>(R.id.tv_comics_description).text = description
                view.findViewById<AppCompatTextView>(R.id.tv_comics_price).text = comics.prices[0].price.toString()
            }
        })

        view.findViewById<AppCompatButton>(R.id.btn_close).setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance() = ComicsFragment()

        private val PORTRAIT_IMAGE = 1
    }
}