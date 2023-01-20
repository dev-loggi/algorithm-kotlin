package boj.dp

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 11048 이동하기
 *
 * 시간 제한: 1초
 * 메모리 제한: 256 MB
 * */
class BOJ_11048_Move : BOJSolution(info(), testCases()) {

    companion object {
        private val DR = intArrayOf(1, 0, 1)
        private val DC = intArrayOf(0, 1, 1)
    }

    override fun runEachSolution() {
        // 미로의 크기 (1 ≤ N, M ≤ 1,000)
//        val (N, M) = readln().split(" ").map { it.toInt() }
//        val maze = Array(N) { intArrayOf() }
//
//        for (r in 0 until N) {
//            maze[r] = readln().split(" ").map { it.toInt() }.toIntArray()
//        }
//
//        solution(N, M, maze).let { println(it) }

        bestSolution()
    }

    /** DP **/
    private fun bestSolution() {
        // 미로의 크기 (1 ≤ N, M ≤ 1,000)
        val (N, M) = readln().split(" ").map { it.toInt() }
        val maze = Array(N + 1) { IntArray(M + 1) }

        for (r in 1..N) {
            maze[r] = intArrayOf(0) + readln().split(" ")
                .map { it.toInt() }
                .toIntArray()
        }

        for (r in 1..N) for (c in 1..M) {
            maze[r][c] = maze[r - 1][c - 1]
                .coerceAtLeast(maze[r][c - 1])
                .coerceAtLeast(maze[r - 1][c]) + maze[r][c]
        }

        println(maze[N][M])
    }

    /** BFS **/
    private fun solution(N: Int, M: Int, maze: Array<IntArray>): Int {
        var answer = Int.MIN_VALUE
        val queue = ArrayDeque<IntArray>()
        val visited = Array(N) { BooleanArray(M) }
        val max = Array(N) { IntArray(M) { Int.MIN_VALUE } }

        queue.offer(intArrayOf(0, 0))
        visited[0][0] = true
        max[0][0] = maze[0][0]

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            if (r == N-1 && c == M-1)
                answer = answer.coerceAtLeast(max[r][c])

            for (d in 0..2) {
                val nr = r + DR[d]
                val nc = c + DC[d]

                if (nr !in 0 until N || nc !in 0 until M)
                    continue

                val nextCandy = max[r][c] + maze[nr][nc]

                if (visited[nr][nc] && nextCandy <= max[nr][nc])
                    continue

                visited[nr][nc] = true
                max[nr][nc] = nextCandy
                queue.offer(intArrayOf(nr, nc))
            }
        }

        return answer
    }
}

private fun info() = BOJSolution.Info(
    no = 11048,
    title = "이동하기",
    category = listOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/11048",
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "3 4\n" +
                "1 2 3 4\n" +
                "0 0 0 5\n" +
                "9 8 7 6",
        output = "31",
    ),
    BOJSolution.TestCase(
        input = "3 3\n" +
                "1 0 0\n" +
                "0 1 0\n" +
                "0 0 1",
        output = "3"
    ),
    BOJSolution.TestCase(
        input = "4 3\n" +
                "1 2 3\n" +
                "6 5 4\n" +
                "7 8 9\n" +
                "12 11 10",
        output = "47"
    ),
)