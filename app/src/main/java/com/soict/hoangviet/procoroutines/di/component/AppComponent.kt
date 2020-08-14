package com.soict.hoangviet.procoroutines.di.component

import android.app.Application
import android.content.Context
import com.soict.hoangviet.procoroutines.di.builder.ActivityBuilder
import com.soict.hoangviet.procoroutines.di.builder.FragmentBuilder
import com.soict.hoangviet.procoroutines.di.module.AppModule
import com.soict.hoangviet.procoroutines.di.module.NetworkModule
import com.soict.hoangviet.procoroutines.application.BaseApplication
import com.soict.hoangviet.procoroutines.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class),
        (AppModule::class),
        (NetworkModule::class),
        (ActivityBuilder::class),
        (FragmentBuilder::class),
        (ViewModelModule::class)]
)
@Singleton
interface AppComponent {
    fun getContext(): Context
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(baseApplication: BaseApplication)
}