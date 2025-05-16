package com.talhakasikci.kisileruygulamasi.UI.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler
import com.talhakasikci.kisileruygulamasi.data.dataSource.KisilerDataSource
import com.talhakasikci.kisileruygulamasi.data.repo.KisilerRepository
import com.talhakasikci.kisileruygulamasi.room.Veritabani
import kotlinx.coroutines.launch

class AnaSayfaViewModel(application: Application) : AndroidViewModel(application) {
    private val kisilerDao = Veritabani.veritabaniErisim(application)?.getKisilerDao()
    private val kisilerDataSource = KisilerDataSource(kisilerDao!!)
    private val kisilerRepo = KisilerRepository(kisilerDataSource)
    
    private val _kisiler = MutableLiveData<List<Kisiler>>()
    val kisiler: LiveData<List<Kisiler>> = _kisiler
    
    fun kisileriYukle() {
        viewModelScope.launch {
            val kisiler = kisilerRepo.kisileriYukle()
            _kisiler.value = kisiler
        }
    }
}