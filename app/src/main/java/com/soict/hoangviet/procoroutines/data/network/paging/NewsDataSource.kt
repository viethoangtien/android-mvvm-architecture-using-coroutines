package com.soict.hoangviet.procoroutines.data.network.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.data.network.response.ListResponse
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class NewsDataSource(val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Int, NewsResponse>() {
    var listResponseNewsLiveData: MutableLiveData<ListLoadMoreResponse<NewsResponse>> =
        MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsResponse>
    ) {
        listResponseNewsLiveData.postValue(ListLoadMoreResponse.loading())
        coroutineScope.launch {
            try {
                val newsList = loadDataSource(loadInitialParams = params)
                newsList.response?.let {
                    it.news?.let {
                        listResponseNewsLiveData.postValue(ListLoadMoreResponse.success(it))
                        callback.onResult(it, null, 2)
                    }
                }
            } catch (e: Throwable) {
                listResponseNewsLiveData.postValue(ListLoadMoreResponse.error(e))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsResponse>) {
        coroutineScope.launch {
            try {
                val newsList = loadDataSource(loadParams = params)
                newsList.response?.let {
                    it.news?.let {
                        listResponseNewsLiveData.postValue(ListLoadMoreResponse.success(it))
                        callback.onResult(it, params.key + 1)
                    }
                }
            } catch (e: Throwable) {
                listResponseNewsLiveData.postValue(ListLoadMoreResponse.error(e))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsResponse>) {

    }

    /**
     * load data
     */
    abstract suspend fun loadDataSource(
        loadInitialParams: LoadInitialParams<Int>? = null,
        loadParams: LoadParams<Int>? = null
    ): ListLoadMoreResponse<NewsResponse>


}