package dev.xget.havasreddit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dev.xget.havasreddit.core.di.NetworkModule
import dev.xget.havasreddit.core.di.NetworkModuleI


class HavasRedditApp : Application() {

    override fun onCreate() {
        super.onCreate()
        networkModule = NetworkModule(this)
    }

    //companion object to access the network module from all over the app
    companion object {
        lateinit var networkModule: NetworkModule
    }
}