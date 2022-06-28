package programmers.practice.level1

import common.Solution


/**
 * 두 개 뽑아서 더하기
 * https://programmers.co.kr/learn/courses/30/lessons/68644?language=kotlin
 * */
class TakeTwoNumbersAndSums : Solution {

    override fun execute() {
        solution(intArrayOf(2,1,3,4,1)).let { println(it) } // [2,3,4,5,6,7]
        solution(intArrayOf(5,0,2,7)).let { println(it) } // [2,5,7,9,12]
    }

    fun solution(numbers: IntArray): IntArray {
        return numbers.combinations(2)
            .map { it.sum() }
            .toSortedSet()
            .toIntArray()
    }

    fun IntArray.combinations(r: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun dfs(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }
                dfs(depth + 1, i + 1, newList)
            }
        }

        dfs(0, 0, emptyList())
        return result
    }
}