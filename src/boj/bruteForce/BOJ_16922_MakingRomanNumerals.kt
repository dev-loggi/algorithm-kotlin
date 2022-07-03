package boj.bruteForce

import boj.BOJSolution

class BOJ_16922_MakingRomanNumerals : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val N = readLine()!!.toInt()

        solution(N)
    }

    fun solution(N: Int) {
        val answer = intArrayOf(1, 5, 10, 50)
            .homoCombination(N)
            .map { it.sum() }
            .distinct()
            .size

        println(answer)
    }

    fun IntArray.homoCombination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}

private val _info = BOJSolution.Info(
    no = 16922,
    title = "로마 숫자 만들기",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/16922"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "1",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "2",
        output = "10"
    ),
    BOJSolution.TestCase(
        input = "10",
        output = "244"
    ),
)