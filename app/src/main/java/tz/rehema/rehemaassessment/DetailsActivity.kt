package tz.rehema.rehemaassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import tz.rehema.rehemaassessment.databinding.ActivityDetailsBinding
import tz.rehema.rehemaassessment.databinding.ActivityMainBinding

class DetailsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_details)

         val activityDetailsBinding: ActivityDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_details);

        val photourl = intent.getStringExtra("photourl")
        val title = intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val center = intent.getStringExtra("center")
        val date_created = intent.getStringExtra("date_created")

        Glide.with(this).load(photourl).into(activityDetailsBinding.imgPic)
        activityDetailsBinding.title.text = title
        activityDetailsBinding.description.text = description
        activityDetailsBinding.center.text = center
        activityDetailsBinding.date.text = date_created

        activityDetailsBinding.backImage.setOnClickListener(
            View.OnClickListener {
            finish()
        })

//        Toast.makeText(this,title,Toast.LENGTH_LONG).show()

    }
}