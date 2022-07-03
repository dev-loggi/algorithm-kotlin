package programmers.practice.level1

import Solution

/**
 * 실패율
 * https://programmers.co.kr/learn/courses/30/lessons/42889?language=kotlin
 * */
class FailureRate : Solution {

    override fun execute() {
        bestSolution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3))
            .let { println("answer=${it.contentToString()}") } // [3,4,2,1,5]
        bestSolution(4, intArrayOf(4,4,4,4,4))
            .let { println("answer=${it.contentToString()}") } // [4,1,2,3]
    }

    fun solution(N: Int, stages: IntArray): IntArray {
        return (1..N)
            .map { n ->
                val stay = stages.count { it == n }
                val clear = stages.count { it >= n }
                n to if (clear == 0) 0.0 else stay / clear.toDouble()
            }
            .sortedByDescending { it.second }
            .map { it.first }
            .toIntArray()
    }

    fun bestSolution(N: Int, stages: IntArray): IntArray {
        val failurePoints = DoubleArray(N) { 0.0 }

        for (n in 1..N) {
            var stay = 0
            var clear = 0

            for (stage in stages) {
                if (n == stage) stay++
                if (n <= stage) clear++
            }

            failurePoints[n - 1] =
                if (clear == 0) 0.0
                else stay / clear.toDouble()
        }

        return failurePoints.mapIndexed { i, point -> i + 1 to point }
            .sortedByDescending { it.second }
            .map { it.first }
            .toIntArray()
    }
}