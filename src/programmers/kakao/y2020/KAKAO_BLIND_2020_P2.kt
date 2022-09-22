package programmers.kakao.y2020

import programmers.Programmers

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 2] 문자열 압축
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057
 * */
class KAKAO_BLIND_2020_P2 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("case $index: $case")
            val answer = solution(case)
            println("answer=$answer")
        }
    }

    fun solution(s: String): Int {
        return (1..(s.length / 2).coerceAtLeast(1))
            .map { zip(s, it).length }
            .minOf { it }
    }

    private fun zip(s: String, zipSize: Int): String {
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

        return builder.toString()
    }
}

private fun testCases(): Array<String> = arrayOf(
    // case 1
    // answer = 7
    "aabbaccc",

    // case 2
    // answer = 9
    "ababcdcdababcdcd",

    // case 3
    // answer = 8
    "abcabcdede",

    // case 4
    // answer = 14
    "abcabcabcabcdededededede",

    // case 5
    // answer = 17
    "xababcdcdababcdcd",

    // case 6
    // answer = 4
    "ab ab c".replace(" ", ""),

    // case 7
    // answer = 5
    "abc abc d".replace(" ", ""),

    // case 8
    // answer = 8
    "ab ab ab c da da".replace(" ", ""),

    // case 9
    // answer = 8
    "ab ab ab cb cb ef".replace(" ", ""),
)