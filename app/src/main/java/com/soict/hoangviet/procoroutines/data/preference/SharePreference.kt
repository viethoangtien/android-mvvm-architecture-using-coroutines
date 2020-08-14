package com.soict.hoangviet.procoroutines.data.sharepreference

import kotlin.reflect.KClass

interface SharePreference {
    fun <T> put(key: String, value: T)
    fun <T> get(key: String, clazz: Class<T>):T
    fun setArrayListString(arrayName: String, list: ArrayList<String>)
    fun getArrayListString(arrayName: String): ArrayList<String>
    fun onDestroy()
}