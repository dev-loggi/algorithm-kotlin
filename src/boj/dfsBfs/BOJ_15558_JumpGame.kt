package boj.dfsBfs

import boj.BOJSolution
import java.util.*

/**
 * 15558
 * 점프 게임
 *
 * 시간 제한: 2초
 * 메모리 제한: 512MB
 * */
class BOJ_15558_JumpGame : BOJSolution(info(), testCases()) {

    companion object {
        private val DL = intArrayOf(0, 0, 1)
        private val DI = intArrayOf(-1, 1, 1)
    }

    override fun runEachSolution() {
        // (1 ≤ N, k ≤ 100,000)
        val (N, k) = readln().split(" ").map { it.toInt() }.toIntArray()

        val lines = Array(2) {
            readln().map { it.digitToInt() }.toIntArray()
        }

        DI[2] = k

        solution(lines, N, k).let { println(it) }
    }

    private fun solution(lines: Array<IntArray>, N: Int, k: Int): Int {
        var isSucceed = false
        val queue = ArrayDeque<IntArray>()
        val visited = Array(2) { BooleanArray(N) }

        queue.offer(intArrayOf(0, 0))
        visited[0][0] = true

        var nextTime = 0

        while (queue.isNotEmpty()) {
            nextTime += 1

            for (q in queue.indices) {
                val (line, i) = queue.poll() ?: break

                for (c in 0..2) {
                    val line2 = (line + DL[c]) % 2
                    val i2 = i + DI[c]

                    if (i2 >= N) {
                        isSucceed = true
                        queue.clear()
                        break
                    }

                    if (i2 < nextTime || visited[line2][i2] || lines[line2][i2] == 0)
                        continue

                    visited[line2][i2] = true
                    queue.offer(intArrayOf(line2, i2))
                }
            }
        }

        return if (isSucceed) 1 else 0
    }
}

private fun info() = BOJSolution.Info(
    no = 15558,
    title = "점프 게임",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/15558",
    solutionUrl = "",
)

private fun testCases() = listOf(
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
//    // 반례
//    BOJSolution.TestCase(
//        input = "1 1" +
//                "1" +
//                "1",
//        output = "1"
//    ),
//    BOJSolution.TestCase(
//        input = "1 1" +
//                "1" +
//                "0",
//        output = "1"
//    ),
)