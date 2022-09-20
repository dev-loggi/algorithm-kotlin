package programmers.kakao.y2019

import programmers.Programmers

/**
 * 2019 KAKAO BLIND RECRUITMENT
 * [No. 2] 오픈채팅방
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42888?language=kotlin
 * */
class KAKAO_BLIND_2019_P2 : Programmers.Solution {

    override fun execute() {
        testCases().forEach { testCase ->
            solution(testCase).let { answer ->
                println("answer ->")
                answer.forEach { println(it) }
            }
        }
    }

    private class Record(val command: String, val id: String)

    fun solution(recordList: Array<String>): Array<String> {
        val userIdToNickname = mutableMapOf<String, String>()

        return recordList
            .asSequence()
            .map {
                val records = it.split(" ")

                when (records[0][0]) {
                    'E' -> {
                        userIdToNickname[records[1]] = records[2]
                        Record("들어왔습니다.", records[1])
                    }
                    'L' -> {
                        Record("나갔습니다.", records[1])
                    }
                    'C' -> {
                        userIdToNickname[records[1]] = records[2]
                        Record("C", records[0])
                    }
                    else -> throw IllegalArgumentException()
                }
            }
            .filterNot { it.command == "C" }
            .toList()
            .map { "${userIdToNickname[it.id]}님이 ${it.command}" }
            .toTypedArray()
    }
}

private fun testCases(): Array<Array<String>> = arrayOf(
    // test case 1
    arrayOf(
        "Enter uid1234 Muzi",
        "Enter uid4567 Prodo",
        "Leave uid1234",
        "Enter uid1234 Prodo",
        "Change uid4567 Ryan",
    ), // answer = ["Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다."]
)