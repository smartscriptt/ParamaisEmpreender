package ifam.com.paramaisempreender

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {
    val TAG = "MyActivity"
    val db = FirebaseFirestore.getInstance()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        auth = Firebase.auth

            conf_bt.setOnClickListener {
            CadastrarUsuario()
        }
    }
    private fun CadastrarUsuario() {
        val email = email_etxt.text.toString().trim()
        val senha = senha_etxt.text.toString().trim()
        val nome = nome_etxt.text.toString().trim()
        val sobrenome = sobren_etxt.text.toString().trim()


        if (email.isEmpty() || senha.isEmpty() || nome.isEmpty() || sobrenome.isEmpty()){
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()

        }else{
            auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    Log.d(TAG, "createUserWithEmail:success")
                    val uid = FirebaseAuth.getInstance().uid ?: ""

                    val ref = FirebaseDatabase.getInstance().getReference("/User/$uid")

                    val userobject = User(uid, nome, email, senha, sobrenome)
                    ref.setValue(userobject)
                            .addOnSuccessListener {
                                Toast.makeText(applicationContext, "Seus dados foram salvos com sucesso", Toast.LENGTH_LONG).show()
                                proximaTel()
                            }
                }else{
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(this, "Falha na criacao.", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun proximaTel(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
