package com.example.restapi_ps2017261

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        var API = retrofit.create(RetrofitInterface::class.java)
        var call = API.posts

        call?.enqueue(object:Callback<List<PostModel?>?>{

            override fun onResponse(
                call: Call<List<PostModel?>?>,
                response: Response<List<PostModel?>?>
            ) {

                var postList : List<PostModel>? = response.body() as List<PostModel>
                var PostArray = arrayOfNulls<String>(postList!!.size)

                for (i  in postList.indices)
                    PostArray[i] = postList[i].title

                var ListAdapter = ArrayAdapter<String>(applicationContext,android.R.layout.simple_dropdown_item_1line, PostArray)
                findViewById<ListView>(R.id.listview).adapter = ListAdapter
            }

            override fun onFailure(call: Call<List<PostModel?>?>, t: Throwable) {

            }

        })
    }
}