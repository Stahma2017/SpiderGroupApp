package com.example.spidergroupapp.di.app

import android.app.Application
import android.content.Context
import com.example.spidergroupapp.di.main.MainComponent
import com.example.spidergroupapp.data.network.ImgurApi
import com.example.spidergroupapp.view.base.BaseErrorHandler
import com.example.spidergroupapp.view.base.ErrorHandler
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Component(modules = [ApplicationModule::class, RxModule::class, NetworkModule::class, ErrorHandlerModule::class])
interface ApplicationComponent{
    fun addMainComponent(): MainComponent
}

@Module
class ApplicationModule constructor(private val application: Application) {
    @Provides
    fun provideApp() : Context {
        return application
    }
}

@Module
class RxModule{
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}

@Module
class ErrorHandlerModule{
    @Provides
    fun provideErrorHandler(context: Context): ErrorHandler {
        return BaseErrorHandler(context)
    }
}

@Module
class NetworkModule{
    private val CLIENT_ID = "Authorization"
    private val CLIENT_ID_VALUE = "Client-ID f0516714958d548"
    private val BASE_URL = "https://api.imgur.com/3/"

    private val CLIENT_SECRET = "a5c4a676f249d5498bac46ff5d74c92a4345609d"

    @Provides
    @Named("Logging")
    fun provideLogingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Named("Query")
    fun provideQueryInterceptor(): Interceptor {
        return Interceptor { it: Interceptor.Chain ->
            var request = it.request()
            request = request.newBuilder()
                .addHeader(CLIENT_ID, CLIENT_ID_VALUE)
                .build()
            it.proceed(request)
        }
    }

    @Provides
    fun provideHttpClient(@Named("Logging") loggingInterceptor: Interceptor,
                          @Named("Query") queryInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(queryInterceptor)
            .build()
    }

    @Provides
    fun provideOxfordApi(httpClient : OkHttpClient): ImgurApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ImgurApi::class.java)
    }
}