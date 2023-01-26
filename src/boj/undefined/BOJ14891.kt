package boj.undefined

import boj.BOJSolution
import boj.BOJSolution.Info
import boj.BOJSolution.TestCase

class BOJ14891 : BOJSolution(info(), testCase()) {

    companion object {
        const val N_POLE = 0
        const val S_POLE = 1
        const val CLOCKWISE = 1
        const val COUNTERCLOCKWISE = -1
    }

    var K = 0
    lateinit var wheels: Array<IntArray>
    lateinit var rotates: Array<IntArray>

    fun main() {
        wheels = (1..4).map { _ ->
            readln().map(Char::digitToInt).toIntArray()
        }.toTypedArray()

        K = readln().toInt()

        rotates = (1..K).map { _ ->
            readln().split(" ").map(String::toInt).toIntArray()
        }.toTypedArray()

        println(solution())
    }

    fun solution(): Int {
//        println("solution(): K=$K, wheels[0]=${wheels[0].contentToString()}")
        for ((i, dir) in rotates) {
            val index = i - 1

            rotateLeft(index, dir)
            rotateRight(index, dir)
            rotate(index, dir)
        }

        var score = 0
        if (wheels[0][0] == S_POLE) score += 1
        if (wheels[1][0] == S_POLE) score += 2
        if (wheels[2][0] == S_POLE) score += 4
        if (wheels[3][0] == S_POLE) score += 8

        return score
    }

    fun rotateLeft(index: Int, dir: Int) {
        val leftIndex = index - 1

        if (leftIndex >= 0 && wheels[index][6] != wheels[leftIndex][2]) {
            rotateLeft(leftIndex, -dir)
            rotate(leftIndex, -dir)
        }
    }

    fun rotateRight(index: Int, dir: Int) {
        val rightIndex = index + 1

        if (rightIndex < wheels.size && wheels[index][2] != wheels[rightIndex][6]) {
            rotateRight(rightIndex, -dir)
            rotate(rightIndex, -dir)
        }
    }

    fun rotate(index: Int, dir: Int) {
        val wheel = wheels[index]

        when (dir) {
            CLOCKWISE -> {
                val temp = wheel.last()
                for (i in wheel.lastIndex downTo 1) {
                    wheel[i] = wheel[i - 1]
                }
                wheel[0] = temp
            }
            COUNTERCLOCKWISE -> {
                val temp = wheel.first()
                for (i in 0 until wheel.lastIndex) {
                    wheel[i] = wheel[i + 1]
                }
                wheel[wheel.lastIndex] = temp
            }
        }
    }

    override fun runEachSolution() = main()
}

private fun info() = Info(
    no = 14981,
    title = "톱니바퀴",
    category = listOf(),
    problemUrl = "https://www.acmicpc.net/problem/14891",
)

private fun testCase() = listOf(
    TestCase(
        input = """
            10101111
            01111101
            11001110
            00000010
            2
            3 -1
            1 1
        """.trimIndent(),
        output = "7",
    ),
    TestCase(
        input = """
            11111111
            11111111
            11111111
            11111111
            3
            1 1
            2 1
            3 1
        """.trimIndent(),
        output = "15",
    ),
    TestCase(
        input = """
            10001011
            10000011
            01011011
            00111101
            5
            1 1
            2 1
            3 1
            4 1
            1 -1
        """.trimIndent(),
        output = "6",
    ),
    TestCase(
        input = """
            10010011
            01010011
            11100011
            01010101
            8
            1 1
            2 1
            3 1
            4 1
            1 -1
            2 -1
            3 -1
            4 -1
        """.trimIndent(),
        output = "5",
    ),
)