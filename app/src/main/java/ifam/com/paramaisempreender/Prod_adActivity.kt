package ifam.com.paramaisempreender

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.renderscript.Script
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startActivityForResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabase.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_prod_ad.*
import kotlinx.android.synthetic.main.fragment_adicionar.*

class Prod_adActivity : AppCompatActivity() {
    private lateinit var salV: DatabaseReference
    private lateinit var refDec: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prod_ad)

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        salV = getInstance().getReference("/Categorias")
        refDec = getInstance().getReference("ProdutoUser/$userId")


        btn_inserir.setOnClickListener {
            inseredados()
        }
        img_p.setOnClickListener(){
            upload()
        }
    }
    val userName = UserUtil
    private fun inseredados(){

        val nomepro = produto_nome.text.toString()
        val catego = categoria.text.toString()
        val precco = Integer.parseInt(preco_pro.text.toString())
        if (nomepro.isEmpty() || catego.isEmpty() || precco == 0) {
            Toast.makeText(applicationContext, "Preencha todos os campos", Toast.LENGTH_LONG).show()
        }else {

            val pubId = salV.push().key
            val uid = FirebaseAuth.getInstance().uid ?: ""
            val objetpro = Produto(uid, nomepro, precco, catego,"@"+userName?.usernome)

            refDec.child(pubId!!).setValue(objetpro)

                Toast.makeText(applicationContext, "Produto Cadastrado com sucesso", Toast.LENGTH_LONG).show()

                produto_nome.text.clear()
                categoria.text.clear()
                preco_pro.text.clear()

        }
    }

    var selectfturi: Uri? = null

    private fun uploadImag() {
         if ( selectfturi == null) return
        val pubId = salV.push().key
        val ref = FirebaseStorage.getInstance().getReference("${usuarioLog()!!.uid}/$pubId/images")

        ref.putFile(selectfturi!!)
        ref.downloadUrl.addOnSuccessListener {
            Log.d("Prod_adActivity","Local do arquivo:$it")
            insereImageFire(it.toString())
        }
    }

    private fun upload() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = ("image/*")
        startActivityForResult(intent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            selectfturi = data.data

                    //if(Build.VERSION.SDK_INT < 28) {
                      //  val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectfturi)
                        //val bitmapDrawable = BitmapDrawable(bitmap)
                      //  img_p.setBackgroundDrawable(bitmapDrawable)
                    //}else{
                       // val source = ImageDecoder.createSource(contentResolver, selectfturi!!)
                      //  val bitmap = ImageDecoder.decodeBitmap(source)
                    //    img_p.s
                 //   }


            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,selectfturi)


                    img_p.text = ""
            val bitmapDrawable = BitmapDrawable(bitmap)
            img_p.setBackgroundDrawable(bitmapDrawable)


            uploadImag()
        }

    }

   // private fun getCapture(){
    //}
    private fun insereImageFire(img: String){
        val pubId = salV.push().key
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val salV = getInstance().getReference("/Produto/$userId/$pubId/imagep")
        val refDec = getInstance().getReference("/Categotias/$pubId/imagep")

        salV.setValue(img)
        refDec.setValue(img).addOnSuccessListener {
            Log.d("Salvar","Salvo com sucesso")
        }

    }

    fun usuarioLog(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }

}
