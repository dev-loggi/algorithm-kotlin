package programmers.practice.level1

import Solution

/**
 * 3진법 뒤집기
 * https://programmers.co.kr/learn/courses/30/lessons/68935
 * */
class TernaryReversed : Solution {

    override fun execute() {
        solution(45).let { println(it) } // 7
        solution(125).let { println(it) } // 229
    }

    fun solution(n: Int): Int = n.toString(3).reversed().toInt(3)
}