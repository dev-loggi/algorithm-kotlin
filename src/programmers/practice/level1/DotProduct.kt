package programmers.practice.level1

import common.Solution

/**
 * 내적(Dot product)
 * https://programmers.co.kr/learn/courses/30/lessons/70128?language=kotlin
 * https://en.wikipedia.org/wiki/Dot_product
 * */
class DotProduct : Solution {

    override fun execute() {
        solution(intArrayOf(1,2,3,4), intArrayOf(-3,-1,0,2)).let { println("answer=$it") } // 3
        solution(intArrayOf(-1,0,1), intArrayOf(1,0,-1)).let { println("answer=$it") } // -2
    }

    fun solution(a: IntArray, b: IntArray): Int {
        return a.foldIndexed(0) { idx, sum, num -> sum + num * b[idx] }
    }

    fun solution2(a: IntArray, b: IntArray): Int {
        return a.zip(b).sumOf { it.first + it.second }
    }
}