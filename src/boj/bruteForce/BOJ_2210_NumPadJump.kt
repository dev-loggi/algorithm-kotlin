package boj.bruteForce

import boj.BOJSolution

class BOJ_2210_NumPadJump : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
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

private fun info() = BOJSolution.Info(
    no = 2210,
    title = "숫자판 점프",
    category = listOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/submit/2210"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input =
        "1 1 1 1 1\n" +
                "1 1 1 1 1\n" +
                "1 1 1 1 1\n" +
                "1 1 1 2 1\n" +
                "1 1 1 1 1",
        output = "15"
    )
)