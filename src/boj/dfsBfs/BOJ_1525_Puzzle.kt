package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 1525
 * 퍼즐
 * https://www.acmicpc.net/problem/1525
 * https://jsl663.tistory.com/31
 * */
class BOJ_1525_Puzzle : BOJSolution(info(), testCases()) {

    companion object {
        private const val DEST = "123456780"
        private val RANGE = 0..2
        private val DX = intArrayOf(-1, 0, 1, 0)
        private val DY = intArrayOf(0, -1, 0, 1)
    }

    override fun main() {
        var puzzle = ""

        for (i in 0 until 3) {
            puzzle += readLine()
        }

        solution(puzzle.replace(" ", ""))
            .let { println(it) }
    }

    fun solution(puzzle: String): Int {
        if (puzzle == DEST)
            return 0

        val puzzleSet = mutableSetOf(puzzle)
        val queue = ArrayDeque(listOf(puzzle))

        var count = 0 // 이동 횟수

        while (queue.isNotEmpty()) { // BFS
            count++

            for (i in queue.indices) { // BFS 한 사이클
                val p = queue.poll()
                val idx = p.indexOf('0')

                for (dir in 0 until 4) { // 빈 칸('0') 상, 하, 좌, 우 이동
                    val next = p.move(idx, dir)
                        ?: continue

                    if (next == DEST) // 정렬 완료
                        return count

                    if (puzzleSet.contains(next)) // 중복 체크
                        continue

                    queue.offer(next)
                    puzzleSet.add(next)
                }
            }
        }

        return -1
    }

    private fun String.move(i: Int, d: Int): String? {
        val nx = i % 3 + DX[d]
        val ny = i / 3 + DY[d]
        if (nx !in RANGE || ny !in RANGE)
            return null

        val ni = ny * 3 + nx
        val sb = StringBuilder(this)
        sb.setCharAt(i, this[ni])
        sb.setCharAt(ni, this[i])
        return sb.toString()
    }
}

private fun info() = BOJSolution.Info(
    no = 1525,
    title = "퍼즐",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/1525",
    solutionUrl = "https://jsl663.tistory.com/31"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "1 0 3\n" +
                "4 2 5\n" +
                "7 8 6",
        output = "3"
    ),
    BOJSolution.TestCase(
        input = "3 6 0\n" +
                "8 1 2\n" +
                "7 4 5",
        output = "-1"
    )
)