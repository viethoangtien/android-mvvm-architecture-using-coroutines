package com.soict.hoangviet.procoroutines.data.network.response

class BaseError(message: String, val code: Int, var isShowToast: Boolean = false) :
    Throwable(message) {
}