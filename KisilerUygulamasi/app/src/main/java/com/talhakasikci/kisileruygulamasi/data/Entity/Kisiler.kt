package com.talhakasikci.kisileruygulamasi.data.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "kisiler")
data class Kisiler(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kisi_id")
    var kisi_id: Int,
    @ColumnInfo(name = "kisi_ad")
    var kisi_ad: String,
    @ColumnInfo(name = "kisi_tel")
    var kisi_tel: String
) : Serializable