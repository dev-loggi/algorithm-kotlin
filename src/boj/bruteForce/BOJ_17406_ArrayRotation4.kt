package boj.bruteForce

import common.Solution

/**
 * 17406
 * 배열 돌리기4
 * https://www.acmicpc.net/problem/17406
 * 완전 탐색
 * */
class BOJ_17406_ArrayRotation4 : Solution {

    override fun execute() {
        main()
    }

    fun main() {
        // 1 ≤ A[i][j] ≤ 100
        // 1 ≤ s
        // 1 ≤ r-s < r < r+s ≤ N
        // 1 ≤ c-s < c < c+s ≤ M
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 세로 크기(3 ≤ N ≤ 50)
        val M = input[1].toInt() // 가로 크기(3 ≤ M ≤ 50)
        val K = input[2].toInt() // 연산 횟수(1 ≤ K ≤ 6)

        val matrix = (0 until N).map { _ ->
            readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()

        val rotations = (0 until K).map { _ ->
            readLine()!!.split(" ").mapIndexed { i, n ->
                if (i < 2) n.toInt() - 1
                else n.toInt()
            }.toIntArray()
        }.toTypedArray()

        solution(N, M, K, matrix, rotations)
    }

    fun solution(N: Int, M: Int, K: Int, matrix: Array<IntArray>, rotations: Array<IntArray>) {
        val answer = rotations.permutation(K)
            .minOf {
                val copied = matrix.deepCopy()

                it.forEach { rotate ->
                    copied.rotationBy(rotate)
                }

                copied.minRowSum
            }

        println(answer)
    }

    fun <T> Array<T>.permutation(r: Int): List<List<T>> {
        val result = mutableListOf<List<T>>()

        fun recursive(depth: Int, list: List<T>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in indices) {
                if (list.contains(this[i]))
                    continue

                val newList = list.toMutableList()
                    .also { it.add(this[i]) }
                recursive(depth + 1, newList)
            }
        }

        recursive(0, emptyList())
        return result
    }

    fun Array<IntArray>.rotationBy(rotate: IntArray) {
        val matrix = this
        val r0 = rotate[0]
        val c0 = rotate[1]
        val rm = intArrayOf(1, 0, -1, 0)
        val cm = intArrayOf(0, 1, 0, -1)

        for (s in 1..rotate[2]) {
            val width = s * 2 + 1
            val start = matrix[r0 - s][c0 - s]

            var r = r0 - s
            var c = c0 - s
            for (d in 0..3) { // direction: 0 ~ 3
                for (i in 0 until width - 1) {
                    matrix[r][c] = matrix[r + rm[d]][c + cm[d]]

                    r += rm[d]
                    c += cm[d]
                }
            }

            matrix[r0 - s][c0 - s + 1] = start
            println()
        }
    }

    fun Array<IntArray>.deepCopy(): Array<IntArray> {
        return Array(size) { r -> IntArray(this[r].size) { c -> this[r][c] } }
    }

    val Array<IntArray>.minRowSum: Int
        get() = minOf { it.sumOf { n -> n } }


    fun Array<IntArray>.log() {
        forEach { println(it.joinToString(" ")) }
    }

}
/*
[case1]
5 6 2
1 2 3 2 5 6
3 8 7 2 1 3
8 2 3 1 4 5
3 4 5 1 1 1
9 3 2 1 4 3
3 4 2
4 2 1
[case1 answer]
12
* */