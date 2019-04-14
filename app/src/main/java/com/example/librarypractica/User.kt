package com.example.librarypractica

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class User(): Parcelable {
    @SerializedName("username")
    var username:String = ""
    @SerializedName("password")
    var password:String = ""
    @SerializedName("favoriteBooks")
    var favoriteBooks:ArrayList<Item> = ArrayList()

    constructor(parcel: Parcel) : this() {
        username = parcel.readString()
        password = parcel.readString()
        favoriteBooks = parcel.readArrayList(Item::class.java.classLoader) as ArrayList<Item>
    }

    constructor(mail: String, password: String, listBooks:ArrayList<Item>): this() {
        this.username = mail
        this.password = password
        this.favoriteBooks = listBooks
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(username)
        parcel.writeString(password)
        parcel.writeList(favoriteBooks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}