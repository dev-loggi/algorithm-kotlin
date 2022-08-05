package programmers.practice.level2

import Solution
import kotlin.math.absoluteValue


/**
 * 조이스틱
 * https://programmers.co.kr/learn/courses/30/lessons/42860?language=kotlin
 * */
class JoyStick : Solution {

    override fun execute() {
        //"JEROEN"	56
        //"JAN"	23
        solution("JEROEN").let { println("answer=$it") } // 56
        solution("JAN").let { println("answer=$it") } // 23
    }

    private val Int.abs: Int
        get() = absoluteValue

    fun solution(str: String): Int {
        val name = str.toCharArray()

        val arr = IntArray(10)

        arr.sliceArray(0 until 3).sum()

        return 0
    }

    fun solution2(str: String): Int {
        val name = str.toCharArray()
        val range = name.indices
        var changedCharCount = name.count { it != 'A' }

        if (changedCharCount == 0)
            return 0

        var count = 0

        if (name[0] != 'A') {
            count += name[0] - 'A'
            name[0] = 'A'
            changedCharCount--
        }

        var idx = 0
        var j = 0
        var k = 0
        while (true) {

            while (j in range && name[j] == 'A') {
                j = (j + 1) % name.size
            }
            while (name[k] == 'A') {
                k = if (k - 1 in range) k - 1 else range.last
            }

            val d1 = (idx - j).abs
            val d2 = (idx - k).abs

            idx = if (d1 < d2) j else k
            count += if (d1 < d2) d1 else d2

            count += name[idx] - 'A'
            name[idx] = 'A'

            if (--changedCharCount == 0)
                break
        }

        return count
    }
}