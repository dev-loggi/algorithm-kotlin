package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 16920
 * 확장 게임
 * */
class BOJ_16920_ExpansionGame : BOJSolution(info(), testCases()) {

    companion object {
        private const val BLANK = -1
        private const val BARRIER = 9

        private val DR = intArrayOf(0, -1, 0, 1)
        private val DC = intArrayOf(-1, 0, 1, 0)
    }

    override fun main() {
        // 1 ≤ N, M ≤ 1,000
        // 1 ≤ P ≤ 9
        // 1 ≤ Si ≤ 10^9
        val (N, M, P) = readLine()!!.split(" ").map { it.toInt() }
        val S = readLine()!!.split(" ")
            // S(i) 최댓값이 10^9 로 너무 큰 수이며
            // 성을 확장할 때 board 에서 최대 거리는 N + M 이므로
            .map { Math.min(it.toInt(), N + M) }
            .toIntArray()

        val board: Array<IntArray> = Array(N) { intArrayOf() }

        for (i in 0 until N) {
            board[i] = readLine()!!
                .map { when (it) {
                    '.' -> BLANK
                    '#' -> BARRIER
                    else -> it.digitToInt() - 1
                }}
                .toIntArray()
        }

        solution(N, M, P, S, board).let {
            println(it.joinToString(" "))
        }
    }

    private fun solution(
        N: Int, M: Int, P: Int,
        S: IntArray,
        board: Array<IntArray>
    ): IntArray {
        val castleCount = IntArray(P)
        // 각 선수마다 큐 생성
        val eachQueue = Array(P) { ArrayDeque<IntArray>() }

        // 초기 보드에 놓여진 성의 위치를 각 선수 큐에 넣기
        for (r in 0 until N) for (c in 0 until M) {
            val number = board[r][c]
            if (number !in 0..8)
                continue

            eachQueue[number].offer(intArrayOf(r, c))
            castleCount[number] += 1
        }

        // 모든 선수의 큐가 Empty 일 때까지
        while (eachQueue.any { it.isNotEmpty() }) {

            // 각 선수마다 BFS
            for (player in 0 until P) {
                val queue = eachQueue[player]
                if (queue.isEmpty())
                    continue

                castleCount[player] += bfs(N, M, S, board, player, queue)
            }
        }

        return castleCount
    }

    private fun bfs(
        N: Int, M: Int,
        S: IntArray,
        board: Array<IntArray>,
        player: Int,
        queue: ArrayDeque<IntArray>
    ): Int {
        var expandedCount = 0

        // 각자의 턴마다 i번의 선수는 BFS 를 S(i) 번 반복함
        for (si in 1..S[player]) {
            for (q in queue.indices) {
                val (r, c) = queue.poll()

                for (dir in 0..3) {
                    val nr = r + DR[dir]
                    val nc = c + DC[dir]

                    if (nr !in 0 until N || nc !in 0 until M || board[nr][nc] != BLANK )
                        continue

                    board[nr][nc] = player
                    queue.offer(intArrayOf(nr, nc))

                    expandedCount += 1
                }
            }

            if (queue.isEmpty())
                break
        }

        return expandedCount
    }
}

private fun info() = BOJSolution.Info(
    no = 16920,
    title = "확장 게임",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/16920",
    solutionUrl = "",
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "3 3 2\n" +
                "1 1\n" +
                "1..\n" +
                "...\n" +
                "..2",
        output = "6 3"
    ),
    BOJSolution.TestCase(
        input = "3 3 2\n" +
                "1 1\n" +
                "1.1\n" +
                "...\n" +
                "..2",
        output = "7 2"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "1 1\n" +
                "1...\n" +
                "....\n" +
                "....\n" +
                "...2",
        output = "10 6"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "1 1\n" +
                "1..1\n" +
                "....\n" +
                "....\n" +
                "...2",
        output = "11 5"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "2 1\n" +
                "1..1\n" +
                "....\n" +
                "....\n" +
                "...2",
        output = "14 2"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "3 1\n" +
                "1..1\n" +
                "....\n" +
                "....\n" +
                "...2",
        output = "14 2"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "1 1\n" +
                "1..1\n" +
                "#.##\n" +
                "....\n" +
                "...2",
        output = "7 6"
    ),
    BOJSolution.TestCase(
        input = "4 4 2\n" +
                "2 1\n" +
                "1..1\n" +
                "#.##\n" +
                "....\n" +
                "...2",
        output = "10 3"
    ),

    // custom cases
    BOJSolution.TestCase(
        input = "6 10 3\n" +
                "1 3 1\n" +
                "..........\n" +
                ".1......1.\n" +
                "...#......\n" +
                ".....3....\n" +
                "#.....#...\n" +
                ".#.2......",
        output = "26 25 4"
    ),
    BOJSolution.TestCase(
        input = "20 40 3\n" +
                "5 10000 10\n" +
                "........................................\n" +
                "........................................\n" +
                ".....1....1..........333................\n" +
                "..........................3333..........\n" +
                ".................3...........33.........\n" +
                "........1..............3................\n" +
                "...............3..........3.............\n" +
                "...1.......1............................\n" +
                ".............3.....1....................\n" +
                "....3..3.3..............................\n" +
                "............3...........................\n" +
                "......3.................................\n" +
                "........................................\n" +
                "........................................\n" +
                "........................................\n" +
                "........................................\n" +
                "........................................\n" +
                "........................................\n" +
                "........................................\n" +
                ".......................................2",
        output = "218 563 19"
    ),
)