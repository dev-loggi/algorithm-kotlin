package boj.dp

import boj.BOJSolution
import java.util.ArrayDeque
import kotlin.math.min

/**
 * 1463
 * 1로 만들기
 *
 * https://www.acmicpc.net/problem/1463
 * DP
 * */
class BOJ_1463_MakeItToOne : BOJSolution(info(), testCase()) {

    class Point(val x: Int, val count: Int)

    override fun main() {
        val N = readln().toInt() // (1 ≤ N ≤ 10^6)

        println(solution2(N))
    }

    /** DP 재귀 */
    private fun solution1(n: Int): Int {
        if (n == 1) return 0
        if (n == 2 || n == 3) return 1

        val a = if (n % 3 == 0) solution1(n/3) else Int.MAX_VALUE
        val b = if (n % 2 == 0) solution1(n/2) else Int.MAX_VALUE
        val c = solution1(n-1)

        return min(min(a, b), c) + 1
    }

    /** DP 백트래킹 */
    private fun solution2(N: Int): Int {
        val dp = IntArray(1_000_001)

        for (x in 2..N) {
            dp[x] = dp[x - 1] + 1

            if (x % 2 == 0) dp[x] = min(dp[x], dp[x / 2] + 1)
            if (x % 3 == 0) dp[x] = min(dp[x], dp[x / 3] + 1)
        }

        return dp[N]
    }

    /** BFS */
    private fun solution3(N: Int): Int {
        val add = intArrayOf(1, 0, 0)
        val mul = intArrayOf(1, 2, 3)

        val visited = BooleanArray(1_000_001)
        val queue = ArrayDeque<Point>()

        queue.offer(Point(1, 0))

        while (queue.isNotEmpty()) {
            val curr = queue.poll()

            if (curr.x == N)
                return curr.count

            for (i in 0..2) {
                val nx = curr.x * mul[i] + add[i]

                if (nx > 1_000_000 || visited[nx])
                    continue

                visited[nx] = true
                queue.offer(Point(nx, curr.count + 1))
            }
        }

        return 0
    }
}

private fun info() = BOJSolution.Info(
    no = 1463,
    title = "1로 만들기",
    category = arrayOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/1463",
    solutionUrl = "",
)

private fun testCase() = arrayOf(
    BOJSolution.TestCase(
        input = "2",
        output = "1",
    ),
    BOJSolution.TestCase(
        input = "10",
        output = "3",
    ),
)