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
        val sums = mutableSetOf<Int>()
        val romans = intArrayOf(1, 5, 10, 50)

        fun recursive(depth: Int, start: Int, sum: Int) {
            if (depth == N) {
                sums.add(sum)
                return
            }
            for (i in start until romans.size) {
                recursive(depth + 1, i, sum + romans[i])
            }
        }

        recursive(0, 0, 0)

        println(sums.size)
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