package boj

import common.Solution

/**
 * 11005
 * 진법 변환 2
 * https://www.acmicpc.net/problem/11005
 * */
class BOJ_11005_BaseConversion2 : Solution {

    override fun execute() {
        repeat(5) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toLong()
        val B = input[1].toInt()


        solution(N, B)
        solution2(N, B)
        println()
    }

    fun solution(N: Long, B: Int) {
        val digits = mutableMapOf<Int, Char>()
        for (i in 0 until B) {
            digits[i] = if (i < 10) i.digitToChar() else (55 + i).toChar()
        }

        var answer = ""
        var n = N
        while (n > 0) {
            answer = "${digits[(n % B).toInt()]}$answer"
            n /= B
        }

        println(answer)
    }

    fun solution2(N: Long, B: Int) {
        println(N.toString(B).uppercase())
    }

}
/*
[case1]
60466175 36
[case1 answer]
ZZZZZ
* */