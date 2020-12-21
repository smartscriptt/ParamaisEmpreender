package ifam.com.paramaisempreender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EscolherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolher)
    }
    fun sendMessage(view: View){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
    fun proxTela(view: View){
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
}