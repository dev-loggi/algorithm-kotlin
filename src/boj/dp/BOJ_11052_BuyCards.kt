package boj.dp

import boj.BOJSolution
import kotlin.math.max

/**
 * [BOJ 11052] 카드 구매하기
 * https://www.acmicpc.net/problem/11052
 * */
class BOJ_11052_BuyCards : BOJSolution(info(), testCase()) {

    override fun main() {
        val N = readln().toInt() // 카드의 개수 (1 ≤ N ≤ 1,000)
        val cards = intArrayOf(0) + readln().split(" ")
            .map { it.toInt() } // (1 ≤ Pi ≤ 10,000)
            .toIntArray()

        println(solutionByDFS2(N, cards))
    }

    private fun solutionByDFS2(N: Int, cards: IntArray): Long {
        val dp = Array(N + 1) { LongArray(N + 1) }

        fun dfs(n: Int, sum: Long): Long {
            if (n == N)
                return sum

            var max = 0L

            for (i in 1..(N - n)) {
                val sub = if (dp[n][i] != 0L) {
                    dp[n][i]
                } else {
                    dp[n][i] = dfs(n + i, sum + cards[i])
                    dp[n][i]
                }

                if (max < sub)
                    max = sub
            }

            return max
        }

        return dfs(0, 0)
    }

    /** 시간초과 */
    private fun solutionByDFS(N: Int, cards: IntArray): Long {

        fun dfs(n: Int, sum: Long): Long {
            if (n == N)
                return sum

            var max = 0L

            for (i in 1..(N - n)) {
                max = max(max, dfs(n + i, sum + cards[i]))
            }

            return max
        }

        return dfs(0, 0)
    }
}

private fun info() = BOJSolution.Info(
    no = 11052,
    title = "카드 구매하기",
    category = arrayOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/11052",
)

private fun testCase() = arrayOf(
    BOJSolution.TestCase(
        input = "4\n" +
                "1 5 6 7",
        output = "10",
    ),
    BOJSolution.TestCase(
        input = "5\n" +
                "10 9 8 7 6",
        output = "50",
    ),
    BOJSolution.TestCase(
        input = "10\n" +
                "1 1 2 3 5 8 13 21 34 55",
        output = "55",
    ),
    BOJSolution.TestCase(
        input = "10\n" +
                "5 10 11 12 13 30 35 40 45 47",
        output = "50",
    ),
    BOJSolution.TestCase(
        input = "4\n" +
                "5 2 8 10",
        output = "20",
    ),
    BOJSolution.TestCase(
        input = "4\n" +
                "3 5 15 16",
        output = "18",
    ),
)