package com.talhakasikci.ders2.object_oriented_programming

fun main() {
    val f = Functions()

    f.greeting1("talha")
    val greeting2 = f.greeting2("talha")
    println("return: $greeting2")

    f.toplam(5,6)
    f.toplam(4.3,2.3)
    f.toplam(4,5,7)
}