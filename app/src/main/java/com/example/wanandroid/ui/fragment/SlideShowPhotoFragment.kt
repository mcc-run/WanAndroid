package com.example.wanandroid.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.wanandroid.R


class SlideShowPhotoFragment : Fragment() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflater =  inflater.inflate(R.layout.fragment_slide_show_photo, container, false)

        imageView = inflater.findViewById(R.id.photo_SlideShow)

        return inflater
    }

    fun getImage() : ImageView{
        return imageView
    }

}