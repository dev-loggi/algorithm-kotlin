package kakao.y2020

import common.Solution

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

    private fun String.splitUV(): List<String> {
        var n1 = 0
        var n2 = 0
        val u = StringBuilder()
        var v = ""
        for (i in this.indices) {
            val ch = this[i]
            if (ch == '(') n1++ else n2++
            u.append(ch)

            if (n1 > 0 && n2 > 0 && n1 == n2) {
                v = this.drop(i + 1)
                break
            }
        }

        return listOf(u.toString(), v)
    }
}