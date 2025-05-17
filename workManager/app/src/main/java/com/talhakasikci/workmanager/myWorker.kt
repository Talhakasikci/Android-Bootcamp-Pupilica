package com.talhakasikci.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class myWorker(context:Context,workerParameters: WorkerParameters) :Worker(context,workerParameters){
    override fun doWork(): Result {
        val toplam = 10*20
        Log.e("arkaplan","$toplam")
        return Result.success()
    }



}