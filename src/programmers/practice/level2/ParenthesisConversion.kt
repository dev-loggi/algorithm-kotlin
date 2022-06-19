package programmers.practice.level2

import common.Solution

/**
 * 괄호 변환
 * https://programmers.co.kr/learn/courses/30/lessons/60058?language=kotlin
 * 2020 KAKAO BLIND RECRUITMENT
 * */
class ParenthesisConversion : Solution {

    override fun execute() {
        //"(()())()"	"(()())()"
        //")("	"()"
        //"()))((()"	"()(())()"

        solution("(()())()").let { println("answer=$it") } // "(()())()"
        solution(")(").let { println("answer=$it") } // "()"
        solution("()))((()").let { println("answer=$it") } // "()(())()"
    }

    fun solution(p: String): String = solve123(p)

    private fun solve123(w: String): String {
        if (w.isBlank()) return "" // 1

        val (u, v) = w.splitUV() // 2

        return if (u.isValidParenthesis()) u + solve123(v) // 3-1
        else solve4(u, v) // 4
    }

    private fun solve4(u: String, v: String): String { // 4
        return "(${solve123(v)})${u.drop(1).dropLast(1).reverseParenthesis()}"
    }

    private fun String.splitUV(): Pair<String, String> {
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

    private fun String.isValidParenthesis(): Boolean {
        var count = 0
        for (c in this) {
            if (c == '(') count++
            if (c == ')') count--
            if (count < 0) return false
        }
        return true
    }

    private fun String.reverseParenthesis(): String {
        return map { if (it == '(') ')' else '(' }.joinToString("")
    }
}