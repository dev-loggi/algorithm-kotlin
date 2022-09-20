package programmers.practice.level2

import programmers.Programmers.Solution


/**
 * 삼각 달팽이
 * https://programmers.co.kr/learn/courses/30/lessons/68645
 * */
class TriangleSnail : Solution {

    override fun execute() {
        var answer: IntArray

        answer = solution(4)
        println("answer=${answer.contentToString()}")
        // [1,2,9,3,10,8,4,5,6,7]

        answer = solution(5)
        println("answer=${answer.contentToString()}")
        // [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]

        answer = solution(6)
        println("answer=${answer.contentToString()}")
        // [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
    }

    class TriangleCoordinate(n: Int) {

        val isRunning: Boolean
            get() = number < maxNumber

        val result: IntArray
            get() = coordinate.flatten().filterNot { it == 0 }.toIntArray()

        val coordinate = Array(n) { Array(n) { 0 } }
        private val maxNumber = (0..n).sum()

        private val range = 0 until n
        private var direction = DOWN
        private var curX = 0
        private var curY = -1
        private var number = 0

        fun next() {
            checkDirection()

            curX = getNextX()
            curY = getNextY()

            coordinate[curY][curX] = ++number
        }

        private fun checkDirection() {
            val nextX = getNextX()
            val nextY = getNextY()

            if (nextX !in range || nextY !in range) {
                direction = (direction + 1) % 3
            } else if (coordinate[nextY][nextX] != 0) {
                direction = (direction + 1) % 3
            }
        }

        private fun getNextX(): Int = when (direction) {
            DOWN -> curX
            RIGHT -> curX + 1
            LEFT_UP -> curX - 1
            else -> throw IllegalArgumentException("getNextX(): direction=$direction, curX=$curX")
        }

        private fun getNextY(): Int = when (direction) {
            DOWN -> curY + 1
            RIGHT -> curY
            LEFT_UP -> curY - 1
            else -> throw IllegalArgumentException("getNextY(): direction=$direction, curY=$curY")
        }

        companion object {
            private const val DOWN = 0 // 하
            private const val RIGHT = 1 // 우
            private const val LEFT_UP = 2 // 좌상
        }
    }

    fun solution(n: Int): IntArray {
        val coordinate = TriangleCoordinate(n)

        while (coordinate.isRunning) {
            coordinate.next()
        }

        coordinate.coordinate.forEach {
            println(it.contentToString())
        }

        return coordinate.result
    }
}