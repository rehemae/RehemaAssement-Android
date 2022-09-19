package tz.rehema.rehemaassessment.model

import android.util.Log
import android.widget.ImageView

import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName


class NasaSearchApiModel {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("center")
    var center: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("date_created")
    var date_created: String? = null

    @SerializedName("href")  //for a photo link
    var href: String? = null

    companion object  {

        @JvmStatic
        @BindingAdapter("href")
        fun loadImage(imageView: ImageView, imageURL: String) {

            Log.e("imsgeurl",imageURL)
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
//                        .circleCrop()
                )
                .load(imageURL)

                .into(imageView)
        }

    }

}