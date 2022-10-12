package boj.dp

import java.util.ArrayDeque
import kotlin.math.min
import boj.BOJSolution

/**
 * [BOJ 9095] 1,2,3 더하기
 * https://www.acmicpc.net/problem/9095
 * */
class BOJ_9095_Plus123 : BOJSolution(info(), testCase()) {

    override fun main() {
        val T = readln().toInt()
        val answers = IntArray(T) {
            val N = readln().toInt() // (1 ≤ N ≤ 11)

            solutionByDP(N)
//            solutionByBFS(N)
//            solutionByDFS(N)
        }

        answers.forEach { println(it) }
    }

    private fun solutionByDP(N: Int): Int {
        val dp = IntArray(N + 1).apply {
            this[1] = 1
            this[2] = 2
            this[3] = 4
        }

        for (i in 4..N) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
        }

        return dp[N]
    }

    private fun solutionByBFS(N: Int): Int {
        var count = 0
        val queue = ArrayDeque<Int>()
        queue.offer(0)

        while (queue.isNotEmpty()) {
            val n = queue.poll()
            if (n == N) {
                count += 1
                continue
            }

            for (i in 1..min(3, N - n)) {
                queue.offer(n + i)
            }
        }

        return count
    }

    private fun solutionByDFS(N: Int): Int {
        var count = 0

        fun dfs(n: Int) {
            if (n == N) {
                count += 1
                return
            }

            for (i in 1..min(3, N - n)) {
                dfs(n + i)
            }
        }

        dfs(0)
        return count
    }
}

private fun info() = BOJSolution.Info(
    no = 9095,
    title = "1,2,3 더하기",
    category = arrayOf(BOJSolution.DP, BOJSolution.BFS, BOJSolution.DFS),
    problemUrl = "https://www.acmicpc.net/problem/9095",
)

private fun testCase() = arrayOf(
    BOJSolution.TestCase(
        input = "3\n" +
                "4\n" +
                "7\n" +
                "10",
        output = "7\n" +
                "44\n" +
                "274",
    ),
)