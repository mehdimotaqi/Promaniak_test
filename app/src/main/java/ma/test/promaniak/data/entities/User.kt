package ma.test.promaniak.data.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @SerializedName("id")
    val userId: String = "",
    @SerializedName("name")
    val userName: String,
    @SerializedName("email")
    val userMail: String,
    @SerializedName("phone")
    val userPhone: String,
    @SerializedName("image")
    val imageUrl: String?): Parcelable