package com.example.calculators

import kotlin.math.pow

private val TAG = "Utility"

class Utility() {

}
fun equal(value1: Double, value2: Double, operator: Char): String {
    var result: Double = 0.0
    try {
        when (operator) {
            '+' -> result = value1 + value2
            '-' -> result = value1 - value2
            '/' -> result = value1 / value2
            '*' -> result = value1 * value2
            '^'->result= value1.pow(value2)
        }
        return checkLongOrDouble(result)


    } catch (e: Exception) {
        return "Error"
    }
}
     fun checkLongOrDouble(result: Double):String {
        val returnResult = result.toString()
        val index = returnResult.indexOf('.')
        val afterPoint = returnResult.substring(index + 1)
        if (afterPoint.toLong() == "0".toLong())
            return result.toInt().toString()
        return returnResult
    }