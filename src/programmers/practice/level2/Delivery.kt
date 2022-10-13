package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 배달
 * https://programmers.co.kr/learn/courses/30/lessons/12978?language=kotlin
 * */
class Delivery : Solution {

    override fun execute() {
        solution(5, arrayOf(
            intArrayOf(1,2,1),
            intArrayOf(2,3,3),
            intArrayOf(5,2,2),
            intArrayOf(1,4,2),
            intArrayOf(5,3,1),
            intArrayOf(5,4,2),
        ), 3)
            .let { println("answer=$it") } // 4

        solution(6, arrayOf(
            intArrayOf(1,2,1),
            intArrayOf(1,3,2),
            intArrayOf(2,3,2),
            intArrayOf(3,4,3),
            intArrayOf(3,5,2),
            intArrayOf(3,5,3),
            intArrayOf(5,6,1),
        ), 4)
            .let { println("answer=$it") } // 4
    }

    private lateinit var graph: MutableMap<Int, MutableSet<Int>>
    private lateinit var time: Array<IntArray>

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        graph = mutableMapOf()
        time = Array(N + 1) { IntArray(N + 1) { Int.MAX_VALUE } }

        for ((c1, c2, t) in road) {
            graph.getOrPut(c1) { mutableSetOf() }.add(c2)
            graph.getOrPut(c2) { mutableSetOf() }.add(c1)

            if (t < time[c1][c2]) {
                time[c1][c2] = t
                time[c2][c1] = t
            }
        }

        return delivery(N, k)
    }

    private fun delivery(N: Int, k: Int): Int {
        val set = mutableSetOf<Int>()
        val visited = BooleanArray(N + 1)
        visited[1] = true

        fun dfs(curr: Int, accTime: Int, tab: String) {
            if (k < accTime)
                return

            println("${tab}dfs(curr=$curr, accTime=$accTime)")
            set.add(curr)

            for (next in graph[curr]!!) {
                if (visited[next])
                    continue

                visited[next] = true
                dfs(next, accTime + time[curr][next], "$tab    ")
                visited[next] = false
            }
        }

        dfs(1, 0, "")
        return set.size
    }

    fun solution2(N: Int, road: Array<IntArray>, k: Int): Int {
        val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()

        for (info in road) {
            graph.getOrPut(info[0]) { mutableListOf() }
                .add(info[1] to info[2])

            graph.getOrPut(info[1]) { mutableListOf() }
                .add(info[0] to info[2])
        }

        println(graph)

        return delivery(graph, k)
    }

    private fun delivery(graph: Map<Int, List<Pair<Int, Int>>>, k: Int): Int {
        val availableCountries = mutableSetOf(1)

        fun dfs(n: Int, route: MutableSet<Int>, time: Int) {
            println("n=$n, route=$route, time=$time")
            route.add(n)

            for (country in graph[n]!!) {
                if (route.contains(country.first))
                    continue

                println("$country, nextTime=${time + country.second}")
                if (time + country.second <= k) {
                    availableCountries.add(country.first)

                    dfs(country.first, route.toMutableSet(),time + country.second)
                }
            }
        }

        dfs(1, mutableSetOf(1), 0)
        return availableCountries.size
    }
}