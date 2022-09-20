package programmers.practice.level2

import programmers.Programmers.Solution

class TwoDifferentBits : Solution {

    override fun execute() {
        solution(longArrayOf(2, 7))
            .let { println(it.contentToString()) } // 3, 11
    }

    fun solution(numbers: LongArray): LongArray {
        return numbers.map { n -> f(n) }.toLongArray()
    }

    private fun f(N: Long): Long {
        if (N % 2 == 0L)
            return N + 1

        var n = N + 1

        while (true) {
            var diff = N xor n

            var count = 0
            while (diff > 0 && count <= 2) {
                if (diff % 2 == 1L)
                    count++

                diff /= 2L
            }

            if (count in 1..2)
                break

            n++
        }

        return n
    }

    private fun f2(N: Long): Long {
        if (N % 2 == 0L)
            return N + 1

        var n = N + 1;

        while (true) {
            val binary = (N xor n).toString(2)

            var count = 0
            for (i in binary.indices) {
                if (binary[i] == '1') count++
                if (count > 2) break
            }

            if (count in 1..2)
                break

            n++
        }

        return n;
    }
}