package com.talhakasikci.ders2.object_oriented_programming.Inheritance

class Dog :Mammal() {
    fun bark(){
        println("Woof")
    }

    //override
    override fun eat(){
        println("*override* Dog is eating dog food")
    }
}