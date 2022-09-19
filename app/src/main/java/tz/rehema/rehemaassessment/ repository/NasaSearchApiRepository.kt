package tz.rehema.rehemaassessment.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tz.rehema.rehemaassessment.model.NasaSearchApiModel
import tz.rehema.rehemaassessment.network.RetrofitClient

class NasaSearchApiRepository {
    private val mmNasaSearchApiModelmist = ArrayList<NasaSearchApiModel>()
    private val mutableLiveData = MutableLiveData<List<NasaSearchApiModel>>()

    ////call to internet and  retun  MutableLiveData
    fun getMutableLiveData(): MutableLiveData<List<NasaSearchApiModel>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
        val call = userDataService.apiRequestsObject

        call.enqueue(object : Callback<JsonObject> {
            internal var message: String? = null

            override fun onResponse(call: Call<JsonObject>, resp: Response<JsonObject>) {

                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to NasaSearchApiViewModel class
                val gson = GsonBuilder().create()

                if (resp != null && resp.body() != null) {

                    Log.e("data_rehema_response",resp.body()!!.toString())

                    val jsonObj_collection = JsonParser().parse(resp.body()!!.get("collection").toString()).asJsonObject
                    Log.e("data_rehema_collection",jsonObj_collection.toString())
                    val jsonArray_items = JsonParser().parse(jsonObj_collection.get("items").toString()).asJsonArray

                    if (jsonArray_items != null) {
                        Log.e("data_rehema_items",jsonArray_items.toString())

                        for (i in 0..jsonArray_items.size() - 1) {
                            try {
                                val jsonobj_all_items =
                                    JsonParser().parse(jsonArray_items.get(i).toString()).asJsonObject
                                Log.e("rehema_items_objects ",jsonobj_all_items.toString())
                                val jsonArray_data = JsonParser().parse(jsonobj_all_items.get("data").toString()).asJsonArray
                                for (j in 0..jsonArray_data.size() - 1) {
                                    val jsonobj_all_data =
                                        JsonParser().parse(jsonArray_data.get(j).toString()).asJsonObject
                                    Log.e("rehema_data_objects ",jsonobj_all_data.toString())


                                    //fetch photo at index 1 of the all irems
                                    if(j==jsonArray_data.size()-1){
                                        val jsonArray_image_links = JsonParser().parse(jsonobj_all_items.get("links").toString()).asJsonArray
                                        Log.e("rehema_links_array ",jsonArray_image_links.toString())
                                        for (k in 0..jsonArray_image_links.size() - 1) {
                                            val jsonobj_all_links =
                                                JsonParser().parse(jsonArray_image_links.get(k).toString()).asJsonObject
                                            Log.e("rehema_links_objects ",jsonobj_all_links.toString())

                                            //fill our model
                                            val responseModel =
                                                gson.fromJson(jsonobj_all_data.toString().removeSuffix("}")+
                                                        jsonobj_all_links.toString().replace("{",",").toString(), NasaSearchApiModel::class.java)

//                                                 val responseModel = gson.fromJson(jsonobj_all_data, NasaSearchApiModel::class.java)

                                            Log.d("angaaa_new",""+responseModel)

                                            mmNasaSearchApiModelmist.add(responseModel)

                                        }
                                    }

                                }
                            } catch (ex: Exception) {
                                Log.e("error_failure1",ex.message.toString())
                            }
                        }
                        mutableLiveData.value = mmNasaSearchApiModelmist
                    }

//                    val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
//                    Log.e("data_anga",json.toString())
//                    if (json != null) {
//
//                        for (i in 0..json.size() - 1) {
//                            try {
//
//                                val jsonobj =
//                                    JsonParser().parse(json.get(i).toString()).asJsonObject
//
//                                val responseModel =
//                                    gson.fromJson(jsonobj, NasaSearchApiModel::class.java)
//
//                                mmNasaSearchApiModelmist.add(responseModel)
//
//                            } catch (ex: Exception) {
//
//                            }
//
//
//                        }
//                        mutableLiveData.value = mmNasaSearchApiModelmist
//
//                    }
                }


            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                t.printStackTrace()
                Log.e("error_failure",t.toString())
            }
        })



        ///call the API that define In Interface
//        val call = userDataService.apiRequestsArray
//        call.enqueue(object : Callback<JsonArray> {
//            internal var message: String? = null
//
//            override fun onResponse(call: Call<JsonArray>, resp: Response<JsonArray>) {
//
//                /// parse the data if  Response successfully and store data in MutableLiveData  and retrun to NasaSearchApiViewModel class
//                val gson = GsonBuilder().create()
//
//                if (resp != null && resp.body() != null) {
//
//                    val json = JsonParser().parse(resp.body()!!.toString()).asJsonArray
//                    Log.e("data_anga",json.toString())
//                    if (json != null) {
//
//                        for (i in 0..json.size() - 1) {
//                            try {
//
//                                val jsonobj =
//                                    JsonParser().parse(json.get(i).toString()).asJsonObject
//
//                                val responseModel =
//                                    gson.fromJson(jsonobj, NasaSearchApiModel::class.java)
//
//                                mmNasaSearchApiModelmist.add(responseModel)
//
//                            } catch (ex: Exception) {
//
//                            }
//
//
//                        }
//                        mutableLiveData.value = mmNasaSearchApiModelmist
//
//                    }
//                }
//
//
//            }
//
//            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
//                t.printStackTrace()
//                Log.e("error_failure",t.toString())
//            }
//        })


        return mutableLiveData
    }

    companion object {

        private val TAG = "NasaSearchApiRepository"
    }
}