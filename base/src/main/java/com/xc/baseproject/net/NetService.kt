package com.xc.baseproject.net

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetService {
    private val baseOkHttpClient: OkHttpClient
    private val baseRetrofit: Retrofit
    val wayJdRetrofit: Retrofit

    const val wayJdApiAppKey = "e4388cc76bf9529e6e29a938cbdf839d"

    private const val baseUrl = "https://localhost/"
    private const val baseWayJdApiUrl = "https://way.jd.com/"

    init {
        val okHttpBuilder = OkHttpClient.Builder()
        baseOkHttpClient = okHttpBuilder.build()

        val retrofitBuilder = Retrofit.Builder()
        retrofitBuilder.baseUrl(baseUrl)
        retrofitBuilder.client(baseOkHttpClient)
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        baseRetrofit = retrofitBuilder.build()

        wayJdRetrofit = baseRetrofit.newBuilder().client(baseOkHttpClient.newBuilder().addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain?): Response {
                if (chain == null) {
                    Log.e("Net Error", "okHttp intercept receive null chain")
                    return Response.Builder() //
                            .code(400)
                            .message("okHttp intercept receive null chain")
                            .build()
                }
                val request = chain.request().newBuilder().build()
                return chain.proceed(request)
            }
        }).build())
                .baseUrl(baseWayJdApiUrl).build()
    }

}