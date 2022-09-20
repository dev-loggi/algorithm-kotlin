package programmers.practice.level1

import programmers.Programmers.Solution
import java.util.*

/**
 * 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061?language=kotlin
 *
 * [0, 0, 0, 0, 0]
 * [0, 0, 1, 0, 3],
 * [0, 2, 5, 0, 1],
 * [4, 2, 4, 4, 2],
 * [3, 5, 1, 3, 1]
 *
 * [1, 5, 3, 5, 1, 2, 1, 4]
 * result = 4
 */
class CranePuppetGame : Solution {

    override fun execute() {
        val answer = solution(arrayOf(
            intArrayOf(0,0,0,0,0),
            intArrayOf(0,0,1,0,3),
            intArrayOf(0,2,5,0,1),
            intArrayOf(4,2,4,4,2),
            intArrayOf(3,5,1,3,1),
        ),
            intArrayOf(1,5,3,5,1,2,1,4)
        )
        println("answer=$answer")

        val queue = ArrayDeque<Int>()
        queue.poll()
    }

    private fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0

        val boardLines = Array(board.size) { ArrayDeque<Int>() }
        val basket = ArrayDeque<Int>()

        board.forEachIndexed { raw, rawArray ->
            rawArray.forEachIndexed { col, puppet ->
                if (puppet != 0) {
                    boardLines[col].offer(puppet)
                }
            }
        }

        for (line in moves) {
            val puppet = boardLines[line - 1].poll() ?: continue
            if (puppet == basket.peekFirst()) {
                basket.pop()
                answer += 2
            } else {
                basket.push(puppet)
            }
        }

        return answer
    }
}