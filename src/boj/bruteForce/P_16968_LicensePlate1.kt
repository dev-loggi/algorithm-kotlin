package boj.bruteForce

import common.Solution

/**
 * 차량 번호판1
 * https://www.acmicpc.net/problem/16968
 * */
class P_16968_LicensePlate1 : Solution {

    override fun execute() {
        for (i in 0 until 3) main()
    }

    fun main() {
        val formats = readLine()?.map { it } ?: return

        val map = mapOf(
            'c' to 26,
            'd' to 10
        )

        var answer = map[formats[0]]!!

        for (i in 1 until formats.size) {
            val front = formats[i - 1]
            val format = formats[i]

            answer *= if (front != format) map[format]!! else map[format]!! - 1
        }

        println(answer)
    }
}