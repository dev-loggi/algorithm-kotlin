package programmers.practice.level1

import Solution

/**
 * 나누어 떨어지는 숫자 배열
 * https://school.programmers.co.kr/learn/courses/30/lessons/12910?language=kotlin
 * */
class DivisibleIntArray : Solution {

    override fun execute() {
        solution(intArrayOf(5, 9, 7, 10), 5).let { println(it.contentToString()) } // [5, 10]
        solution(intArrayOf(2, 36, 1, 3), 1).let { println(it.contentToString()) } // [1, 2, 3, 36]
        solution(intArrayOf(3,2,6), 10).let { println(it.contentToString()) } // [-1]
    }

    fun solution(arr: IntArray, divisor: Int): IntArray {
        return arr.filter { it % divisor == 0 }.sorted()
            .let { if (it.isEmpty()) intArrayOf(-1) else it.toIntArray() }
    }
}