package kakao.test

import Solution

class KakaoInternship2022ex1 : Solution {

    override fun execute() {
        var answer: IntArray

        answer = solution(arrayOf(
            intArrayOf(1, 4),
            intArrayOf(3, 4),
            intArrayOf(3, 10),
        ))
        println("answer=${answer.contentToString()}") // 1, 10

        answer = solution(arrayOf(
            intArrayOf(1, 1),
            intArrayOf(2, 2),
            intArrayOf(1, 2),
        ))
        println("answer=${answer.contentToString()}") // 2, 1
    }

    fun solution(v: Array<IntArray>): IntArray {
        val xList = v.map { it[0] }
        val x = xList.map { i -> xList.count { j -> i == j } }
            .indexOf(1)
            .let { xList[it] }

        val yList = v.map { it[1] }
        val y = yList.map { i -> yList.count { j -> i == j } }
            .indexOf(1)
            .let { yList[it] }

        return intArrayOf(x, y)
    }
}