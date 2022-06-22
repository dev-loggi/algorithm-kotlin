package boj.bruteForce

import common.Solution

/**
 * 16922
 * 로마 숫자 만들기
 * https://www.acmicpc.net/problem/16922
 * */
class BOJ_16922_MakingRomanNumerals : Solution {

    override fun execute() {
        repeat(3) { main() }
    }

    fun main() {
        val N = readLine()!!.toInt()

        solution(N)
    }

    fun solution(N: Int) {
        val answer = intArrayOf(1, 5, 10, 50)
            .homoCombination(N)
            .map { it.sum() }
            .distinct()
            .size

        println(answer)
    }

    fun IntArray.homoCombination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}
/*
[case1]
1
[case1 answer]
4

[case2]
2
[case2 answer]
10

[case3]
10
[case3 answer]
244

* */