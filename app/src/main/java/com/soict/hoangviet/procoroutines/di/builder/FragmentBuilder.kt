package com.soict.hoangviet.procoroutines.di.builder

import com.soict.hoangviet.procoroutines.ui.news.NewsFragment
import com.soict.hoangviet.procoroutines.ui.validation.ValidationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector()
    abstract fun bindValidationFragment(): ValidationFragment

    @ContributesAndroidInjector()
    abstract fun bindNewsFragment(): NewsFragment
}