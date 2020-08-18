package com.soict.hoangviet.procoroutines.base.paging

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.soict.hoangviet.procoroutines.data.network.response.ListLoadMoreResponse

abstract class BaseDataSourceFactory<K : Int, V : Any> : DataSource.Factory<K, V>() {
    override fun create(): DataSource<K, V> {
        return createDataSource()
    }

    abstract fun createDataSource(): DataSource<K, V>

    /**
     * load data
     */
    abstract suspend fun loadDataSource(
        loadInitialParams: PageKeyedDataSource.LoadInitialParams<K>? = null,
        loadParams: PageKeyedDataSource.LoadParams<K>? = null
    ): ListLoadMoreResponse<V>
}