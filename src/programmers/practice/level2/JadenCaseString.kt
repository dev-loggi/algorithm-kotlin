package programmers.practice.level2

import Solution
import java.lang.StringBuilder
import java.util.*

class JadenCaseString : Solution {

    override fun execute() {
        solution3("3people   unFollowed me  Hello").let { println(it) }
    }

    fun solution(s: String): String {
        return s.foldIndexed(StringBuilder()) { i, builder, c ->
            builder.append(
                if ((i == 0 || s[i - 1] == ' ') && (c.isUpperCase() || c.isLowerCase())) {
                    c.uppercaseChar()
                } else {
                    c.lowercaseChar()
                }
            )
        }.toString()
    }

    fun solution2(s: String): String {
        return s.lowercase().split(" ").map { it.capitalize() }.joinToString(" ")
    }

    fun solution3(s: String): String {
        return s.lowercase().split(" ").map { it.replaceFirstChar { c -> c.uppercaseChar() } }.joinToString(" ")
    }
}