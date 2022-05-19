package com.example.devguidenewandroidappdevelopment

import java.util.*

fun main(args: Array<String>) {
    println("Hello, world!")
    val i = 500_000
    val j = 5
    println(i * j)

    var age = 30
    age = 26
    println(age)

    val name = "new"
    println(name)

    val x = 1..10 step 2
    val y = 10 downTo 1 step 2

    val result = 2
    when (result) {
        0 -> println("No result!")
        in 1..10 -> println("Got result!")
        else -> println("a lot of result!")
    }

    if (result in 1..10) {
        println("Got result")
    } else {
        println("")
    }

    val pets = arrayOf("dog", "cat", "rat")
    for (pet in pets) {
        print("$pet ")
    }

    for ((index, pet) in pets.withIndex()) {
        println("Item at $index is $pet")
    }

    var bicycle = 0
    while (bicycle < 50) {
        bicycle++
    }

    do {
        bicycle--
    } while (bicycle > 50)

    println(bicycle)

    repeat(5) {
        println("Hello")
    }

    val myList = listOf("trumpet", "paino", "violin")
    println(myList)

    val myList1 = mutableListOf("trumpet", "paino", "violin")
    println(myList1)
    myList1.add("base")
    println(myList1)
    myList1.remove("paino")
    println(myList1)

    val myArray = arrayOf("dog", "cat", "rat")
    val myArray1 = arrayOf("dog1", "cat1", "rat1")
    val combined = myArray + myArray1
    println("======")
    println(Arrays.toString(myArray))
    println(Arrays.toString(combined))

    val myIntArray = intArrayOf(1, 2, 4)

    val a: String? = null
    a?.uppercase() // NPE

    val number: Int? = null
    var numberDec = number?.dec() ?: 0
/*    var numberDec: Int = 0
    if (number != null) {
       numberDec = number.dec()
    } else {
        numberDec = 0
    } */

    println(sum(1, 2))
    println(sum(1))
    println(sum(b = 5, a = 3))

    printSum(1, 3)

    println("Hello-World".removeDash())

    val sumFull: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    val sum = { x: Int, y: Int -> x + y }

    println(sumFull(5, 5))
    println(sum(4, 4))

    val enc1: (String) -> String = { s -> s.uppercase() }
    val enc2: (String) -> String = { s -> s.lowercase() }

    println(encodeMes("helloworld", enc1))
    println(encodeMes("HELLOWORLD", enc2))

    val shape = Shape()
    shape.draw()
}

 class Shape {
    val vertexCount: Int = 0

    open fun draw() {
        println("draw on shape")
    }
}

fun encodeMes(msg: String, encode: (String) -> String): String {
    return encode(msg)
}

fun oldremoveDash(str: String): String {
    return str.replace("-", "")
}

fun String.removeDash(): String {
    return this.replace("-", "")
}

fun sum(a: Int, b: Int = 1): Int {
    return a + b
}

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${sum(a, b)}")
}

