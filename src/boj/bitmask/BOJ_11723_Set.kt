package boj.bitmask

import boj.BOJSolution

/**
 * [BOJ 11723] 집합
 * https://www.acmicpc.net/problem/11723
 * */
class BOJ_11723_Set : BOJSolution(info(), testCase()) {

    override fun main() {
        val M = readln().toInt() // 연산의 수 (1 ≤ M ≤ 3,000,000)
        var n = 0
        val answer = StringBuilder()

        for (i in 1..M) {
            val cmdLine = readln().split(" ")
            val cmd = cmdLine[0]
            val x =
                if (cmdLine.size == 2) cmdLine[1].toInt()
                else -1

            when (cmd) {
                "add" -> n = n or (1 shl x)
                "remove" -> n = n and (1 shl x).inv()
                "check" -> answer.appendLine(if (n and (1 shl x) == 0) 0 else 1)
                "toggle" -> n = n xor (1 shl x)
                "all" -> n = Int.MAX_VALUE
                "empty" -> n = 0
            }
        }

        println(answer)
    }
}

private fun info() = BOJSolution.Info(
    no = 11723,
    title = "집합",
    category = arrayOf(BOJSolution.BRUTE_FORCE, BOJSolution.BITMASK),
    problemUrl = "",
)

private fun testCase() = arrayOf(
    BOJSolution.TestCase(
        input = "26\n" +
                "add 1\n" +
                "add 2\n" +
                "check 1\n" +
                "check 2\n" +
                "check 3\n" +
                "remove 2\n" +
                "check 1\n" +
                "check 2\n" +
                "toggle 3\n" +
                "check 1\n" +
                "check 2\n" +
                "check 3\n" +
                "check 4\n" +
                "all\n" +
                "check 10\n" +
                "check 20\n" +
                "toggle 10\n" +
                "remove 20\n" +
                "check 10\n" +
                "check 20\n" +
                "empty\n" +
                "check 1\n" +
                "toggle 1\n" +
                "check 1\n" +
                "toggle 1\n" +
                "check 1",
        output = "1\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "0\n" +
                "1\n" +
                "1\n" +
                "0\n" +
                "0\n" +
                "0\n" +
                "1\n" +
                "0",
    )
)