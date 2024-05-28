package dev.xget.havasreddit.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.xget.havasreddit.core.utils.HttpRoutes
import dev.xget.havasreddit.data.remote.api.RedditApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

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

interface NetworkModuleI{
    val httpLoggingInterceptor: HttpLoggingInterceptor
    val okHttpClient: OkHttpClient
    val gsonConverterFactory: GsonConverterFactory
    val retrofit: Retrofit
}

class NetworkModule(
    private val appContext : Context
) : NetworkModuleI {
    override val httpLoggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    override val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
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

}
