package boj.bruteForce

import boj.BOJSolution
import boj.BOJSolution.Companion.BRUTE_FORCE

/**
 * 16936
 * 나3곱2
 * https://www.acmicpc.net/problem/16936
 * 완전 탐색
 * */
class BOJ_16936_Division3Multiplication2 : BOJSolution(info(), testCases()) {

    override fun main() {
        val N = readLine()!!.toInt()
        val sequence = readLine()!!.split(" ")
            .map { it.toLong() }
            .toLongArray()

        solution(N, sequence)
    }

    fun solution(N: Int, sequence: LongArray) {
        var newSequence: LongArray? = null

        fun next(i: Int, visited: BooleanArray, list: List<Long>) {
            if (newSequence != null)
                return
            if (list.size == N) {
                newSequence = list.toLongArray()
                return
            }

            visited[i] = true
            for (j in sequence.indices) {
                if (visited[j]) continue

                val n = sequence[i]
                val m = sequence[j]
                if ((n * 2 == m) || (n % 3 == 0L && n / 3 == m)) {
                    next(j, visited, list.toMutableList().also { it.add(m) })
                }
            }
        }

        for (i in sequence.indices) {
            if (newSequence != null)
                break

            next(i, BooleanArray(N), listOf(sequence[i]))
        }

        println(newSequence?.joinToString(" "))
    }
}

private fun info() = BOJSolution.Info(
    no = 16936,
    title = "나3곱2",
    category = arrayOf(BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/16936"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input =
        "6\n" +
        "4 8 6 3 12 9",
        output =
        "9 3 6 12 4 8"
    ),
    BOJSolution.TestCase(
        input =
        "4\n" +
        "42 28 84 126",
        output =
        "126 42 84 28"
    ),
)