package boj.dfsBfs

import common.Solution
import java.text.DecimalFormat
import java.util.ArrayDeque

/**
 * 2178
 * 미로 탐색
 * https://www.acmicpc.net/problem/2178
 * */
class P_2178_MazeExploration : Solution {

/*
4 6
101111
101010
101011
111011

4 6
110110
110110
111111
111101

2 25
1011101110111011101110111
1110111011101110111011101

7 7
1011111
1110001
1000001
1000001
1000001
1000001
1111111
*/

    override fun execute() {
        for (i in 0 until 4) main()
    }

    fun main() {
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

        val pattern = Array(maze[maze.lastIndex][maze.last().lastIndex].toString().length) { 0 }.joinToString("")
        maze.forEach { println(it.map { n -> DecimalFormat(pattern).format(n) }) }
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