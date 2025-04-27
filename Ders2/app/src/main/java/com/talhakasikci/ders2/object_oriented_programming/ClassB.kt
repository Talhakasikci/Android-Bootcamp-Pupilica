package com.talhakasikci.ders2.object_oriented_programming

class ClassB: MyInterface {
    override var degisken: Int = 100

    override fun metod1() {
        println("metod1 calisti :degisken = $degisken")
    }

    override fun metod2(): String {
        return "metod2 calisti :degisken = $degisken"
    }


}