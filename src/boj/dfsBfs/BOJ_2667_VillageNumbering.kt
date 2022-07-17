package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 2667
 * 단지 번호 붙이기
 * https://www.acmicpc.net/problem/2667
 * */
class BOJ_2667_VillageNumbering : BOJSolution(info(), testCases()) {

    override fun main() {
        val n = readLine()?.toIntOrNull() ?: return
        val map = mutableListOf<IntArray>()
        val visited = Array(n) { BooleanArray(n) { false } }

        for (i in 0 until n) {
            val line = readLine()
                ?.map { it.digitToInt() }
                ?.toIntArray() ?: return
            map.add(line)
        }

        var k = 1 // 단지 번호
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    visited[i][j] = true
                    bfs(map, visited, Point(i, j), k++)
                }
                visited[i][j] = true
            }
        }

        map.forEach { println(it.contentToString()) }

        println(k - 1)
        (1 until k).map { i -> map.sumOf { it.count { n -> n == i } } }
            .sorted()
            .forEach { println(it) }
    }

    fun bfs(map: List<IntArray>, visited: Array<BooleanArray>, start: Point, k: Int) {
        val queue = ArrayDeque<Point>().apply { offer(start) }
        val range = map.indices

        fun visit(row: Int, col: Int) {
            if (row !in range || col !in range || visited[row][col] || map[row][col] == 0)
                return
            visited[row][col] = true
            map[row][col] = k
            queue.offer(Point(row, col))
        }

        map[start.row][start.col] = k

        while (queue.isNotEmpty()) {
            val p = queue.poll()

            visit(p.row - 1, p.col) // 상
            visit(p.row + 1, p.col) // 하
            visit(p.row, p.col - 1) // 좌
            visit(p.row, p.col + 1) // 우
        }
    }

    class Point(val row: Int, val col: Int)

    private fun logVisited(visited: Array<BooleanArray>) {
        visited.forEach { println(it.map { b -> if (b) 'T' else 'F' }.joinToString("")) }
    }

}

private fun info() = BOJSolution.Info(
    no = 2667,
    title = "단지 번호 붙이기",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/2667"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase( // case 1
        input = "7\n" +
                "0110100\n" +
                "0110101\n" +
                "1110101\n" +
                "0000111\n" +
                "0100000\n" +
                "0111110\n" +
                "0111000",
        output ="3\n" +
                "7\n" +
                "8\n" +
                "9",
    ),
    BOJSolution.TestCase( // case 2
        input = "7\n" +
                "0110100\n" +
                "0100101\n" +
                "1110101\n" +
                "0001011\n" +
                "1100000\n" +
                "0101111\n" +
                "0101001",
        output ="3\n" +
                "7\n" +
                "8\n" +
                "9",
    ),
)