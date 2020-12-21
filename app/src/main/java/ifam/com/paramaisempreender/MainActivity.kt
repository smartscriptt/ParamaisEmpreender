package ifam.com.paramaisempreender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
var UserUtil:User? =null
class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            startActivity(Intent (this, EscolherActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}