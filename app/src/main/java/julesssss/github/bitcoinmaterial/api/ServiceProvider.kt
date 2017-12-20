package julesssss.github.bitcoinmaterial.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import julesssss.github.bitcoinmaterial.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide required Service objects
 * - services: main endpoint used for user & device requests
 * - metadata: for retrieving subscription products
 */
internal object ServiceProvider {

    private val gson: Gson
    private var okHttpClient: OkHttpClient

    val coinbaseService by lazy { initCsgServiceApi() }

    init {
        val interceptor = HttpLoggingInterceptor()
        gson = GsonBuilder().create()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    private fun initCsgServiceApi(): CoinbaseService = Retrofit.Builder()
            .baseUrl(BuildConfig.COINBASE_ENDPOINT_V2)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(CoinbaseService::class.java)

}