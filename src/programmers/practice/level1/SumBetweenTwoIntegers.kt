package programmers.practice.level1

import Solution
import kotlin.math.absoluteValue

/**
 * 두 정수 사이의 합
 * https://school.programmers.co.kr/learn/courses/30/lessons/12912?language=kotlin
 * */
class SumBetweenTwoIntegers : Solution {

    override fun execute() {
        solution(3, 5).let { println(it) } // 12
    }

    fun solution(a: Int, b: Int): Long {
        return ((a - b).absoluteValue + 1L) * (a + b) / 2
    }
}