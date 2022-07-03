package boj.bruteForce

import boj.BOJSolution

/**
 * 16968
 * 차량 번호판1
 * https://www.acmicpc.net/problem/16968
 * */
class BOJ_16968_LicensePlate1 : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
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

private val _info = BOJSolution.Info(
    no = 16968,
    title = "차량 번호판1",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/16968"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "dd",
        output = "90"
    ),
    BOJSolution.TestCase(
        input = "cc",
        output = "650"
    ),
    BOJSolution.TestCase(
        input = "dcdd",
        output = "23400"
    ),
)