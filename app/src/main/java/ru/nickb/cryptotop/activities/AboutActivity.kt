package ru.nickb.cryptotop.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.activity_about.*
import ru.nickb.cryptotop.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        buttonRateApp.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=${applicationContext.packageName}")))



            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)

        }
    }

}
