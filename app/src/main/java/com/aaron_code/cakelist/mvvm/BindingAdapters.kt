package com.aaron_code.cakelist.mvvm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value=["imageUrl"], requireAll = false)
fun setImageUrl(imageView: ImageView, url: String) {
    if (!url.isEmpty()) {
        Picasso.get().load(url).into(imageView)
    } else {
        imageView.setImageBitmap(null)
    }
}