package boj.bitmask

import boj.BOJSolution
import java.util.*
import kotlin.math.max

/**
 * [BOJ 14391] 종이 조각
 * https://www.acmicpc.net/problem/14391
 * */
class BOJ_14391_PaperPiece : BOJSolution(info(), testCase()) {

    private var maxSum = 0
    private var N = 0
    private var M = 0
    private lateinit var num: Array<IntArray>
    private lateinit var combs: Array<Array<BooleanArray>>

    override fun main() {
        // (1 ≤ N, M ≤ 4)
        val line = readln().split(" ").map { it.toInt() }
        maxSum = 0
        N = line[0]
        M = line[1]

        num = Array(N) {
            readln().map { it.digitToInt() }.toIntArray()
        }

        combs = getCombinations()

        dfs(0, Array(N) { booleanArrayOf() })

        println(maxSum)
    }

    private fun dfs(depth: Int, flag: Array<BooleanArray>) {
        if (depth < N) {
            for (comb in combs[depth]) {
                flag[depth] = comb

                dfs(depth + 1, flag)
            }
        } else {
            var sum = 0

            // 가로 방향 숫자
            for (r in 0 until N) {
                var n = 0
                for (c in 0 until M) {
                    if (flag[r][c]) {
                        n = n * 10 + num[r][c]
                    } else {
                        sum += n
                        n = 0
                    }
                }
                sum += n
            }
            // 세로 방향 숫자
            for (c in 0 until M) {
                var n = 0
                for (r in 0 until N) {
                    if (!flag[r][c]) {
                        n = n * 10 + num[r][c]
                    } else {
                        sum += n
                        n = 0
                    }
                }
                sum += n
            }

            maxSum = max(sum, maxSum)
        }
    }

    private fun getCombinations(): Array<Array<BooleanArray>> {
        val combsAll = LinkedList<Array<BooleanArray>>()
        var combs = LinkedList<BooleanArray>()
        val visited = BooleanArray(M)

        fun dfs(depth: Int, start: Int, r: Int, arr: BooleanArray) {
            if (depth == r) {
                combs.add(arr)
                return
            }

            for (i in start until M) {
                if (visited[i]) continue

                val newArr = arr.copyOf()
                newArr[i] = true
                visited[i] = true
                dfs(depth + 1, i + 1, r, newArr)
                visited[i] = false
            }
        }

        for (n in 0 until N) {
            combs = LinkedList<BooleanArray>()

            for (r in 0..M) {
                dfs(0, 0, r, BooleanArray(M))
            }

            combsAll.add(combs.toTypedArray())
        }

        return combsAll.toTypedArray()
    }

    private fun Array<BooleanArray>.print() {
        for (arr in this) {
            println(arr.joinToString { b -> if (b) "T" else "F" })
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 14391,
    title = "종이 조각",
    category = arrayOf(BOJSolution.BRUTE_FORCE, BOJSolution.BITMASK, BOJSolution.DFS),
    problemUrl = "https://www.acmicpc.net/problem/14391",
)

private fun testCase() = arrayOf(
    BOJSolution.TestCase(
        input = "2 3\n" +
                "123\n" +
                "312",
        output = "435",
    ),
    BOJSolution.TestCase(
        input = "2 2\n" +
                "99\n" +
                "11",
        output = "182",
    ),
    BOJSolution.TestCase(
        input = "4 3\n" +
                "001\n" +
                "010\n" +
                "111\n" +
                "100",
        output = "1131",
    ),
    BOJSolution.TestCase(
        input = "1 1\n" +
                "8",
        output = "8",
    ),
)