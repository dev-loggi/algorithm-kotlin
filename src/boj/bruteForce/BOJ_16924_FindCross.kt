package boj.bruteForce

import boj.BOJSolution
import kotlin.math.max

class BOJ_16924_FindCross : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt()
        val M = input[1].toInt()

        val grid = Array(N) { CharArray(M) }

        for (i in 0 until N) {
            grid[i] = readLine()!!.toCharArray()
        }

        solution(grid, N, M)
    }

    fun solution(grid: Array<CharArray>, N: Int, M: Int) {
        val crosses = mutableListOf<IntArray>()
        val visited = Array(N) { BooleanArray(M) }
        val rm = intArrayOf(0, -1, 0, 1)
        val cm = intArrayOf(-1, 0, 1, 0)

        fun canMove(row: Int, col: Int): Boolean {
            return (row in 0 until N) && (col in 0 until M) && (grid[row][col] == '*')
        }

        for (row in 1..N - 2) {
            for (col in 1..M - 2) {
                if (grid[row][col] != '*')
                    continue

                for (size in 1..max(N, M)) {
                    if (!canMove(row + rm[0] * size, col + cm[0] * size)) break // 좌
                    if (!canMove(row + rm[1] * size, col + cm[1] * size)) break // 상
                    if (!canMove(row + rm[2] * size, col + cm[2] * size)) break // 우
                    if (!canMove(row + rm[3] * size, col + cm[3] * size)) break // 하

                    visited[row][col] = true
                    visited[row + rm[0] * size][col + cm[0] * size] = true
                    visited[row + rm[1] * size][col + cm[1] * size] = true
                    visited[row + rm[2] * size][col + cm[2] * size] = true
                    visited[row + rm[3] * size][col + cm[3] * size] = true
                    crosses.add(intArrayOf(row + 1, col + 1, size))
                }
            }
        }

        var isComplete = true

        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == '*' && !visited[row][col]) {
                    isComplete = false
                    break
                }
            }
        }

        if (isComplete) {
            println(crosses.size)
            crosses.forEach { println(it.joinToString(" ")) }
        } else {
            println(-1)
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 16924,
    title = "십자가 찾기",
    category = listOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/16924"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input =
        "6 8\n" +
                "....*...\n" +
                "...**...\n" +
                "..*****.\n" +
                "...**...\n" +
                "....*...\n" +
                "........",
        output =
        "3\n" +
                "3 4 1\n" +
                "3 5 2\n" +
                "3 5 1"
    ),
    BOJSolution.TestCase(
        input =
        "5 5\n" +
                ".*...\n" +
                "****.\n" +
                ".****\n" +
                "..**.\n" +
                ".....",
        output =
        "3\n" +
                "2 2 1\n" +
                "3 3 1\n" +
                "3 4 1"
    ),
    BOJSolution.TestCase(
        input =
        "5 5\n" +
                ".*...\n" +
                "***..\n" +
                ".*...\n" +
                ".*...\n" +
                ".....",
        output = "-1"
    ),
    BOJSolution.TestCase(
        input =
        "3 3\n" +
                "*.*\n" +
                ".*.\n" +
                "*.*",
        output = "-1"
    ),
)