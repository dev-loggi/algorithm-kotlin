package boj

abstract class BOJSolution(
    private val info: Info,
    private val testCases: Array<TestCase>
) {

    companion object {
        const val MATERIALIZATION = "구현"
        const val BRUTE_FORCE = "Brute Force"
        const val DFS = "DFS"
        const val BFS = "BFS"
        const val GREEDY = "Greedy"
        const val DP = "DP"
        const val SLIDING_WINDOW = "Sliding Window"
        const val TWO_POINTER = "Two Pointer"
    }

    class Info(val no: Int, val title: String, val category: Array<String>, val problemUrl: String, val solutionUrl: String = "")
    class TestCase(val input: String, val output: String)

    abstract fun main()

    fun execute() {
        println("[BOJ ${info.no}]: ${info.title}")
        println(info.category.joinToString(", "))
        println("${info.problemUrl}\n")

        testCases.forEachIndexed { index, testCase ->
            println("[예제 입력 ${index + 1}]")
            println(testCase.input)
            println("\n* input:")

            main()

            println("* answer:")
            println(testCase.output)
            println()
        }
    }
}