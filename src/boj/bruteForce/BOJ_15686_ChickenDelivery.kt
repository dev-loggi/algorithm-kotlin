package boj.bruteForce

import common.Solution
import kotlin.math.abs

/**
 * 15686
 * 치킨 배달
 * https://www.acmicpc.net/problem/15686
 * 완전 탐색
 * */
class BOJ_15686_ChickenDelivery : Solution {

    override fun execute() {
        repeat(4) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 지도 세로 크기(2 ≤ N ≤ 50)
        val M = input[1].toInt() // 폐업을 시키지 않을 치킨집의 최대 갯수(1 ≤ M ≤ 13)
        val map = Array(N) { IntArray(M) }

        // 1 ≤ 집의 갯수 ≤ 2N
        // M ≤ 치킨 집의 갯수 ≤ 13
        for (i in 0 until N) {
            map[i] = readLine()!!.split(" ")
                .map { it.toInt() }
                .toIntArray()
        }

        solution(N, M, map)
    }

    fun solution(N: Int, M: Int, map: Array<IntArray>) {
        val totalShops = mutableListOf<IntArray>()
        val homes = mutableListOf<IntArray>()

        for (r in map.indices) {
            for (c in map[r].indices) {
                when (map[r][c]) {
                    1 -> homes.add(intArrayOf(r, c))
                    2 -> totalShops.add(intArrayOf(r, c))
                }
            }
        }

        val minOfCityChickenDistance = totalShops.combination(M)
            .minOf { shops: List<IntArray> ->
                homes.sumOf { it.chickenDistanceByHome(shops) }
            }

        println(minOfCityChickenDistance)
    }

    fun IntArray.chickenDistanceByHome(shops: List<IntArray>): Int {
        return shops.minOfOrNull { abs(this[0] - it[0]) + abs(this[1] - it[1]) } ?: 0
    }

    fun <T> List<T>.combination(r: Int): List<List<T>> {
        val result = mutableListOf<List<T>>()

        fun recursive(depth: Int, start: Int, list: List<T>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }
                recursive(depth + 1, i + 1, newList)
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}
/*
[case1]
5 3
0 0 1 0 0
0 0 2 0 1
0 1 2 0 0
0 0 1 0 0
0 0 0 0 2
[case1 answer]
5

[case2]
5 2
0 2 0 1 0
1 0 1 0 0
0 0 0 0 0
2 0 0 1 1
2 2 0 1 2
[case2 answer]
10

[case3]
5 1
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
1 2 0 0 0
[case3 answer]
11

[case4]
5 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
1 2 0 2 1
[case4 answer]
32

* */