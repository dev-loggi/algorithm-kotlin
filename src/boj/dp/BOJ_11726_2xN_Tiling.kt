package boj.dp

import boj.BOJSolution

/**
 * 11726
 * 2×n 타일링
 *
 * https://www.acmicpc.net/problem/11726
 * DP
 * */
class BOJ_11726_2xN_Tiling : BOJSolution(info(), testCase()) {

    private var m = 3
    private val dp = IntArray(1001).apply {
        this[1] = 1
        this[2] = 2
    }

    override fun runEachSolution() {
        val N = readln().toInt() // (1 ≤ N ≤ 1,000)

        for (n in m..N) {
            dp[n] = (dp[n - 1] + dp[n - 2]) % 10_007
        }

        if (m <= N)
            m = N + 1

        println(dp[N])
    }
}

private fun info() = BOJSolution.Info(
    no = 11726,
    title = "2xN 타일링",
    category = listOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/11726",
)

private fun testCase() = listOf(
    BOJSolution.TestCase(
        input = "2",
        output = "2",
    ),
    BOJSolution.TestCase(
        input = "9",
        output = "55",
    ),

    BOJSolution.TestCase(
        input = "1",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "1",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "1",
        output = "1"
    ),
)