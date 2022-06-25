package boj.bruteForce

import common.Solution

/**
 * 2210
 * 숫자판 점프
 * https://www.acmicpc.net/submit/2210
 * 완전 탐색
 * */
class BOJ_2210_NumPadJump : Solution {

    override fun execute() {
        main()
    }

    fun main() {
        val matrix = Array(5) { CharArray(5) }
        for (i in 0 until 5) {
            matrix[i] = readLine()!!.split(" ")
                .map { it[0] }
                .toCharArray()
        }

        solution(matrix)
    }

    fun solution(matrix: Array<CharArray>) {
        val numbers = mutableSetOf<String>()

        fun dfs(depth: Int, r: Int, c: Int, number: String) {
            if (r !in 0..4 || c !in 0..4)
                return
            if (depth == 6) {
                numbers.add(number)
                return
            }

            dfs(depth + 1, r, c - 1, number + matrix[r][c])
            dfs(depth + 1, r - 1, c, number + matrix[r][c])
            dfs(depth + 1, r, c + 1, number + matrix[r][c])
            dfs(depth + 1, r + 1, c, number + matrix[r][c])
        }

        for (r in matrix.indices) {
            for (c in matrix[r].indices) {
                dfs(0, r, c, "")
            }
        }

        println(numbers.size)
    }

}

/*
[case1]
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 2 1
1 1 1 1 1
[case1 answer]
15
* */