package com.talhakasikci.ders2.standart_programlama

fun main(){

    val yas = 20
    println(yas>18)
    if(yas>=18){
        println("reşit")
    }else if(yas<18 && yas>0){
        println("reşit değil")

    }else{
        println("geçersiz yaş")
    }

    val un = "admin"
    val password = "1234"
    val number = 10

    if(un == "admin" && password == "1234"){
        println("giriş başarılı")
    }else{
        println("giriş başarısız")
    }

    if(number ==9||number==10){
        println("sayı 9 veya 10")
    }else{
        println("sayı 9 veya 10 değil")
    }

    val number2 = 10
    when(number2){
      1 -> println("sayı 1")
      2 -> println("sayı 2")
      else -> println("sayı 1 veya 2 değil")
    }
}