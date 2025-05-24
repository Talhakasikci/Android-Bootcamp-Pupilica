package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.UI.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.Yemek
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.sepet_yemekler
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.remote.ApiClient
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.remote.YemeklerApi
import kotlinx.coroutines.launch

class YemeklerViewModel: ViewModel() {

    val yemeklerListesi = MutableLiveData<List<Yemek>>()
    val sepetYemeklerListesi = MutableLiveData<List<sepet_yemekler>>()
    val hataMesaji = MutableLiveData<String>()

    private val api = ApiClient.retrofit.create(YemeklerApi::class.java)

    fun tumYemekleriGetir(){
        viewModelScope.launch {
            try{
                Log.d("YemeklerViewModel", "Tüm yemekler getiriliyor...")
                val cevap = api.tumYemekleriGetir()
                yemeklerListesi.value = cevap.yemekler ?: emptyList()
                Log.d("YemeklerViewModel", "Yemekler başarıyla getirildi: ${cevap.yemekler?.size} adet")
            }catch (e:Exception){
                Log.e("YemeklerViewModel", "Yemekler getirilirken hata: ${e.message}")
                hataMesaji.value = e.localizedMessage ?: "Beklenmeyen bir hata oluştu"
            }

        }
    }

    fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        viewModelScope.launch {
            try {
                Log.d("YemeklerViewModel", "Sepete ekleniyor: $yemek_adi, adet: $yemek_siparis_adet")
                api.sepeteYemekEkle(
                    yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi
                )
                Log.d("YemeklerViewModel", "Sepete ekleme başarılı!")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepete ekleme hatası: ${e.message}")
                hataMesaji.value = e.localizedMessage ?: "Sepete eklenirken hata oluştu"
            }
        }
    }

    fun sepettenYemekSil(
        sepet_yemek_id: Int,
        kullanici_adi: String
    ) {
        viewModelScope.launch {
            try {
                Log.d("YemeklerViewModel", "Sepetten siliniyor: $sepet_yemek_id")
                api.sepettenYemekSil(sepet_yemek_id, kullanici_adi)
                Log.d("YemeklerViewModel", "Sepetten silme başarılı!")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepetten silme hatası: ${e.message}")
                hataMesaji.value = e.localizedMessage ?: "Sepetten silinirken hata oluştu"
            }
        }
    }

    fun sepettekiYemekleriGetir(kullanici_adi: String) {
        viewModelScope.launch {
            try {
                Log.d("YemeklerViewModel", "Sepetteki yemekler getiriliyor...")
                val cevap = api.sepettekiYemekleriGetir(kullanici_adi)
                Log.d("YemeklerViewModel", "API Cevabı: success=${cevap.success}, yemek sayısı=${cevap.sepet_yemekler?.size}")
                sepetYemeklerListesi.value = cevap.sepet_yemekler ?: emptyList()
                Log.d("YemeklerViewModel", "Sepetteki yemekler başarıyla getirildi: ${cevap.sepet_yemekler?.size ?: 0} adet")
            } catch (e: Exception) {
                Log.e("YemeklerViewModel", "Sepetteki yemekler getirilirken hata: ${e.message}")
                hataMesaji.value = e.localizedMessage ?: "Sepetteki yemekler getirilirken hata oluştu"
            }
        }
    }
}