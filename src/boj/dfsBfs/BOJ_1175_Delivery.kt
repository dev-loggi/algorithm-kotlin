package boj.dfsBfs

import common.Solution
import java.util.ArrayDeque

/**
 * 1175
 * 배달
 * https://www.acmicpc.net/problem/1175
 * bfs
 * */
// TODO: 미완성
class BOJ_1175_Delivery : Solution {

    override fun execute() {
        main()
    }

    companion object {
        const val BACK_SPACE = 8
        const val CR = 13

        const val LEFT = 0
        const val UP = 1
        const val RIGHT = 2
        const val DOWN = 3
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 세로 크기
        val M = input[1].toInt() // 가로 크기

        val map = Array(N) { CharArray(M) }
        var start: Point? = null
        var c1: Point? = null
        var c2: Point? = null

        for (r in 0 until N) {
            map[r] = readLine()!!.toCharArray()

            for (c in 0 until map[r].size) {
                if (map[r][c] == 'S') {
                    start = Point(r, c)
                } else if (map[r][c] == 'C') {
                    if (c1 == null) c1 = Point(r, c)
                    else if (c2 == null) c2 = Point(r, c)
                }
            }
        }

        println(dp(map, start!!))
    }

    fun dp(
        map: Array<CharArray>,
        start: Point
    ): Int {
        println("dp(): start=$start")
        map.forEach { println(it.joinToString("")) }
        println()

        val fakeMap = Array(6) { Array(map.size) { r -> CharArray(map[r].size) { c -> map[r][c] } } }
        val answers = mutableListOf<Point>()

        // dp[dest][row][col][direction]
        // dest=0: start -> c1
        // dest=1: c1 -> c2
        // dest=2: finish

        // dest=3: start -> c2
        // dest=4: c2 -> c1
        // dest=5: finish

        // direction: LEFT(1), UP(2), RIGHT(3), DOWN(4)

        val dp = Array(6) { Array(map.size) { Array(map[0].size) {
            BooleanArray(4)
        } } }.apply {
            this[0][start.row][start.col][LEFT] = true
            this[0][start.row][start.col][UP] = true
            this[0][start.row][start.col][RIGHT] = true
            this[0][start.row][start.col][DOWN] = true

            this[3][start.row][start.col][LEFT] = true
            this[3][start.row][start.col][UP] = true
            this[3][start.row][start.col][RIGHT] = true
            this[3][start.row][start.col][DOWN] = true
        }

        val queue = ArrayDeque<Point>().apply {
            add(Point(start.row, start.col, 0, -1)) // S -> C1 -> C2
            add(Point(start.row, start.col, 3, -1)) // S -> C2 -> C1
        }

        val rangeRow = map.indices
        val rangeCol = map[0].indices

        fun visit(prev: Point, row: Int, col: Int, direction: Int) {
            if (row !in rangeRow || col !in rangeCol || map[row][col] == '#')
                return

            if (dp[prev.dest][row][col][direction])
                return

            //println("queue.size=${queue.size}")
            println("Prev: $prev")
            println("Next: row=$row, col=$col, direction=$direction")

            val newPoint = if (map[row][col] == 'C') {
                dp[prev.dest][row][col][direction] = true
                dp[prev.dest + 1][row][col][direction] = true

                Point(row, col, prev.dest + 1, direction, prev.count + 1)
            } else {
                dp[prev.dest][row][col][direction] = true

                Point(row, col, prev.dest, direction, prev.count + 1)
            }

            queue.offer(newPoint)
            fakeMap[prev.dest][prev.row][prev.col] = '.'
            fakeMap[prev.dest][row][col] = 'P'
            fakeMap[prev.dest].forEach { println(it.joinToString("")) }
            println()

//            print(Char(BACK_SPACE))
//            print(Char(CR))
            readLine()
            //Thread.sleep(1000)
        }

        while (queue.isNotEmpty()) {
            val p = queue.poll()

            if (p.dest == 2 || p.dest == 5) {
                answers.add(p)
            } else {
                if (p.direction != LEFT) visit(p, p.row, p.col - 1, LEFT) // 좌
                if (p.direction != UP) visit(p, p.row - 1, p.col, UP) // 상
                if (p.direction != RIGHT) visit(p, p.row, p.col + 1, RIGHT) // 우
                if (p.direction != DOWN) visit(p, p.row + 1, p.col, DOWN) // 하
            }
        }

        println(answers)

        return answers.minOfOrNull { it.count } ?: -1
    }

