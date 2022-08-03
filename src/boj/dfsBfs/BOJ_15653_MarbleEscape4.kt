package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque
import kotlin.math.absoluteValue

class BOJ_15653_MarbleEscape4 : BOJSolution(info(), testCases()) {

    companion object {
        private val MOVE = arrayOf(
            intArrayOf(0, -1), // 좌
            intArrayOf(-1, 0), // 상
            intArrayOf(0, 1), // 우
            intArrayOf(1, 0), // 하
        )
    }

    override fun main() {
        // (3 ≤ N, M ≤ 10)
        val (N, M) = readln().split(" ").map { it.toInt() }.toIntArray()

        val board = Array(N) { charArrayOf() }
        val startPos = IntArray(4)

        for (r in 0 until N) {
            board[r] = readln().toCharArray()
            board[r].forEachIndexed { c, ch ->
                if (ch == 'R') {
                    startPos[0] = r
                    startPos[1] = c
                    board[r][c] = '.'
                } else if (ch == 'B') {
                    startPos[2] = r
                    startPos[3] = c
                    board[r][c] = '.'
                }
            }
        }

        solution(N, M, board, startPos).let { println(it) }
    }

    private fun solution(N: Int, M: Int, board: Array<CharArray>, start: IntArray): Int {
        val queue = ArrayDeque<IntArray>()
        val visited = Array(N) { Array(M) { Array(N) { BooleanArray(M) } } }

        queue.offer(start)
        visited[start[0]][start[1]][start[2]][start[3]] = true

        var time = 0
        var isSucceed = false

        while (queue.isNotEmpty()) {
            time += 1

            for (q in queue.indices) {
                val curr = queue.poll() ?: break

                for (dir in 0..3) {
                    val next = getNext(board, curr, dir)

                    if (next[2] == -1) { // 파란 구슬이 구멍에 빠짐
                        continue
                    } else if (next[0] == -1) { // 빨간만 구슬에 빠짐
                        isSucceed = true
                        queue.clear()
                        break
                    }

                    if (visited[next[0]][next[1]][next[2]][next[3]])
                        continue

                    visited[next[0]][next[1]][next[2]][next[3]] = true
                    queue.offer(next)
                }
            }
        }

        return if (isSucceed) time else -1
    }

    private fun getNext(board: Array<CharArray>, curr: IntArray, dir: Int): IntArray {
        var (r1, c1, r2, c2) = curr
        val next = intArrayOf(-1, -1, -1, -1)

        var isRedGoal = false
        var isBlueGoal = false

        while (board[r1][c1] == '.') {
            r1 += MOVE[dir][0]
            c1 += MOVE[dir][1]
        }

        if (board[r1][c1] == 'O') {
            isRedGoal = true
        } else {
            next[0] = r1 - MOVE[dir][0]
            next[1] = c1 - MOVE[dir][1]
        }

        while (board[r2][c2] == '.') {
            r2 += MOVE[dir][0]
            c2 += MOVE[dir][1]
        }

        if (board[r2][c2] == 'O') {
            isBlueGoal = true
        } else {
            next[2] = r2 - MOVE[dir][0]
            next[3] = c2 - MOVE[dir][1]
        }

        if (!isRedGoal && !isBlueGoal) {
            if (r1 == r2 && c1 == c2) { // 두 구슬이 겹쳤을 때
                val redDiff = (next[0] - curr[0]).abs + (next[1] - curr[1]).abs
                val bluDiff = (next[2] - curr[2]).abs + (next[3] - curr[3]).abs

                if (redDiff > bluDiff) { // 빨간 공이 멀리서 옴
                    next[0] -= MOVE[dir][0]
                    next[1] -= MOVE[dir][1]
                } else { // 파란 공이 멀리서 옴
                    next[2] -= MOVE[dir][0]
                    next[3] -= MOVE[dir][1]
                }
            }
        }

        return next
    }

    private val Int.abs: Int
        get() = absoluteValue

    private fun Array<CharArray>.printBoard() {
        this.forEach { println(it.joinToString("")) }
    }

    private fun IntArray.logMarble(): String {
        return "Red=(${this[0]}, ${this[1]}), Blue=(${this[2]}, ${this[3]})"
    }

    private val Int.dir: String
        get() = when (this) {
            0 -> "LT"
            1 -> "UP"
            2 -> "RT"
            3 -> "DN"
            else -> throw IllegalStateException()
        }
}

private fun info() = BOJSolution.Info(
    no = 15643,
    title =" 구슬 탈출 4",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/15653",
    solutionUrl = "",
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "5 5\n" +
                "#####\n" +
                "#..B#\n" +
                "#.#.#\n" +
                "#RO.#\n" +
                "#####",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "7 7\n" +
                "#######\n" +
                "#...RB#\n" +
                "#.#####\n" +
                "#.....#\n" +
                "#####.#\n" +
                "#O....#\n" +
                "#######",
        output = "5"
    ),
    BOJSolution.TestCase(
        input = "7 7\n" +
                "#######\n" +
                "#..R#B#\n" +
                "#.#####\n" +
                "#.....#\n" +
                "#####.#\n" +
                "#O....#\n" +
                "#######",
        output = "5"
    ),
    BOJSolution.TestCase(
        input = "10 10\n" +
                "##########\n" +
                "#R#...##B#\n" +
                "#...#.##.#\n" +
                "#####.##.#\n" +
                "#......#.#\n" +
                "#.######.#\n" +
                "#.#...##.#\n" +
                "#.#.#.#..#\n" +
                "#...#.O#.#\n" +
                "##########",
        output = "12"
    ),
    BOJSolution.TestCase(
        input = "3 7\n" +
                "#######\n" +
                "#R.O.B#\n" +
                "#######",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "10 10\n" +
                "##########\n" +
                "#R#...##B#\n" +
                "#...#.##.#\n" +
                "#####.##.#\n" +
                "#......#.#\n" +
                "#.######.#\n" +
                "#.#....#.#\n" +
                "#.#.##...#\n" +
                "#O..#....#\n" +
                "##########",
        output = "7"
    ),
    BOJSolution.TestCase(
        input = "3 10\n" +
                "##########\n" +
                "#.O....RB#\n" +
                "##########",
        output = "-1"
    ),
    // Custom Case
    BOJSolution.TestCase(
        input = "7 7\n" +
                "#######\n" +
                "#...BR#\n" +
                "#..####\n" +
                "#.....#\n" +
                "#####.#\n" +
                "#O....#\n" +
                "#######",
        output = "5"
    )
)
/*

7 7
#######
#...BR#
#..####
#.....#
#####.#
#O....#
#######

* */