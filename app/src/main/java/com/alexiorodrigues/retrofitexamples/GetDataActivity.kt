package com.alexiorodrigues.retrofitexamples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

class GetDataActivity : AppCompatActivity(), Callback<DataModel> {

    private val BASE_URL = "https://www.getpostman.com/collections/a2c503b52fd19c63f8d1/getclientprefs/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_data)

        Timber.plant(Timber.DebugTree())

        getServerData()
    }

    fun getServerData() {
        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val dataAPI = retrofit.create<DataAPI>(DataAPI::class.java)

        val call = dataAPI.getSettings()
        call.enqueue(this)
    }

    override fun onFailure(call: Call<DataModel>?, t: Throwable?) {
        Timber.w("DEBUG Server throwable: %s", t)
        Timber.w("DEBUG onFailure")
        Timber.w("DEBUG Server call: %s", call)
    }


    override fun onResponse(call: Call<DataModel>?, response: Response<DataModel>?) {
        Timber.w("DEBUG onResponse")
        Timber.w("DEBUG Server call: %s", call)

        if (response?.isSuccessful!!) {
            Timber.w("DEBUG SUCCESSFULL")
            Timber.w("DEBUG Server body: %s", response.body())
            Timber.w("DEBUG Server response: %s", response)
        } else {
            Timber.w("DEBUG UNSUCCESSFULL")
            Timber.w("DEBUG Server body: %s", response.body())
        }
    }
}
