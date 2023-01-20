package boj.workbook.ses9928

import boj.BOJSolution

/**
 * BOJ 1238
 * 파티
 * https://www.acmicpc.net/problem/1238
 * */
class BOJ1238 : BOJSolution(info(), testCase()) {



    override fun runEachSolution() {
        // N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X
        val (N, M, X) = readln().split(" ").map { it.toInt() }
        val time = Array(N + 1) { IntArray(N + 1) }

    }


}

private fun info() = BOJSolution.Info(
    no = 1238,
    title = "파티",
    category = listOf(),
    problemUrl = "https://www.acmicpc.net/problem/1238",
)

private fun testCase() = listOf(
    BOJSolution.TestCase(
        input = """
            4 8 2
            1 2 4
            1 3 2
            1 4 7
            2 1 1
            2 3 5
            3 1 2
            3 4 4
            4 2 3
        """.trimIndent(),
        output = "10",
    ),
)