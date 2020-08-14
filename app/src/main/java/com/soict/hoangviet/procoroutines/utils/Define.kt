package com.soict.hoangviet.procoroutines.utils

object Define {
    const val PREF_NAME = "app_sharepreference"

    object ResponseStatus {
        const val LOADING = 0
        const val SUCCESS = 1
        const val ERROR = 2
    }

    object ApiService {
        object RelativeUrl {
            const val LIST_NEWS = "listNews"
        }

        object Parameter {
            const val PAGE = "page"
            const val TOKEN = "token"
            const val ID = "id"
            const val FAB_ID = "fab_id"
            const val DATE_START = "date_start"
            const val DATE_FINISH = "date_finish"
            const val SECTION = "section"
            const val KOUJI_ID = "kouji_id"
            const val KEYWORD = "keyword"
            const val SORT = "sort"
            const val PAGE_INDEX = "pageIndex"
            const val PAGE_SIZE = "pageSize"
        }

        object Constant {
            const val LIMIT = 20
        }

        object Header {
            const val AUTHORIZATION_HEADER = "Authorization"
        }
    }
}