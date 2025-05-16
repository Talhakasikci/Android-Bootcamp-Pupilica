package com.talhakasikci.kisileruygulamasi.data.dataSource

import android.util.Log
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.room.KisilerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KisilerDataSource(private val kisilerDao: KisilerDao) {
    
    suspend fun kaydet(kisiAdi: String, kisiTel: String) {
        withContext(Dispatchers.IO) {
            val yeniKisi = Kisiler(0, kisiAdi, kisiTel)
            kisilerDao.kisiEkle(yeniKisi)
            Log.e("kisiKaydet", "$kisiAdi $kisiTel")
        }
    }

    suspend fun kisileriYukle(): List<Kisiler> {
        return withContext(Dispatchers.IO) {
            kisilerDao.tumKisileriGetir()
        }
    }

    suspend fun kisiGuncelle(kisi: Kisiler) {
        withContext(Dispatchers.IO) {
            kisilerDao.kisiGuncelle(kisi)
            Log.e("kisiGuncelle", "${kisi.kisi_ad} ${kisi.kisi_tel}")
        }
    }

    suspend fun kisiSil(kisi: Kisiler) {
        withContext(Dispatchers.IO) {
            kisilerDao.kisiSil(kisi)
            Log.e("kisiSil", "${kisi.kisi_ad} ${kisi.kisi_tel}")
        }
    }
}