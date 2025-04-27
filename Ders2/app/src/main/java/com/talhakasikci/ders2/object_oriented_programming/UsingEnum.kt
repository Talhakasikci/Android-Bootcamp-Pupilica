package com.talhakasikci.ders2.object_oriented_programming

fun main() {
    calculatePayment(KonserveBoyut.LARGE,21)
}

fun calculatePayment(size:KonserveBoyut,pieces:Int){
    when(size){
        KonserveBoyut.SMALL ->{
            println("Küçük boyut için ödeme: ${pieces*5}")
        }
        KonserveBoyut.MEDIUM->{
            println("Orta boyut için ödeme: ${pieces*10}")
        }
        KonserveBoyut.LARGE->{
            println("Büyük boyut için ödeme: ${pieces*15}")
        }

    }
}