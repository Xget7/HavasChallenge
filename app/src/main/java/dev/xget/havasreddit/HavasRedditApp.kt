package dev.xget.havasreddit

import android.app.Application
import android.os.Debug
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import coil.util.DebugLogger
import dev.xget.havasreddit.core.di.AppModule
import dev.xget.havasreddit.core.di.NetworkModule


class HavasRedditApp : Application() , ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        networkModule = NetworkModule(this)
        appModule = AppModule(this)
    }

    //companion object to access the network module from all over the app
    companion object {
        lateinit var networkModule: NetworkModule
        lateinit var appModule: AppModule
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .networkCachePolicy(CachePolicy.ENABLED)
            .logger(DebugLogger())
            .build()
    }
}