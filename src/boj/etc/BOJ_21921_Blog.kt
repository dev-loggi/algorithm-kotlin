package boj.etc

import boj.BOJSolution

/**
 * 21921 블로그
 * */
class BOJ_21921_Blog : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val (N, X) = readln().split(" ").map { it.toInt() }
        val visitor = readln().split(" ").map { it.toInt() }.toIntArray()

        solution(N, X, visitor)
    }

    fun solution(N: Int, X: Int, visitor: IntArray) {
        var sum = visitor.sliceArray(0 until X).sum()
        var max = sum
        var cnt = 1

        for (i in 0 until visitor.size - X) {
            sum -= visitor[i]
            sum += visitor[i + X]

            if (max < sum) {
                max = sum
                cnt = 1
            } else if (max == sum) {
                cnt += 1
            }
        }

        if (max == 0) {
            println("SAD")
        } else {
            println(max)
            println(cnt)
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 21921,
    title = "블로그",
    category = listOf(BOJSolution.SLIDING_WINDOW),
    problemUrl = "https://www.acmicpc.net/problem/21921",
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "5 2\n" +
                "1 4 2 5 1",
        output = "7\n" +
                "1",
    ),
    BOJSolution.TestCase(
        input = "7 5\n" +
                "1 1 1 1 1 5 1",
        output = "9" +
                "2",
    ),
    BOJSolution.TestCase(
        input = "5 3\n" +
                "0 0 0 0 0",
        output = "SAD",
    ),
)