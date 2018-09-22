package com.xc.baseproject.basefeature.testtuling

import com.xc.baseproject.net.NetService
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

object TulingRepository {

    data class Response(val code: String, val charge: Boolean, val msg: String, val result: Result)
    data class Result(val code: Int, val text: String)

    interface Api {
        @GET("turing/turing")
        fun getTulingResponse(
                @Query("info") info: String?,
                @Query("loc") loc: String = "",
                @Query("userid") userid: String = UUID.randomUUID().toString(),
                @Query("appkey") appkey: String = NetService.wayJdApiAppKey
        ): Observable<Response>
    }

    private val getTulingApi by lazy {
        NetService.wayJdRetrofit.create(Api::class.java)
    }

    fun getTulingResponse(info: String?,
                          loc: String = "",
                          userid: String = UUID.randomUUID().toString(),
                          appkey: String = NetService.wayJdApiAppKey): Observable<Response> {
        return getTulingApi.getTulingResponse(info, loc, userid, appkey)
    }
}