package com.talhakasikci.ders2.standart_programlama

fun main(){
    for(i in 1..10){//sırayla
        println("sayi $i")
    }

    for(i in 1..20 step 2){//ikişer atlayarak
        println("sayi $i")
    }

    for(i in 20 downTo 1 step 2){//tersten
        println("sayi $i")
    }

    var counter = 1

    while (counter <= 10){
        println("sayi $counter")
        counter++
    }

    while(counter>=-10){
        println("sayi $counter")
        counter -=2
    }

    for(i in 1..10){
        if(i == 3)
            continue
        if(i==7)
            break

        println("continue & break: $i")
    }
}