package programmers.practice.level2

import programmers.Programmers

/**
 * 예상 대진표
 * https://school.programmers.co.kr/learn/courses/30/lessons/12985
 *
 * 최소 공통 조상(LCA, Lowest Common Ancestor)
 * 2진법, 비트 연산
 * */
class ExpectedScorecard : Programmers.Solution {

    override fun execute() {
        bestSolution(8, 4, 7).let { println() }
        bestSolution(16, 4, 7).let { println() }
    }

    private fun bestSolution(N: Int, A: Int, B: Int): Int {
        return ((A - 1) xor (B - 1)).toString(2).length
    }

    private fun solution(N: Int, A: Int, B: Int): Int {
        var round = 0
        var a = A + N - 1
        var b = B + N - 1

        while (a != b) {
            round += 1
            a /= 2
            b /= 2
        }

        return round
    }
}