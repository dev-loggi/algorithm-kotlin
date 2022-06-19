package boj.dfsBfs

import common.Solution
import java.util.ArrayDeque


/**
 * 1012
 * 유기농 배추
 * https://www.acmicpc.net/problem/1012
 * bfs
 * */
class BOJ_1012_OrganicCabbage : Solution {

/*
[case1]
2
10 8 17
0 0
1 0
1 1
4 2
4 3
4 5
2 4
3 4
7 4
8 4
9 4
7 5
8 5
9 5
7 6
8 6
9 6
10 10 1
5 5
[case1 answer]
5
1

[case2]
1
5 3 6
0 2
1 2
2 2
3 2
4 2
4 0
[case2 answer]
2
*/
    override fun execute() {
        for (i in 0 until 2) main()
    }

    fun main() {
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