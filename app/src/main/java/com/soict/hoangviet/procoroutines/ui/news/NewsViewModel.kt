package com.soict.hoangviet.procoroutines.ui.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.soict.hoangviet.procoroutines.base.ui.BaseViewModel
import com.soict.hoangviet.procoroutines.data.network.paging.NewsDataSourceFactory
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.data.network.response.ListResponse
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import javax.inject.Inject

class NewsViewModel @Inject constructor(val context: Context) : BaseViewModel() {
    private val pageSize = 10
    var listNews: LiveData<PagedList<NewsResponse>>
    private var newsDataSourceFactory: NewsDataSourceFactory

    init {
        newsDataSourceFactory = object : NewsDataSourceFactory(viewModelScope) {
            override suspend fun loadDataSource(
                loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
                loadParams: PageKeyedDataSource.LoadParams<Int>?
            ): ListLoadMoreResponse<NewsResponse> {
                loadInitialParams?.let {
                    return repository.getListNews(pageIndex = 1, pageSize = it.requestedLoadSize)
                }
                return repository.getListNews(loadParams!!.key, loadParams.requestedLoadSize)
            }
        }
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(true)
            .build()
        listNews = LivePagedListBuilder(newsDataSourceFactory, config).build()
    }

    fun getNewsDataSource() =
        Transformations.switchMap(newsDataSourceFactory.newsDataSourceLiveData) {
            it.listResponseNewsLiveData
        }
}