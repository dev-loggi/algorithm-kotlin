package programmers.practice.level1

import Solution

/**
 * 2016년
 * https://programmers.co.kr/learn/courses/30/lessons/12901?language=kotlin
 * */
class Year2016 : Solution {

    val days = intArrayOf(0, 31, 29, 31, 30, 31, 31, 30, 31, 30, 31, 30, 31)
    val dayOfWeek = arrayOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    override fun execute() {
        solution(5, 24).let { println(it) } // "TUE"
    }

    fun solution(a: Int, b: Int): String {
        solution2(1, 1).let { println(it) }
        solution2(5, 24).let { println(it) }
        solution2(5, 24).let { println(it) }
        solution2(5, 24).let { println(it) }
        solution2(5, 24).let { println(it) }

        for (i in 0..3) {
            for (j in 1..7) {
                val day = i * 7 + j
                val res = solution2(1, day)
                print("1월 ${day}일: $res ")
            }
            println()
        }

        return (1 until a)
            .sumOf { days[it] }
            .let { dayOfWeek[(it + b + 4) % 7] }
    }

    fun solution2(a: Int, b: Int): String {
        return (1 until a)
            .sumOf { days[it] }
            .let { dayOfWeek[(it + b + 4) % 7] }
    }
}