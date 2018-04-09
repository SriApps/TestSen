package com.test.sri.testsen

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit




/**
 * Created by  Myworld on  24/03/2018.
 */
interface RetrofitApiService {

    @GET("properties")
    fun loadfeed(): Observable<RealEstateModel>;

    companion object RetrofitApiclient {

        fun create(): RetrofitApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://demo0065087.mockable.io/test/")
                    .build()

            return retrofit.create<RetrofitApiService>(RetrofitApiService::class.java)
        }
    }


}