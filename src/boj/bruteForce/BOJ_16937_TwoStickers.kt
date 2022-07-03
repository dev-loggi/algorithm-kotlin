package boj.bruteForce

import Solution
import boj.BOJSolution
import kotlin.math.max

/**
 * 16937
 * 두 스티커
 * https://www.acmicpc.net/problem/16937
 * 완전 탐색
 * */
class BOJ_16937_TwoStickers : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
        main()
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val H = input[0].toInt() // 모눈종이 세로 크기
        val W = input[1].toInt() // 모눈종이 가로 크기
        val N = readLine()!!.toInt() // 스티커 갯수
        val RC = Array(N) { IntArray(2) } // 스티커 각각의 크기

        for (i in 0 until N) {
            RC[i] = readLine()!!.split(" ")
                .map { it.toInt() }
                .toIntArray()
        }

        solution(H, W, N, RC)
    }

    fun solution(H: Int, W: Int, N: Int, RC: Array<IntArray>) {
        val maxArea = IntArray(N) { it }
            .combination(2)
            .map { stickers ->
                val s1 = RC[stickers[0]]
                val s2 = RC[stickers[1]]

                if ((s1[1] + s2[1] <= W && max(s1[0], s2[0]) <= H) || // s1, s2 가로 배치
                    (s1[0] + s2[0] <= H && max(s1[1], s2[1]) <= W) || // s1, s2 세로 배치
                    (s1[0] + s2[1] <= W && max(s1[1], s2[0]) <= H) || // s1(r), s2 가로 배치
                    (s1[1] + s2[0] <= H && max(s1[0], s2[1]) <= W) || // s1(r), s2 세로 배치
                    (s1[1] + s2[0] <= W && max(s1[0], s2[1]) <= H) || // s1, s2(r) 가로 배치
                    (s1[0] + s2[1] <= H && max(s1[1], s2[0]) <= W) || // s1, s2(r) 세로 배치
                    (s1[0] + s2[0] <= W && max(s1[1], s2[1]) <= H) || // s1(r), s2(r) 가로 배치
                    (s1[1] + s2[1] <= H && max(s1[0], s2[0]) <= W)    // s1(r), s2(r) 세로 배치
                ) {
                    s1[0] * s1[1] + s2[0] * s2[1]
                } else {
                    0
                }
            }
            .maxOfOrNull { it } ?: 0

        println(maxArea)
    }

    fun IntArray.combination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i + 1, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}

private val _info = BOJSolution.Info(
    no = 16937,
    title = "두 스티커",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    url = "https://www.acmicpc.net/problem/16937"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input =
        "2 2\n" +
        "2\n" +
        "1 2\n" +
        "2 1",
        output = "4"
    ),
    BOJSolution.TestCase(
        input =
        "10 9\n" +
        "4\n" +
        "2 3\n" +
        "1 1\n" +
        "5 10\n" +
        "9 11",
        output = "4"
    ),
    BOJSolution.TestCase(
        input =
        "10 10\n" +
        "3\n" +
        "6 6\n" +
        "7 7\n" +
        "20 5",
        output = "0"
    ),
)