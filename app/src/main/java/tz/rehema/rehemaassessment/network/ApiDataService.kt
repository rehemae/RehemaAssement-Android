package tz.rehema.rehemaassessment.network

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface ApiDataService {


//    search?q=milky%20way&media_type=image&year_start=2017&year_end=2017

    @get:GET("search?q=milky%20way&media_type=image&year_start=2017&year_end=2017")
    val apiRequestsObject: Call<JsonObject>

//    @get:GET("users")
//    val apiRequestsArray: Call<JsonArray>
}