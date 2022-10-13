package programmers.practice.level2

import programmers.Programmers.Solution
import kotlin.math.max

/**
 * n^2 배열 자르기
 * https://school.programmers.co.kr/learn/courses/30/lessons/87390?language=kotlin
 * */
class N2ArraySlicing : Solution {

    override fun execute() {
        solution(4, 7, 14).let { println(it.contentToString()) }
    }

    fun solution(n: Int, left: Long, right: Long): IntArray {
        return IntArray((right - left + 1).toInt()) { x ->
            max((left + x) / n, (left + x) % n).toInt() + 1
        }
    }

    /** 시간초과 */
    fun solution2(n: Int, left: Long, right: Long): IntArray {
        return List(n) { i -> List(n) { j -> max(i, j) + 1 } }
            .flatten()
            .slice(left.toInt()..right.toInt())
            .toIntArray()
    }
}