package programmers.kakao.y2021

import programmers.Programmers

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 1] 신규 아이디 추천
 *
 * https://programmers.co.kr/learn/courses/30/lessons/72410?language=kotlin
 * */
class KAKAO_BLIND_2021_P1 : Programmers.Solution {

    //[[1, 4], [3, 4], [3, 10]]
    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("test case $index → $case")

            val answer = solution(case)
            println("★ answer=$answer")
        }

    }

    fun solution(new_id: String): String = new_id
        .lowercase() // 1단계
        .filter { it.isLowerCase() || it.isDigit() || it in arrayOf('_', '-', '.') } // 2단계
        .replace("[.]*[.]".toRegex(), ".") // 3단계
        .removePrefix(".").removeSuffix(".") // 4단계
        .ifBlank { "a" } // 5단계
        .take(15).removePrefix(".").removeSuffix(".") // 6단계
        .let { if (it.length == 1) it + it + it else it } // 7단계
        .let { if (it.length == 2) it + it.last() else it } // 7단계

    fun solution2(new_id: String): String = new_id
        .lowercase() // 1단계
        .filter { it.isLowerCase() || it.isDigit() || it in arrayOf('-', '_', '.') } // 2단계
        .removeContinuous('.') // 3단계
        .let { if (it.getOrNull(0) == '.') it.drop(1) else it } // 4단계
        .let { if (it.getOrNull(it.lastIndex) == '.') it.dropLast(1) else it } // 4단계
        .let { if (it.isBlank()) "a" else it } // 5단계
        .take(15) // 6단계
        .let { if (it.last() == '.') it.dropLast(1) else it } // 6단계
        .let { if (it.length == 1) it + it + it else it } // 7단계
        .let { if (it.length == 2) it + it.last() else it } // 7단계

    private fun String.removeContinuous(c: Char): String {
        val sb = StringBuilder()
        var count = 0
        for (i in indices) {
            val ch = get(i)

            if (i == 0 || ch != c) {
                sb.append(ch)
            } else if (get(i - 1) != c) {
                sb.append(ch)
            }
        }
        return sb.toString()
    }
}

private fun testCases(): Array<String> = arrayOf(
    // case 1
    // answer = "bat.y.abcdefghi"
    "...!@BaT#*..y.abcdefghijklm",

    // case 1
    // answer = "z--"
    "z-+.^.",

    // case 1
    // answer = "aaa"
    "=.=",

    // case 1
    // answer = "123_.def"
    "123_.def",

    // case 1
    // answer = "abcdefghijklmn"
    "abcdefghijklmn.p",
)