package programmers.kakao.y2020

import programmers.Programmers
import java.util.LinkedList

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 3] 자물쇠와 열쇠
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60059
 * */
class KAKAO_BLIND_2020_P3 : Programmers.Solution {

    override fun execute() {
        testCases().forEach {
            solution(it.first, it.second).let { println("★ answer=$it\n") }
        }
    }

    data class Point(val r: Int, val c: Int)

    fun solution(originKey: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val N = lock.size
        val M = originKey.size

        val grooveCount = lock.sumOf { it.count { n -> n == 0 } }

        var key = originKey
        var bumps = key.getBumps()

        for (rotation in 0 until 4) {

            for (moveR in -(N - 1) until M) for (moveC in -(N - 1) until M) {
                var count = 0

                for (bump in bumps) {
                    val r = bump.r - moveR
                    val c = bump.c - moveC

                    if (r !in 0 until N || c !in 0 until N)
                        continue

                    if (lock[r][c] == 1)
                        break

                    count += 1
                }

                if (count == grooveCount) {
                    return true
                }
            }

            if (rotation != 3) {
                key = key.rotate90()
                bumps = key.getBumps()
            }
        }

        return false
    }

    private fun Array<IntArray>.rotate90(): Array<IntArray> {
        val key = this
        val n = key.lastIndex
        val rotateKey = Array(key.size) { IntArray(key.size) }

        for (r in key.indices) for (c in key.indices)
            rotateKey[c][n - r] = key[r][c]

        return rotateKey
    }

    private fun Array<IntArray>.getBumps(): LinkedList<Point> {
        val bumps = LinkedList<Point>()

        for (r in indices) for (c in indices) {
            if (this[r][c] == 1)
                bumps.add(Point(r, c))
        }

        return bumps
    }

    private fun Array<IntArray>.print() {
        forEach { println(it.joinToString("")) }
    }
}

private fun testCases(): Array<Pair<Array<IntArray>, Array<IntArray>>> = arrayOf(
    // test case 1
    Pair(
        first = arrayOf(
            intArrayOf(0,0,0),
            intArrayOf(1,0,0),
            intArrayOf(0,1,1),
        ),
        second = arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(1,1,0),
            intArrayOf(1,0,1),
        )
    ), // answer = true
    // test case 2
    Pair(
        first = arrayOf(
            intArrayOf(1,0,0,0),
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,0,0),
        ),
        second = arrayOf(
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,0),
        )
    ), // answer = true
    // test case 3
    Pair(
        first = arrayOf(
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,1,0),
            intArrayOf(0,0,0,0),
            intArrayOf(1,0,0,1),
        ),
        second = arrayOf(
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(0,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1),
            intArrayOf(0,1,1,1,1,1),
        )
    ), // answer = true
    // test case 4
    Pair(
        first = arrayOf(
            intArrayOf(0,0,0,0),
            intArrayOf(0,0,0,0),
            intArrayOf(0,1,0,1),
            intArrayOf(0,0,1,0),
        ),
        second = arrayOf(
            intArrayOf(1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,1,1),
            intArrayOf(1,1,1,1,1,0,1),
            intArrayOf(1,1,1,1,1,1,0),
            intArrayOf(1,1,1,1,1,0,1),
        ),
    ), // answer = true
)