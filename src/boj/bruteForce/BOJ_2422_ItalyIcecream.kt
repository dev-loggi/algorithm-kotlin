package boj.bruteForce

import common.Solution

/**
 * 2422
 * 한윤정이 이탈리아에 가서 아이스크림을 사먹는데
 * https://www.acmicpc.net/problem/2422
 * 완전 탐색
 * */
class BOJ_2422_ItalyIcecream : Solution {

    override fun execute() {
        main()
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 아이스크림 종류의 수 (1 ≤ N ≤ 200)
        val M = input[1].toInt() // 섞어먹으면 안 되는 조합의 수 (0 ≤ M ≤ 10,000)
        val badCombs = (0 until M).map {
            readLine()!!.split(" ").map { it.toInt() - 1 }
        }

        bestSolution(N, M, badCombs)
        solution(N, M, badCombs)
    }

    fun bestSolution(N: Int, M: Int, badCombs: List<List<Int>>) {
        val badCombMap = mutableMapOf<Int, MutableSet<Int>>()
        for (comb in badCombs) {
            badCombMap.getOrPut(comb[0]) { mutableSetOf() }.add(comb[1])
            badCombMap.getOrPut(comb[1]) { mutableSetOf() }.add(comb[0])
        }

        println(badCombMap)

        val countOfGoodComb = IntArray(N) { it }
            .goodCombinations(3, badCombMap)

        println(countOfGoodComb)
    }

    fun IntArray.goodCombinations(r: Int, badCombMap: Map<Int, Set<Int>>): Int {
        var count = 0

        fun recursive(depth: Int, start: Int, comb: IntArray) {
            if (depth == r) {
                count++
                return
            }

            for (i in start until size) {
                if (comb.any { badCombMap[it]?.contains(i) == true })
                    continue

                val newComb = comb.clone()
                newComb[depth] = i
                recursive(depth + 1, i + 1, newComb)
            }
        }

        recursive(0, 0, IntArray(r) { -1 })
        return count
    }

    /**
     * 시간 초과
     * */
    fun solution(N: Int, M: Int, badCombs: List<List<Int>>) {
        val goodCombs = IntArray(N) { it }
            .combination(3)
            .filterNot { good ->
                badCombs.any { bad -> good.containsAll(bad) }
            }

        println(goodCombs.size)
    }

    fun IntArray.combination(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }

                recursive(depth + 1, i + 1, newList)
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}
/*
[case1]
5 3
1 2
3 4
1 3
[case1 answer]
3

[case2]
200 10
1 2
3 4
5 6
7 8
9 10
1 3
1 4
1 5
1 6
1 7
[case2 answer]
1311438
* */