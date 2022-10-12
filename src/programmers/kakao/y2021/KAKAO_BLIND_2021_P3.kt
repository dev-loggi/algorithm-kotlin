package programmers.kakao.y2021

import programmers.Programmers
import java.util.*

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 3] 합승 택시 요금
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72413
 * */
class KAKAO_BLIND_2021_P3 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            val (n, s, a, b) = case.first
            val fares = case.second

            println("case $index -> n=$n, s=$s, a=$a, b=$b, fares=${fares.joinToString(", ") { it.contentToString() }}")

            val answer = solution(n, s, a, b, fares)
            println("♠ answer=$answer")
        }
    }

    fun solution(n: Int, s: Int, a: Int, b: Int, fare_info: Array<IntArray>): Int {
        val graph = mutableMapOf<Int, LinkedList<Int>>()
        val fare = Array(n + 1) { IntArray(n + 1) }

        for (info in fare_info) {
            graph.computeIfAbsent(info[0]) { LinkedList() }.add(info[1])
            graph.computeIfAbsent(info[1]) { LinkedList() }.add(info[0])
            fare[info[0]][info[1]] = info[2]
            fare[info[1]][info[0]] = info[2]
        }

        println(graph)
        fare.forEach { println(it.contentToString()) }

        

        return 0
    }
}

private fun testCases(): Array<Pair<IntArray, Array<IntArray>>> = arrayOf(
    // case 1
    // answer = 82
    Pair(
        first = intArrayOf(6, 4, 6, 2),
        second = arrayOf(
            intArrayOf(4, 1, 10),
            intArrayOf(3, 5, 24),
            intArrayOf(5, 6, 2),
            intArrayOf(3, 1, 41),
            intArrayOf(5, 1, 24),
            intArrayOf(4, 6, 50),
            intArrayOf(2, 4, 66),
            intArrayOf(2, 3, 22),
            intArrayOf(1, 6, 25),
        )
    ),

    // case 2
    // answer = 14
    Pair(
        first = intArrayOf(7, 3, 4, 1),
        second = arrayOf(
            intArrayOf(5, 7, 9),
            intArrayOf(4, 6, 4),
            intArrayOf(3, 6, 1),
            intArrayOf(3, 2, 3),
            intArrayOf(2, 1, 6),
        )
    ),

    // case 3
    // answer = 18
    Pair(
        first = intArrayOf(6, 4, 5, 6),
        second = arrayOf(
            intArrayOf(2,6,6),
            intArrayOf(6,3,7),
            intArrayOf(4,6,7),
            intArrayOf(6,5,11),
            intArrayOf(2,5,12),
            intArrayOf(5,3,20),
            intArrayOf(2,4,8),
            intArrayOf(4,3,9),
        )
    ),
)