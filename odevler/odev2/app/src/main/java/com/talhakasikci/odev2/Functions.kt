package com.talhakasikci.odev2

class Functions {

    fun convertToFahrenheit(celcius:Double):Double{
        val fahrenheit = celcius*1.8 +32
        return fahrenheit
    }

    fun calculatePerimeter(length:Double, width:Double):Double{
        val perimeter = length * 2 + width * 2
        return perimeter
    }

    fun factoriel(number:Int):Int{
        if(number < 0){
            return -1
        }
        var result = 1
        for (i in 1..number){
            result *= i
        }

        return result
    }

    fun findLetterA(string:String):Int{
        var lengt = string.length
        var count = 0
        for (i in 0 until lengt){
            if(string[i] == 'a' || string[i] == 'A'){
                count++
            }
        }

        return count
    }

    fun interiorAngles(edge:Int):Int{
        val result = (edge - 2)*180
        return result
    }

    fun salary(workDay:Int):Double{
        val workHour = workDay*8
        var totalSalary = 0
        if(workHour>=160){
            val extraHour = workHour - 160
            val extraSalary = extraHour * 20
            val normalHour = workHour-extraHour
            val normalSalary = normalHour * 10
             totalSalary = extraSalary + normalSalary
        }else{
             totalSalary = workHour * 10
        }

        return totalSalary.toDouble()

    }

    fun kota(GB:Int):Int{

        var result  =0

        if(GB <=50){
            result = 0
        }else{
            result = 100 +(GB-50)*4
        }
        return result

    }


}