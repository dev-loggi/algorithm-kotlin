package programmers.kakao.y2022

import programmers.Programmers.Solution

/**
 *
 * */
class KakaoInternship2022p5 : Solution {

    companion object {
        private const val SHIFT_ROW = "ShiftRow"
        private const val ROTATE = "Rotate"
    }

    override fun execute() {
        var answer: Array<IntArray>

        answer = solution(
            arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            ),
            arrayOf(ROTATE, SHIFT_ROW)
        )

        answer.forEach { println(it.contentToString()) }
        println()
        println()
        println()

        answer = solution(
            arrayOf(
                intArrayOf(8, 6, 3),
                intArrayOf(3, 3, 7),
                intArrayOf(8, 4, 9)
            ),
            arrayOf(ROTATE, SHIFT_ROW, SHIFT_ROW)
        )

        answer.forEach { println(it.contentToString()) }
        println()
        println()
        println()

        answer = solution(
            arrayOf(
                intArrayOf(1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8),
                intArrayOf(9, 10, 11, 12),
            ),
            arrayOf(SHIFT_ROW, ROTATE, SHIFT_ROW, ROTATE)
        )

        answer.forEach { println(it.contentToString()) }
    }

    fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {

        val matrix = Matrix(rc)
        println(matrix)

        operations.forEach {
            when (it) {
                SHIFT_ROW -> matrix.shiftRow()
                ROTATE -> matrix.rotation()
            }
        }

        return matrix.rc
    }

    class Matrix(val rc: Array<IntArray>) {
        private val rowSize = rc.size
        private val colSize = rc[0].size

        private var curX = 0
        private var curY = 1
        private var direction = DOWN

        fun shiftRow() {
            val lastRow = rc.last()
            for (i in rc.lastIndex downTo 1) {
                rc[i] = rc[i - 1]
            }
            rc[0] = lastRow
        }

        fun rotation() {
            curX = 0
            curY = 1
            direction = DOWN
            checkDirection()
            val first = rc[curY][curX]
            val count = (rowSize + colSize) * 2 - 4

            for (i in 0 until count - 1) {
                checkDirection()

                val nextX = getNextX()
                val nextY = getNextY()

                println("d=$direction: ($curX, $curY) -> ($nextX, $nextY)")

                rc[curY][curX] = rc[nextY][nextX]

                curX = nextX
                curY = nextY
            }

            rc[0][0] = first
        }

        private fun checkDirection() {
            direction = when {
                curX == 0 && curY == rowSize - 1 -> RIGHT
                curX == colSize - 1 && curY == rowSize - 1 -> UP
                curX == colSize - 1 && curY == 0 -> LEFT
                curX == 0 && curY == 0 -> DOWN
                else -> direction
            }
        }

        private fun getNextX(): Int = when (direction) {
            DOWN, UP -> curX
            RIGHT -> curX + 1
            LEFT -> curX - 1
            else -> throw IllegalArgumentException()
        }


        private fun getNextY(): Int = when (direction) {
            RIGHT, LEFT -> curY
            DOWN -> curY + 1
            UP -> curY - 1
            else -> throw IllegalArgumentException()
        }

        override fun toString(): String =
            rc.joinToString("\n") { it.contentToString() }

        companion object {
            private const val DOWN = 0
            private const val RIGHT = 1
            private const val UP = 2
            private const val LEFT = 3
        }
    }
}