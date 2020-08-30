package com.soict.hoangviet.procoroutines.ui.news

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.soict.hoangviet.procoroutines.base.paging.BasePagedRefreshViewModel
import com.soict.hoangviet.procoroutines.base.ui.BaseViewModel
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.data.network.response.NewsResponse
import javax.inject.Inject

class NewsViewModel @Inject constructor(val context: Context) :
    BasePagedRefreshViewModel<NewsResponse>() {

    fun refreshData() {
        isRefresh.value = true
        dataSource?.invalidate()
    }

    override suspend fun loadDataSource(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>?,
        loadParams: PageKeyedDataSource.LoadParams<Int>?
    ): ListLoadMoreResponse<NewsResponse>{
        loadInitialParams?.let {
            return repository.getListNews(pageIndex = 1, pageSize = it.requestedLoadSize)
        }
        return repository.getListNews(loadParams!!.key, loadParams.requestedLoadSize)
    }
}
