package com.soict.hoangviet.procoroutines.di.builder

import com.soict.hoangviet.procoroutines.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}