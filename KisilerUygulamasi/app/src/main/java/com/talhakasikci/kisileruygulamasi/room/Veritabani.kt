package com.talhakasikci.kisileruygulamasi.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.talhakasikci.kisileruygulamasi.data.Entity.Kisiler

@Database(entities = [Kisiler::class], version = 1)
abstract class Veritabani : RoomDatabase() {
    abstract fun getKisilerDao(): KisilerDao

    companion object {
        var INSTANCE: Veritabani? = null

        fun veritabaniErisim(context: Context): Veritabani? {
            if (INSTANCE == null) {
                synchronized(Veritabani::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Veritabani::class.java,
                        "kisiler.sqlite"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
} 