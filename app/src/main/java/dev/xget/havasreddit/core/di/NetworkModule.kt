package dev.xget.havasreddit.core.di

import android.content.Context

import dev.xget.havasreddit.core.utils.HttpRoutes
import dev.xget.havasreddit.core.utils.NetworkUtils.hasNetwork
import dev.xget.havasreddit.data.remote.api.RedditApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//    //Network module
//    @Singleton
//    @Provides
//    fun provideHttpInterceptor(): HttpLoggingInterceptor {
//        return HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideGsonConverterFactory(): GsonConverterFactory {
//        return GsonConverterFactory.create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: Converter.Factory): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(HttpRoutes.BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }
//
//    fun provideRedditApiService(retrofit: Retrofit): RedditApiService {
//        return retrofit.create(RedditApiService::class.java)
//    }
//}

interface NetworkModuleI {
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val gsonConverterFactory: GsonConverterFactory
    val retrofit: Retrofit
    val redditApiService: RedditApiService
}

class NetworkModule(
    private val appContext: Context
) : NetworkModuleI {
    override val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    override val okHttpClient: OkHttpClient by lazy {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        val cache = Cache(appContext.cacheDir, cacheSize)
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                var request = chain.request()

                request = if (hasNetwork(appContext) == true) {
                    //Get Cache stored 5 seconds ago
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                } else {
                    //Get Cache stored 7 days ago if not connected to the internet   if the cache is older than 7 days, then discard it,
                    //and indicate an error in fetching the response.
                    val maxStale = 60 * 60 * 24 * 7 // 1-week stale
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    ).build()
                }

                chain.proceed(request)
            }
            .build()
    }
    override val gsonConverterFactory: GsonConverterFactory by lazy {
        GsonConverterFactory.create()
    }
    override val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(HttpRoutes.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
    override val redditApiService: RedditApiService by lazy {
        retrofit.create(RedditApiService::class.java)
    }


}
