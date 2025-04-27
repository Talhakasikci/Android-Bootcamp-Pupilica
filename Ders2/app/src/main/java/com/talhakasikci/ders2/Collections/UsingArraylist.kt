package com.talhakasikci.ders2.Collections

fun main() {

    val fruits = ArrayList<String>()

    fruits.add("Apple")
    fruits.add("banana")
    fruits.add("kiwi")

    println(fruits)
    println("***************")
    fruits[1] = "Orange" // 1. indexteki elemanı değiştir
    println(fruits)
    println("***************")
    fruits.add(1,"Mango")//1. indexe yeni eleman ekle
    println("***************")
    println(fruits)
    println("***************")
    println("boyut : ${fruits.size}")
    println("***************")
    fruits.reverse()
    println(fruits)
    println("***************")
    fruits.sort()
    println(fruits)
    println("***************")
    for (i in fruits){
        println("index $i ")
    }
    println("***************")
    for((index,fruit) in fruits.withIndex() ){
        println("index $index : $fruit")
    }

    println("***************")

    fruits.removeAt(2)
    println(fruits)
    println("***************")

    fruits.clear()
    println(fruits)

    println("***************")
    val filmList = ArrayList<Films>()

    filmList.add(Films(1,"neşeli hayatlar",180))
    filmList.add(Films(2,"yarının ötesinde",120))
    filmList.add(Films(3,"yok musun",150))

    println(filmList)

    for(film in filmList){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("***************")
    }

    //sorting

    println("***************")

    println("fiyata göre artan")
    val sorted1 = filmList.sortedWith(compareBy{it.price})
    for(film in sorted1){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }

    println("fiyata göre azalan")
    val sorted2 = filmList.sortedWith(compareByDescending{it.price})
    for(film in sorted2){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }

    println("isime göre artan")
    val sorted3 = filmList.sortedWith((compareBy{it.name}))
    for(film in sorted3){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }
    println("isime göre azalan")
    val sorted4 = filmList.sortedWith(compareByDescending{it.name})
    for(film in sorted4){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }

    println("Filtreleme1")
    val filters1 = filmList.filter { it.price>100 && it.price<150 }
    for(film in filters1){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }
    println("Filtreleme1")
    val filters2 = filmList.filter { it.name.contains("a") }
    for (film in filters2){
        println("film id : ${film.id} \nfilm name : ${film.name} \nfilm price : ${film.price}")
        println("-----------------")
    }
     println("Hashset")





}