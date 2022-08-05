package programmers.practice.level1

import Solution

/**
 * 2016년
 * https://programmers.co.kr/learn/courses/30/lessons/12901?language=kotlin
 * */
class Year2016 : Solution {

    override fun execute() {
        solution(5, 24).let { println(it) } // TUE
    }

    private val days = arrayOf(0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    private val dayOfWeek = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    fun solution(a: Int, b: Int): String {
        return dayOfWeek[((1 until a).sumOf { days[it] } + b + 4) % 7]
    }
}