package com.talhakasikci.ders2.Collections

fun main() {
    //hashMap JSON veri modeline benzemektedir
    //key-value çiftleri ile çalışır
    //key'ler unique olmalıdır

    val iller = HashMap<Int,String>()

    iller.put(34,"İstanbul")
    iller.put(6,"Ankara")
    iller.put(35,"İzmir")
    iller.put(16,"Bursa")
    println("****************")
    println(iller)
    println("****************")
    iller[16] = "Yeni Bursa" //key'e göre güncelleme yapar
    println(iller)
    println("****************")
    val il = iller.get(6) //key'e göre değer alır
    println(il)
    println("****************")
    for((key,value) in iller){
        println("key: $key, value: $value")
    }
    println("****************")
    iller.remove(34)
    println(iller)
    println("****************")
    iller.clear()
    println(iller)
}