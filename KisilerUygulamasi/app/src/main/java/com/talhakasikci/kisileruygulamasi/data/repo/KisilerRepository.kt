package com.talhakasikci.kisileruygulamasi.data.repo

import android.util.Log
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.data.dataSource.KisilerDataSource

class KisilerRepository(private val kisilerDataSource: KisilerDataSource) {
    
    suspend fun kaydet(kisiAdi: String, kisiTel: String) {
        kisilerDataSource.kaydet(kisiAdi, kisiTel)
    }

    suspend fun kisileriYukle(): List<Kisiler> {
        return kisilerDataSource.kisileriYukle()
    }

    suspend fun kisiGuncelle(kisi: Kisiler) {
        kisilerDataSource.kisiGuncelle(kisi)
    }

    suspend fun kisiSil(kisi: Kisiler) {
        kisilerDataSource.kisiSil(kisi)
    }
}