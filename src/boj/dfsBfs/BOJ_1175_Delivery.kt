package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 1175
 * 배달
 * https://www.acmicpc.net/problem/1175
 * bfs
 * */
class BOJ_1175_Delivery : BOJSolution(info(), testCases()) {

    companion object {
        private val MR = intArrayOf(0, -1, 0, 1) // move row
        private val MC = intArrayOf(-1, 0, 1, 0) // move col
    }

    override fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 세로 크기
        val M = input[1].toInt() // 가로 크기

        val map = Array(N) { CharArray(M) }

        var cnt = 0
        for (r in 0 until N) {
            map[r] = readLine()!!.toCharArray()

            for (c in map[r].indices) {
                if (map[r][c] == 'C') {
                    if (cnt == 0) cnt++
                    else map[r][c] = 'D' // 두 번째 목적지를 'D'로 치환
                }
            }
        }

        solution(map, N, M).let { println(it) }
    }

    fun solution(map: Array<CharArray>, N: Int, M: Int): Int {
        val rangeR = 0 until N
        val rangeC = 0 until M

        val (sr, sc) = map.find { it == 'S' } // 민식이의 초기 위치(sr, sc)

        // visited[s][r][c][d]
        // s=0: S -> C 까지의 경로
        // s=1: C -> D
        // s=2: D -> C
        // r: row, c: col, d: direction
        val visited = Array(3) { Array(N) { Array(M) { BooleanArray(4) } } }
        for (d in 0 until 4) visited[0][sr][sc][d] = true

        val queue = ArrayDeque<IntArray>()
        queue.offer(intArrayOf(0, sr, sc, -1)) // 출발지

        var count = 0
        while (queue.isNotEmpty()) { // BFS
            count++

            for (i in queue.indices) { // 큐 한 사이클 돌기
                val (s, r, c, d) = queue.poll()

                for (dn in 0 until 4) { // 상, 하, 좌, 우 이동
                    if (d == dn) // 기존 방향 제외
                        continue

                    val rn = r + MR[dn]
                    val cn = c + MC[dn]

                    if (rn !in rangeR || cn !in rangeC || map[rn][cn] == '#' || visited[s][rn][cn][dn])
                        continue

                    val block = map[rn][cn]

                    if (block == 'C' || block == 'D') {
                        if (s == 0) { // 목적지 첫 번째 도착
                            val next = if (block == 'C') 1 else 2

                            // 방금 거쳐간 목적지에 다시 들어오지 않게 하기
                            for (j in 0 until 4)
                                visited[next][rn][cn][j] = true

                            queue.offer(intArrayOf(next, rn, cn, dn))
                        } else { // 두 번째 목적지 도착
                            return count
                        }
                    } else {
                        visited[s][rn][cn][dn] = true
                        queue.offer(intArrayOf(s, rn, cn, dn))
                    }
                }
            }
        }

        return -1
    }

    private fun Array<CharArray>.find(predicate: (Char) -> Boolean): Pair<Int, Int> {
        for (r in indices) for (c in this[r].indices)
            if (predicate(this[r][c])) return r to c
        return -1 to -1
    }
}

private fun info() = BOJSolution.Info(
    no = 1175,
    title = "배달",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/1175"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase( // case 1
        input = "2 3\n" +
                "SCC\n" +
                "...",
        output = "4"
    ),
    BOJSolution.TestCase( // case 2
        input = "1 5\n" +
                "C.C.S",
        output = "-1"
    ),
    BOJSolution.TestCase( // case 3
        input = "3 3\n" +
                "#.#\n" +
                "CSC\n" +
                "#.#",
        output = "5"
    ),
    BOJSolution.TestCase( // case 4
        input = "10 7\n" +
                "#.#....\n" +
                "##...#.\n" +
                "C#...#.\n" +
                ".....#.\n" +
                "..#....\n" +
                "..#S.#.\n" +
                ".##..#.\n" +
                "###..##\n" +
                "..C.#.#\n" +
                "###.#..",
        output = "24"
    ),
    BOJSolution.TestCase( // case 5
        input = "3 36\n" +
                "#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#C\n" +
                ".................S..................\n" +
                "C#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#.#",
        output = "155"
    ),
)