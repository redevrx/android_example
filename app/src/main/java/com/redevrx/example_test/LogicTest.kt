package com.redevrx.example_test

fun main() {
    testOne()
}


fun testOne(){
    val rows = 10 // Number of rows in the pyramid
    var pattern = ""

    for (i in 1..rows) {
        pattern += "X" // Append an 'X' to the pattern

        // Print the pattern for the current row
        println(pattern)
    }
}

fun testTwo(){
    val rows = 10 // Number of rows in the pyramid
    var pattern = ""

    for (i in 1..rows) {
        // Print the leading zeros
        for (j in 1..rows - i) {
            pattern += "0"
        }

        // Print the 'X's
        for (j in 1..i) {
            pattern += "X"
        }

        // Print the pattern for the current row
        println(pattern)

        // Reset the pattern for the next row
        pattern = ""
    }
}

fun testTree(){
    val rows = 5 // Number of rows in the pyramid

    for (i in 0 until rows) {
        for (j in 0 until 2 * rows - 1) {
            if (j >= rows - 1 - i && j <= rows - 1 + i) {
                print("X")
            } else {
                print("0")
            }
        }
        println()
    }
}