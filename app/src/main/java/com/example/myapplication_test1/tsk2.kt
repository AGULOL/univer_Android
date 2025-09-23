package com.example.myapplication_test1

class NumberListProcessor(private val numbers: List<Int>) {

    fun printEvenNumbers() {
        val evenNumbers = numbers.filter { it % 2 == 0 }
        println("Even numbers: $evenNumbers")
    }
}

class FactorialCalculator(private val number: Int) {

    fun calculateFactorial(): Long {
        var factorial: Long = 1
        for (i in 1..number) {
            factorial *= i
        }
        return factorial
    }

    fun printFactorial() {
        val result = calculateFactorial()
        println("Factorial $number: $result")
    }
}

class Rectangle(val width: Double, val height: Double) {

    fun area(): Double {
        return width * height
    }

    fun printArea() {
        println("Area: ${area()}")
    }
}

class StringReverser(private val text: String) {

    fun reverse(): String {
        return text.reversed()
    }

    fun printReversedString() {
        val reversedText = reverse()
        println("Original text: $text")
        println("Reversed String: $reversedText")
    }
}

fun main() {
    val numberListProcessor = NumberListProcessor(listOf(1, 3, 7, 15, 67, 102))
    numberListProcessor.printEvenNumbers()

    val numberListProcessor2 = NumberListProcessor(listOf(3, 7, 15, 67, 102))
    numberListProcessor2.printEvenNumbers()


    val factorialCalculator = FactorialCalculator(9)
    factorialCalculator.printFactorial()

    val rectangle = Rectangle(5.0, 10.0)
    rectangle.printArea()

    val stringReverser = StringReverser("kotlin")
    stringReverser.printReversedString()
}
