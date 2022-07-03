package programmers.practice.level2

import Solution
import java.math.BigDecimal


/**
 * 가장 큰 수.
 * 정렬
 * https://programmers.co.kr/learn/courses/30/lessons/42746?language=kotlin
 * */
class LargestNumber : Solution {

    override fun execute() {
        var answer: String = solution(
            intArrayOf(6, 10, 2)
        )
        println("answer=$answer") // "6210"

        answer = solution(
            intArrayOf(3, 30, 34, 5, 9)
        )
        println("answer=$answer") // "9534330"

        answer = solution(
            intArrayOf(3, 30, 34, 332, 334, 343, 345, 552, 998, 1000, 100, 10, 5, 9, 0)
        )
        println("answer=$answer")

        val testCase = Array(1001) { it }.toIntArray()
        answer = solution(testCase)
        println("answer=$answer")
    }

    private val comparator = Comparator<Int> { o1, o2 ->
        "$o2$o1".compareTo("$o1$o2")
    }

    fun solution(numbers: IntArray): String {
        println(numbers.contentToString())

        return numbers.sortedWith(comparator)
            .also { println(it) }
            .joinToString("")
            .let { BigDecimal(it).toString() }
    }

    fun solution2(numbers: IntArray): String =
        numbers.sortedWith { o1, o2 -> "$o2$o1".compareTo("$o1$o2") }
            .joinToString("")
            .let { BigDecimal(it).toString() }
}
