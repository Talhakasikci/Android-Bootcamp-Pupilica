package com.talhakasikci.odev2

fun main() {

    val functions = Functions()

    println("Fahrenheit: ${functions.convertToFahrenheit(25.0)}")
    println("Perimeter: ${functions.calculatePerimeter(5.0, 10.0)}")
    println("Factoriel: ${functions.factoriel(5)}")
    println("Letter A Count: ${functions.findLetterA("Talha Kasikci")}")
    println("Interior Angles: ${functions.interiorAngles(5)}")
    println("Salary: ${functions.salary(20)}")
    println("price: ${functions.kota(70)}")


}