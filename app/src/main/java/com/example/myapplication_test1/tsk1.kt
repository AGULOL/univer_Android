package com.example.myapplication_test1

open class Payment(var amount: Double) {
    open fun process() = println("Processing payment of $amount")
}

class CreditCardPayment(amount: Double): Payment(amount){
    override fun process() {
        println("Paid $amount with credit card")
    }
}

class PaypalPayment(amount: Double): Payment(amount){
    override fun process() {
        println("Paid $amount wia PayPal")
    }
}

class BankTransferPayment(amount: Double): Payment(amount){
    override fun process() {
        println("Paid $amount wia bank transfer")
    }
}

fun makeTransaction(payment: Payment){
    payment.process()
}

fun main() {
    val payments = listOf(
        CreditCardPayment(50.0),
        PaypalPayment(25.50),
        BankTransferPayment(100.0),
        Payment(10.0)
    )

    payments.forEach { payment ->
        makeTransaction(payment)
    }
//    println("2345")
}