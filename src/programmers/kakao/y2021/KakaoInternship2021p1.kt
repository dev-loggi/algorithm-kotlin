package programmers.kakao.y2021

import programmers.Programmers.Solution

class KakaoInternship2021p1 : Solution {

    override fun execute() {
        var answer = 0

        answer = solution("one4seveneight")
        println("answer=$answer")

        answer = solution("23four5six7")
        println("answer=$answer")

        answer = solution("2three45sixseven")
        println("answer=$answer")

        answer = solution("123")
        println("answer=$answer")
    }

    enum class Digits {
        zero, one, two, three, four, five, six, seven, eight, nine
    }

    fun solution(s: String): Int {
        var answer = s

        Digits.values().forEach {
            answer = answer.replace(it.name, it.ordinal.toString())
        }

        return answer.toInt()
    }
}