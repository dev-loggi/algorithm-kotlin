package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 위장
 * https://programmers.co.kr/learn/courses/30/lessons/42578?language=kotlin
 * */
class DisGuise : Solution {

    override fun execute() {
        //[["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]	5
        //[["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]	3

        solution2(arrayOf(
            arrayOf("yellowhat", "headgear"),
            arrayOf("bluesunglasses", "eyewear"),
            arrayOf("green_turban", "headgear"),
        )).let { println(it) } // 5
        solution2(arrayOf(
            arrayOf("crowmask", "face"),
            arrayOf("bluesunglasses", "face"),
            arrayOf("smoky_makeup", "face"),
        )).let { println(it) } // 3
        solution2(arrayOf(
            arrayOf("a1", "A"), arrayOf("a2", "A"),
            arrayOf("b1", "B"),
            arrayOf("c1", "C"),
        )).let { println(it) } // 11
        solution2(arrayOf(
            arrayOf("a1", "A"), arrayOf("a2", "A"),
            arrayOf("b1", "B"),
            arrayOf("c1", "C"),
            arrayOf("d1", "D")
        )).let { println(it) } // 23
    }

    fun solution(clothes: Array<Array<String>>): Int {
        return clothes
            .groupBy(
                keySelector = { it[1] },
                valueTransform = { it[0] }
            )
            .map { it.value }
            .combinationAll() - 1
    }

    fun solution2(clothes: Array<Array<String>>): Int {
        return clothes.groupBy { it[1] }.values.fold(1) { acc, v -> acc * ((v.size) + 1) } - 1
    }

    fun List<List<String>>.combinationAll(): Int {
        var count = 0

        fun dfs(depth: Int, start: Int) {
            count++

            if (depth == size)
                return

            for (i in start until size) {
                for (j in this[i].indices) {
                    dfs(depth + 1, i + 1)
                }
            }
        }

        dfs(0, 0)
        return count
    }


}