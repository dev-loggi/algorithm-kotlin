package boj

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import java.io.PrintStream
import kotlin.system.measureTimeMillis

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

        val (userOutputs, times) = runSolution()

        printTestCases(userOutputs, times)
    }

    private fun printProblemInfo() {
        val (problemNo, title, categories, problemUrl, solutionUrl) = info
        val categoriesString = if (categories.isNotEmpty()) {
            categories.joinToString()
        } else {
            "미분류"
        }

        println("""
            [문제 정보]
            문제: (BOJ $problemNo) $title
            링크: $problemUrl
            유형: $categoriesString
        """.trimIndent())
    }

    private fun runSolution(): Pair<List<String>, List<Double>> {
        val systemIn = System.`in`
        val systemOut = System.out
        val userOutputs: List<String>
        val times = mutableListOf<Double>()

        testCases.joinToString("\n") { it.input }.byteInputStream().use { inputs ->
            System.setIn(inputs)

            ByteOutputStream().use { output ->
                PrintStream(output).use { print ->
                    System.setOut(print)

                    repeat(testCases.size) {
                        val millis = measureTimeMillis {
                            runEachSolution()
                        }
                        times.add(millis / 1000.0)
                    }

                    System.setOut(systemOut)

                    userOutputs = output.newInputStream().reader().readLines()
                }
            }

            System.setIn(systemIn)
            System.setOut(systemOut)
        }

        return userOutputs to times
    }

    private fun printTestCases(userOutputs: List<String>, times: List<Double>) {
        val totalCount = testCases.size
        var correctCount = 0

        for ((index, testCase) in testCases.withIndex()) {
            val testCaseNo = index + 1
            val userOutput = userOutputs[index]
            val (input, answer) = testCase
            val correct = if (userOutput == answer) "정답(O)" else "오답(X)"
            val time = times[index]

            if (userOutput == answer) {
                correctCount += 1
            }

            println(
"""-------------------------------
[예제 입력 $testCaseNo]
* input:
$input

* output:
${userOutputs[index]}

* answer:
$answer

* 결과: $correct, $time 초
""".trimIndent()
            )
        }

        println("""
            -------------------------------
            
            * 맞은 개수: 전체 ${totalCount}개 중 ${correctCount}개 맞음
        """.trimIndent())
    }
}