package com.example.myapplication_test1

class Box<T>(val data: T){
    fun getValue(): T {
        return data
    }

    fun<R> map(trans: (T) -> R): Box<R> {
        return Box(trans(data))
    }
}


fun main() {
    val box= Box<Int>(256)
    val strbox: Box<String> = box.map { it.toString() }
    val boolbox = box.map { it <256 }
    println("Box<Int>: ${box.getValue()}")
    println("Box<String>: ${strbox.getValue()}")
    println("Box<Bool>: ${boolbox.getValue()}")
}