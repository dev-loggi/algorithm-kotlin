package programmers.practice.level1

import Solution

/**
 * 최소 직사각형
 * https://programmers.co.kr/learn/courses/30/lessons/86491
 * */
class MinRectangle : Solution {

    override fun execute() {
        //[[60, 50], [30, 70], [60, 30], [80, 40]]	4000
        //[[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	120
        //[[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	133

        solution(arrayOf(
            intArrayOf(60, 50), intArrayOf(30, 70), intArrayOf(60, 30), intArrayOf(80, 40)
        )).let { println(it) } // 4000
        solution(arrayOf(
            intArrayOf(10, 7), intArrayOf(12, 3), intArrayOf(8, 15), intArrayOf(14, 7), intArrayOf(5, 15)
        )).let { println(it) } // 120
        solution(arrayOf(
            intArrayOf(14, 4), intArrayOf(19, 6), intArrayOf(6, 16), intArrayOf(18, 7), intArrayOf(7, 11)
        )).let { println(it) } // 133
    }

    fun solution(sizes: Array<IntArray>): Int {
        return sizes.onEach { it.sort() }
            .let { it.maxOf { s -> s[0] } * it.maxOf { s -> s[1] } }
    }
}