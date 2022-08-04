package boj.dfsBfs

import boj.BOJSolution
import java.util.*

/**
 * 15558
 * 점프 게임
 * */
class BOJ_15558_JumpGame : BOJSolution(info(), testCases()) {

    companion object {
        private val MOVE = intArrayOf(-1, 1, 1)
    }

    override fun main() {
        // (1 ≤ N, k ≤ 100,000)
        val (N, k) = readln().split(" ").map { it.toInt() }.toIntArray()

        val lines = Array(2) {
            readln().map { it.digitToInt() }.toIntArray()
        }

        MOVE[2] = k

        solution(lines, N, k).let { println(it) }
    }

    private fun solution(lines: Array<IntArray>, N: Int, k: Int): Int {
        var isSucceed = false
        val queue = ArrayDeque<IntArray>()
        val visited = Array(2) { BooleanArray(N) }

        queue.offer(intArrayOf(0, 0))
        visited[0][0] = true

        while (queue.isNotEmpty()) {
            val (line, i) = queue.poll()

            for (c in 0..2) {
                val line2 = if (c == 2) (line + 1) % 2 else line
                val i2 = i + MOVE[c]

                if (i2 >= N) {
                    isSucceed = true
                    break
                }

                if (i2 < 0 || visited[line2][i2] || lines[line2][i2] == 0)
                    continue

                visited[line2][i2] = true
                queue.offer(intArrayOf(line2, i2))
            }

            if (isSucceed)
                break
        }

        return if (isSucceed) 1 else 0
    }
}

private fun info() = BOJSolution.Info(
    no = 15558,
    title = "점프 게임",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/15558",
    solutionUrl = "",
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "7 3\n" +
                "1110110\n" +
                "1011001",
        output = "1",
    ),
    BOJSolution.TestCase(
        input = "6 2\n" +
                "110101\n" +
                "011001",
        output = "0",
    ),
)