package programmers.practice.level2

import Solution
import kotlin.math.ceil


/**
 * 기능 개발
 * 스택/큐
 * https://programmers.co.kr/learn/courses/30/lessons/42586?language=kotlin
 * */
class FunctionDevelopment : Solution {

    override fun execute() {
        var answer: IntArray = solution(
            intArrayOf(93, 30, 55),
            intArrayOf(1, 30, 5)
        )
        println("answer=${answer.contentToString()}") // 2, 1

        answer = solution(
            intArrayOf(95, 90, 99, 99, 80, 99),
            intArrayOf(1, 1, 1, 1, 1, 1)
        )
        println("answer=${answer.contentToString()}") // 1, 3, 2
    }

    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val workingDays = Array(progresses.size) { 0 }

        // case2: 5, 10, 1, 1, 20, 1
        for (i in workingDays.indices) {
            workingDays[i] = ceil((100 - progresses[i]) / speeds[i].toDouble()).toInt()
        }
        println("workingDays=${workingDays.contentToString()}")

        // case2: 5, 10, 10, 10, 20, 20
        for (i in 1..workingDays.lastIndex) {
            if (workingDays[i - 1] > workingDays[i]) {
                workingDays[i] = workingDays[i - 1]
            }
        }
        println("workingDays=${workingDays.contentToString()}")

        workingDays.distinct()
            .forEach { day ->
                answer.add(workingDays.count { day == it })
            }

        return answer.toIntArray()
    }
}