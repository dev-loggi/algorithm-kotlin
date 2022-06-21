package boj.bruteForce

import common.Solution
import kotlin.math.min

/**
 * 16971
 * 양념 반 후라이드 반
 * https://www.acmicpc.net/problem/16917
 * */
class BOJ_16971_HalfSeasonedHalfFried : Solution {

    override fun execute() {
        repeat(3) { main() }
    }

    fun main() {
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
/*
[case1]
1500 2000 1600 3 2
[case1] - answer
7900

[case2]
1500 2000 1900 3 2
[case2] - answer
8500

[case3]
1500 2000 500 90000 100000
[case3] - answer
100000000

* */