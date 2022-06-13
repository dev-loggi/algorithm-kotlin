package programmers.practice.level1

import common.Solution

class AddNegativePositiveNumbers : Solution {

    override fun execute() {
        solution(intArrayOf(4,7,12), booleanArrayOf(true,false,true)).let { println("answer=$it") } // 9
        solution(intArrayOf(1,2,3), booleanArrayOf(false,false,true)).let { println("answer=$it") } // 0
    }

    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        return absolutes.foldIndexed(0) { i, sum, n -> sum + if (signs[i]) n else -n }
    }
}