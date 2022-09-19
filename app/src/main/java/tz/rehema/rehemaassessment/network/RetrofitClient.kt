package tz.rehema.rehemaassessment.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
//    private val BASE_URL = "https://api.github.com/"
//    private val BASE_URL = "https://images-api.nasa.gov/search?q=milky%20way&media_type=image&year_start=2017&year_end=2017"

    private val BASE_URL = "https://images-api.nasa.gov/"
    private var retrofit: Retrofit? = null

    val service: ApiDataService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create<ApiDataService>(ApiDataService::class.java!!)
        }
}