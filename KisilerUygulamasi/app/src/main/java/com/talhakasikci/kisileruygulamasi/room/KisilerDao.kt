package com.talhakasikci.kisileruygulamasi.room

import androidx.room.*
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler

@Dao
interface KisilerDao {
    @Query("SELECT * FROM kisiler")
    suspend fun tumKisileriGetir(): List<Kisiler>

    @Insert
    suspend fun kisiEkle(kisi: Kisiler)

    @Update
    suspend fun kisiGuncelle(kisi: Kisiler)

    @Delete
    suspend fun kisiSil(kisi: Kisiler)

    @Query("SELECT * FROM kisiler WHERE kisi_ad like '%' || :aramaKelimesi || '%'")
    suspend fun kisiAra(aramaKelimesi: String): List<Kisiler>
} 