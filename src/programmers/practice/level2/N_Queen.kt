package programmers.practice.level2

import programmers.Programmers
import kotlin.math.abs

/**
 * N-Queen
 * https://school.programmers.co.kr/learn/courses/30/lessons/12952
 *
 * DFS, 완전탐색
 * https://data-engineer.tistory.com/22
 * */
class N_Queen : Programmers.Solution {

    override fun execute() {
        for (n in 1..12) {
            println("solution($n)::")
            println("    answer=${solution(n)}")
        }
    }

    fun solution(n: Int): Int {
        return queen(0, n, IntArray(n))
    }

    fun queen(r: Int, n: Int, col: IntArray): Int {
        if (r == n)
            return 1

        var count = 0

        for (c in 0 until n) {
            if (!possible(r, c, col))
                continue

            col[r] = c
            count += queen(r+1, n, col)
        }

        return count
    }

    fun possible(r: Int, c: Int, col: IntArray): Boolean {
        for (i in 0 until r) {
            if (c == col[i] || abs(c - col[i]) == r - i)
                return false
        }
        return true
    }
}