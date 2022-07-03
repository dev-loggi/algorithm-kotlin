package boj.bruteForce

import Solution
import boj.BOJSolution
import kotlin.math.abs

class BOJ_15686_ChickenDelivery : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
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

private val _info = BOJSolution.Info(
    no = 15686,
    title = "치킨 배달",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/15686"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input =
        "5 3\n" +
                "0 0 1 0 0\n" +
                "0 0 2 0 1\n" +
                "0 1 2 0 0\n" +
                "0 0 1 0 0\n" +
                "0 0 0 0 2",
        output = "5"
    ),
    BOJSolution.TestCase(
        input =
        "5 2\n" +
                "0 2 0 1 0\n" +
                "1 0 1 0 0\n" +
                "0 0 0 0 0\n" +
                "2 0 0 1 1\n" +
                "2 2 0 1 2",
        output = "10"
    ),
    BOJSolution.TestCase(
        input =
        "5 1\n" +
                "1 2 0 0 0\n" +
                "1 2 0 0 0\n" +
                "1 2 0 0 0\n" +
                "1 2 0 0 0\n" +
                "1 2 0 0 0",
        output = "11"
    ),
    BOJSolution.TestCase(
        input =
        "5 1\n" +
                "1 2 0 2 1\n" +
                "1 2 0 2 1\n" +
                "1 2 0 2 1\n" +
                "1 2 0 2 1\n" +
                "1 2 0 2 1",
        output = "32"
    ),
)