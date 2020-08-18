package com.soict.hoangviet.procoroutines.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.soict.hoangviet.procoroutines.base.paging.BaseDataSourceFactory
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import kotlinx.coroutines.CoroutineScope

abstract class NewsDataSourceFactory(val coroutineScope: CoroutineScope) :
    BaseDataSourceFactory<Int, NewsResponse>() {
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun createDataSource(): DataSource<Int, NewsResponse> {
        val dataSource = object : NewsDataSource(coroutineScope) {
            override suspend fun loadDataSource(
                loadInitialParams: LoadInitialParams<Int>?,
                loadParams: LoadParams<Int>?
            ): ListLoadMoreResponse<NewsResponse> {
                return this@NewsDataSourceFactory.loadDataSource(loadInitialParams, loadParams)
            }
        }
        newsDataSourceLiveData.postValue(dataSource)
        return dataSource
    }
}