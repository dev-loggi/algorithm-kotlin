package programmers.practice.level2

import Solution

/**
 * 문자열 압축
 * https://programmers.co.kr/learn/courses/30/lessons/60057
 * */
class StringZip : Solution {

    override fun execute() {
        // "aabbaccc"	7
        // "ababcdcdababcdcd"	9
        // "abcabcdede"	8
        // "abcabcabcabcdededededede"	14
        // "xababcdcdababcdcd"	17

        solution("aabbaccc")
            .let { println("answer=$it\n") } // 7
        solution("ababcdcdababcdcd")
            .let { println("answer=$it\n") } // 9
        solution("abcabcdede")
            .let { println("answer=$it\n") } // 8
        solution("abcabcabcabcdededededede")
            .let { println("answer=$it\n") } // 14
        solution("xababcdcdababcdcd")
            .let { println("answer=$it\n\n") } // 17

        solution("ab ab c".replace(" ", ""))
            .let { println("answer=$it\n") }
        solution("abc abc d".replace(" ", ""))
            .let { println("answer=$it\n") }
        solution("ab ab ab c da da".replace(" ", ""))
            .let { println("answer=$it\n") }
        solution("ab ab ab cb cb ef".replace(" ", ""))
            .let { println("answer=$it\n") }
    }

    fun solution(s: String): Int {
        println("solution(): s=$s, length=${s.length}")

        return (1..(s.length / 2).coerceAtLeast(1))
            .map { zip(s, it).length }
            .minOf { it }
    }

    fun zip(s: String, zipSize: Int): String {
        val builder = StringBuilder()
        val stringList = s.chunked(zipSize)

        var repeatCount = 0
        for (i in stringList.indices) {
            val cur = stringList[i]
            val next = stringList.getOrNull(i + 1)

            if (cur == next) {
                repeatCount += 1
            } else {
                if (repeatCount == 0) {
                    builder.append(cur)
                } else {
                    builder.append(repeatCount + 1)
                        .append(cur)
                }

                repeatCount = 0
            }
        }

        println("zip($zipSize): $builder")
        return builder.toString()
    }

}