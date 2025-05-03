package com.talhakasikci.ders4_calismayapisi

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.talhakasikci.ders4_calismayapisi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MainToDetalButton.setOnClickListener {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("info","zürafalar çok uzun")
            startActivity(intent)
            finish()
        }


    }
}