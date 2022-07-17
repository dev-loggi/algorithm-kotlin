package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

class BOJ_2178_MazeExploration : BOJSolution(info(), testCases()) {

    override fun main() {
        val input = readLine()?.split(" ") ?: return
        val n = input[0].toIntOrNull() ?: return
        val m = input[1].toIntOrNull() ?: return

        val maze = mutableListOf<IntArray>()

        for (i in 0 until n) {
            val line = readLine() ?: return
            if (line.length != m) return
            maze.add(line.map { it.digitToInt() }.toIntArray())
        }

        bfs(maze, Point(maze.lastIndex, maze.last().lastIndex))
            .let { println("answer=$it") }
    }

    private fun bfs(maze: List<IntArray>, end: Point): Int {
        val visited = Array(maze.size) { BooleanArray(maze[0].size) { false } }
        val queue = ArrayDeque<Point>().apply { offer(Point(0, 0)) }

        val rangeRow = maze.indices
        val rangeCol = maze[0].indices

        visited[0][0] = true

        fun visit(p: Point, row: Int, col: Int) {
            if (row in rangeRow && col in rangeCol && maze[row][col] != 0 && !visited[row][col]) {
                maze[row][col] = maze[p.row][p.col] + 1
                visited[row][col] = true
                queue.offer(Point(row, col))
            }
        }

        while (queue.isNotEmpty()) {
            val p = queue.poll()
            if (p == end)
                break

            visit(p, p.row - 1, p.col) // 상
            visit(p, p.row + 1, p.col) // 하
            visit(p, p.row, p.col - 1) // 좌
            visit(p, p.row, p.col + 1) // 우
        }

        return maze[maze.lastIndex][maze.last().lastIndex]
    }

    data class Point(val row: Int, val col: Int) {
        override fun toString(): String = "($row, $col)"
    }

    private fun log(maze: List<CharArray>, p: Point, count: Int) {
        val newMaze = Array(maze.size) { IntArray(maze[0].size) { 0 } }
        newMaze[p.row][p.col] = 1
        println("count=$count")
        newMaze.forEach { println(it.joinToString("")) }
        println()
    }
}

private fun info() = BOJSolution.Info(
    no = 2178,
    title = "미로 탐색",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/2178"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase( // case 1
        input = "4 6\n" +
                "101111\n" +
                "101010\n" +
                "101011\n" +
                "111011",
        output = "15"
    ),
    BOJSolution.TestCase( // case 2
        input = "4 6\n" +
                "110110\n" +
                "110110\n" +
                "111111\n" +
                "111101",
        output = "9"
    ),
    BOJSolution.TestCase( // case 3
        input = "2 25\n" +
                "1011101110111011101110111\n" +
                "1110111011101110111011101",
        output = "38"
    ),
    BOJSolution.TestCase( // case 4
        input = "7 7\n" +
                "1011111\n" +
                "1110001\n" +
                "1000001\n" +
                "1000001\n" +
                "1000001\n" +
                "1000001\n" +
                "1111111",
        output = "13"
    )
)