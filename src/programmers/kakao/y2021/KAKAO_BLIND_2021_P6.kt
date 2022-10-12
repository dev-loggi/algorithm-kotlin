package programmers.kakao.y2021

import programmers.Programmers

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 6] 카드 짝 맞추기
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72415
 * */
class KAKAO_BLIND_2021_P6 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            val (board, r, c) = case
            println("case $index -> r=$r, c=$c")
            board.forEach { println("    ${it.joinToString("")}") }

            val answer = solution(board, r, c)
            println("♠ answer=$answer")
        }
    }

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        return 0
    }
}

// board	r	c	result
//[[1,0,0,3],[2,0,0,0],[0,0,0,2],[3,0,1,0]]	1	0	14
//[[3,0,0,2],[0,0,1,0],[0,1,0,0],[2,0,0,3]]	0	1	16
private fun testCases(): Array<Triple<Array<IntArray>, Int, Int>> = arrayOf(
    // case 1
    // answer = 14
    Triple(
        first = arrayOf(
            intArrayOf(1,0,0,3),
            intArrayOf(2,0,0,0),
            intArrayOf(0,0,0,2),
            intArrayOf(3,0,1,0),
        ),
        second = 1,
        third = 0
    ),

    // case 2
    // answer = 16
    Triple(
        first = arrayOf(
            intArrayOf(3,0,0,2),
            intArrayOf(0,0,1,0),
            intArrayOf(0,1,0,0),
            intArrayOf(2,0,0,3),
        ),
        second = 0,
        third = 1
    ),
)