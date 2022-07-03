package boj.unsolved

import Solution
import boj.BOJSolution
import java.text.DecimalFormat
import java.util.ArrayDeque

/**
 * 9019
 * DSLR
 * https://www.acmicpc.net/problem/9019
 *
 * TODO: 미해결
 * */
class BOJ_9019_DSLR : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    private val formatter = DecimalFormat("0000")

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val T = readLine()!!.toInt()
        val answers = mutableListOf<String>()

        for (i in 0 until T) {
            val line = readLine()!!.split(" ")
            val A = line[0]
            val B = line[1]
            answers.add(solution(A, B))
        }

        answers.forEach { println(it) }
    }

    fun solution(A: String, B: String): String {
        if (A == B) return ""

        var answer = ""
        val visited = BooleanArray(10000)
        val queue = ArrayDeque<Pair<String, String>>()

        fun bfs(a: String, cmd: Char, cmdLine: String): Boolean {
            val a2 = a.command(cmd)
            if (a2.toInt() == B.toInt()) {
                answer = cmdLine + cmd
                return true
            }
            if (visited[a2.toInt()])
                return false

            visited[a2.toInt()] = true
            queue.offer(a2 to cmdLine + cmd)

            return false
        }

        visited[A.toInt()] = true
        queue.offer(A to "")

        var count = 0
        while (queue.isNotEmpty()) {
            count++
            val (a, cmdLine) = queue.poll()

            if (bfs(a, 'D', cmdLine)) break
            if (bfs(a, 'S', cmdLine)) break
            if (bfs(a, 'L', cmdLine)) break
            if (bfs(a, 'R', cmdLine)) break
        }

        return answer
    }

    fun String.command(c: Char): String {
        val n = formatter.format(this.toInt())
        return when (c) {
            'D' -> (n.toInt() * 2 % 10000).toString()
            'S' -> if (n.toInt() == 0) "9999" else (n.toInt() - 1).toString()
            'L' -> "${n.drop(1)}${n.take(1)}"
            'R' -> "${n.takeLast(1)}${n.dropLast(1)}"
            else -> throw IllegalArgumentException("cmd=$c")
        }
    }
}

private val _info = BOJSolution.Info(
    no = 9019,
    title = "DSLR",
    category = arrayOf(BOJSolution.BFS),
    url = "https://www.acmicpc.net/problem/9019"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "3\n" +
                "1234 3412\n" +
                "1000 1\n" +
                "1 16",
        output ="LL\n" +
                "L\n" +
                "DDDD"
    )
)