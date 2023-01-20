package boj.bruteForce

import boj.BOJSolution

/**
 * 17089
 * 세 친구
 * https://www.acmicpc.net/problem/17089
 * 완전 탐색
 * */
class BOJ_17089_ThreeFriends : BOJSolution(info(), testCases()) {

    override fun runEachSolution() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 사람의 수(3 ≤ N ≤ 4,000)
        val M = input[1].toInt() // 친구 관계의 수(0 ≤ M ≤ 4,000)
        val relationMap = mutableMapOf<Int, HashSet<Int>>()

        for (i in 0 until M) {
            val line = readLine()!!.split(" ")
            relationMap.getOrPut(line[0].toInt() - 1) { hashSetOf() }.add(line[1].toInt() - 1)
            relationMap.getOrPut(line[1].toInt() - 1) { hashSetOf() }.add(line[0].toInt() - 1)
        }

        solution(N, M, relationMap)
    }

    fun solution(N: Int, M: Int, relationMap: Map<Int, Set<Int>>) {
        val answer = IntArray(N) { it }
            .friendCombinations(3, relationMap)

        println(answer)
    }

    fun IntArray.friendCombinations(r: Int, relationMap: Map<Int, Set<Int>>): Int {
        val friendSums = hashSetOf<Int>()

        fun recursive(depth: Int, start: Int, comb: IntArray) {
            if (depth == r) {
                val sum = comb.sumOf { relationMap[it]!!.size }
                friendSums.add(sum - 6)
                return
            }

            for (i in start until size) {
                if (relationMap[i]?.containsAll(comb.take(depth)) == true) {
                    val newComb = comb.clone()
                    newComb[depth] = i
                    recursive(depth + 1, i + 1, newComb)
                }
            }
        }

        recursive(0, 0, IntArray(r) { -1 })
        return friendSums.minOfOrNull { it } ?: -1
    }

}

private fun info() = BOJSolution.Info(
    no = 17089,
    title = "세 친구",
    category = listOf(BOJSolution.BRUTE_FORCE),
    problemUrl = "https://www.acmicpc.net/problem/17089"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "5 6\n" +
                "1 2\n" +
                "1 3\n" +
                "2 3\n" +
                "2 4\n" +
                "3 4\n" +
                "4 5",
        output = "2",
    ),
    BOJSolution.TestCase(
        input = "7 4\n" +
                "2 1\n" +
                "3 6\n" +
                "5 1\n" +
                "1 7",
        output = "-1",
    ),
)