package boj.bruteForce

import boj.BOJSolution

/**
 * 16938
 * 캠프 준비
 * https://www.acmicpc.net/problem/16938
 * 완전 탐색
 * */
class BOJ_16938_CampReady : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // N개의 문제
        val L = input[1].toInt() // L <= 출제할 문제 난이도의 합
        val R = input[2].toInt() // 출제할 문제 난이도의 합 <= R
        val X = input[3].toInt() // X <= 가장 어려운 문제 난이도 - 가장 쉬운 문제 난이도
        val exam = readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()

        solution(N, L, R, X, exam)
    }

    fun solution(N: Int, L: Int, R: Int, X: Int, exam: IntArray) {
        val numberOfCases = (2..N)
            .map { n -> exam.combination(n) }
            .flatten()
            .filter { ex ->
                ex.sumOf { it } in L..R && X <= ex.maxOf { it } - ex.minOf { it }
            }
            .size

        println(numberOfCases)
    }

    fun IntArray.combination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i + 1, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }

}

private fun info() = BOJSolution.Info(
    no = 16938,
    title = "캠프 준비",
    category = listOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/16938"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input =
        "3 5 6 1\n" +
        "1 2 3",
        output = "2"
    ),
    BOJSolution.TestCase(
        input =
        "4 40 50 10\n" +
        "10 20 30 25",
        output = "2"
    ),
    BOJSolution.TestCase(
        input =
        "5 25 35 10\n" +
        "10 10 20 10 20",
        output = "6"
    ),
)