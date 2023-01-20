package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

class BOJ_1012_OrganicCabbage : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val T = readLine()?.toIntOrNull() ?: return // 테스트 케이스 갯수
        val answers = mutableListOf<Int>()

        for (i in 0 until T) {
            answers.add(solution())
        }

        answers.forEach { println(it) }
    }

    fun solution(): Int {
        // M: 가로 길이, N: 세로 길이, K: 배추의 갯수
        val (M, N, K) = readLine()?.split(" ")?.toInputLine2() ?: return 0

        val farm = Array(N) { IntArray(M) { 0 } }
        val visited = Array(N) { BooleanArray(M) { false } }

        for (i in 0 until K) {
            val (x, y) = readLine()?.split(" ")?.toInputLine3() ?: return 0
            farm[y][x] = 1
        }

        farm.forEach { println(it.contentToString()) }

        // traverse farm
        var count = 0
        for (y in farm.indices) {
            for (x in farm[y].indices) {
                if (visited[y][x] || farm[y][x] == 0) {
                    visited[y][x]
                    continue
                }

                count += 1
                visited[y][x] = true
                bfs(farm, visited, x, y)
            }
        }

        return count
    }

    fun bfs(farm: Array<IntArray>, visited: Array<BooleanArray>, x: Int, y: Int) {
        println("bfs(): x=$x, y=$y")
        val rangeX = farm[0].indices
        val rangeY = farm.indices
        val queue = ArrayDeque<Point>().apply { offer(Point(x, y)) }

        fun visit(x: Int, y: Int) {
            if (x !in rangeX || y !in rangeY || visited[y][x] || farm[y][x] == 0)
                return

            queue.offer(Point(x, y))
            visited[y][x] = true
        }

        while (queue.isNotEmpty()) {
            val p = queue.poll()

            visit(p.x, p.y + 1) // 상
            visit(p.x, p.y - 1) // 하
            visit(p.x + 1, p.y) // 좌
            visit(p.x - 1, p.y) // 우
        }
    }

    private fun List<String>?.toInputLine2(): InputLine2? {
        return if (this == null) null
        else InputLine2(this[0].toInt(), this[1].toInt(), this[2].toInt())
    }

    private fun List<String>?.toInputLine3(): InputLine3? {
        return if (this == null) null
        else InputLine3(this[0].toInt(), this[1].toInt())
    }

    data class InputLine2(val M: Int, val N: Int, val K: Int)
    data class InputLine3(val x: Int, val y: Int)
    data class Point(val x: Int, val y: Int)

}

private fun info() = BOJSolution.Info(
    no = 1012,
    title = "유기농 배추",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/1012"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "2\n" +
                "10 8 17\n" +
                "0 0\n" +
                "1 0\n" +
                "1 1\n" +
                "4 2\n" +
                "4 3\n" +
                "4 5\n" +
                "2 4\n" +
                "3 4\n" +
                "7 4\n" +
                "8 4\n" +
                "9 4\n" +
                "7 5\n" +
                "8 5\n" +
                "9 5\n" +
                "7 6\n" +
                "8 6\n" +
                "9 6\n" +
                "10 10 1\n" +
                "5 5",
        output ="5\n" +
                "1"
    ),
    BOJSolution.TestCase(
        input = "1\n" +
                "5 3 6\n" +
                "0 2\n" +
                "1 2\n" +
                "2 2\n" +
                "3 2\n" +
                "4 2\n" +
                "4 0",
        output ="2"
    )
)