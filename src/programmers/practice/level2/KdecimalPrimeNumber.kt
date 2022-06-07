package programmers.practice.level2

import common.Solution
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * k진수에서 소수 개수 구하기
 * https://programmers.co.kr/learn/courses/30/lessons/92335
 * */
class KdecimalPrimeNumber : Solution {

    override fun execute() {
        solution(2, 3)
            .let { println("answer=$it") }
        solution(437674, 3)
            .let { println("answer=$it") } // 3
        solution(110011, 10)
            .let { println("answer=$it") } // 2
    }

    fun solution(n: Int, k: Int): Int {
        return n
            .toDecimalOf(k)
            //.toString(k)
            .also { println("$n -> $it") }
            .split("0")
            .filter { it.toLongOrNull()?.isPrimeNumber() == true }
            .also { println(it) }
            .size
    }

    fun Long.isPrimeNumber(): Boolean {
        if (this < 2) return false

        for (i in 2..sqrt(this.toDouble()).toInt()) {
            if (this % i == 0L)
                return false
        }

        return true
    }

    fun Int.toDecimalOf(k: Int): String {
        var result = ""
        var temp = this

        while (temp > 0) {
            result = "${temp % k}$result"
            temp /= k
        }

        return result
    }
}