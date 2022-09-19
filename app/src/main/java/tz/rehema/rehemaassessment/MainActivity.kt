package tz.rehema.rehemaassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import tz.rehema.rehemaassessment.CustomAdatper.NasaSearchApi_CustomAdapter
import tz.rehema.rehemaassessment.databinding.ActivityMainBinding
import tz.rehema.rehemaassessment.model.NasaSearchApiModel
import tz.rehema.rehemaassessment.viewmodel.NasaSearchApiViewModel
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    internal var activityMainBinding: ActivityMainBinding?= null
    internal var loadBar : ProgressBar ? = null
    var mainViewModel: NasaSearchApiViewModel? = null
    private var mNasaSearchApi_CustomAdapter: NasaSearchApi_CustomAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main);

        // bind RecyclerView
        val recyclerView = activityMainBinding?.viewnasaapi
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProviders.of(this).get(NasaSearchApiViewModel::class.java)

        //init the Custom adataper
        mNasaSearchApi_CustomAdapter = NasaSearchApi_CustomAdapter()
        //set the CustomAdapter
        recyclerView.setAdapter(mNasaSearchApi_CustomAdapter)

        getAllCollections()
    }

    private fun getAllCollections() {
        ///get the list of collections from api response
        mainViewModel!!.allNasaSearchApi.observe(this,
            Observer<List<Any>> { mNasaSearchApiModel ->
                ///if any thing chnage the update the UI
                mNasaSearchApi_CustomAdapter?.setNasaSearchApiList(mNasaSearchApiModel as ArrayList<NasaSearchApiModel>)
                loadBar?.visibility = View.GONE
            })
    }
}