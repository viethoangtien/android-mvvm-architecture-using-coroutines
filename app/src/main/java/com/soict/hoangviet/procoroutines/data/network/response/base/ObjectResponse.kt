package com.soict.hoangviet.procoroutines.data.network.response

import com.soict.hoangviet.procoroutines.utils.Define

open class ObjectResponse<T>(
    val type: Int,
    val data: T?,
    val error: Throwable?
) : BaseResponse() {
    companion object {
        fun <T> loading(): ObjectResponse<T> =
            ObjectResponse(Define.ResponseStatus.LOADING, null, null)

        fun <T> success(data: T): ObjectResponse<T> =
            ObjectResponse(Define.ResponseStatus.SUCCESS, data, null)

        fun <T> error(throwable: Throwable): ObjectResponse<T> =
            ObjectResponse(Define.ResponseStatus.ERROR, null, throwable)

    }
}