package com.talhakasikci.ders2.object_oriented_programming.Inheritance

class Cat:Mammal() {
    fun meow(){
        println("Meow")
    }

    override fun birth() {
        println( "*override* Cat is giving birth")
    }
}