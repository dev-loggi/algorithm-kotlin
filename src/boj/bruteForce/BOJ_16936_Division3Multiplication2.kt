package boj.bruteForce

import common.Solution

/**
 * 16936
 * 나3곱2
 * https://www.acmicpc.net/problem/16936
 * 완전 탐색
 * */
class BOJ_16936_Division3Multiplication2 : Solution {

    override fun execute() {
        repeat(2) { main() }
    }

    fun main() {
        val N = readLine()!!.toInt()
        val sequence = readLine()!!.split(" ")
            .map { it.toLong() }
            .toLongArray()

        solution(N, sequence)
    }

    fun solution(N: Int, sequence: LongArray) {
        var newSequence: LongArray? = null

        fun next(i: Int, visited: BooleanArray, list: List<Long>) {
            if (newSequence != null)
                return
            if (list.size == N) {
                newSequence = list.toLongArray()
                return
            }

            visited[i] = true
            for (j in sequence.indices) {
                if (visited[j]) continue

                val n = sequence[i]
                val m = sequence[j]
                if ((n * 2 == m) || (n % 3 == 0L && n / 3 == m)) {
                    next(j, visited, list.toMutableList().also { it.add(m) })
                }
            }
        }

        for (i in sequence.indices) {
            if (newSequence != null)
                break

            next(i, BooleanArray(N), listOf(sequence[i]))
        }

        println(newSequence?.joinToString(" "))
    }
}

/*
[case1]
6
4 8 6 3 12 9
[case1 answer]
9 3 6 12 4 8

[case2]
4
42 28 84 126
[case2 answer]
126 42 84 28
* */