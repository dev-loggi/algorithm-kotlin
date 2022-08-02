package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/*
 * 16959
 * 체스판 여행1
 * https://www.acmicpc.net/problem/16959
 * bfs
 * */
class BOJ_16959_ChessboardTravel1 : BOJSolution(info(), testCases()) {

    companion object {
        private const val NIGHT = 0
        private const val BISHOP = 1
        private const val ROOK = 2

        private val MOVE = arrayOf(
            arrayOf( // NIGHT
                intArrayOf(1,-2), intArrayOf(-1,-2), intArrayOf(-2,-1), intArrayOf(-2,1),
                intArrayOf(-1,2), intArrayOf(1,2), intArrayOf(2,1), intArrayOf(2,-1)
            ),
            arrayOf( // BISHOP
                intArrayOf(-1,-1), intArrayOf(-1,1), intArrayOf(1,1), intArrayOf(1,-1)
            ),
            arrayOf( // ROOK
                intArrayOf(0,-1), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(1,0)
            ),
        )
    }

    override fun main() {
        val N = readLine()!!.toInt() // (3 ≤ N ≤ 10)
        val board = Array(N) { IntArray(N) }

        var r0 = -1 // 시작 위치 row
        var c0 = -1 // 시작 위치 col

        for (r in 0 until N) {
            board[r] = readLine()!!.split(" ")
                .map { it.toInt() - 1 } // 인덱스 매핑을 위해 모든 원소 -1
                .toIntArray()

            if (r0 > 0 || c0 > 0)
                continue

            for (c in board[r].indices) {
                if (board[r][c] == 0) {
                    r0 = r
                    c0 = c
                }
            }
        }

        solution(N, board, r0, c0).let { println(it) }
    }

    fun solution(N: Int, board: Array<IntArray>, r0: Int, c0: Int): Int {
        val range = 0 until N
        val last = N * N - 1

        // visited[n][h][r][c]
        // n: 말의 출발 번호(0 ≤ n ≤ N^2 - 1)
        // h: horse(말 종류)
        // r: row
        // c: col
        val visited = Array(N * N) { Array(3) { Array(N) { BooleanArray(N) } } }
        val queue = ArrayDeque<IntArray>()

        for (h in 0 until 3) {
            visited[0][h][r0][c0] = true
            queue.offer(intArrayOf(0, h, r0, c0))
        }

        var sec = 0

        while (queue.isNotEmpty()) { // BFS
            sec++

            for (q in queue.indices) { // sec 당 queue 사이클
                val cur = queue.poll()
                val (n, h, r, c) = cur

                if (n == last) // 마지막 목적지 도착
                    return sec - 1

                // 1. 말 변경
                for (h2 in 0 until 3) {
                    if (visited[n][h2][r][c])
                        continue

                    visited[n][h2][r][c] = true
                    queue.offer(intArrayOf(n, h2, r, c))
                }

                // 2. 말 이동
                when (h) {
                    NIGHT -> for (move in MOVE[h]) {
                        val next = moveNight(cur, move, board, visited, range)
                            ?: continue

                        queue.offer(next)
                    }
                    BISHOP, ROOK -> for (move in MOVE[h]) {
                        var r2 = r + move[0]
                        var c2 = c + move[1]

                        while (r2 in range && c2 in range) {
                            val n2 =
                                if (board[r2][c2] == n + 1) n + 1 // 다음 목적지 도착
                                else n // 미도착

                            if (visited[n2][h][r2][c2])
                                break

                            visited[n][h][r2][c2] = true

                            if (n2 == last) // N*N 도착
                                return sec

                            visited[n2][h][r2][c2] = true
                            queue.offer(intArrayOf(n2, h, r2, c2))

                            r2 += move[0]
                            c2 += move[1]
                        }
                    }
                }
            }
        }

        return sec
    }

    private fun moveNight(
        cur: IntArray, move: IntArray,
        board: Array<IntArray>,
        visited: Array<Array<Array<BooleanArray>>>,
        range: IntRange
    ): IntArray? {
        val (n, h, r, c) = cur

        val r2 = r + move[0]
        val c2 = c + move[1]

        if (r2 !in range || c2 !in range)
            return null

        val n2 =
            if (board[r2][c2] == n + 1) n + 1 // 다음 목적지 도착
            else n // 미도착

        if (visited[n2][h][c2][r2])
            return null

        visited[n2][h][r2][c2] = true
        return intArrayOf(n2, h, r2, c2)
    }
}

private fun info() = BOJSolution.Info(
    no = 16959,
    title = "체스판 여행1",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/16959",
    solutionUrl = ""
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "3\n" +
                "1 9 3\n" +
                "8 6 7\n" +
                "4 2 5",
        output = "12"
    ),
    BOJSolution.TestCase(
        input = "3\n" +
                "1 5 8\n" +
                "9 2 4\n" +
                "3 6 7",
        output = "12"
    ),
    BOJSolution.TestCase(
        input = "4\n" +
                "5 4 1 13\n" +
                "8 3 6 16\n" +
                "15 9 14 12\n" +
                "11 2 7 10",
        output = "23"
    ),
    BOJSolution.TestCase(
        input = "5\n" +
                "21 14 2 3 12\n" +
                "19 8 16 18 7\n" +
                "9 17 10 15 4\n" +
                "24 5 1 23 11\n" +
                "25 13 22 6 20",
        output = "38"
    ),
)