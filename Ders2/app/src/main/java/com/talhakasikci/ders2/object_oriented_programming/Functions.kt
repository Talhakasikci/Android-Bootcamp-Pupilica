package com.talhakasikci.ders2.object_oriented_programming

class Functions {

    //void fonksiyon
    fun greeting1(name:String){
        val result = "Hello $name"
        println(result)
    }

    //return type fonksiyon
    fun greeting2(name:String):String{
        val result = "Hello $name"

        return result
    }

    //overloading
    fun toplam(sayi1:Int,sayi2:Int){
    println("sayi: ${sayi1+sayi2}")
    }

    fun toplam(sayi1:Int,sayi2:Int,sayi3:Int){
        println("sayi: ${sayi1+sayi2+sayi3}")
    }
    fun toplam(sayi1:Double,sayi2:Double){
        println("sayi: ${sayi1+sayi2}")
    }

}