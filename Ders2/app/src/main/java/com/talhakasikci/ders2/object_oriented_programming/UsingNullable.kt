package com.talhakasikci.ders2.object_oriented_programming

fun main() {
    //bir verinin null gelip gelmeyecegini bildigimizde

    var message:String? = null
    //? null gelebilir demek

    //message = "merhaba"
    //println(message?.uppercase()) //?. null gelebilir demek

    //yöntem 2
    //!!    //!! null gelmeyecek demek
    //message = "merhaba"
    //println(message!!.uppercase()) //!! null gelmeyecek demek

    //yöntem 3
    //null kontrol
    if(message != null){
        println(message.uppercase())
    }else{
        println("message null")
    }

    message?.let {
        println(message.uppercase())
    }

}