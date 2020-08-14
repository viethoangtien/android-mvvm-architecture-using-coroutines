package com.soict.hoangviet.procoroutines.adapter

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.soict.hoangviet.procoroutines.R
import com.soict.hoangviet.procoroutines.module.GlideApp

class BindingAdapter {
    companion object {
        @BindingAdapter("android:src")
        @JvmStatic
        fun ImageView.bindImageView(
            url: String?
        ) {
            GlideApp.with(this.context)
                .load(url)
                .error(R.drawable.img_image_default)
                .placeholder(R.drawable.img_image_default)
                .into(this)
        }

        @BindingAdapter("notification")
        @JvmStatic
        fun TextView.bindNotificationTitle(
            content: String?
        ) {
            content?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    text = (Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT))
                } else {
                    text = (Html.fromHtml(it));
                }
            }
        }
    }
}