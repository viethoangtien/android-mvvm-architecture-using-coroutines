package com.soict.hoangviet.procoroutines.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.soict.hoangviet.procoroutines.common.BaseLoadingDialog
import com.soict.hoangviet.procoroutines.extension.connectivityManager
import com.soict.hoangviet.procoroutines.extension.inputManager

object DeviceUtil {
    fun hideSoftKeyBoard(activity: Activity) {
        val inputMethodManager = activity.inputManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun isConnectedToNetwork(context: Context): Boolean {
        val mConnectivityManager = context.connectivityManager
        if (mConnectivityManager != null) {
            val activeNetwork = mConnectivityManager.activeNetworkInfo
            return (activeNetwork != null && activeNetwork.isConnected)
        }
        return false
    }
}