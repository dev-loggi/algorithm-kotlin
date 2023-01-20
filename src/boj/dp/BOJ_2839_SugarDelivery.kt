package boj.dp

import boj.BOJSolution

/**
 * 2839
 * 설탕배달
 * https://www.acmicpc.net/problem/2839
 * DP
 * */
class BOJ_2839_SugarDelivery : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val N = readLine()?.toIntOrNull() ?: return

        // N = 5 * a + 3 * b
        var a = N / 5
        var b = 0

        while (a >= 0) {
            b = (N - 5 * a) / 3

            println("a=$a, b=$b: ${5 * a} + ${3 * b} =? $N")

            if (5 * a + 3 * b == N) {
                break
            } else {
                a -= 1
            }
        }

        if (a >= 0) {
            println(a + b)
        } else {
            println(-1)
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 2839,
    title = "설탕 배달",
    category = listOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/2839"
)

private fun testCases() = listOf(
    BOJSolution.TestCase( // case 1
        input = "18",
        output = "4",
    ),
    BOJSolution.TestCase( // case 2
        input = "4",
        output = "-1",
    ),
    BOJSolution.TestCase( // case 3
        input = "6",
        output = "2",
    ),
    BOJSolution.TestCase( // case 4
        input = "9",
        output = "3",
    ),
    BOJSolution.TestCase( // case 5
        input = "11",
        output = "3",
    ),
)