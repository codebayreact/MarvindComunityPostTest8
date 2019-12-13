package com.bayreact.marvindcomunity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.intentFor

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
       Handler().postDelayed({
           finish()
           startActivity(intentFor<LoginActivity>())
       },3000)
    }
}
