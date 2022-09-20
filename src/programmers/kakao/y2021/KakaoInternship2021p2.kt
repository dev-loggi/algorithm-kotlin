package programmers.kakao.y2021

import test.Solution
import java.util.ArrayDeque

class KakaoInternship2021p2 : Solution {

    override fun execute() {
        // ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"],
        // ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"],
        // ["PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"],
        // ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"],
        // ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]
        // [1, 0, 1, 1, 1]
        val answer = solution(arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
        ))
        println("answer=${answer.contentToString()}")
    }

    data class Point(val x: Int, val y: Int) {
        override fun toString(): String = "($x, $y)"
    }

    fun solution(places: Array<Array<String>>): IntArray {
        return places
            .map { checkDistancing(it.map { s -> s.toCharArray() }) }
            .toIntArray()
    }

    /** 각 방 탐색 */
    private fun checkDistancing(place: List<CharArray>): Int {
        println("checkDistancing()")
        place.forEach { println(it.contentToString()) }

        for ((y, arr) in place.withIndex()) {
            for ((x, c) in arr.withIndex()) {
                if (c == 'P') {
                    if (!bfs(place, Point(x, y)))
                        return 0
                }
            }
        }

        return 1
    }

    private fun bfs(place: List<CharArray>, start: Point): Boolean {
        var isKeepDistancing = true

        println("bfs(): start=$start")

        val queue = ArrayDeque(listOf(start))
        val distances = Array(place.size) { Array(place.size) { -1 } }
        distances[start.y][start.x] = 0

        // (좌, 우, 상, 하) 방문: return false -> 거리두기 x
        val visit = { cur: Point, next: Point ->

            if (next.x in place.indices && next.y in place.indices) {
                val curDistance = distances[cur.y][cur.x]
                if (curDistance < 2) {
                    distances[next.y][next.x] = curDistance + 1

                    when (place[next.y][next.x]) {
                        'O' -> queue.offer(next)
                        'P' -> isKeepDistancing = false
                    }
                }
            }

            isKeepDistancing
        }

        while (queue.isNotEmpty()) {
            val point = queue.poll()
            println("bfs, queue.poll()=$point")

            if (!visit(point, Point(point.x + 1, point.y))) break // 우
            if (!visit(point, Point(point.x, point.y + 1))) break // 하
            if (!visit(point, Point(point.x - 1, point.y))) break // 좌
            if (!visit(point, Point(point.x, point.y - 1))) break // 상
        }

        return isKeepDistancing
    }
}