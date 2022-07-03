package programmers.practice.level1

import Solution


/**
 * 로또의 최고 순위와 최저 순위
 * https://programmers.co.kr/learn/courses/30/lessons/77484?language=kotlin
 *
 * */
class Lotto : Solution {

    override fun execute() {
        // [44, 1, 0, 0, 31, 25]
        // [31, 10, 45, 1, 6, 19]
        var answer: IntArray = solution(
            intArrayOf(44, 1, 0, 0, 31, 25),
            intArrayOf(31, 10, 45, 1, 6, 19)
        )
        println("answer=${answer.contentToString()}") // [3, 5]

        // [0, 0, 0, 0, 0, 0]
        // [38, 19, 20, 40, 15, 25]
        answer = solution(
            intArrayOf(0, 0, 0, 0, 0, 0),
            intArrayOf(38, 19, 20, 40, 15, 25)
        )
        println("answer=${answer.contentToString()}") // [1, 6]

        // [45, 4, 35, 20, 3, 9]
        // [20, 9, 3, 45, 4, 35]
        answer = solution(
            intArrayOf(45, 4, 35, 20, 3, 9),
            intArrayOf(20, 9, 3, 45, 4, 35)
        )
        println("answer=${answer.contentToString()}") // [1, 1]
    }

    private fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val matchingCount = lottos
            .filterNot { it == 0 }
            .distinct()
            .count { num -> win_nums.any { it == num } }

        return intArrayOf(
            getRank(matchingCount + lottos.count { it == 0 }),
            getRank(matchingCount)
        )
    }

    private fun getRank(count: Int): Int =
        if (count in 2..6) 7 - count
        else 6
}