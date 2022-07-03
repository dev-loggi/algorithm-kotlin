package boj.bruteForce

import boj.BOJSolution
import kotlin.math.abs

/**
 * 17088
 * 등차수열 변환
 * https://www.acmicpc.net/problem/17088
 * 완전 탐색
 * */
class BOJ_17088_ArithmeticSequenceTransform : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val N = readLine()!!.toInt() // 수열의 크기
        val A = readLine()!!.split(" ") // 수열
            .map { it.toInt() }
            .toIntArray()

        solution(N, A)
    }

    fun solution(N: Int, A: IntArray) {
        if (N <= 2) {
            println(0)
            return
        }

        val answer = mutableListOf<Int>()
        val operators = intArrayOf(1, 0, -1).homoPermutation(2)

        for (op in operators) {
            val B = IntArray(N) { i -> A[i] }
            B[0] += op[0]
            B[1] += op[1]

            // b1, b2가 나올 수 있는 9가지 경우의 수에 따라 공차(diff)가 결정됨
            val diff = B[1] - B[0]
            for (i in 1 until B.size - 1) {
                if (abs(diff - (B[i + 1] - B[i])) > 1)
                    break

                B[i + 1] = B[i] + diff
            }

            if (B.isArithmeticSequence()) {
                val count = A.foldIndexed(0) { idx, acc, n ->
                    if (A[idx] == B[idx]) acc else acc + 1
                }

                answer.add(count)
            }
        }

        println(answer.minOfOrNull { it } ?: -1)
    }

    private fun IntArray.isArithmeticSequence(): Boolean {
        if (size < 3) return true
        val diff = this[1] - this[0]
        for (i in 1 until size - 1) {
            if (this[i + 1] - this[i] != diff)
                return false
        }
        return true
    }

    fun IntArray.homoPermutation(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in indices) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }
                recursive(depth + 1, newList)
            }
        }

        recursive(0, emptyList())
        return result
    }
}

private val _info = BOJSolution.Info(
    no = 17088,
    title = "등차수열 변환",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/17088"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "4\n" +
                "24 21 14 10",
        output = "3"
    ),
    BOJSolution.TestCase(
        input = "2\n" +
                "5 5",
        output = "0"
    ),
    BOJSolution.TestCase(
        input = "3\n" +
                "14 5 1",
        output = "-1"
    ),
    BOJSolution.TestCase(
        input = "5\n" +
                "1 3 6 9 12",
        output = "1"
    ),
)