package programmers.practice.level2

import common.Solution
import java.util.ArrayDeque

/**
 * 괄호 회전하기
 * https://programmers.co.kr/learn/courses/30/lessons/76502
 * */
class RotateParentheses : Solution {

    override fun execute() {
        solution("[](){}").let { println("answer=$it") } // 3
        solution("}]()[{").let { println("answer=$it") } // 2
        solution("[)(]").let { println("answer=$it") } // 0
        solution("}}}").let { println("answer=$it") } // 0
    }

    private val map = mapOf(
        ']' to '[',
        '}' to '{',
        ')' to '('
    )

    fun solution(s: String): Int {
        if (s.length % 2 == 1)
            return 0

        return s.indices
            .map { i -> s.drop(i) + s.take(i) }
            .count { check(it) }
    }

    fun check(s: String): Boolean {
        val stack = ArrayDeque<Char>()

        for (i in s.indices) {
            when (val c = s[i]) {
                '[', '{', '(' -> stack.push(c)
                ']', '}', ')' -> {
                    if (stack.isEmpty())
                        return false

                    if (map[c] != stack.pop())
                        return false
                }
            }
        }

        return true
    }
}