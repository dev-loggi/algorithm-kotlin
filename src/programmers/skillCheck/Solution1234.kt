package programmers.skillCheck

import common.Solution
import java.math.BigInteger
import kotlin.math.max


class Solution1234 : Solution {

    override fun execute() {
        var answer = 0

        println("answer=${solution(3)}\n") // 2
        println("answer=${solution(5)}\n\n\n") // 5

        for (i in 0..100) {
            println("fib[$i]: ${fibonacci(i)}")
        }
    }

    //private val fibonacciArray = Array<BigInteger>(100000) { BigInteger.ZERO }

    fun solution(n: Int): Int =
        fibonacci(n).remainder(BigInteger.valueOf(1234567)).toInt()

    private fun fibonacci(n: Int): BigInteger {
        val fibonacciArray = Array<BigInteger>(max(2, n + 1)) { BigInteger.ZERO }
        fibonacciArray[0] = BigInteger.ZERO
        fibonacciArray[1] = BigInteger.ONE

        for (i in 2 .. n) {
            fibonacciArray[i] = fibonacciArray[i - 1] + fibonacciArray[i - 2]
        }

        return fibonacciArray[n]
    }
}