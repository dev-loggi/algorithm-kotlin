package programmers.practice.level1

import Solution

/**
 * 문자열 내 마음대로 정렬하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12915?language=kotlin
 * */
class SortingStringsMyOwnWay : Solution {

    override fun execute() {
        solution(arrayOf("sun", "bed", "car"), 1).let { println(it.contentToString()) } // ["car", "bed", "sun"]
    }

    fun solution(strings: Array<String>, n: Int): Array<String> {
        return strings.apply { sortWith(compareBy({ it[n] }, { it })) }
    }
}