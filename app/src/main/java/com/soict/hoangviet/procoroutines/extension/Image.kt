package com.soict.hoangviet.procoroutines.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.soict.hoangviet.procoroutines.R
import com.soict.hoangviet.procoroutines.module.GlideApp

/**
 * Load Image with GlideApp
 */
fun <T : Any> ImageView.loadImage(
    context: Context,
    image: T?,
    @DrawableRes placeHolder: Int = R.drawable.img_image_default,
    @DrawableRes error: Int = R.drawable.img_image_default
) {
    GlideApp.with(context)
        .load(image)
        .placeholder(placeHolder)
        .error(error)
        .into(this)
}

fun ImageView.loadImageListener(
    context: Context,
    image: Any?,
    onSuccessListener: (() -> Unit)? = null,
    onFailureListener: (() -> Unit)? = null
) {
    GlideApp.with(context)
        .load(image)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onFailureListener?.invoke()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onSuccessListener?.invoke()
                return false
            }

        })
        .into(this)
}

