package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 12851
 * 숨바꼭질 2
 * */
class BOJ_12851_HideAndSeek2 : BOJSolution(info(), testCases()) {

    companion object {
        private val RANGE = 0..100_000
        private val DX = intArrayOf(-1, 1, 0) // 더하기
        private val DM = intArrayOf(1, 1, 2) // 곱하기
    }

    override fun runEachSolution() {
        // 수빈이의 위치 N(0 ≤ N ≤ 100,000)
        // 동생의 위치 K(0 ≤ K ≤ 100,000)
        val (N, K) = readLine()!!.split(" ")
            .map { it.toInt() }

        val (minTime, count) = solution(N, K)

        println(minTime)
        println(count)
    }

    private fun solution(N: Int, K: Int): IntArray {
        if (N == K)
            return intArrayOf(0, 1)

        val queue = ArrayDeque<Int>()
        val visitedTime = IntArray(RANGE.last + 1) { Int.MAX_VALUE }

        queue.offer(N)
        visitedTime[N] = 0

        var time = 0
        var count = 0
        var isFinished = false
        while (queue.isNotEmpty() && !isFinished) {
            time += 1

            for (q in queue.indices) {
                val curr = queue.poll()

                for (i in 0..2) {
                    val next = (curr + DX[i]) * DM[i]
                    if (next !in RANGE)
                        continue
                    if (next !in RANGE || visitedTime[next] < time)
                        continue

                    if (next == K) {
                        isFinished = true
                        count += 1
                        break
                    }

                    visitedTime[next] = time
                    queue.offer(next)
                }
            }
        }

        return intArrayOf(time, count)
    }
}

private fun info() = BOJSolution.Info(
    no = 12851,
    title = "숨바꼭질 2",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/12851",
    solutionUrl = ""
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "5 17",
        output = "4\n" +
                "2"
    )
)