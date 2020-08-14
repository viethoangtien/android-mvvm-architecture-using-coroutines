package com.soict.hoangviet.procoroutines.data.network.api

import com.soict.hoangviet.procoroutines.data.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(val apiService: ApiService) {
    suspend fun getListNews(pageIndex: Int, pageSize: Int) =
        apiService.getListNews(pageIndex, pageSize)
}