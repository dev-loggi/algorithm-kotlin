package boj.bruteForce

import common.Solution

/**
 * 16938
 * 캠프 준비
 * https://www.acmicpc.net/problem/16938
 * 완전 탐색
 * */
class BOJ_16938_CampReady : Solution {

    override fun execute() {
        repeat(3) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // N개의 문제
        val L = input[1].toInt() // L <= 출제할 문제 난이도의 합
        val R = input[2].toInt() // 출제할 문제 난이도의 합 <= R
        val X = input[3].toInt() // X <= 가장 어려운 문제 난이도 - 가장 쉬운 문제 난이도
        val exam = readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()

        solution(N, L, R, X, exam)
    }

    fun solution(N: Int, L: Int, R: Int, X: Int, exam: IntArray) {
        val numberOfCases = (2..N)
            .map { n -> exam.combination(n) }
            .flatten()
            .filter { ex ->
                ex.sumOf { it } in L..R && X <= ex.maxOf { it } - ex.minOf { it }
            }
            .size

        println(numberOfCases)
    }

    fun IntArray.combination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i + 1, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }

}

/*
[case1]
3 5 6 1
1 2 3
[case1 answer]
2

[case2]
4 40 50 10
10 20 30 25
[case2 answer]
2

[case3]
5 25 35 10
10 10 20 10 20
[case3 answer]
6

* */