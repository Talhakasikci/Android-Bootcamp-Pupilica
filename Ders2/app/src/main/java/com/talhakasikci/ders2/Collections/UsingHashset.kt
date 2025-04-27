package com.talhakasikci.ders2.Collections

fun main() {
    //HASHSET - Bir veriden sadece bir tane
    val hashSet = HashSet<String>()//string yerine any koyarsak her türlü veri alır
    hashSet.add("elma")
    hashSet.add("armut")
    hashSet.add("muz")
    hashSet.add("elma") //bir tane elma ekle

    println(hashSet)
}