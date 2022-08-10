package programmers.practice.level2

import Solution

/**
 * n^2 배열 자르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87390?language=kotlin
 * */
class N2ArraySlicing : Solution {

    override fun execute() {
        solution2(4, 7, 14).let { println(it.contentToString()) }
    }

    fun solution(n: Int, left: Long, right: Long): IntArray {
        return List(n) { i -> List(n) { j -> if (j <= i) i + 1 else j + 1 } }
            .slice((left / n).toInt()..(right / n).toInt())
            .flatten()
            .slice(left.toInt()..right.toInt())
            .toIntArray()
    }

    fun solution2(n: Int, left: Long, right: Long): IntArray {
        return List(n) { i -> List(n) { j -> if (j <= i) i + 1 else j + 1 } }
            .flatten()
            .slice(left.toInt()..right.toInt())
            .toIntArray()
    }
}