    fun dp2(
        map: Array<CharArray>,
        start: Point
    ): Int {
        val fakeMap = Array(map.size) { r -> CharArray(map[r].size) { c -> map[r][c] } }
        val answers = mutableListOf<Point>()

        // dp[dest][row][col][direction]
        // dest=0: start -> c1 or start -> c2
        // dest=1: c1 -> c2 or c2 -> c1
        // direction: 좌(0), 상(1), 우(2), 하(3)
        val dp = Array(3) { Array(map.size) { Array(map[0].size) {
            BooleanArray(4)
        } } }
        val queue = ArrayDeque<Point>().apply {
            add(start)
        }

        val rangeRow = map.indices
        val rangeCol = map[0].indices

        dp[0][start.row][start.col][LEFT] = true
        dp[0][start.row][start.col][UP] = true
        dp[0][start.row][start.col][RIGHT] = true
        dp[0][start.row][start.col][DOWN] = true

        var count = 0
        fun visit(prev: Point, row: Int, col: Int, direction: Int) {
            if (row !in rangeRow || col !in rangeCol || map[row][col] == '#')
                return

            if (dp[prev.dest][row][col][direction])
                return

            count++
            println("count=$count, queue.size=${queue.size}")
            println("prev: $prev")
            println("next: row=$row, col=$col, direction=$direction")

            val newPoint = if (map[row][col] == 'C') {
                dp[prev.dest][row][col][direction] = true
                dp[prev.dest + 1][row][col][direction] = true

                Point(row, col, prev.dest + 1, prev.count + 1)
            } else {
                dp[prev.dest][row][col][direction] = true

                Point(row, col, prev.dest, prev.count + 1)
            }

            queue.offer(newPoint)
            fakeMap[prev.row][prev.col] = '.'
            fakeMap[row][col] = 'P'
            fakeMap.forEach { println(it.joinToString("")) }
            println()

//            print(Char(BACK_SPACE))
//            print(Char(CR))
            readLine()
            //Thread.sleep(1000)
        }

        while (queue.isNotEmpty()) {
            val p = queue.poll()

            if (p.dest == 2) {
                answers.add(p)
            } else {
                visit(p, p.row, p.col - 1, LEFT) // 좌
                visit(p, p.row - 1, p.col, UP) // 상
                visit(p, p.row, p.col + 1, RIGHT) // 우
                visit(p, p.row + 1, p.col, DOWN) // 하
            }
        }

        println(answers)

        return answers.minOf { it.count }
    }

    data class Point(
        val row: Int,
        val col: Int,
        val dest: Int = 0,
        val direction: Int = 0,
        val count: Int = 0
    ) {
        override fun toString(): String {
            return "row=$row, col=$col, dest=$dest, direction=$direction, count=$count"
        }
    }
}

/*
[case1]
2 3
SCC
...
[case1] - answer
4

[case2]
1 5
C.C.S
[case2] - answer
-1

[case3]
3 3
#.#
CSC
#.#
[case3] - answer
5

[case4]
10 7
#.#....
##...#.
C#...#.
.....#.
..#....
..#S.#.
.##..#.
###..##
..C.#.#
###.#..
[case4] - answer
24

[case5]
3 36
#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#C
.................S..................
C#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#
[case5] - answer
155

*/