package programmers.practice.level1

import common.Solution
import kotlin.math.max

/**
 * 부족한 금액 계산하기
 * https://programmers.co.kr/learn/courses/30/lessons/82612?language=kotlin
 * */
class CalculateShortfall : Solution {

    override fun execute() {
        solution(3, 20, 4).let { println(it) } // 10
    }

    fun solution(price: Int, money: Int, count: Int): Long {
        return max((1L..count).sumOf { it * price } - money, 0)
    }
}