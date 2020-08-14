package com.soict.hoangviet.procoroutines.data.network

import com.soict.hoangviet.procoroutines.data.network.response.ObjectResponse
import com.soict.hoangviet.procoroutines.data.network.response.ListResponse
import com.soict.hoangviet.procoroutines.utils.Define
import com.soict.hoangviet.procoroutines.data.network.response.*
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/v1/category")
    suspend fun getCategory(): Call<ObjectResponse<*>>

    @POST("/v1/user_update_profile")
    @Multipart
    suspend fun updateProfile(
        @Part fileImage: MultipartBody.Part
    ): Call<ObjectResponse<*>>

    @HTTP(method = "DELETE", path = "/v1/cart", hasBody = true)
    suspend fun deleteCartdetail(
        @Header(ApiConstant.RequestParam.AUTHORIZATION_HEADER) authToken: String,
        @Body requestBody: RequestBody
    ): Call<ObjectResponse<*>>

    @GET
    @Headers("Content-Type:application/json")
    suspend fun dynamicUrl(@Url url: String): Call<ObjectResponse<*>>

    @GET(Define.ApiService.RelativeUrl.LIST_NEWS)
    suspend fun getListNews(
        @Query(Define.ApiService.Parameter.PAGE_INDEX) pageIndex: Int,
        @Query(Define.ApiService.Parameter.PAGE_SIZE) pageSize: Int
    ): ListLoadMoreResponse<NewsResponse>
}