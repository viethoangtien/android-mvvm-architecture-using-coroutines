package com.soict.hoangviet.procoroutines.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.soict.hoangviet.procoroutines.R
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import com.soict.hoangviet.procoroutines.extension.inflate
import com.soict.hoangviet.procoroutines.extension.loadImage
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_news.*

class NewsAdapter(val context: Context) : PagedListAdapter<NewsResponse, NewsAdapter.NewsViewHolder>(NewsDiffCallback) {
    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<NewsResponse>() {
            override fun areItemsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NewsResponse, newItem: NewsResponse): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(context, parent.inflate(R.layout.item_news))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val data = getItem(position) as NewsResponse
        holder.bind(data)
    }

    class NewsViewHolder(val context: Context, override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {
        fun bind(data: NewsResponse) {
            imv_news.loadImage(context, data.thumbImg)
            tv_title.text = data.title
            tv_author.text = data.author
            tv_feed.text = data.feed
            tv_clock.text = data.publishDate
        }
    }
}