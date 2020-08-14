package com.soict.hoangviet.procoroutines.ui.news

import android.bluetooth.le.AdvertiseData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beetech.productmanagement.di.annotation.LayoutId
import com.soict.hoangviet.procoroutines.R
import com.soict.hoangviet.procoroutines.adapter.NewsAdapter
import com.soict.hoangviet.procoroutines.base.ui.BaseFragment
import com.soict.hoangviet.procoroutines.custom.decoration.NewsDecoration
import com.soict.hoangviet.procoroutines.databinding.FragmentNewsBinding
import com.soict.hoangviet.procoroutines.extension.injectViewModel
import kotlinx.android.synthetic.main.fragment_news.*

@LayoutId(R.layout.fragment_news)
class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    override fun backFromAddFragment() {

    }

    override fun backPressed(): Boolean {
        return true
    }

    override fun initView() {
        initAdapter()
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(requireContext())
        rcv_news.adapter = newsAdapter
        rcv_news.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        rcv_news.addItemDecoration(NewsDecoration(resources.getDimensionPixelSize(R.dimen.content_padding_8_dp)))
    }

    override fun initViewModel() {
        newsViewModel = injectViewModel(viewModelFactory)
    }

    override fun initData() {

    }

    override fun initListener() {
        newsViewModel.listNews.observe(this, Observer {
            it?.let {
                newsAdapter.submitList(it)
            }
        })
        newsViewModel.getNewsDataSource().observe(this, Observer {
            it?.let {
                handleListLoadMoreResponse(it)
            }
        })
    }
}