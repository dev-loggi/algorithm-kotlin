package boj.unsolved

import boj.BOJSolution
import java.text.DecimalFormat

/**
 * 1913
 * 달팽이1
 * 구현
 * https://www.acmicpc.net/problem/1913
 *
 * 25 10 11 12 13
 * 24  9  2  3 14
 * 23  8  1  4 15
 * 22  7  6  5 16
 * 21 20 19 18 17
 * */
class BOJ_1913_Snail : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val N = readLine()?.toIntOrNull() ?: return
        val number = readLine()?.toIntOrNull() ?: return

        solution(N, number)
    }

    fun solution(N: Int, number: Int) {
        val matrix = Array(N) { IntArray(N) { 0 } }
        val range = 0 until N

        val cursor = Cursor()
        var answerCursor = Cursor()
        var count = N * N
        var direction = Cursor.DOWN

        matrix[cursor.row][cursor.col] = count--

        fun checkDirection(): Boolean {
            return cursor.row in range &&
                    cursor.col in range &&
                    matrix[cursor.row][cursor.col] == 0
        }

        for (i in 0 until N * N - 1) {
            cursor.next(direction)

            if (!checkDirection()) {
                cursor.back()
                direction = (direction + 1) % 4
                cursor.next(direction)
            }

            matrix[cursor.row][cursor.col] = count

            if (count == number) {
                answerCursor = Cursor(cursor.row + 1, cursor.col + 1)
            }

            count--
        }

        val pattern = Array((N * N).toString().length) { 0 }.joinToString("")
        matrix.forEach {
            //println(it.joinToString(" "))
            println(it.joinToString(" ") { i -> DecimalFormat(pattern).format(i) })
        }
        println(answerCursor)
    }

    class Cursor(row: Int = 0, col: Int = 0) {

        val row: Int
            get() = _row
        val col: Int
            get() = _col

        private var _row = row
        private var _col = col
        private var preRow = row
        private var preCol = col

        fun next(direction: Int) = apply {
            preRow = _row
            preCol = _col
            when (direction) {
                DOWN -> _row++
                RIGHT -> _col++
                UP -> _row--
                LEFT -> _col--
            }
        }

        fun back() = apply {
            _row = preRow
            _col = preCol
        }

        override fun toString(): String = "$row $col"

        companion object {
            const val DOWN = 0
            const val RIGHT = 1
            const val UP = 2
            const val LEFT = 3
        }
    }
}

private val _info = BOJSolution.Info(
    no = 1913,
    title = "달팽이1",
    category = arrayOf(BOJSolution.MATERIALIZATION),
    url = "https://www.acmicpc.net/problem/1913"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "7\n" +
                "35",
        output ="49 26 27 28 29 30 31\n" +
                "48 25 10 11 12 13 32\n" +
                "47 24 9 2 3 14 33\n" +
                "46 23 8 1 4 15 34\n" +
                "45 22 7 6 5 16 35\n" +
                "44 21 20 19 18 17 36\n" +
                "43 42 41 40 39 38 37\n" +
                "5 7"
    )
)