package com.talhakasikci.odev5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.talhakasikci.odev5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentNumber = ""
    private var totalSum = 0
    private var isNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val display = binding.textDisplay

        val numberButtons = listOf(

            binding.button0,
            binding.button1,
            binding.button2,
            binding.button3,
            binding.button4,
            binding.button5,
            binding.button6,
            binding.button7,
            binding.button8,
            binding.button9

        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                val digit = button.text.toString()
                if (isNewNumber) {
                    currentNumber = digit
                    isNewNumber = false
                } else {
                    currentNumber += digit
                }
                display.text = currentNumber
            }
        }

        binding.buttonPlus.setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                val number = currentNumber.toIntOrNull()

                if (number != null && number <= Int.MAX_VALUE) {
                    totalSum += number
                    currentNumber = ""
                    isNewNumber = true
                    display.text = "$totalSum +"
                } else {
                    Toast.makeText(this, "Please enter a valid number (MAX value: ${Int.MAX_VALUE})", Toast.LENGTH_SHORT).show()
                    display.text = totalSum.toString()
                    currentNumber = ""
                    isNewNumber = true
                }
            }
        }

        binding.buttonEquals.setOnClickListener {
            if(currentNumber.isNotEmpty()){
                val number = currentNumber.toIntOrNull()

                if (number != null && number <= Int.MAX_VALUE) {
                    totalSum += number
                    display.text = totalSum.toString()
                    currentNumber = ""
                    isNewNumber = true
                } else {
                    Toast.makeText(this, "Please enter a valid number (MAX value: ${Int.MAX_VALUE})", Toast.LENGTH_SHORT).show()
                    display.text = totalSum.toString()
                    currentNumber = ""
                    isNewNumber = true
                }
            }

        }

        binding.buttonClear.setOnClickListener {
            totalSum = 0
            currentNumber = ""
            isNewNumber = true
            display.text = "0"
        }

    }

}