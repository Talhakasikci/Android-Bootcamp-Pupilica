package com.talhakasikci.workmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.talhakasikci.workmanager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonYap.setOnClickListener {
            val calismaKosulu = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val myWorkRequest = OneTimeWorkRequestBuilder<myWorkerBildirim>()
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setConstraints(calismaKosulu)
                .build()
            WorkManager.getInstance(this).enqueue(myWorkRequest)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id)
                .observe(this) { it ->
                    val durum = it?.state?.name
                    Log.e("arkaplan", "$durum")
                }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // İzin iste
            }
        }
    }
}