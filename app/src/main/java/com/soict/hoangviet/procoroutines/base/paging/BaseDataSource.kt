package com.soict.hoangviet.procoroutines.base.paging

import androidx.lifecycle.viewModelScope
import androidx.paging.PageKeyedDataSource
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import kotlinx.coroutines.launch

abstract class BaseDataSource<T>(
    private val viewModel: BasePagedRefreshViewModel<T>
) : PageKeyedDataSource<Int, T>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, T>
    ) {
        viewModel.viewModelScope.launch {
            try {
                if (viewModel.isRefresh.value != true) viewModel.showLoading()
                val itemList = loadDataSource(loadInitialParams = params)
                itemList.response?.news?.let { itemList ->
                    viewModel.loadSuccess(itemList)
                    viewModel.isEmptyList.value = itemList.isEmpty()
                    callback.onResult(
                        itemList,
                        null,
                        // last page
                        if (itemList.size < viewModel.pageSize) null
                        // load next page
                        else viewModel.firstPage + 1
                    )
                }
            } catch (throwable: Throwable) {
                viewModel.handleError(throwable)
            } finally {
                viewModel.hideRefreshLoading()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {
        viewModel.viewModelScope.launch {
            try {
                viewModel.isLoadMore.value = true
                val itemList = loadDataSource(loadParams = params)
                itemList.response?.news?.let { itemList ->
                    viewModel.loadSuccess(itemList)
                    viewModel.isEmptyList.value = itemList.isEmpty()
                    callback.onResult(
                        itemList,
                        // last page
                        if (itemList.size < viewModel.pageSize) null
                        // load next page
                        else params.key + 1
                    )
                }
            } catch (throwable: Throwable) {
                viewModel.handleError(throwable)
            } finally {
                viewModel.hideRefreshLoading()
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {

    }

    /**
     * load data
     */
    abstract suspend fun loadDataSource(
        loadInitialParams: LoadInitialParams<Int>? = null,
        loadParams: LoadParams<Int>? = null
    ): ListLoadMoreResponse<T>
}