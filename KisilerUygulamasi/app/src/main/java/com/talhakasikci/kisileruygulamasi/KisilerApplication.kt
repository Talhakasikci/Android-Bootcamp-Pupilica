package com.talhakasikci.kisileruygulamasi

import android.app.Application
import com.talhakasikci.kisileruygulamasi.room.Veritabani

class KisilerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize database
        Veritabani.veritabaniErisim(this)
    }
} 