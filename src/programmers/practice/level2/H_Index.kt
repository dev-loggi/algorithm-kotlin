package programmers.practice.level2

import Solution
import kotlin.math.min


/**
 * H-Index(과학자의 생산성과 영향력을 나타내는 지표)
 * 정렬
 * https://programmers.co.kr/learn/courses/30/lessons/42747?language=kotlin
 * */
class H_Index : Solution {

    override fun execute() {
        var answer: Int = bestSolution(intArrayOf(3, 0, 6, 1, 5))
        println("answer=$answer") // 3

        answer = bestSolution(intArrayOf(10, 8, 5, 4, 3))
        println("answer=$answer") // 4

        answer = bestSolution(intArrayOf(25, 8, 5, 3, 3))
        println("answer=$answer") // 3

        answer = bestSolution(intArrayOf(0))
        println("answer=$answer") // 0

        answer = bestSolution(intArrayOf(0, 0))
        println("answer=$answer") // 0

        answer = bestSolution(intArrayOf(0, 1))
        println("answer=$answer") // 1
    }

    fun solution(citations: IntArray): Int {
        var answer = citations.size

        for (h in 1 .. citations.size) {
            if (citations.count { it >= h } < h) {
                answer = h - 1
                break
            }
        }

        return answer
    }

    fun bestSolution(citations: IntArray): Int =
        citations.sortedDescending()
            .mapIndexed { index, value -> min(index + 1, value) }
            .maxOf { it }
}