package dev.xget.havasreddit.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


interface AppModuleI {
    val applicationContext: Context
}

class AppModule(
    private val application: Application
) : AppModuleI {
    override val applicationContext: Context by lazy {
        application.applicationContext
    }
}