package boj.etc

import boj.BOJSolution
import kotlin.math.absoluteValue
import kotlin.random.Random

/**
 * 2470 두 용액
 *
 * 시간 제한: 1초
 * 메모리 제한: 128 MB
 * */
class BOJ_2470_TwoSolutions : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        IntArray(10) { Random.nextInt(-50, 50) }.let { println(it.joinToString(" ")) }
        IntArray(10) { Random.nextInt(0, 50) }.let { println(it.joinToString(" ")) }
        IntArray(10) { Random.nextInt(-50, -1) }.let { println(it.joinToString(" ")) }

        val N = readln().toInt()
        val values = readln().split(" ").map { it.toInt() }.toIntArray()

        solution1(values).let { println(it.joinToString(" ")) }
    }

    private fun solution1(values: IntArray): IntArray {
        values.sort()
        println(values.contentToString())

        var (i, j) = 0 to values.lastIndex
        var (value1, value2) = values[i] to values[j]
        var minSum = Int.MAX_VALUE

        while (i < j) {
            val sum = values[i] + values[j]

            if (sum.absoluteValue < minSum) {
                minSum = sum.absoluteValue
                value1 = values[i]
                value2 = values[j]
            }

            if (sum < 0) i += 1
            if (sum > 0) j -= 1
            if (sum == 0) break
        }

        return intArrayOf(value1, value2)
    }
}

private fun info() = BOJSolution.Info(
    no = 2470,
    title = "두 용액",
    category = listOf(BOJSolution.TWO_POINTER),
    problemUrl = "https://www.acmicpc.net/problem/2470",
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "5\n" +
                "-2 4 -99 -1 98",
        output = "-99 98"
    )
)