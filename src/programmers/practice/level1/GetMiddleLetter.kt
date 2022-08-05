package programmers.practice.level1

import Solution

/**
 * 가운데 글자 가져오기
 * https://school.programmers.co.kr/learn/courses/30/lessons/12903?language=kotlin
 * */
class GetMiddleLetter : Solution {

    override fun execute() {
        solution("abcde").let { println(it) } // c
        solution("qwer").let { println(it) } // we
    }

    fun solution(s: String): String {
        return s.slice((s.length - 1) / 2 .. s.length / 2)
    }
}