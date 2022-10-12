package programmers.kakao.y2022

import programmers.Programmers.Solution


/**
 *
 * */
class KakaoInternship2022p1 : Solution {

    override fun execute() {
        var answer = ""

        answer = solution(
            arrayOf("AN", "CF", "MJ", "RT", "NA"),
            intArrayOf(5, 3, 2, 7, 5)
        )
        println("answer=$answer") // "TCMA"

        answer = solution(
            arrayOf("TR", "RT", "TR"),
            intArrayOf(7, 1, 3)
        )
        println("answer=$answer") // "RCJA"
    }

    fun solution(survey: Array<String>, choices: IntArray): String {
        val scoreMap = hashMapOf<Char, Int>(
            'R' to 0, 'T' to 0,
            'C' to 0, 'F' to 0,
            'J' to 0, 'M' to 0,
            'A' to 0, 'N' to 0,
        )

        survey.forEachIndexed { index, s ->
            val score = choices[index]
            if (score < 4) {
                scoreMap[s[0]] = scoreMap[s[0]]!! + (4 - score)
            } else if (score > 4) {
                scoreMap[s[1]] = scoreMap[s[1]]!! + (score - 4)
            }
        }

        println("scoreMap=$scoreMap")

        var answer = ""
        answer += if (scoreMap['R']!! >= scoreMap['T']!!) 'R' else 'T'
        answer += if (scoreMap['C']!! >= scoreMap['F']!!) 'C' else 'F'
        answer += if (scoreMap['J']!! >= scoreMap['M']!!) 'J' else 'M'
        answer += if (scoreMap['A']!! >= scoreMap['N']!!) 'A' else 'N'

        return answer
    }
}