package boj

import common.Solution

/**
 * 2745
 * 진법 변환
 * https://www.acmicpc.net/problem/2745
 * */
class BOJ_2745_BaseConversion : Solution {

    override fun execute() {
        repeat(5) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0]
        val B = input[1].toInt()

        solution(N, B)
        solution2(N, B)
        println()
    }

    fun solution(N: String, B: Int) {
        N.uppercase().reversed()
            .foldIndexed(0L) { idx, acc, n ->
                val v = if (n.isDigit()) n.digitToInt() else n.code - 55
                acc + v * B.pow(idx)
            }
            .let { println(it) }
    }

    fun solution2(N: String, B: Int) {
        println(N.toInt(B))
    }

    fun Int.pow(n: Int): Long {
        var acc = 1L
        for (i in 0 until n) acc *= this
        return acc
    }
}
/*
[case1]
ZZZZZ 36
[case1 answer]
60466175
* */