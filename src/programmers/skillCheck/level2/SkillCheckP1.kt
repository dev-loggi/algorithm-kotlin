package programmers.skillCheck.level2

import common.Solution
import utils.CollectionUtils
import kotlin.math.pow
import kotlin.math.sqrt

class SkillCheckP1 : Solution {

    override fun execute() {
        // 10	2	[4, 3]
        // 8	1	[3, 3]
        // 24	24	[8, 6]
    }

    fun solution(brown: Int, yellow: Int): IntArray {
        // b + y = w * h
        // b = 2(w + h) - 4
        // y = (w - 2)(h - 2)

        return (brown + yellow).divisors()
            .find {
                val w = it.second
                val h = it.first
                yellow == (w - 2) * (h - 2) && brown == 2 * (w + h) - 4
            }!!.toList().asReversed().toIntArray()
    }

    private fun Int.divisors(): List<Pair<Int, Int>> {
        val n = this
        val result = mutableListOf<Pair<Int, Int>>()

        for (i in 1..sqrt(n.toDouble()).toInt() + 1) {
            if (n % i == 0) {
                result.add(i to n / i)
            }
        }
        return result
    }

}