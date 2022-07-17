package boj.bruteForce

import boj.BOJSolution
import kotlin.math.min

/**
 * 16971
 * 양념 반 후라이드 반
 * https://www.acmicpc.net/problem/16917
 * */
class BOJ_16971_HalfSeasonedHalfFried : BOJSolution(info(), testCases()) {

    override fun main() {
        val input = readLine()!!.split(" ")
        val A = input[0].toInt() // 양념 치킨 가격
        val B = input[1].toInt() // 후라이드 치킨 가격
        val C = input[2].toInt() // 반반 치킨 가격
        val X = input[3].toInt() // 양념 치킨 X마리
        val Y = input[4].toInt() // 후라이드 치킨 Y마리

        solution(A, B, C, X, Y)
    }

    fun solution(A: Int, B: Int, C: Int, X: Int, Y: Int) {
        var x = X
        var y = Y

        val price = if (A + B <= 2 * C) {
            A * x + B * y
        } else {
            var price = 0

            val minXY = min(x, y)
            price += minXY * 2 * C
            x -= minXY
            y -= minXY

            price += if (A <= 2 * C) A * x else 2 * C * x
            price += if (B <= 2 * C) B * y else 2 * C * y
            price
        }

        println(price)
    }
}

private fun info() = BOJSolution.Info(
    no = 16971,
    title = "양념 반 후라이드 반",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/16917"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "1500 2000 1600 3 2",
        output = "7900"
    ),
    BOJSolution.TestCase(
        input = "1500 2000 1900 3 2",
        output = "8500"
    ),
    BOJSolution.TestCase(
        input = "1500 2000 500 90000 100000",
        output = "100000000"
    ),
)