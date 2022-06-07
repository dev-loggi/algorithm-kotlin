package programmers.practice.level2

import common.Solution
import kotlin.math.*


/**
 * 카펫
 * 완전 탐색
 * https://programmers.co.kr/learn/courses/30/lessons/42842?language=kotlin
 * */
class Carpet : Solution {

    override fun execute() {
        var answer: IntArray

        answer = bestSolution(10, 2)
        println("answer=${answer.contentToString()}") // 4, 3

        answer = bestSolution(8, 1)
        println("answer=${answer.contentToString()}") // 3, 3

        answer = bestSolution(24, 24)
        println("answer=${answer.contentToString()}") // 8, 6
    }

    fun bestSolution(brown: Int, yellow: Int): IntArray {
        // b + y = w * h
        // b = 2(w + h) - 4
        // y = (w - 2)(h - 2)

        return (brown + yellow).divisors()
            .find {
                val w = it.second
                val h = it.first
                yellow == (w - 2) * (h - 2) && brown == 2 * (w + h) - 4
            }!!.toList().asReversed().toIntArray()
    }

    private fun Int.divisors(): List<Pair<Int, Int>> {
        val n = this
        val result = mutableListOf<Pair<Int, Int>>()

        for (i in 1..sqrt(n.toDouble()).toInt() + 1) {
            if (n % i == 0) {
                result.add(i to n / i)
            }
        }
        return result
    }

    fun solution(brown: Int, yellow: Int): IntArray {
        var width = 0
        var height = 0

        width = ((2 + brown / 2.0 + sqrt((2 + brown / 2.0).pow(2) - 4 * (brown + yellow))) / 2).toInt()
        height = 2 + brown / 2 - width

        return intArrayOf(width, height)
    }

    fun solution2(brown: Int, yellow: Int): IntArray {
        return (1..yellow)
            .filter { yellow % it == 0 }
            .first { brown == (yellow / it * 2) + (it * 2) + 4 }
            .let { intArrayOf(yellow / it + 2, it + 2) }
    }

}