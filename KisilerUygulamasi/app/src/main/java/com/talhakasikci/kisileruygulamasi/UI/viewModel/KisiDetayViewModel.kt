package com.talhakasikci.kisileruygulamasi.UI.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.data.dataSource.KisilerDataSource
import com.talhakasikci.kisileruygulamasi.data.repo.KisilerRepository
import com.talhakasikci.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.launch

class KisiDetayViewModel(application: Application) : AndroidViewModel(application) {
    private val kisilerDao = Veritabani.veritabaniErisim(application)?.getKisilerDao()
    private val kisilerDataSource = KisilerDataSource(kisilerDao!!)
    private val detayRepo = KisilerRepository(kisilerDataSource)

    fun kisiGuncelle(kisi: Kisiler) {
        viewModelScope.launch {
            detayRepo.kisiGuncelle(kisi)
        }
    }

    fun kisiSil(kisi: Kisiler) {
        viewModelScope.launch {
            detayRepo.kisiSil(kisi)
        }
    }
}