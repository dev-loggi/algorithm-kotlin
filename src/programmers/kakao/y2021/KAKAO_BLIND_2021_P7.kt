package programmers.kakao.y2021

import programmers.Programmers

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 7] 매출 하락 최소화
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72416
 * */
class KAKAO_BLIND_2021_P7 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            val (sales, links) = case
            println("case $index -> sales=${sales.contentToString()}, links=${links.joinToString { it.contentToString() }}")

            val answer = solution(sales, links)
            println("♠ answer=$answer")
        }
    }

    fun solution(sales: IntArray, links: Array<IntArray>): Int {
        return 0
    }
}

private fun testCases(): Array<Pair<IntArray, Array<IntArray>>> = arrayOf(
    // case 1
    // answer = 44
    Pair(
        first = intArrayOf(14, 17, 15, 18, 19, 14, 13, 16, 28, 17),
        second = arrayOf(
            intArrayOf(10, 8),
            intArrayOf(1, 9),
            intArrayOf(9, 7),
            intArrayOf(5, 4),
            intArrayOf(1, 5),
            intArrayOf(5, 10),
            intArrayOf(10, 6),
            intArrayOf(1, 3),
            intArrayOf(10, 2),
        )
    ),

    // case 1
    // answer = 6
    Pair(
        first = intArrayOf(5, 6, 5, 3, 4),
        second = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(1, 2),
        )
    ),

    // case 1
    // answer = 5
    Pair(
        first = intArrayOf(5, 6, 5, 1, 4),
        second = arrayOf(
            intArrayOf(2, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(1, 2),
        )
    ),

    // case 1
    // answer = 2
    Pair(
        first = intArrayOf(10, 10, 1, 1),
        second = arrayOf(
            intArrayOf(3, 2),
            intArrayOf(4, 3),
            intArrayOf(1, 4),
        )
    ),
)