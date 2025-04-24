package com.talhakasikci.ders2.object_oriented_programming

fun main() {
    // Nesne oluşturma
    val food1 = Foods(1,"lahmacun",90.0,300)
    //değer okuma


    val food2 = Foods(2,"Tavuk Döner",120.0,400)
    food2.name = "Lüx et döner"

    food1.printInfo()
    food2.printInfo()
}