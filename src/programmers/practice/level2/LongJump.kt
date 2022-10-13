package programmers.practice.level2

import programmers.Programmers
import kotlin.math.max

/**
 * 멀리뛰기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12914
 *
 * DP
 * */
class LongJump : Programmers.Solution {

    override fun execute() {
        solution(4).let { println(it) }
        solution(3).let { println(it) }
    }

    private var idx = 3
    private val dp = LongArray(2001).apply {
        this[1] = 1
        this[2] = 2
    }

    fun solution(n: Int): Long {
        for (i in idx..n) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567
        }

        idx = max(idx, n + 1)

        return dp[n]
    }
}