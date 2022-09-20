package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 배달
 * https://programmers.co.kr/learn/courses/30/lessons/12978?language=kotlin
 * */
class Delivery : Solution {

    override fun execute() {
        //5	[[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]	3	4
        //6	[[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]	4	4

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

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
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