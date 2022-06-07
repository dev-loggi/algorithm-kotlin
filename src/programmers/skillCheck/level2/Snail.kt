package programmers.skillCheck.level2

import com.sun.org.apache.xalan.internal.xsltc.DOM

class Snail {

    // n=4, [1,2,9,3,10,8,4,5,6,7]
    // n=5, [1,2,12,3,13,11,4,14,15,10,5,6,7,8,9]
    // n=6, [1,2,15,3,16,14,4,17,21,13,5,18,19,20,12,6,7,8,9,10,11]
    fun solution(n: Int): IntArray {
        val board = Array(n) { Array(n) { 0 } }
        val horse = Horse()

        val count = n * (n + 1) / 2

        (1..count).forEach { num ->
            horse.next()
            if (board.getOrNull(horse.raw)?.getOrNull(horse.col) == 0) {
                board[horse.raw][horse.col] = num
            } else {
                horse.changeDirection()
                horse.next()
                board[horse.raw][horse.col] = num
            }
        }

        return board.flatMap { it.filterNot { num -> num == 0 } }.toIntArray()
    }

    data class Horse(
        var col: Int = 0,
        var raw: Int = -1,
        var direction: Int = DOWN
    ) {

        fun back() {
            when (direction) {
                DOWN -> raw--
                RIGHT -> col--
                LEFT_UP -> { col++; raw++ }
            }
        }

        fun next() {
            when (direction) {
                DOWN -> raw++
                RIGHT -> col++
                LEFT_UP -> { col--; raw-- }
            }
        }

        fun changeDirection() {
            back()
            direction = (direction + 1) % 3
        }

        companion object {
            const val DOWN = 0
            const val RIGHT = 1
            const val LEFT_UP = 2
        }
    }


}