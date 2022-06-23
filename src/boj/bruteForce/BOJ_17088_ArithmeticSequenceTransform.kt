package boj.bruteForce

import common.Solution

/**
 * 17088
 * 등차수열 변환
 * https://www.acmicpc.net/problem/17088
 * 완전 탐색
 * */
class BOJ_17088_ArithmeticSequenceTransform : Solution {

    override fun execute() {
        repeat(4) { main() }
    }

    fun main() {
        val N = readLine()!!.toInt() // 수열의 크기
        val A = readLine()!!.split(" ") // 수열
            .map { it.toInt() }
            .toIntArray()

        solution(N, A)
    }

    fun solution(N: Int, A: IntArray) {
        println("solution(): ${A.joinToString(" ")}")
        if (N <= 2) {
            println(0)
            return
        }

        val answer = mutableListOf<Int>()
        val operators = intArrayOf(1, 0, -1).homoPermutation(2)
        println(operators)

        for (firstOp in operators) {
            val B = IntArray(N) { i -> A[i] }
            B[0] += firstOp[0]
            B[1] += firstOp[1]

            // b1, b2가 나올 수 있는 9가지 경우의 수에 따라 공차(diff)가 결정됨
            val diff = B[1] - B[0]
            for (i in 1 until B.size - 1) {
                if (B[i + 1] - B[i] == diff)
                    continue

                for (op in operators) {
                    if ((B[i + 1] + op[1]) - (B[i] + op[0]) == diff) {
                        B[i] += op[0]
                        B[i + 1] += op[1]
                        break
                    }
                }
            }

            if (B.isArithmeticSequence()) {
                println(B.contentToString())

                val count = A.foldIndexed(0) { idx, acc, n ->
                    if (A[idx] == B[idx]) acc else acc + 1
                }

                answer.add(count)
            }
        }

        println(answer.minOfOrNull { it } ?: -1)
    }

    private fun IntArray.isArithmeticSequence(): Boolean {
        if (size < 3) return true
        val diff = this[1] - this[0]
        for (i in 1 until size - 1) {
            if (this[i + 1] - this[i] != diff)
                return false
        }
        return true
    }

    fun IntArray.homoPermutation(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in indices) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }
                recursive(depth + 1, newList)
            }
        }

        recursive(0, emptyList())
        return result
    }
}

/*
[case1]
4
24 21 14 10
[case1 answer]
3

[case2]
2
5 5
[case2 answer]
0

[case3]
3
14 5 1
[case3 answer]
-1

[case4]
5
1 3 6 9 12
[case4 answer]
1
* */