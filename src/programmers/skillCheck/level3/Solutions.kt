package programmers.skillCheck.level3

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class Solutions {

    fun execute() {
        var answer: Int

        // [3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]
        answer = solution(6, arrayOf(
            intArrayOf(3, 6),
            intArrayOf(4, 3),
            intArrayOf(3, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 2),
            intArrayOf(2, 4),
            intArrayOf(5, 2),
        ))
        println("answer=$answer\n\n") // 3

        // [4, 3], [4, 2], [3, 2], [1, 2], [2, 5]
        answer = solution2(5, arrayOf(
            intArrayOf(4, 3),
            intArrayOf(4, 2),
            intArrayOf(3, 2),
            intArrayOf(1, 2),
            intArrayOf(2, 5),
        ))
        println("answer=$answer") // 2
    }

    fun solution(n: Int, edge: Array<IntArray>): Int {
        return traverseByBFS(makeGraph(edge))
    }

    private fun traverseByBFS(graph: HashMap<Int, HashSet<Int>>): Int {
        val queue = ArrayDeque(listOf(1))
        val set = hashSetOf(1)

        val depthMap = hashMapOf(Pair(1, 0))

        while (queue.isNotEmpty()) {
            val node = queue.pop()
            set.add(node)

            graph[node]?.filterNot { set.contains(it) }
                ?.forEach {
                    queue.offer(it)
                    set.add(it)

                    depthMap[it] = depthMap[node]!! + 1
                }
        }

        val maxDepth = depthMap.values.maxOf { it }

        return depthMap.values.count { it == maxDepth }
    }

    private fun makeGraph(edge: Array<IntArray>): HashMap<Int, HashSet<Int>> {
        val graph = hashMapOf<Int, HashSet<Int>>()
        edge.forEach {
            graph.getOrPut(it[0]) { hashSetOf() }.add(it[1])
            graph.getOrPut(it[1]) { hashSetOf() }.add(it[0])
        }
        return graph
    }


    fun solution2(n: Int, results: Array<IntArray>): Int {
        var answer = 0

        val graph = makeGraph2(results)
        val graphReverse = makeGraphReverse(results)

        println(graph)
        println(graphReverse)
        println()

        val playerSet = hashSetOf<Int>()
        for (i in 1..n) {
            playerSet.addAll(traverse(graph, i))
            playerSet.addAll(traverse(graphReverse, i))

            println("for($i): $playerSet, size=${playerSet.size}, n=$n")
            if (playerSet.size == n) {
                answer++
            }
            playerSet.clear()
        }

        return answer
    }

    private fun traverse(graph: HashMap<Int, HashSet<Int>>, start: Int): HashSet<Int> {
        val queue = ArrayDeque(listOf(start))
        val set = hashSetOf(start)

        while (queue.isNotEmpty()) {
            val player = queue.pop()
            set.add(player)

            println("start=$start, player=$player")

            graph[player]?.filterNot { set.contains(it) }
                ?.forEach {
                    queue.offer(it)
                    set.add(it)
                }
        }

        return set
    }

    private fun makeGraph2(results: Array<IntArray>): HashMap<Int, HashSet<Int>> =
        hashMapOf<Int, HashSet<Int>>().also { graph ->
            results.forEach {
                graph.getOrPut(it[0]) { hashSetOf() }.add(it[1])
            }
        }

    private fun makeGraphReverse(results: Array<IntArray>): HashMap<Int, HashSet<Int>> =
        hashMapOf<Int, HashSet<Int>>().also { graph ->
            results.forEach {
                graph.getOrPut(it[1]) { hashSetOf() }.add(it[0])
            }
        }
}