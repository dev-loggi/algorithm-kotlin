package boj.bruteForce

import common.Solution

/**
 * 16943
 * 숫자 재배치
 * https://www.acmicpc.net/problem/16943
 * 완전 탐색
 * */
class BOJ_16943_NumberReplacement : Solution {

    override fun execute() {
        repeat(3) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val A = input[0].toInt()
        val B = input[1].toInt()

        solution(A, B)
    }

    fun solution(A: Int, B: Int) {
        val lengthA = A.toString().length

        val maxNumber = A.toString()
            .permutation(lengthA)
            .asSequence()
            .map { it.joinToString("") }
            .filterNot { it.startsWith('0') }
            .map { it.toInt() }
            .filter { it < B }
            .maxOfOrNull { it } ?: -1

        println(maxNumber)
    }

    fun String.permutation(r: Int): List<CharArray> {
        val result = mutableListOf<CharArray>()

        fun recursive(depth: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list.map { this[it] }.toCharArray())
                return
            }

            for (i in indices) {
                if (list.contains(i)) continue

                val newList = list.toMutableList()
                    .also { it.add(i) }

                recursive(depth + 1, newList)
            }
        }

        recursive(0, emptyList())
        return result
    }
}

/*
[case1]
1234 3456
[case1 answer]
3421

[case2]
1000 5
[case2 answer]
-1

[case3]
789 123
[case3 answer]
-1
* */