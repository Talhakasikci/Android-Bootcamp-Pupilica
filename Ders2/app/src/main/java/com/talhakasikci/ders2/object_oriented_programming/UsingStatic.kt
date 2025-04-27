package com.talhakasikci.ders2.object_oriented_programming

fun main() {
    val a = ClassA()

    //standart erişim(public)
    //println(a.x)
    //a.metod()

    //sanal obje-isimsiz-nameless
    //println(ClassA().x)//1.nesne oluşturuldu
    //ClassA().metod()//2. nesne oluşturuldu
    //yani bellekte çok fazla yer tutar performansı düşürür

    //companion object-Statik
    println(ClassA.x)
    ClassA.metod()
}