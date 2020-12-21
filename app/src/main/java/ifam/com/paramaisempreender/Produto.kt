package ifam.com.paramaisempreender

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Produto(
        val uid: String? = null,
        val nomepr: String? = null,
        val preco: Int? = null,
        val categoria: String? = null,
        val imgUri: String? = null,

): Parcelable{
    constructor():this("","", 0,"", "")
}

