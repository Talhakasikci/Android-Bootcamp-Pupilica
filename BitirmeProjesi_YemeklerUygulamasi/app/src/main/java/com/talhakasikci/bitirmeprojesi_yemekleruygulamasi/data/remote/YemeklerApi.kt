package com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.remote

import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.YemekCevap
import com.talhakasikci.bitirmeprojesi_yemekleruygulamasi.data.models.sepet_cevap
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Query

interface YemeklerApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun tumYemekleriGetir(): YemekCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    )

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullanici_adi: String
    ): sepet_cevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: Int,
        @Field("kullanici_adi") kullanici_adi: String)
}