package boj.unsolved

import boj.BOJSolution

/**
 * 2745
 * 진법 변환
 * https://www.acmicpc.net/problem/2745
 * */
class BOJ_2745_BaseConversion : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val input = readLine()!!.split(" ")
        val N = input[0]
        val B = input[1].toInt()

        solution(N, B)
    }

    fun solution(N: String, B: Int) {
        N.uppercase().reversed()
            .foldIndexed(0L) { idx, acc, n ->
                val v = if (n.isDigit()) n.digitToInt() else n.code - 55
                acc + v * B.pow(idx)
            }
            .let { println(it) }
    }

    fun solution2(N: String, B: Int) {
        println(N.toInt(B))
    }

    fun Int.pow(n: Int): Long {
        var acc = 1L
        for (i in 0 until n) acc *= this
        return acc
    }
}

private fun info() = BOJSolution.Info(
    no = 2745,
    title = "진법 변환",
    category = listOf(BOJSolution.MATERIALIZATION),
    problemUrl = "https://www.acmicpc.net/problem/2745"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "ZZZZZ 36",
        output = "60466175"
    )
)