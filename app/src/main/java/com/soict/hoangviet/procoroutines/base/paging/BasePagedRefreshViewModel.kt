package com.soict.hoangviet.procoroutines.base.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.soict.hoangviet.procoroutines.base.ui.BaseViewModel
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse
import com.soict.hoangviet.procoroutines.utils.Define

abstract class BasePagedRefreshViewModel<T> : BaseViewModel() {
    val listResponse: MutableLiveData<ListLoadMoreResponse<T>> = MutableLiveData()

    //Check list is empty
    val isEmptyList = MutableLiveData(false)

    // refresh flag
    val isRefresh = MutableLiveData(false)

    // load more flag
    val isLoadMore = MutableLiveData(false)

    // number item per page
    val pageSize = Define.Paging.PAGE_SIZE

    //First page for load data
    val firstPage = Define.Paging.FIRST_PAGE

    //data source
    var dataSource: BaseDataSource<T>? = null

    //Page list Config for creating pageList
    private val pageListConfig = PagedList.Config.Builder()
        .setPageSize(pageSize)//load item per page
        .setInitialLoadSizeHint(pageSize)//load first
        .setEnablePlaceholders(true)
        .build()

    val itemList = LivePagedListBuilder(object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            return createDataSource()
        }
    }, pageListConfig).build()

    private fun createDataSource(): BaseDataSource<T> {
        return object : BaseDataSource<T>(this) {
            override suspend fun loadDataSource(
                loadInitialParams: LoadInitialParams<Int>?,
                loadParams: LoadParams<Int>?
            ): ListLoadMoreResponse<T> {
                return this@BasePagedRefreshViewModel.loadDataSource(loadInitialParams, loadParams)
            }
        }.apply {
            dataSource = this
        }
    }

    fun handleError(throwable: Throwable) {
        listResponse.value = ListLoadMoreResponse.error(throwable)
    }

    fun loadSuccess(itemList: List<T>) {
        listResponse.value = ListLoadMoreResponse.success(itemList as ArrayList<T>)
    }

    fun hideRefreshLoading() {
        isLoadMore.value = false
        isRefresh.value = false
    }

    fun showLoading() {
        listResponse.value = ListLoadMoreResponse.loading()
    }

    /**
     * load data
     */
    abstract suspend fun loadDataSource(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<Int>? = null,
        loadParams: PageKeyedDataSource.LoadParams<Int>? = null
    ): ListLoadMoreResponse<T>
}