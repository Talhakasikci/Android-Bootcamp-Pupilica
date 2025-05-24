package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Yemek(
    val yemek_id: Int,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int
): Parcelable

@Parcelize
data class sepet_yemekler(
    val sepet_yemek_id:String,
    val yemek_adi:String,
    val yemek_resim_adi:String,
    val yemek_fiyat:String,
    val yemek_siparis_adet:String,
    val kullanici_adi:String
): Parcelable

data class YemekCevap(
    val yemekler: List<Yemek>?,
    val success: Int
)

data class sepet_cevap(
    val sepet_yemekler: List<sepet_yemekler>?,
    val success: Int
)
