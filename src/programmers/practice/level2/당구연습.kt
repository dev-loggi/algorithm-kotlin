package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 당구 연습
 * 수학, 최소 거리, 대칭이동
 * https://school.programmers.co.kr/learn/courses/30/lessons/169198?language=kotlin
 * */
class 당구연습 : Solution {

    private val Int.square: Int get() = this * this

    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        return balls
            .map { calcMinDistance(m, n, startX, startY, it[0], it[1]) }
            .toIntArray()
    }

    private fun calcMinDistance(m: Int, n: Int, startX: Int, startY: Int, endX: Int, endY: Int): Int {
        return intArrayOf(
            if (startY == endY && endX < startX) Int.MAX_VALUE else (startX + endX).square + (endY - startY).square, // 좌
            if (startX == endX && startY < endY) Int.MAX_VALUE else (endX - startX).square + (n - startY + n - endY).square, // 상
            if (startY == endY && startX < endX) Int.MAX_VALUE else (m - startX + m - endX).square + (endY - startY).square, // 우
            if (startX == endX && endY < startY) Int.MAX_VALUE else (endX - startX).square + (startY + endY).square, // 하
        ).min()
    }

    override fun execute() {
        // [52, 37, 116]
        solution(10, 10, 3, 7, arrayOf(intArrayOf(7, 7), intArrayOf(2, 7), intArrayOf(7, 3)))
            .contentToString()
            .let(::println)
    }
}