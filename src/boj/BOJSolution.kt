package boj

import Solution

abstract class BOJSolution : Solution {

    companion object {
        const val MATERIALIZATION = "구현"
        const val BRUTE_FORCE = "Brute Force"
        const val DFS = "DFS"
        const val BFS = "BFS"
        const val GREEDY = "Greedy"
        const val DP = "DP"
    }

    class Info(val no: Int, val title: String, val category: Array<String>, val url: String)
    class TestCase(val input: String, val output: String)

    abstract val info: Info
    abstract val testCases: Array<TestCase>

    abstract fun executeTestCases()

    final override fun execute() {
        println("[BOJ ${info.no}]: ${info.title}")
        println(info.category.joinToString(", "))
        println("${info.url}\n")

        testCases.forEachIndexed { index, testCase ->
            println("[예제 입력 ${index + 1}]")
            println(testCase.input)
            println("\n* input:")

            executeTestCases()

            println("* answer:")
            println(testCase.output)
            println()
        }
    }
}