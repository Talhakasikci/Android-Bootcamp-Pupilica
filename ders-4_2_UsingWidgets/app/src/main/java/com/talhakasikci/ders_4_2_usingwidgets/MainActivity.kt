package com.talhakasikci.ders_4_2_usingwidgets

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.talhakasikci.ders_4_2_usingwidgets.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->

            if(isChecked){
                val selectedButton = findViewById<Button>(checkedId)// o an seçili olan buton

                val buttonWrite = selectedButton.text.toString()

                Log.e("Logs", "Selected Button: $buttonWrite")
            }


        }

        val ulkeler :ArrayList<String> = arrayListOf("Türkiye", "Almanya", "Fransa", "İtalya", "İspanya")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ulkeler)

        binding.autoCompleteTv.setAdapter(arrayAdapter)


    }
}