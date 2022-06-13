package boj.dfsBfs

import common.Solution
import java.util.*

/**
 * 1260
 * DFS 와 BFS
 * https://www.acmicpc.net/problem/1260
 * */
class P_1260_DFSandBFS : Solution {

/*
[case1]
4 5 1
1 2
1 3
1 4
2 4
3 4
[case1 answer]
1 2 4 3
1 2 3 4

[case2]
5 5 3
5 4
5 2
1 2
3 4
3 1
[case2 answer]
3 1 2 5 4
3 1 4 2 5

[case3]
1000 1 1000
999 1000
[case3 answer]
1000 999
1000 999

[case4]
10 9 1
1 2
1 3
2 4
2 5
2 6
3 7
3 8
4 9
6 10
[case1 answer]
1 2 4 9 5 6 10 3 7 8
1 2 3 4 5 6 7 8 9 10

*/
    override fun execute() {
        for (i in 0 until 3) main()
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