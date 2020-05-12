package com.vivy.vivytask.util

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.vivy.vivytask.R


@BindingAdapter("content")
fun loadImage(view: ImageView, url: String?) {
    if (url!=null){
        Picasso.get().load(url).into(view)
    }else{
       view.setImageDrawable(ContextCompat.getDrawable(view.context, R.drawable.doctor_default))
    }



}