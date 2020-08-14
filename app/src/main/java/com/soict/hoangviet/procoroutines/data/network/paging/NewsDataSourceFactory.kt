package com.soict.hoangviet.procoroutines.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.data.network.response.ListResponse
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import kotlinx.coroutines.CoroutineScope

abstract class NewsDataSourceFactory(val coroutineScope: CoroutineScope) : DataSource.Factory<Int, NewsResponse>() {
    val newsDataSourceLiveData = MutableLiveData<NewsDataSource>()

    override fun create(): DataSource<Int, NewsResponse> {
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

    /**
     * load data
     */
    abstract suspend fun loadDataSource(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>? = null,
        loadParams: PageKeyedDataSource.LoadParams<Int>? = null
    ): ListLoadMoreResponse<NewsResponse>
}