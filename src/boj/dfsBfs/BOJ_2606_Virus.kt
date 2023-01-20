package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 2606
 * 바이러스
 * https://www.acmicpc.net/problem/2606
 * bfs
 * */
class BOJ_2606_Virus : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val N = readLine()?.toIntOrNull() ?: return // 컴퓨터 갯수
        val M = readLine()?.toIntOrNull() ?: return // 네트워크 갯수

        val network = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 1..N) {
            network[i] = mutableSetOf<Int>()
        }

        for (i in 0 until M) {
            val input = readLine()?.split(" ") ?: return
            val a = input[0].toIntOrNull() ?: return
            val b = input[1].toIntOrNull() ?: return

            network[a]?.add(b)
            network[b]?.add(a)
        }

        println(bfs(network))
    }

    private fun bfs(network: Map<Int, Set<Int>>): Int {
        val infected = mutableSetOf(1)
        val queue = ArrayDeque<Int>().apply { offer(1) }

        while (queue.isNotEmpty()) {
            val p = queue.poll()
            infected.add(p)

            network[p]?.forEach { q ->
                if (!infected.contains(q)) {
                    queue.offer(q)
                }
            }
        }

        return infected.size - 1
    }
}

private fun info() = BOJSolution.Info(
    no = 2606,
    title = "바이러스",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/2606"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "7\n" +
                "6\n" +
                "1 2\n" +
                "2 3\n" +
                "1 5\n" +
                "5 2\n" +
                "5 6\n" +
                "4 7",
        output = "4"
    )
)