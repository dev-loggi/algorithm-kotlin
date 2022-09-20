package programmers.kakao.y2021

import test.Solution


// 신규 아이디 추천
// https://programmers.co.kr/learn/courses/30/lessons/72410?language=kotlin
class KakaoBlindRecruitment2021p1 : Solution {

    //직사각형을 만드는 데 필요한 4개의 점 중 3개의 좌표가 주어질 때, 나머지 한 점의 좌표를 구하려고 합니다. 점 3개의 좌표가 들어있는 배열 v가 매개변수로 주어질 때, 직사각형을 만드는 데 필요한 나머지 한 점의 좌표를 return 하도록 solution 함수를 완성해주세요. 단, 직사각형의 각 변은 x축, y축에 평행하며, 반드시 직사각형을 만들 수 있는 경우만 입력으로 주어집니다.
    //[[1, 4], [3, 4], [3, 10]]
    override fun execute() {
        //	bat.y.abcdefgh


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