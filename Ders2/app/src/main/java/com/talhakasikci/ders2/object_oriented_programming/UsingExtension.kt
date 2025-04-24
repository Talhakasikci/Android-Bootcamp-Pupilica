package com.talhakasikci.ders2.object_oriented_programming

fun main() {

    //var olan sınıflara yeni fonksiyonlar ekleme
    val sonuc = 5 multiplyWithParam 3//infix ile bu şekilde yazılabilir
    val sonuc2 = 5.multiplyWithParam(3)//normal kullanım
    println(sonuc)

}

infix fun Int.multiplyWithParam(number:Int):Int{

    return this * number
}