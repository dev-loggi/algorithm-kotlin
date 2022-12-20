package programmers.practice.level3

import programmers.Programmers.Solution
import codelab.Util
import java.util.Queue
import java.util.LinkedList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * 가장 먼 노드
 * 그래프
 * https://programmers.co.kr/learn/courses/30/lessons/49189
 * */
class FarthestNode : Solution {

    companion object {
        val TEST_CASE = TestCase(
            6, arrayOf(
                intArrayOf(3, 6),
                intArrayOf(4, 3),
                intArrayOf(3, 2),
                intArrayOf(1, 3),
                intArrayOf(1, 2),
                intArrayOf(2, 4),
                intArrayOf(5, 2),
            )
        )
    }

    class TestCase(val n: Int, val edge: Array<IntArray>)

    override fun execute() {
    }

    fun solution(case: Int) {
        val result = when (case) {
            0 -> solution(TEST_CASE.n, TEST_CASE.edge)
            else -> 0
        }

        println("FarthestNode.solution(): $result")
    }

    fun solution(n: Int, edge: Array<IntArray>): Int {
        val graph = makeGraph(edge)
        val depths = Array(n) { 0 }

        traverseByBFS(graph, 1, depths)

        println("graph=$graph")
        println("depths=${Util.getLog(depths)}")

        val maxDepth = depths.maxOf { it }

        return depths.count { it == maxDepth }
    }

    private fun traverseByBFS(graph: HashMap<Int, HashSet<Int>>, start: Int, depths: Array<Int>) {
        val queue: Queue<Int> = LinkedList()
        val set = hashSetOf(1)

        queue.add(start)

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            println("traverse(): node=$node, depth=${depths[node - 1]}")

            graph[node]?.forEach {
                if (!set.contains(it)) {
                    queue.add(it)
                    set.add(it)
                    depths[it - 1] = depths[node - 1] + 1
                }
            }
        }
    }

    private fun makeGraph(edge: Array<IntArray>): HashMap<Int, HashSet<Int>> =
        hashMapOf<Int, HashSet<Int>>().also { graph ->
            edge.forEach { nodes ->
                graph[nodes[0]] = (graph[nodes[0]] ?: hashSetOf()).also {
                    it.add(nodes[1])
                }
                graph[nodes[1]] = (graph[nodes[1]] ?: hashSetOf()).also {
                    it.add(nodes[0])
                }
            }
        }
}