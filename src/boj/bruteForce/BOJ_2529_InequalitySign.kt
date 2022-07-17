package boj.bruteForce

import boj.BOJSolution

class BOJ_2529_InequalitySign : BOJSolution(info(), testCases()) {

    override fun main() {
        val k = readLine()?.toIntOrNull() ?: return
        val operators = readLine()?.split(" ") ?: return

        if (!checkCondition(k, operators))
            return

        // 최댓값
        Array(k + 1) { 9 - it } // 9, 8, 7 ...
            .sortWith(operators)
            .joinToString("")
            .let { println(it) }

        // 최솟값
        Array(k + 1) { it } // 0, 1, 2 ...
            .sortWith(operators)
            .joinToString("")
            .let { println(it) }
    }

    private fun checkCondition(k: Int, operators: List<String>): Boolean {
        return k in 2..9 && k == operators.size && operators.all { it == ">" || it == "<" }
    }

    private fun Array<Int>.sortWith(operators: List<String>) = apply {
        for (i in operators.indices) {
            for (j in 0..i) {
                swapIf(operators[j], j, j + 1)
            }
        }
        for (i in operators.lastIndex downTo 0) {
            for (j in operators.lastIndex downTo i) {
                swapIf(operators[j], j, j + 1)
            }
        }
    }

    private fun Array<Int>.swapIf(op: String, i: Int, j: Int) {
        when (op) {
            ">" -> if (this[i] < this[j]) swap(i, j)
            "<" -> if (this[i] > this[j]) swap(i, j)
        }
    }

    private fun Array<Int>.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }
}

private fun info() = BOJSolution.Info(
    no = 2529,
    title = "부등호",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/2529"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input =
        "2\n" +
                "< >",
        output =
        "897\n" +
                "021"
    ),
    BOJSolution.TestCase(
        input =
        "9\n" +
                "> < < < > > > < <",
        output =
        "9567843012\n" +
                "1023765489"
    )
)