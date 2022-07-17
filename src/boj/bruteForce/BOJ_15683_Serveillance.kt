package boj.bruteForce

import boj.BOJSolution

class BOJ_15683_Serveillance : BOJSolution(info(), testCases()) {

    override fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 사무실 세로 크기
        val M = input[1].toInt() // 사무실 가로 크기
        val office = Array(N) { IntArray(M) }

        for (i in 0 until N) {
            office[i] = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        }

        solution(N, M, office)
    }

    fun solution(N: Int, M: Int, office: Array<IntArray>) {
        val answers = sortedSetOf<Int>()
        val rangeR = 0 until N
        val rangeC = 0 until M
        val rm = intArrayOf(0, -1, 0, 1)
        val cm = intArrayOf(-1, 0, 1, 0)
        val cctvInfo = mapOf(
            1 to arrayOf(intArrayOf(0), intArrayOf(1), intArrayOf(2), intArrayOf(3)),
            2 to arrayOf(intArrayOf(0, 2), intArrayOf(1, 3)),
            3 to arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 0)),
            4 to arrayOf(intArrayOf(0, 1, 2), intArrayOf(1, 2, 3), intArrayOf(2, 3, 0), intArrayOf(3, 0, 1)),
            5 to arrayOf(intArrayOf(0, 1, 2, 3))
        )
        val cctvList = mutableListOf<IntArray>()
        var countOfWall = 0

        // find cctv
        for (r in office.indices) {
            for (c in office[r].indices) {
                if (office[r][c] in 1..5) {
                    cctvList.add(intArrayOf(r, c))
                } else if (office[r][c] == 6) {
                    countOfWall++
                }
            }
        }

        fun visit(R: Int, C: Int, dir: Int, visited: Array<BooleanArray>): Int {
            var count = 0
            var r = R
            var c = C
            while (r in rangeR && c in rangeC && office[r][c] != 6) {
                if (!visited[r][c]) count++

                visited[r][c] = true
                r += rm[dir]
                c += cm[dir]
            }
            return count
        }

        fun dfsCCTV(cctvIdx: Int, count: Int, visited: Array<BooleanArray>) {
            if (cctvIdx == cctvList.size) {
                answers.add(N * M - count - countOfWall)
                return
            }

            val cctv = cctvList[cctvIdx]
            val cctvNum = office[cctv[0]][cctv[1]]

            cctvInfo[cctvNum]!!.forEach { dirs ->
                val newVisited = visited.deepCopy()

                val sum = dirs.sumOf { dir ->
                    visit(cctv[0], cctv[1], dir, newVisited)
                }
                dfsCCTV(cctvIdx + 1, count + sum, newVisited)
            }
        }

        dfsCCTV(0, 0, Array(N) { BooleanArray(M) })

        println(answers.first())
    }

    private fun Array<BooleanArray>.deepCopy(): Array<BooleanArray> {
        return Array(size) { r -> BooleanArray(this[r].size) { c -> this[r][c] } }
    }

    private fun Array<BooleanArray>.log() {
        forEach { println(it.map { b -> if (b) 'T' else 'F' }.joinToString(" ")) }
        println()
    }
}

private fun info() = BOJSolution.Info(
    no = 15638,
    title = "감시",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/15683"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input =
        "4 6\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 1 0 6 0\n" +
                "0 0 0 0 0 0",
        output = "20"
    ),
    BOJSolution.TestCase(
        input =
        "6 6\n" +
                "0 0 0 0 0 0\n" +
                "0 2 0 0 0 0\n" +
                "0 0 0 0 6 0\n" +
                "0 6 0 0 2 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 5",
        output = "15"
    ),
    BOJSolution.TestCase(
        input =
        "6 6\n" +
                "1 0 0 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "0 0 1 0 0 0\n" +
                "0 0 0 1 0 0\n" +
                "0 0 0 0 1 0\n" +
                "0 0 0 0 0 1",
        output = "6"
    ),
    BOJSolution.TestCase(
        input =
        "6 6\n" +
                "1 0 0 0 0 0\n" +
                "0 1 0 0 0 0\n" +
                "0 0 1 5 0 0\n" +
                "0 0 5 1 0 0\n" +
                "0 0 0 0 1 0\n" +
                "0 0 0 0 0 1",
        output = "2"
    ),
    BOJSolution.TestCase(
        input =
        "1 7\n" +
                "0 1 2 3 4 5 6",
        output = "0"
    ),
    BOJSolution.TestCase(
        input =
        "3 7\n" +
                "4 0 0 0 0 0 0\n" +
                "0 0 0 2 0 0 0\n" +
                "0 0 0 0 0 0 4",
        output = "0"
    ),
    BOJSolution.TestCase(
        input =
        "1 1\n" +
                "1",
        output = "0"
    ),
    BOJSolution.TestCase(
        input =
        "8 8\n" +
                "0 0 0 0 3 0 0 0\n" +
                "0 1 0 0 0 0 0 6\n" +
                "0 0 0 3 0 0 1 0\n" +
                "0 0 1 0 0 0 0 4\n" +
                "0 0 0 0 0 1 0 0\n" +
                "0 6 6 0 0 0 1 0\n" +
                "0 0 0 0 0 0 0 0\n" +
                "0 0 0 0 0 0 0 0",
        output = "13"
    )
)