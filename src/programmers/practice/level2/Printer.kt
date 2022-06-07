package programmers.practice.level2

import common.Solution
import java.util.*

/**
 * 프린터
 * 스택/큐
 * https://programmers.co.kr/learn/courses/30/lessons/42587?language=kotlin
 * */
class Printer : Solution {

    override fun execute() {
        var answer: Int

        answer = solution(intArrayOf(2, 1, 3, 2), 2)
        println("answer=$answer") // 1

        answer = solution(intArrayOf(1, 1, 9, 1, 1, 1), 0)
        println("answer=$answer") // 5
    }

    fun solution(priorities: IntArray, location: Int): Int {
        var count = 0

        val queue = ArrayDeque(List(priorities.size) { it })

        while (queue.isNotEmpty()) {
            val printerId = queue.poll()
            val printerPriority = priorities[printerId]

            if (queue.any { priorities[it] > printerPriority }) {
                queue.offer(printerId)
            } else {
                count++

                if (printerId == location)
                    break
            }
        }

        return count
    }
}