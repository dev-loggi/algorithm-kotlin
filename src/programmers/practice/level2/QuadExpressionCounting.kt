package programmers.practice.level2

import programmers.Programmers

/**
 * 쿼드압축 후 개수 세기
 * https://school.programmers.co.kr/learn/courses/30/lessons/68936
 * DFS, 분할정복
 * */
class QuadExpressionCounting : Programmers.Solution {

    override fun execute() {
        solution(arrayOf(
            intArrayOf(1,1,0,0),
            intArrayOf(1,0,0,0),
            intArrayOf(1,0,0,1),
            intArrayOf(1,1,1,1),
        )).let { println(it.contentToString()) } // [4,9]
        solution(arrayOf(
            intArrayOf(1,1,1,1,1,1,1,1),
            intArrayOf(0,1,1,1,1,1,1,1),
            intArrayOf(0,0,0,0,1,1,1,1),
            intArrayOf(0,1,0,0,1,1,1,1),
            intArrayOf(0,0,0,0,0,0,1,1),
            intArrayOf(0,0,0,0,0,0,0,1),
            intArrayOf(0,0,0,0,1,0,0,1),
            intArrayOf(0,0,0,0,1,1,1,1),
        )).let { println(it.contentToString()) } // [10,15]
    }

    fun solution(arr: Array<IntArray>): IntArray {
        return arr.quadCompress()
    }

    private fun Array<IntArray>.quadCompress(): IntArray {
        val count = IntArray(2)

        fun dfs(r1: Int, r2: Int, c1: Int, c2: Int) {
            if (check(r1, r2, c1, c2)) {
                count[this[r1][c1]] += 1
                return
            }

            val midR = (r1 + r2) / 2
            val midC = (c1 + c2) / 2

            dfs(r1, midR, c1, midC)
            dfs(r1, midR, midC + 1, c2)
            dfs(midR + 1, r2, c1, midC)
            dfs(midR + 1, r2, midC + 1, c2)
        }

        dfs(0, lastIndex, 0, lastIndex)
        return count
    }

    private fun Array<IntArray>.check(r1: Int, r2: Int, c1: Int, c2: Int): Boolean {
        val firstVal = this[r1][c1]
        for (r in r1..r2) for (c in c1..c2) {
            if (this[r][c] != firstVal)
                return false
        }
        return true
    }
}