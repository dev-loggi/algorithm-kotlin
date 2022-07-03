package programmers.practice.level3

import Solution

/**
 * 섬 연결하기
 * https://programmers.co.kr/learn/courses/30/lessons/42861?language=kotlin
 * Greedy
 * */
// TODO: 미완성
class ConnectingIslands : Solution {

    override fun execute() {
        solution(4, arrayOf(
            intArrayOf(0,1,1),
            intArrayOf(0,2,2),
            intArrayOf(1,2,5),
            intArrayOf(1,3,1),
            intArrayOf(2,3,8),
        )).let { println("answer=$it") } // 4
    }

    fun solution(n: Int, costs: Array<IntArray>): Int {
        val graph = mutableMapOf<Int, MutableList<IntArray>>()

        for (i in 0 until n) {
            graph[i] = mutableListOf<IntArray>()
        }

        for (cost in costs) {
            graph[cost[0]]!!.add(intArrayOf(cost[1], cost[2]))
            graph[cost[1]]!!.add(intArrayOf(cost[0], cost[2]))
        }

        val minCosts = mutableListOf<Int>()

        // 0..n-1 각각 시작점으로 최소값 순회
        for (i in 0 until n) {
            minCosts.add(dfs(graph, i))
        }

        return minCosts.minOf { it }
    }

    private fun dfs(graph: Map<Int, List<IntArray>>, start: Int): Int {
        var cost = 0
        val set = mutableSetOf(start)

        fun recursive(v: Int) {
            set.add(v)

            graph[v]!!.filterNot { set.contains(it[0]) }
                .minByOrNull { it[1] }
                ?.let {
                    cost += it[1]
                    recursive(it[0])
                }
        }

        recursive(start)
        return cost
    }
}