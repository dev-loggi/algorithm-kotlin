package programmers.practice.level2

import programmers.Programmers
import java.util.*
import kotlin.collections.HashMap

/**
 * 할인 행사
 * https://school.programmers.co.kr/learn/courses/30/lessons/131127
 * */
class DiscountEvent : Programmers.Solution {

    override fun execute() {
        solution2(
            want = arrayOf("banana", "apple", "rice", "pork", "pot"),
            number = intArrayOf(3, 2, 2, 2, 1),
            discount = arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"),
        ).let { println(it) } // 3
        solution2(
            want = arrayOf("apple"),
            number = intArrayOf(10),
            discount = arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"),
        ).let { println(it) } // 0
    }

    fun solution2(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var dayCount = 0
        val counting = number.copyOf()

        for (day in 0..(discount.size - 10)) {
            System.arraycopy(number, 0, counting, 0, number.size)

            for (i in 0 until 10) {
                val foodIdx = want.indexOrNull(discount[day + i])
                    ?: break

                counting[foodIdx] -= 1
            }

            if (counting.all { it == 0 })
                dayCount += 1
        }

        return dayCount
    }

    private fun Array<String>.indexOrNull(element: String): Int? {
        val idx = indexOf(element)
        return if (idx != -1) idx else null
    }

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        // map[Food, Idx]
        val map = HashMap<String, Int>()

        for (i in want.indices) {
            map[want[i]] = i
        }

        var dayCount = 0
        val queue = ArrayDeque<Int>()
        val count = IntArray(want.size)

        // 첫 10일 먼저 카운팅
        for (day in 0 until 10) {
            val foodIdx = map[discount[day]] ?: -1

            queue.offer(foodIdx)

            if (foodIdx != -1)
                count[foodIdx] += 1
        }

        if (check(number, count))
            dayCount += 1

        for (day in 10 until discount.size) {
            val front = queue.poll()
            if (front != -1) {
                count[front] -= 1
            }

            val rear = map[discount[day]] ?: -1
            queue.offer(rear)
            if (rear != -1) {
                count[rear] += 1
            }

            if (check(number, count)) {
                dayCount += 1
            }
        }

        return dayCount
    }

    private fun check(arr1: IntArray, arr2: IntArray): Boolean {
        for (i in arr1.indices) {
            if (arr1[i] != arr2[i])
                return false
        }
        return true
    }
}