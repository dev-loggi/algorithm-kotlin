package programmers.practice.level2

import common.Solution

/**
 * 양궁 대회
 *  https://programmers.co.kr/learn/courses/30/lessons/92342?language=kotlin
 * */
class ArcheryCompetition : Solution {

    override fun execute() {
        //5	[2,1,1,1,0,0,0,0,0,0,0]	[0,2,2,0,1,0,0,0,0,0,0]
        //1	[1,0,0,0,0,0,0,0,0,0,0]	[-1]
        //9	[0,0,1,2,0,1,1,1,1,1,1]	[1,1,2,0,1,2,2,0,0,0,0]
        //10	[0,0,0,0,0,0,0,0,3,4,3]	[1,1,1,1,1,1,1,1,0,0,2]

        solution(5, intArrayOf(2,1,1,1,0,0,0,0,0,0,0))
            .let { println("answer=$it") } // [0,2,2,0,1,0,0,0,0,0,0]
        solution(1, intArrayOf(1,0,0,0,0,0,0,0,0,0,0))
            .let { println("answer=$it") } // [-1]
        solution(9, intArrayOf(0,0,1,2,0,1,1,1,1,1,1))
            .let { println("answer=$it") } // [1,1,2,0,1,2,2,0,0,0,0]
        solution(10, intArrayOf(0,0,0,0,0,0,0,0,3,4,3))
            .let { println("answer=$it") } // [1,1,1,1,1,1,1,1,0,0,2]
    }

    fun solution(n: Int, info: IntArray): IntArray {



        var answer: IntArray = intArrayOf()
        return answer
    }

    private fun solve() {

    }
}