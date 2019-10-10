package ru.nickb.cryptotop.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import ru.nickb.cryptotop.R
import ru.nickb.cryptotop.fragments.CurrenciesListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-2686250832517652/9356163074"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CurrenciesListFragment(), null)
                .commit()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.action_about -> {


                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAd() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        showAd()
    }

}
