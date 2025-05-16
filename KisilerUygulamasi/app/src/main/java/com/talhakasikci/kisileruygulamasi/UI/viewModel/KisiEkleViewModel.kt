package com.talhakasikci.kisileruygulamasi.UI.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.talhakasikci.kisileruygulamasi.data.dataSource.KisilerDataSource
import com.talhakasikci.kisileruygulamasi.data.repo.KisilerRepository
import com.talhakasikci.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.launch

class KisiEkleViewModel(application: Application) : AndroidViewModel(application) {
    private val kisilerDao = Veritabani.veritabaniErisim(application)?.getKisilerDao()
    private val kisilerDataSource = KisilerDataSource(kisilerDao!!)
    private val ekleRepo = KisilerRepository(kisilerDataSource)

    fun kaydet(kisiAdi: String, kisiTel: String) {
        viewModelScope.launch {
            ekleRepo.kaydet(kisiAdi, kisiTel)
        }
    }
}