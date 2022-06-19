package codility.exercise

import common.Solution

/**
 * https://app.codility.com/c/run/training369CDM-AJV/
 * */
class Codility_Ex9_BinaryGap : Solution {

    override fun execute() {
        solution(1041).let { println("answer=$it") } // 5
        solution(10).let { println("answer=$it") } // 0
        solution(31).let { println("answer=$it") } // 0
        solution(1073741825).let { println("answer=$it") } // 0
    }

    fun solution(N: Int): Int {
        val binary = N.toString(2)
        val gaps = mutableListOf<Int>()

        var count = 0
        for (n in binary) {
            when (n) {
                '1' -> {
                    gaps.add(count)
                    count = 0
                }
                '0' -> count++
            }
        }

        println(binary)
        println(gaps)

        return gaps.max()
    }

    private fun List<Int>.max(): Int {
        if (this.isEmpty()) return 0

        var max = this[0]
        for (n in this) if (max < n) max = n
        return max
    }

}