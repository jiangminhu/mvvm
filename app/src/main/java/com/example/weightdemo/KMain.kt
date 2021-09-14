package com.example.weightdemo


fun main() {
//  run()
    val  a : Int=0
    val  b : Int=1

   println( AAAAA().javaClass.hashCode())
   println( AAAAA().javaClass.hashCode())
}

fun  run(){
    val run:()->Unit={
        println("Run  run  run!")
    }
    println("---------------------->")
    Runnable { run() }.run()
}


class  AAAAA{
    var name:String? = "null"
    var name1:String? = null
    var name2:String? = null
}


