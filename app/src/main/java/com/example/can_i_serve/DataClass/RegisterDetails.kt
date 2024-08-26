import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class RegisterDetails(
    var name: String?,
    val age: Int?,
    val location: String?,
    val email: String?,
    val phone: String?,
    val role: Int?,
    val countryCode:String?
) : Serializable
//{
//
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readValue(Int::class.java.classLoader) as? Int,
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readValue(Int::class.java.classLoader) as? Int
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(name)
//        parcel.writeValue(age)
//        parcel.writeString(location)
//        parcel.writeString(email)
//        parcel.writeString(phone)
//        parcel.writeValue(role)
//    }
//
//    override fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<RegisterDetails> {
//        override fun createFromParcel(parcel: Parcel): RegisterDetails {
//            return RegisterDetails(parcel)
//        }
//
//        override fun newArray(size: Int): Array<RegisterDetails?> {
//            return arrayOfNulls(size)
//        }
//    }
//}
