package boj.bruteForce

import boj.BOJSolution

/**
 * 16943
 * 숫자 재배치
 * https://www.acmicpc.net/problem/16943
 * 완전 탐색
 * */
class BOJ_16943_NumberReplacement : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val A = input[0].toInt()
        val B = input[1].toInt()

        solution(A, B)
    }

    fun solution(A: Int, B: Int) {
        val lengthA = A.toString().length

        val maxNumber = A.toString()
            .permutation(lengthA)
            .asSequence()
            .map { it.joinToString("") }
            .filterNot { it.startsWith('0') }
            .map { it.toInt() }
            .filter { it < B }
            .maxOfOrNull { it } ?: -1

        println(maxNumber)
    }

    fun String.permutation(r: Int): List<CharArray> {
        val result = mutableListOf<CharArray>()

        fun recursive(depth: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list.map { this[it] }.toCharArray())
                return
            }

            for (i in indices) {
                if (list.contains(i)) continue

                val newList = list.toMutableList()
                    .also { it.add(i) }

                recursive(depth + 1, newList)
            }
        }

        recursive(0, emptyList())
        return result
    }
}

private val _info = BOJSolution.Info(
    no = 16943,
    title = "숫자 재배치",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/16943"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "1234 3456",
        output = "3421"
    ),
    BOJSolution.TestCase(
        input = "1000 5",
        output = "-1"
    ),
    BOJSolution.TestCase(
        input = "789 123",
        output = "-1"
    ),
)