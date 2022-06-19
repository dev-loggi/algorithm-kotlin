package boj.dfsBfs

import common.Solution
import java.text.DecimalFormat
import java.util.ArrayDeque

/**
 * 16973
 * 직사각형 탈출
 * https://www.acmicpc.net/problem/16973
 * bfs
 * */
class BOJ_16973_RectangleEscape : Solution {

    override fun execute() {
        for (i in 0 until 4) main()
    }

    fun main() {
        val input1 = readLine()!!.split(" ")
        val N = input1[0].toInt()
        val M = input1[1].toInt()

        val matrix = Array(N) { IntArray(M) { 0 } }
        for (r in 0 until N) {
            val row = readLine()!!.split(" ")

            row.forEachIndexed { c, n ->
                matrix[r][c] = if (n == "0") 0 else -1
            }
        }

        val input2 = readLine()!!.split(" ")
        val H = input2[0].toInt()
        val W = input2[1].toInt()
        val Sr = input2[2].toInt()
        val Sc = input2[3].toInt()
        val Fr = input2[4].toInt()
        val Fc = input2[5].toInt()

        val square = Square(W, H, Sr - 1, Sc - 1)

        println(bfs(matrix, square, Fr - 1, Fc - 1))
    }

    fun bfs(
        matrix: Array<IntArray>,
        square: Square,
        Fr: Int,
        Fc: Int
    ): Int {
        val rangeRow = matrix.indices
        val rangeCol = matrix[0].indices
        val visited = Array(matrix.size) { BooleanArray(matrix[0].size) { false } }
        val queue = ArrayDeque<Point>().apply {
            offer(Point(square.row, square.col))
        }
        visited[square.row][square.col] = true

        fun visit(p: Point, s: Square) {
            if (!s.isRange(rangeRow, rangeCol) || visited[s.row][s.col])
                return

            for (r in s.rangeH) if (matrix[r][s.left] == -1) return // 좌벽 확인
            for (c in s.rangeW) if (matrix[s.top][c] == -1) return // 상벽 확인
            for (r in s.rangeH) if (matrix[r][s.right] == -1) return // 우벽 확인
            for (c in s.rangeW) if (matrix[s.bottom][c] == -1) return // 하벽 확인

            println("visit(): $p, $s")

            queue.offer(Point(s.row, s.col))
            visited[s.row][s.col] = true
            matrix[s.row][s.col] = matrix[p.row][p.col] + 1
        }

        while (queue.isNotEmpty()) {
            val p = queue.poll()
            println("\n$p")

            if (p.row == Fr && p.col == Fc)
                break

            visit(p, Square(square.width, square.height, p.row - 1, p.col)) // 상
            visit(p, Square(square.width, square.height, p.row + 1, p.col)) // 하
            visit(p, Square(square.width, square.height, p.row, p.col - 1)) // 좌
            visit(p, Square(square.width, square.height, p.row, p.col + 1)) // 우

            matrix.forEach { println(it.map { n -> DecimalFormat("00").format(n) }) }
        }

        return if (matrix[Fr][Fc] != 0) matrix[Fr][Fc] else -1
    }

    class Square(val width: Int, val height: Int, val row: Int, val col: Int) {
        val left: Int get() = col
        val top: Int get() = row
        val right: Int get() = col + width - 1
        val bottom: Int get() = row + height - 1

        val rangeW = left..right
        val rangeH = top..bottom

        fun isRange(rangeRow: IntRange, rangeCol: IntRange): Boolean {
            return left in rangeCol && top in rangeRow && right in rangeCol && bottom in rangeRow
        }

        override fun toString(): String {
            return "Square($left, $top, $right, $bottom)"
        }
    }

    data class Point(val row: Int, val col: Int)
}

/*
[case1]
4 5
0 0 0 0 0
0 0 1 0 0
0 0 0 0 0
0 0 0 0 0
2 2 1 1 1 4
[case1] - answer
7

[case2]
6 7
0 0 0 0 0 0 0
0 0 0 1 0 0 0
0 0 0 0 0 0 0
0 0 0 0 0 0 1
0 0 1 0 0 0 0
0 0 0 0 0 0 0
2 3 1 1 5 5
[case2] - answer
8

[case3]
6 7
0 0 0 0 0 0 0
0 0 0 1 0 0 0
0 0 0 1 0 0 0
0 0 0 0 0 0 1
0 0 1 0 0 0 0
0 0 0 0 0 0 0
2 3 1 1 5 5
[case3] - answer
-1

[case4]
10 8
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
2 2 1 1 1 7
[case4] - answer
16

[case5]
10 8
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 1 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
0 0 1 0 0 1 0 0
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0
2 2 6 3 1 7
[case5] - answer
9
*/