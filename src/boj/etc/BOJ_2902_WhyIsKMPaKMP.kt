package boj.etc

import boj.BOJSolution

class BOJ_2902_WhyIsKMPaKMP : BOJSolution(info(), testCases()) {

    override fun main() {
        println(solution(readLine()!!))
    }

    fun solution(str: String): String {
        return str.split("-").map { it[0] }.joinToString("")
    }
}

private fun info() = BOJSolution.Info(
    no = 2210,
    title = "숫자판 점프",
    category = arrayOf(),
    problemUrl = ""
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "Knuth-Morris-Pratt",
        output = "KMP"
    ),
    BOJSolution.TestCase(
        input = "Mirko-Slavko",
        output = "MS"
    ),
    BOJSolution.TestCase(
        input = "Pasko-Patak",
        output = "PP"
    ),
)