package programmers.practice.level1

import programmers.Programmers.Solution

/**
 * 문자열 내림차순으로 배치하기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12917?language=kotlin
 * */
class SortStringInDescendingOrder : Solution {

    override fun execute() {
        solution("Zbcdefg").let { println(it) }
    }

    fun solution(s: String): String {
        return s.toCharArray().apply { sortDescending() }.joinToString("")
    }
}