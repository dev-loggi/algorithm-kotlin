package programmers.practice.level1

import Solution

/*
 * 없는 숫자 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/86051?language=kotlin
 * */
class AddMissingNumbers : Solution {

    override fun execute() {
        solution(intArrayOf(1,2,3,4,6,7,8,0)).let { println("answer=$it") } // 14
        solution(intArrayOf(5,8,4,0,6,7,9)).let { println("answer=$it") } // 6
    }

    fun solution(numbers: IntArray): Int {
        return 45 - numbers.sum()
    }
}