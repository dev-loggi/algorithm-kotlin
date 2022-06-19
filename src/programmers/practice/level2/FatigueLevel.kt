package programmers.practice.level2

import common.Solution
import kotlin.math.max


/**
 * 피로도
 * https://programmers.co.kr/learn/courses/30/lessons/87946?language=kotlin
 * */
class FatigueLevel : Solution {

    override fun execute() {
        solution(80, arrayOf(
            intArrayOf(80, 20),
            intArrayOf(50, 40),
            intArrayOf(30, 10)
        )).let { println("answer=$it") } // 3
    }

    fun solution(k: Int, data: Array<IntArray>): Int {
        val dungeons = Array(data.size) { i -> Dungeon(i, data[i][0], data[i][1]) }
        var maxDepth = 0

        fun dfs(n: Int, energy: Int, depth: Int, route: MutableSet<Int>) {
            route.add(n)
            maxDepth = max(maxDepth, depth)
            println("dfs(): n=$n, energy=$energy, depth=$depth, route=$route")

            dungeons.filter { !route.contains(it.n) && energy >= it.required }
                .forEach { dfs(it.n, energy - it.used, depth + 1, route.toMutableSet()) }
        }

        dfs(-1, k, 0, mutableSetOf())

        return maxDepth
    }

    class Dungeon(val n: Int, val required: Int, val used: Int)
}