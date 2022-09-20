package programmers.practice.level1

import programmers.Programmers.Solution

/**
 * 체육복
 * https://programmers.co.kr/learn/courses/30/lessons/42862?language=kotlin#
 * */
class GymSuit : Solution {

    override fun execute() {
        //5	[2, 4]	[1, 3, 5]	5
        //5	[2, 4]	[3]	4
        //3	[3]	[1]	2
        solution(5, intArrayOf(2, 4), intArrayOf(1, 3, 5)).let { println("answer=$it") } // 5
        solution(5, intArrayOf(2, 4), intArrayOf(3)).let { println("answer=$it") } // 4
        solution(3, intArrayOf(3), intArrayOf(1)).let { println("answer=$it") } // 2
    }

    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {

        val clothes = Array(n) { i ->
            var n = 1
            if (lost.contains(i + 1)) n--
            if (reserve.contains(i + 1)) n++
            n
        }

        for (i in clothes.indices) {
            if (clothes[i] != 0)
                continue

            val front = clothes.getOrElse(i - 1) { 0 }
            val rear = clothes.getOrElse(i + 1) { 0 }

            if (front == 2) {
                clothes[i - 1]--
                clothes[i]++
            } else if (rear == 2) {
                clothes[i + 1]--
                clothes[i]++
            }
        }

        return clothes.count { it >= 1 }
    }
}