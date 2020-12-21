package ifam.com.paramaisempreender

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase
    val TAG = "MyActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = Firebase.auth
        firebaseDatabase = FirebaseDatabase.getInstance()
        es_senha_txt.setOnClickListener{
            Recupe()
        }
        bt_entrar.setOnClickListener{
            LogarUser()

        }
        cadas_txt.setOnClickListener{
            Va()
        }
    }
    private fun Recupe(){
        val emailv = email_etxt.text.toString().trim()
        Firebase.auth.sendPasswordResetEmail(emailv).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "Email sent.")
                Toast.makeText(this, "Verifique seu email e redefina a senha", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{ e ->
            Log.w(TAG, "Erro ao enviar", e)
            Toast.makeText(this, "Erro ao enviar solicitacao", Toast.LENGTH_SHORT).show()
        }

    }
    private fun LogarUser() {
        val emailE = edt_email.text.toString().trim()
        val senhaE = edt_senha.text.toString().trim()

        if (emailE.isEmpty() || senhaE.isEmpty()) {
            Toast.makeText(this, "Preencha os campos!", Toast.LENGTH_SHORT).show()

        } else {
            auth.signInWithEmailAndPassword(emailE, senhaE).addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this, "Bem Vindo!", Toast.LENGTH_SHORT).show()
                    Ir()

                }else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Erro ao logar usuario", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun Va(){
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
    private fun Ir(){
        val intent = Intent(this, TelaPActivity::class.java)
        startActivity(intent)
   }
}

//val user = Firebase.auth.currentUser
//if (user != null) {
// User is signed in
//} else {
// No user is signed in
//}

/* val user = Firebase.auth.currentUser
user?.let {
    // Name, email address, and profile photo Url
    val name = user.displayName
    val email = user.email
    val photoUrl = user.photoUrl

    // Check if user's email is verified
    val emailVerified = user.isEmailVerified

    // The user's ID, unique to the Firebase project. Do NOT use this value to
    // authenticate with your backend server, if you have one. Use
    // FirebaseUser.getToken() instead.
    val uid = user.uid
}

btn_inserir.setOnClickListener{
val produto = txt_produto.text.toString()
if(produto.isNotEmpty(){
txt_produto.text.clear()
}else{
txt_produto.error = "Preencha um valor"
}
 */