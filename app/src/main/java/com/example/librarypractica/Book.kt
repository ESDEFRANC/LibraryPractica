package com.example.librarypractica

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Book(title: String, author:String, publisher: String, description: String, thumbnail: String) : Parcelable {

    @SerializedName("title")
    var title:String? = null
    @SerializedName("authors")
    var authors:Array<Any>? = null
    @SerializedName("publisher")
    var publisher:String? = null
    @SerializedName("description")
    var description:String? = null
    @SerializedName("thumbnail")
    var thumbnail:String? = null


    init {
        this.title = title
        this.authors = authors
        this.publisher = publisher
        this.description = description
        this.thumbnail = thumbnail
    }
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeArray(authors)
        parcel.writeString(publisher)
        parcel.writeString(description)
        parcel.writeString(thumbnail)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }


}