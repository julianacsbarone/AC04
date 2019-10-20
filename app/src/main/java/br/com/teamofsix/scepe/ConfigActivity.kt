package br.com.teamofsix.scepe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class ConfigActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Configurações"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
