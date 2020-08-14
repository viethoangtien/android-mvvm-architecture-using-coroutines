package com.soict.hoangviet.procoroutines.di.module

import android.app.Application
import android.content.Context
import com.soict.hoangviet.procoroutines.data.preference.AppSharePreference
import com.soict.hoangviet.procoroutines.data.sharepreference.SharePreference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideSharePreferences(context: Context): SharePreference =
        AppSharePreference(context)

}