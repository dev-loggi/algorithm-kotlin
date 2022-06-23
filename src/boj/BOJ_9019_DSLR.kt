package boj

import common.Solution
import java.text.DecimalFormat
import java.util.ArrayDeque
import kotlin.random.Random

/**
 * 9019
 * DSLR
 * https://www.acmicpc.net/problem/9019
 * */
class BOJ_9019_DSLR : Solution {

    private val formatter = DecimalFormat("0000")

    override fun execute() {
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
/*
[case1]
3
1234 3412
1000 1
1 16
[case1 answer]
LL
L
DDDD
* */