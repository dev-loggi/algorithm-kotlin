package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

class BOJ_17071_HideAndSeek5 : BOJSolution() {

    companion object {
        private const val MAX = 500_000
        private val RANGE = 0..MAX
        private val DX = intArrayOf(-1, 1, 0) // 더하기
        private val DM = intArrayOf(1, 1, 2) // 곱하기
    }

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 수빈이의 위치(0 ≤ N ≤ 500,000)
        val K = input[1].toInt() // 동생의 위치(0 ≤ K ≤ 500,000)

        solution(N, K).let { println(it) }
    }

    fun solution(N: Int, K: Int): Int {
        if (N == K) return 0

        // visited[0][n]: 수빈이가 "짝수" 초에 위치 n의 방문 여부
        // visited[1][n]: 수빈이가 "홀수" 초에 위치 n의 방문 여부
        val visited = Array(2) { BooleanArray(MAX + 1) }
        val queue = ArrayDeque<Int>()

        visited[0][N] = true
        queue.offer(N)

        var sec = 0
        var k = K

        while (queue.isNotEmpty()) {
            k += (++sec) // sec 초 후 동생의 위치

            if (k > MAX) // 동생이 범위를 벗어남
                return -1

            for (i in queue.indices) { // 큐의 한 사이클
                val cur = queue.poll()

                for (j in 0 until 3) {
                    val next = (cur + DX[j]) * DM[j] // 수빈이의 다음 위치

                    if (next !in RANGE || visited[sec % 2][next])
                        continue

                    visited[sec % 2][next] = true
                    queue.offer(next)
                }
            }

            // 짝수 초에 수빈이가 방문했었던 위치에 짝수 초에 동생이 도착했다면 혹은,
            // 홀수 초에 수빈이가 방문했었떤 위치에 홀수 초에 동생이 도착했다면
            // 현재 동생의 위치(k)에서 최단 시간(sec)에 만날 수 있음
            if (visited[sec % 2][k])
                break
        }

        queue.clear()
        return sec
    }
}

private val _info = BOJSolution.Info(
    no = 17071,
    title = "숨바꼭질 5",
    category = arrayOf(BOJSolution.BFS),
    url = "https://www.acmicpc.net/problem/17071",
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "5 17",
        output = "2"
    ),
    BOJSolution.TestCase(
        input = "17 5",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "6 6",
        output = "0"
    ),
    BOJSolution.TestCase(
        input = "1 500000",
        output = "-1"
    ),
    BOJSolution.TestCase(
        input = "250000 499999",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "1 10",
        output = "6"
    ),

    /* User Test Cases */
    BOJSolution.TestCase(
        input = "7 37",
        output = "5"
    ),
    BOJSolution.TestCase(
        input = "21 70",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "18 58",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "18 66",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "16 50",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "34 0",
        output = "8"
    ),
)