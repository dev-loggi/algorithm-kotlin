package programmers.practice.level2

import Solution

/**
 * 튜플
 * https://programmers.co.kr/learn/courses/30/lessons/64065?language=kotlin
 * */
class Tuple : Solution {

    override fun execute() {
        solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")
            .let { println(it.contentToString()) } // [2, 1, 3, 4]

        solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")
            .let { println(it.contentToString()) } // [2, 1, 3, 4]

        solution("{{20,111},{111}}")
            .let { println(it.contentToString()) } // [111, 20]

        solution("{{123}}")
            .let { println(it.contentToString()) } // [123]

        solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")
            .let { println(it.contentToString()) } // [3, 2, 4, 1]
    }

    fun solution(s: String): IntArray {
        println("solution(): s=$s")
        return s.drop(2).dropLast(2)
            .split("},{")
            .sortedBy { it.length }
            .joinToString(",")
            .split(",")
            .distinct()
            .map { it.toInt() }
            .toIntArray()
    }
}