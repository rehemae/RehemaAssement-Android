package tz.rehema.rehemaassessment.CustomAdatper

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView


import tz.rehema.rehemaassessment.DetailsActivity
import tz.rehema.rehemaassessment.R
import tz.rehema.rehemaassessment.databinding.RowListItemBinding
import tz.rehema.rehemaassessment.model.NasaSearchApiModel
import java.text.SimpleDateFormat
import java.util.*

class NasaSearchApi_CustomAdapter :
    RecyclerView.Adapter<NasaSearchApi_CustomAdapter.NasaSearchApiViewHolder>() {

    private var mNasaSearchApiModel: ArrayList<NasaSearchApiModel>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): NasaSearchApiViewHolder {

        val mNasaSearchApiListItemBinding = DataBindingUtil.inflate<RowListItemBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.row_list_item, viewGroup, false
        )

        return NasaSearchApiViewHolder(mNasaSearchApiListItemBinding)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(mNasaSearchApiViewHolder: NasaSearchApiViewHolder, i: Int) {

        //convert simpledate to local
        try{
            val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            mNasaSearchApiModel!![i].date_created =
                parser.parse(mNasaSearchApiModel!![i].date_created.toString())
                    ?.let { formatter.format(it) }
        }catch (ex: Exception){
           ex.stackTrace
        }

        val currentdatamodel = mNasaSearchApiModel!![i]

        mNasaSearchApiViewHolder.mNasaSearchApiListItemBinding.nasaapiModel = currentdatamodel
        mNasaSearchApiViewHolder.mNasaSearchApiListItemBinding.cvnasaapi
            .setOnClickListener(View.OnClickListener {
//               val intent = Intent(this@NasaSearchApi_CustomAdapter, DetailsActivity::class.java)
                val intent = Intent(mNasaSearchApiViewHolder.itemView.context, DetailsActivity::class.java)
                intent.putExtra("photourl", mNasaSearchApiModel!!.get(i).href)
                intent.putExtra("title",mNasaSearchApiViewHolder.mNasaSearchApiListItemBinding.tvTitle.text)
                intent.putExtra("description",currentdatamodel.description)
                intent.putExtra("center",mNasaSearchApiViewHolder.mNasaSearchApiListItemBinding.tvCenter.text)
                intent.putExtra("date_created",mNasaSearchApiViewHolder.mNasaSearchApiListItemBinding.tvDateCreated.text)
                mNasaSearchApiViewHolder.itemView.context.startActivity(intent)
            })


    }

    override fun getItemCount(): Int {
        return if (mNasaSearchApiModel != null) {
            mNasaSearchApiModel!!.size
        } else {
            0
        }
    }

    fun setNasaSearchApiList(mNasaSearchApiModel: ArrayList<NasaSearchApiModel>) {
        this.mNasaSearchApiModel = mNasaSearchApiModel
        notifyDataSetChanged()
    }

    inner class NasaSearchApiViewHolder(var mNasaSearchApiListItemBinding: RowListItemBinding) :
        RecyclerView.ViewHolder(mNasaSearchApiListItemBinding.root)
}