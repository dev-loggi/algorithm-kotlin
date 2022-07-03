package boj.dfsBfs

import boj.BOJSolution
import java.util.*

/**
 * 1260
 * DFS 와 BFS
 * https://www.acmicpc.net/problem/1260
 * */
class BOJ_1260_DFSandBFS : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val nmv = readLine()?.split(" ") ?: return
        val n = nmv[0].toInt()
        val m = nmv[1].toInt()
        val v = nmv[2].toInt()

        val graph = mutableMapOf<Int, TreeSet<Int>>()

        for (i in 0 until m) {
            val edge = readLine()?.split(" ") ?: return
            graph.getOrPut(edge[0].toInt()) { sortedSetOf() }.add(edge[1].toInt())
            graph.getOrPut(edge[1].toInt()) { sortedSetOf() }.add(edge[0].toInt())
        }

        println(graph)

        dfs(graph, v).let { println(it.joinToString(" ")) }
        bfs(graph, v).let { println(it.joinToString(" ")) }
    }

    private fun dfs(graph: Map<Int, TreeSet<Int>>, start: Int): List<Int> {
        val result = mutableListOf<Int>().apply { add(start) }
        val set = mutableSetOf(start)

        fun recursive(v1: Int) {
            graph[v1]?.forEach { v2 ->
                if (set.contains(v2).not()) {
                    set.add(v2)
                    result.add(v2)

                    recursive(v2)
                }
            }
        }

        recursive(start)
        return result
    }

    private fun bfs(graph: Map<Int, TreeSet<Int>>, start: Int): List<Int> {
        val result = mutableListOf<Int>().apply { add(start) }
        val queue = ArrayDeque<Int>().apply { offer(start) }
        val set = mutableSetOf(start)

        while (queue.isNotEmpty()) {
            val v1 = queue.poll()

            graph[v1]?.forEach { v2 ->
                if (set.contains(v2).not()) {
                    set.add(v2)
                    queue.offer(v2)
                    result.add(v2)
                }
            }
        }

        return result
    }
}

private val _info = BOJSolution.Info(
    no = 1260,
    title = "DFS 와 BFS",
    category = arrayOf(BOJSolution.BFS),
    url = "https://www.acmicpc.net/problem/1260"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase( // case 1
        input = "4 5 1\n" +
                "1 2\n" +
                "1 3\n" +
                "1 4\n" +
                "2 4\n" +
                "3 4",
        output ="1 2 4 3\n" +
                "1 2 3 4"
    ),
    BOJSolution.TestCase( // case 2
        input = "5 5 3\n" +
                "5 4\n" +
                "5 2\n" +
                "1 2\n" +
                "3 4\n" +
                "3 1",
        output ="3 1 2 5 4\n" +
                "3 1 4 2 5"
    ),
    BOJSolution.TestCase( // case 3
        input = "1000 1 1000\n" +
                "999 1000",
        output ="1000 999\n" +
                "1000 999"
    ),
    BOJSolution.TestCase( // case 4
        input = "10 9 1\n" +
                "1 2\n" +
                "1 3\n" +
                "2 4\n" +
                "2 5\n" +
                "2 6\n" +
                "3 7\n" +
                "3 8\n" +
                "4 9\n" +
                "6 10",
        output ="1 2 4 9 5 6 10 3 7 8\n" +
                "1 2 3 4 5 6 7 8 9 10"
    ),
)