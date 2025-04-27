package com.talhakasikci.ders2

fun main() {
    //compile error
    //kodlama yaparken oluşur

    //val x = 10
    //x = 20 -> bu bir compile error

    //run time error
    //program çalışırken oluşur
    val x = 10
    val y = 0

    println("işlem {$x / $y = ${x/y}") // 0a bölme hatası
    println("işlem tamam")

    try {
        println("işlem {$x / $y = ${x/y}") // 0a bölme hatası -> try catch içinde yakalanır
        println("işlem tamam")
    }catch (e:Exception){
        e.printStackTrace()
    }
}