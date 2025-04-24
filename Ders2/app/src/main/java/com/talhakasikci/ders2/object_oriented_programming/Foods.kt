package com.talhakasikci.ders2.object_oriented_programming

class Foods(var id:Int, var name:String, var price:Double,var kcal:Int) {
    //constructor - init metodu
    init {//nesne oluştuktan sonra çalışan metod
        println("*************nesne oluşturuldu*************")
    }


    fun printInfo(){
        println("id: ${id}")
        println("name: ${name}")
        println("price: ${price}")
        println("kcal: ${kcal}")
        println("----------------------")
    }
}