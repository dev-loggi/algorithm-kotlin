package boj

import java.io.ByteArrayOutputStream
import java.io.PrintStream

abstract class BOJSolution(
    private val info: Info,
    private val testCases: List<TestCase>
) {

    companion object {
        const val MATERIALIZATION = "구현"
        const val BRUTE_FORCE = "Brute Force"
        const val BITMASK = "BitMask"
        const val DFS = "DFS"
        const val BFS = "BFS"
        const val GREEDY = "Greedy"
        const val DP = "DP"
        const val SLIDING_WINDOW = "Sliding Window"
        const val TWO_POINTER = "Two Pointer"
    }

    data class Info(val no: Int, val title: String, val category: List<String>, val problemUrl: String, val solutionUrl: String = "")
    data class TestCase(val input: String, val output: String)

    abstract fun runEachSolution()

    fun execute() {
        if (testCases.isEmpty()) {
            return
        }

        printProblemInfo()

        val userOutputs = runSolutions()

        println(userOutputs.map { s -> s.map { c -> c.code } })

        printTestCaseResults(userOutputs)
    }

    private fun printProblemInfo() {
        val (problemNo, title, categories, problemUrl, solutionUrl) = info
        val categoriesToString = if (categories.isNotEmpty()) {
            categories.joinToString()
        } else {
            "미분류"
        }

        println("""
            [문제 정보]
            문제: (BOJ $problemNo) $title
            링크: $problemUrl
            유형: $categoriesToString
        """.trimIndent())
    }

    private fun runSolutions(): List<String> = onTestEnvironment { outputStream ->
        val outputs = mutableListOf<String>()

        repeat(testCases.size) {
            runEachSolution()

            val userOutput = outputStream.toByteArray()
                .decodeToString()
                .trim('\n', '\r')

            outputs.add(userOutput)

            outputStream.reset()
        }

        outputs
    }

    private fun printTestCaseResults(userOutputs: List<String>) {
        val totalCount = testCases.size
        var correctCount = 0

        for ((index, testCase) in testCases.withIndex()) {
            val testCaseNo = index + 1
            val (input, answer) = testCase
            val userOutput = userOutputs[index]
            val correct = if (userOutput == answer) {
                "정답(O)"
            } else {
                "오답(X)"
            }

            if (userOutput == answer) {
                correctCount += 1
            }

            val template =
                """-------------------------------
[예제 입력 $testCaseNo]

* input:
$input

* output:
$userOutput

* answer:
$answer

* 결과: $correct
""".trimIndent()

            println(template)
        }

        println("""
            -------------------------------
            
            * 맞은 개수: 전체 ${totalCount}개 중 ${correctCount}개 맞음
        """.trimIndent())
    }

    private fun <R> onTestEnvironment(block: (outputStream: ByteArrayOutputStream) -> R): R {
        val result: R
        val systemIn = System.`in`
        val systemOut = System.out

        val inputData = testCases.joinToString("\n") { it.input.trim('\n') }

        inputData.byteInputStream().use { inputStream ->
            System.setIn(inputStream)

            ByteArrayOutputStream().use { outputStream ->
                PrintStream(outputStream).use { printStream ->
                    System.setOut(printStream)

                    result = block(outputStream)
                }
            }
        }

        System.setIn(systemIn)
        System.setOut(systemOut)

        return result
    }
}