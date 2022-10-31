package com.example.beprof.models


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

import java.text.DateFormat

@Entity(tableName = "notes")
@Parcelize
data class Note(
    var title:String,
    var note:String,
    var color: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    var date: Long =System.currentTimeMillis(),

    ):Parcelable{
    val instanceDate:String
    get() =DateFormat.getDateInstance().format(date)

}
