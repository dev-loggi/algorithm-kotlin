package kakao.y2020

import Solution

// 괄호 변환
// https://programmers.co.kr/learn/courses/30/lessons/60058?language=kotlin
class KakaoBlindRecruitment : Solution {

    override fun execute() {
        var answer = solution("()))((()")
        println("answer=$answer")
    }

    fun solution(p: String): String {
        val answer = mutableListOf<String>()


        p.splitUV()
            .let { println(it) }


        return answer.joinToString("")
    }

    private fun String.splitUV(): Pair<String, String> {
        if (isBlank()) return "" to ""

        var left = 0
        var right = 0
        var uLength = 0
        for (i in indices) {
            when (this[i]) {
                '(' -> left++
                ')' -> right++
            }
            if (left == right) { uLength = i + 1; break; }
        }
        return take(uLength) to drop(uLength)
    }
}