package com.soict.hoangviet.procoroutines.ui.main

import com.beetech.productmanagement.di.annotation.LayoutId
import com.soict.hoangviet.procoroutines.R
import com.soict.hoangviet.procoroutines.base.ui.BaseActivity
import com.soict.hoangviet.procoroutines.databinding.ActivityMainBinding
import com.soict.hoangviet.procoroutines.extension.injectViewModel
import com.soict.hoangviet.procoroutines.ui.news.NewsFragment
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@LayoutId(R.layout.activity_main)
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mMainViewModel: MainViewModel

    override fun getFragmentContainerId(): Int {
        return R.id.fl_container
    }

    override fun initViewModel() {
        mMainViewModel = injectViewModel(viewModelFactory)
    }

    override fun initView() {
        viewController.addFragment(NewsFragment::class.java, null)
    }

    override fun initData() {

    }

    override fun initListener() {

    }
}
