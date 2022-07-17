package boj.bruteForce

import boj.BOJSolution
import java.util.ArrayDeque
import kotlin.math.max

/**
 * 17281
 * 야구
 * https://www.acmicpc.net/problem/17281
 * 완전 탐색
 * */
class BOJ_17281_Baseball : BOJSolution(info(), testCases()) {

    override fun main() {
        val N = readLine()!!.toInt() // 이닝 수(2 ≤ N ≤ 50)
        val playersByInning = (0 until N).map {
            readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()

        //solution1(N, playersByInning)
        //solution2(N, playersByInning)
        solution3(N, playersByInning)
    }

    fun solution3(N: Int, playersByInning: Array<IntArray>) {
        var maxPoint = Int.MIN_VALUE

        fun play(order: IntArray) {
            var totalPoint = 0
            var position = 0

            playersByInning.forEach { players ->
                // 각 이닝 시작
                var b1 = 0
                var b2 = 0
                var b3 = 0
                var home = 0
                var out = 0

                while (out < 3) {
                    val cur = position++ % 9
                    val batting = players[order[cur]]

                    if (batting == 0) {
                        out += 1
                        continue
                    }

                    if (batting == 1) {
                        home += b3
                        b3 = b2
                        b2 = b1
                        b1 = 1
                    } else if (batting == 2) {
                        home += b3 + b2
                        b3 = b1
                        b2 = 1
                        b1 = 0
                    } else if (batting == 3) {
                        home += b3 + b2 + b1
                        b3 = 1
                        b2 = 0
                        b1 = 0
                    } else if (batting == 4) {
                        home += b3 + b2 + b1 + 1
                        b3 = 0
                        b2 = 0
                        b1 = 0
                    }

                    totalPoint += home


                    home = 0
                }
            }
            maxPoint = max(maxPoint, totalPoint)
        }

        val fixed = 3
        val array = intArrayOf(1, 2, 3, 0, 4, 5, 6, 7, 8)
        val tempArr = intArrayOf(1, 2, 3, 0, 4, 5, 6, 7, 8)
        val range = array.indices
        val visited = BooleanArray(array.size)
        visited[fixed] = true

        fun permutate(depth: Int) {
            if (depth == fixed) {
                permutate(depth + 1)
            } else if (depth == 9) {
                play(tempArr)
            } else {
                for (i in range) {
                    if (visited[i])
                        continue

                    visited[i] = true
                    tempArr[depth] = array[i]
                    permutate(depth + 1)
                    visited[i] = false
                }
            }
        }

        permutate(0)
        println(maxPoint)
    }

    /**
     * 시간 초과(1초 이상)
     * */
    fun solution2(N: Int, playersByInning: Array<IntArray>) {
        var maxPoint = Int.MIN_VALUE
        val playerCount = playersByInning[0].size

        intArrayOf(1, 2, 3, 0, 4, 5, 6, 7, 8).onPermutate(r = playerCount, fixed = 3) { order ->
            var totalPoint = 0
            var position = 0

            playersByInning.forEach { players ->
                // 각 이닝 시작
                val ground = ArrayDeque(listOf(0, 0, 0))
                var out = 0

                while (out < 3) {
                    val cur = position++ % playerCount
                    val batting = players[order[cur]]

                    if (batting == 0) {
                        out += 1
                        continue
                    }

                    for (i in 0 until batting) {
                        if (i == 0) {
                            ground.offer(1)
                        } else {
                            ground.offer(0)
                        }

                        if (ground.poll() == 1)
                            totalPoint += 1
                    }
                }
            }

            maxPoint = max(maxPoint, totalPoint)
        }

        println(maxPoint)
    }

    /**
     * 시간 초과(1초 이상)
     * */
    fun solution1(N: Int, playersByInning: Array<IntArray>) {
        val start = System.currentTimeMillis()

        val playerCount = playersByInning[0].size

        val maxPoint = intArrayOf(1, 2, 3, 0, 4, 5, 6, 7, 8)
            .permutation(r = playerCount, fixed = 3)
            .maxOf { order ->
                var totalPoint = 0
                var position = 0

                playersByInning.forEach { players ->
                    // 각 이닝 시작
                    val ground = ArrayDeque(listOf(0, 0, 0))
                    var out = 0

                    while (out < 3) {
                        val cur = position++ % playerCount
                        val batting = players[order[cur]]

                        if (batting == 0) {
                            out += 1
                            continue
                        }

                        for (i in 0 until batting) {
                            if (i == 0) {
                                ground.offer(1)
                            } else {
                                ground.offer(0)
                            }

                            if (ground.poll() == 1)
                                totalPoint += 1
                        }
                    }
                }

                totalPoint
            }

        println(maxPoint)

        val end = System.currentTimeMillis()
        println("gap(2)=${end-start}")
    }

    fun IntArray.onPermutate(r: Int, fixed: Int, body: (IntArray) -> Unit) {
        val visited = BooleanArray(size)
        val arr = IntArray(size) { if (it == fixed) this[fixed] else -1 }
        visited[fixed] = true

        fun recursive(depth: Int) {
            if (depth == fixed) {
                recursive(depth + 1)
            } else if (depth == r) {
                body(arr)
            } else {
                for (i in indices) {
                    if (visited[i])
                        continue

                    visited[i] = true
                    arr[depth] = this[i]
                    recursive(depth + 1)
                    visited[i] = false
                }
            }
        }

        recursive(0)
    }

    fun IntArray.permutation(r: Int, fixed: Int): List<IntArray> {
        val result = mutableListOf<IntArray>()

        fun recursive(depth: Int, arr: IntArray) {
            if (depth == r) {
                result.add(arr)
            } else if (depth == fixed) {
                recursive(depth + 1, arr)
            } else {
                for (i in indices) {
                    if (i == fixed || arr.contains(this[i]))
                        continue

                    val newArr = arr.clone()
                    newArr[depth] = this[i]
                    recursive(depth + 1, newArr)
                }
            }
        }

        recursive(0, IntArray(r) { if (it == fixed) this[fixed] else -1 })
        return result
    }
}

private fun info() = BOJSolution.Info(
    no = 17281,
    title = "야구",
    category = arrayOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/17281"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "2\n" +
                "4 0 0 0 0 0 0 0 0\n" +
                "4 0 0 0 0 0 0 0 0",
        output = "1"
    ),
    BOJSolution.TestCase(
        input = "2\n" +
                "4 0 0 0 1 1 1 0 0\n" +
                "0 0 0 0 0 0 0 0 0",
        output = "4"
    ),
    BOJSolution.TestCase(
        input = "2\n" +
                "0 4 4 4 4 4 4 4 4\n" +
                "0 4 4 4 4 4 4 4 4",
        output = "43"
    ),
    BOJSolution.TestCase(
        input = "2\n" +
                "4 3 2 1 0 4 3 2 1\n" +
                "1 2 3 4 1 2 3 4 0",
        output = "46"
    ),
    BOJSolution.TestCase(
        input = "9\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0\n" +
                "4 4 4 4 4 4 4 4 0",
        output = "216"
    ),
    BOJSolution.TestCase(
        input = "9\n" +
                "1 2 4 3 0 2 1 0 3\n" +
                "1 2 1 2 0 0 0 0 1\n" +
                "3 4 2 3 1 2 3 4 0\n" +
                "0 1 2 3 4 2 1 0 0\n" +
                "0 0 0 0 0 0 1 4 4\n" +
                "0 4 0 4 0 4 0 4 0\n" +
                "0 4 2 2 2 2 2 2 2\n" +
                "1 1 1 1 1 1 1 1 0\n" +
                "0 2 0 3 0 1 0 2 0",
        output = "89"
    ),
)