package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 9328
 * 열쇠
 *
 * 시간 제한: 1초
 * 메모리 제한: 256MB
 * */
class BOJ_9328_Key : BOJSolution(info(), testCases()) {

    companion object {
        private val DR = intArrayOf(0, -1, 0, 1)
        private val DC = intArrayOf(-1, 0, 1, 0)
        private val DOOR = IntRange('A'.code, 'Z'.code)
        private val KEY = IntRange('a'.code, 'z'.code)
    }

    override fun runEachSolution() {
        // 테스트 케이스 (T ≤ 100)
        val T = readLine()!!.toInt()
        val answers = IntArray(T)

        for (case in 0 until T) {
            //  빌딩 지도의 높이(H)와 너비(W) (2 ≤ H, W ≤ 100)
            val (H, W) = readLine()!!.split(" ").map { it.toInt() }
            val map: Array<CharArray> = Array(H) { charArrayOf() }

            for (h in 0 until H) {
                map[h] = readLine()!!.toCharArray()
            }

            val keys = readLine()!!.toCharArray().toMutableSet()
            keys.remove('0')

            answers[case] = solution(H, W, map, keys)
        }

        answers.forEach { println(it) }
    }

    private fun solution(
        H: Int, W: Int,
        map: Array<CharArray>,
        keys: MutableSet<Char>
    ): Int {
        var documentCount = 0

        val entrances = mutableListOf<IntArray>()

        // 건물 가장자리의 입구 찾기
        for (r in 0 until H) for (c in 0 until W) {
            if ((r in 1..H-2 && c in 1..W-2) || map[r][c] == '*')
                continue

            entrances.add(intArrayOf(r, c))
        }

        // 빌딩의 모든 입구마다 bfs 탐색
        // key 보유 갯수가 더 이상 증가하지 않을 때까지 루프를 돌림
        var prevKeyCount: Int
        do {
            prevKeyCount = keys.size

            for (i in entrances.indices) {
                val (r, c) = entrances[i]

                documentCount += bfs(H, W, map, keys, r, c)
            }

        } while (prevKeyCount != keys.size)

        return documentCount
    }

    private fun bfs(
        H: Int, W: Int,
        map: Array<CharArray>,
        keys: MutableSet<Char>,
        r0: Int, c0: Int
    ): Int {
        var documentCount = 0

        val queue = ArrayDeque<IntArray>()
        var visited = Array(H) { BooleanArray(W) }

        queue.offer(intArrayOf(r0, c0))
        visited[r0][c0] = true

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            val mark = map[r][c]

            when (mark.code) {
                in DOOR -> {
                    if (!keys.contains(mark.lowerCase))
                        continue

                    map[r][c] = '.'
                }
                in KEY -> {
                    map[r][c] = '.'

                    keys.add(mark)

                    visited = Array(H) { BooleanArray(W) }
                    visited[r][c] = true

                    queue.clear()
                }
                '$'.code -> {
                    map[r][c] = '.'

                    documentCount += 1
                }
            }

            // next
            for (d in 0..3) {
                val rn = r + DR[d]
                val cn = c + DC[d]

                if (rn !in 0 until H || cn !in 0 until W || visited[rn][cn] || map[rn][cn] == '*')
                    continue

                visited[rn][cn] = true
                queue.offer(intArrayOf(rn, cn))
            }
        }

        return documentCount
    }

    private val Char.lowerCase: Char
        get() = (code xor 32).toChar()
}

private fun info() = BOJSolution.Info(
    no = 9328,
    title = "열쇠",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/9328",
    solutionUrl = ""
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "3\n" +
                "5 17\n" +
                "*****************\n" +
                ".............**$*\n" +
                "*B*A*P*C**X*Y*.X.\n" +
                "*y*x*a*p**$*$**$*\n" +
                "*****************\n" +
                "cz\n" +

                "5 11\n" +
                "*.*********\n" +
                "*...*...*x*\n" +
                "*X*.*.*.*.*\n" +
                "*$*...*...*\n" +
                "***********\n" +
                "0\n" +

                "7 7\n" +
                "*ABCDE*\n" +
                "X.....F\n" +
                "W.$$$.G\n" +
                "V.$$$.H\n" +
                "U.$$$.J\n" +
                "T.....K\n" +
                "*SQPML*\n" +
                "irony\n",

        output = "3\n" +
                "1\n" +
                "0"
    ),
    BOJSolution.TestCase(
        input = "3\n" +
                "5 17\n" +
                "*****************\n" +
                ".............**$*\n" +
                "*B*A*P*C**X*Y*bX.\n" +
                "*y*x*a*p**$*$**$*\n" +
                "*****************\n" +
                "cz\n" +
                
                "5 20\n" +
                "********************\n" +
                ".....*..............\n" +
                "**X*R*B*A*P*C**X**Y*\n" +
                "**b*$*y*x*a*p**$*r$*\n" +
                "********************\n" +
                "cz\n" +

                "6 22\n" +
                "**********************\n" +
                ".....*................\n" +
                "**X*R*B*A*P*C**X****Y*\n" +
                "**b*$*y*x*a*p**$***r$*\n" +
                "*********k*********K**\n" +
                "**********************\n" +
                "cz\n",

        output = "4\n" +
                "3\n" +
                "3"
    ),
)
/*
[반례 1]
5 20
********************
.....*..............
**X*R*B*A*P*C**X**Y*
**b*$*y*x*a*p**$*r$*
********************
cz

cz
pax
b
yr
$

1 -> XR
2 -> BY
1 -> R
2 ->
1 ->

[반례 2]
6 22
**********************
.....*................
**X*R*B*A*P*C**X****Y*
**b*$*y*x*a*p**$***r$*
*********k********$K**
**********************
cz


* */