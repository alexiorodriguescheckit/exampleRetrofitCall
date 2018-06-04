package com.alexiorodrigues.retrofitexamples

import retrofit2.Call
import retrofit2.http.*

interface DataAPI {

    @GET("/getclientpref")
    fun getSettings(): Call<DataModel>

    @GET("/wrong_temp")
    fun getWrongTempSettings(): Call<DataModel>


}