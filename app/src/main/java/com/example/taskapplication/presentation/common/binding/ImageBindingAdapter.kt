package com.example.taskapplication.presentation.common.binding

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.taskapplication.R
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("image")
fun setIcons(view: ShapeableImageView, image: Int?) {
    image?.let { view.setImageResource(it) }
}

@BindingAdapter("imgUrl","placeHolder")
fun setImgUrl(view: AppCompatImageView, profile_url: String?, placeHolder : Drawable?) {
    val context = view.context
    Log.e("pawanLogs"," profile_url = $profile_url")
    if (profile_url != null && profile_url.length > 10){
//        Glide.with(view.context)
//            .load(profile_url)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .skipMemoryCache(false)
//            .placeholder(placeHolder?: ContextCompat.getDrawable(context, R.drawable.top_service_img1))
//            .error(placeHolder?: ContextCompat.getDrawable(context, R.drawable.top_service_img1))
//            .into(view)

        val imageLoader = ImageLoader.Builder(view.context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(view.context)
            .data(profile_url)
            .placeholder(R.drawable.top_service_img1)
            .error(R.drawable.top_service_img1)
            .target(view)
            .build()

        imageLoader.enqueue(request)

//        view.load(profile_url){
//            crossfade(true)
//            placeholder(R.drawable.top_service_img1)
//            error(R.drawable.top_service_img1)
//        }
    }else{
        view.setImageDrawable(placeHolder?: ContextCompat.getDrawable(context, R.drawable.top_service_img1))
    }
}