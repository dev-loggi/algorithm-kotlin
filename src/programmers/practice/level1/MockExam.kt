package programmers.practice.level1

import Solution

/**
 * 모의고사
 * 완전 탐색
 * https://programmers.co.kr/learn/courses/30/lessons/42840
 *
 * 1) 1, 2, 3, 4, 5
 * 2) 2, 1, 2, 3, 2, 4, 2, 5
 * 3) 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
 * */
class MockExam : Solution {

    override fun execute() {
        var answer: IntArray = solution(intArrayOf(1,2,3,4,5))
        println("answer=${answer.contentToString()}") // 1

        answer = solution(intArrayOf(1, 3, 2, 4, 2))
        println("answer=${answer.contentToString()}") // 1, 2, 3
    }

    fun solution(answers: IntArray): IntArray {
        val answer = mutableListOf<Int>()

        val counts = intArrayOf(0, 0, 0)
        val patterns = arrayOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
            intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)
        )

        answers.forEachIndexed { index, answer ->
            if (patterns[0][index % patterns[0].size] == answer) counts[0]++
            if (patterns[1][index % patterns[1].size] == answer) counts[1]++
            if (patterns[2][index % patterns[2].size] == answer) counts[2]++
        }

        val max = counts.maxOf { it }

        counts.forEachIndexed { index, count ->
            if (count == max) answer.add(index + 1)
        }

        return answer.sorted().toIntArray()
    }
}