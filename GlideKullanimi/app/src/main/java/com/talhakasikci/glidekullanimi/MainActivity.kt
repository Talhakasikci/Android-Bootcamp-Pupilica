package com.talhakasikci.glidekullanimi

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.talhakasikci.glidekullanimi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val yemekler = listOf(
            "izgarasomon.png",
            "izgaratavuk.png",
            "kofte.png",
            "lazanya.png",
            "pizza.png"
        )

        val tatlilar = listOf(
            "baklava.png",
            "kadayif.png",
            "tiramisu.png",
            "sutlac.png"
        )
        val icecekler = listOf(
            "ayran.png",
            "fanta.png",
            "kahve.png",
            "su.png"
        )

        binding.yemekOlustur.setOnClickListener {
            val randomYemek = yemekler.random()
            resmiGoster(randomYemek,binding.YemeklerIDResim)
            val randomTatli = tatlilar.random()
            resmiGoster(randomTatli,binding.TatlLarIV)
            val randomIcecek = icecekler.random()
            resmiGoster(randomIcecek,binding.IceceklerIV)
        }
    }

    fun resmiGoster(resimAdi: String,imageView:ImageView) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$resimAdi"
        Glide.with(this)
            .load(url)
            .override(300, 300)
            .into(imageView)
    }
}