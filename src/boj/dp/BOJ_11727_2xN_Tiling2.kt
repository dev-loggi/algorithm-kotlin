package boj.dp

import boj.BOJSolution

/**
 * [BOJ 11727] 2×n 타일링 2
 * https://www.acmicpc.net/problem/11727
 *
 * */
class BOJ_11727_2xN_Tiling2 : BOJSolution(info(), testCase()) {

    private val dp = IntArray(1001).apply {
        this[1] = 1
        this[2] = 3
    }
    private var start = 3

    override fun runEachSolution() {
        val N = readln().toInt() // (1 ≤ N ≤ 1,000)

        println(solution(N))
    }

    private fun solution(N: Int): Int {
        for (i in start..N) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007
        }

        if (start < N) {
            start = N + 1
        }

        return dp[N]
    }
}

private fun info() = BOJSolution.Info(
    no = 11727,
    title = "2×n 타일링 2",
    category = listOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/11727"
)

private fun testCase() = listOf(
    BOJSolution.TestCase(
        input = "2",
        output = "3",
    ),
    BOJSolution.TestCase(
        input = "8",
        output = "171",
    ),
    BOJSolution.TestCase(
        input = "12",
        output = "2731",
    ),
)