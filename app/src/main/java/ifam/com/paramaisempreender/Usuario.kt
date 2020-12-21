package ifam.com.paramaisempreender

import android.content.Intent
import android.os.Parcelable
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_perfil.*

@Parcelize
data class User(
        var uid: String? = null,
        var nome: String? = null,
        var emaill: String? = null,
        var senha: String? = null,
        var sobrenome: String? = null,
        var usernome: String?= null,
        var telefone: Int? = null
): Parcelable{
constructor():this("","","","","","",0)
}
/*


    private fun inserImage() {

        //val uid = FirebaseAuth.getInstance().uid
        //  val ref = FirebaseFirestore.getInstance()

        //val intent = Intent(Intent.ACTION_PICK)
        //intent.setType("image/+")
        //starActivityForResult(intent,0)
    }

fun starActivityForResult(intent: Intent, i: Int) {
    if(i==0){
        data.getData()

    }}

private fun insereImagemNaArvoreDoFire(profileImageUrl: String) {

    val uid = FirebaseAuth.getInstance().uid ?: ""

    val ref = FirebaseFirestore.getInstance().getReference("/User/$uid")

    val userobject = User(uid, name_edittext.text.toString(), email_edittext_register.text.toString(), password_edittext_register.text.toString(), profileImageUrl, "Nada a dizer.", "Algum lugar por aí.", 0)
    ref.setValue(userobject)
            .addOnSuccessListener {
                Toast.makeText(this, "Seus dados foram salvos com sucesso", Toast.LENGTH_LONG).show()
                progressBar.visibility = View.INVISIBLE
                // action()
            }
}
*/
