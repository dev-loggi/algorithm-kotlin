package boj.dp

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 11060 점프 점프
 *
 * 시간 제한: 1초
 * 메모레 제한: 256 MB
 * */
class BOJ_11060_JumpJump : BOJSolution(info(), testCases()) {

    override fun main() {
        val N = readln().toInt() // (1 ≤ N ≤ 1,000)
        val maze = readln().split(" ").map { it.toInt() }.toIntArray() // (0 ≤ maze[i] ≤ 100)

        solutionWithDP(N, maze).let { println(it) }
    }

    private fun solutionWithDP(N: Int, maze: IntArray): Int {
        val dp = IntArray(N) { i -> if (i == 0) 0 else Int.MAX_VALUE }

        for (curr in 0..N - 2) {
            if (dp[curr] == Int.MAX_VALUE)
                continue

            val nextMin = curr + 1
            val nextMax = (curr + maze[curr]).coerceAtMost(N - 1)

            for (next in nextMin..nextMax) {
                dp[next] = dp[next].coerceAtMost(dp[curr] + 1)
            }
        }

        return if (dp[N - 1] != Int.MAX_VALUE) dp[N - 1] else -1
    }

    private fun solutionWithBFS(N: Int, maze: IntArray): Int {
        if (N == 1)
            return 0

        val queue = ArrayDeque<Int>()
        val visited = BooleanArray(N)

        queue.offer(0)
        visited[0] = true

        var count = 0
        while (queue.isNotEmpty()) {
            count += 1

            for (q in queue.indices) {
                val curr = queue.poll()

                for (jump in 1..maze[curr]) {
                    val next = curr + jump

                    if (N - 1 <= next)
                        return count

                    if (visited[next])
                        continue

                    visited[next] = true
                    queue.offer(next)
                }
            }
        }

        return -1
    }
}

private fun info() = BOJSolution.Info(
    no = 11060,
    title = "점프 점프",
    category = arrayOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/11060",
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "10\n" +
                "1 2 0 1 3 2 1 5 4 2",
        output = "5"
    ),
)