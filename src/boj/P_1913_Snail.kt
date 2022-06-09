package boj

import common.Solution
import java.text.DecimalFormat

/**
 * 1913
 * 달팽이1
 * 구현
 *
 * 25 10 11 12 13
 * 24  9  2  3 14
 * 23  8  1  4 15
 * 22  7  6  5 16
 * 21 20 19 18 17
 * */
class P_1913_Snail : Solution {

    override fun execute() {
        for (i in 0..10)
            solution()
    }

    fun solution() {
        val n = readLine()?.toIntOrNull() ?: return
        val num = readLine()?.toIntOrNull() ?: return

        val matrix = Array(n) { IntArray(n) { 0 } }
        val range = 0 until n

        val cursor = Cursor()
        var answerCursor = Cursor()
        var count = n * n
        var direction = Cursor.DOWN

        matrix[cursor.row][cursor.col] = count--

        fun checkDirection(): Boolean {
            return cursor.row in range &&
                    cursor.col in range &&
                    matrix[cursor.row][cursor.col] == 0
        }

        for (i in 0 until n * n - 1) {
            cursor.next(direction)

            if (!checkDirection()) {
                cursor.back()
                direction = (direction + 1) % 4
                cursor.next(direction)
            }

            matrix[cursor.row][cursor.col] = count

            if (count == num) {
                answerCursor = Cursor(cursor.row + 1, cursor.col + 1)
            }

            count--
        }

        val pattern = Array((n * n).toString().length) { 0 }.joinToString("")
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