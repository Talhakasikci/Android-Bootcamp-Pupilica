package com.talhakasikci.odev6

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.talhakasikci.odev6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var headerAdapter: HeaderAdapter
    private lateinit var subImageAdapter: SubImageAdapter
    private lateinit var subtitledMovieadapter: SubImageAdapter
    private lateinit var scienceMoviesAdapter: SubImageAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPosition = 0
    private val scrollRunnable = object : Runnable {
        override fun run() {
            if (currentPosition < headerAdapter.itemCount - 1) {
                currentPosition++
            } else {
                currentPosition = 0
            }
            binding.headerRecyclerView.smoothScrollToPosition(currentPosition)
            handler.postDelayed(this, 2000) // 2 saniyede bir kaydır
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val images = listOf(
            R.drawable.bones,
            R.drawable.gran_turismo,
            R.drawable.lost,
            R.drawable.nautilus,
            R.drawable.working_man,
            R.drawable.yirmidort
        )

        val images2 = listOf(
            R.drawable.yirmidort,
            R.drawable.working_man,
            R.drawable.gran_turismo,
            R.drawable.lost,
            R.drawable.bones,
            R.drawable.nautilus,
        )

        val scienceMovies = listOf(
            R.drawable.interstellar,
            R.drawable.blade_runner,
            R.drawable.passengers,
            R.drawable.inception
        )
        
        headerAdapter = HeaderAdapter(images)
        binding.headerRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.headerRecyclerView.adapter = headerAdapter

        // Alt resimler için adapter
        subImageAdapter = SubImageAdapter(images)
        binding.subImageRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.subImageRecyclerView.adapter = subImageAdapter

        //diger
        subtitledMovieadapter = SubImageAdapter(images2)
        binding.subtitleMoviesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.subtitleMoviesRV.adapter = subtitledMovieadapter

        //science
        scienceMoviesAdapter = SubImageAdapter(scienceMovies)
        binding.scienceMoviesRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.scienceMoviesRV.adapter = scienceMoviesAdapter

        // SnapHelper ekle
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.headerRecyclerView)

        // Otomatik kaydırmayı başlat
        startAutoScroll()
    }

    private fun startAutoScroll() {
        handler.removeCallbacks(scrollRunnable) // Önceki çağrıları temizle
        handler.postDelayed(scrollRunnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(scrollRunnable)
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
    }
}