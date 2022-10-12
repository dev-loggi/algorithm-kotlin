package programmers.kakao.y2021

import programmers.Programmers
import java.text.DecimalFormat
import java.util.ArrayDeque

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 5] 광고 삽입
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72414
 * */
class KAKAO_BLIND_2021_P5 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            val (play_time, adv_time, logs) = case
            println("case $index -> play_time=$play_time, adv_time=$adv_time, logs=${logs.contentToString()}")

            val answer = solution(play_time, adv_time, logs)
            println("♠ answer=$answer")
        }
    }

    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playTimeSec = play_time.toSecond()
        val advTimeSec = adv_time.toSecond()

        val totalTime = LongArray(playTimeSec + 1)

        // totalTime[x] = x 시각에 시작된 재생 구간의 개수 – x 시각에 종료된 재생구간의 개수
        for (log in logs) {
            val (start, end) = log.split("-")

            val startSec = start.toSecond()
            val endSec = end.toSecond()

            totalTime[startSec] += 1L
            totalTime[endSec] -= 1L
        }

        // totalTime[x] = 시각 x부터 x+1까지 1초 간의 구간을 포함하는 재생 구간의 개수
        for (x in 1..totalTime.lastIndex)
            totalTime[x] = totalTime[x] + totalTime[x - 1]

        // totalTime[x] = 시각 0부터 x+1까지 x+1초 간의 구간을 포함하는 누적 재생시간
        for (x in 1..totalTime.lastIndex)
            totalTime[x] = totalTime[x] + totalTime[x - 1]

        var maxTime = Long.MIN_VALUE
        var answer = 0

        for (x in (advTimeSec - 1) until playTimeSec) {
            val time =
                if (x < advTimeSec) totalTime[x]
                else totalTime[x] - totalTime[x - advTimeSec]

            if (maxTime < time) {
                maxTime = time

                answer = x - advTimeSec + 1
            }
        }

        return answer.toTimeString()
    }

    private fun String.toSecond(): Int {
        return split(":").let { it[0].toInt() * 3600 + it[1].toInt() * 60 + it[2].toInt() }
    }

    private fun Int.toTimeString(): String {
        val fm = DecimalFormat("00")
        val hour = this / 3600
        val min = this % 3600 / 60
        val sec = this % 60
        return "${fm.format(hour)}:${fm.format(min)}:${fm.format(sec)}"
    }
}

private fun testCases(): Array<Triple<String, String, Array<String>>> = arrayOf(
    // case 1
    // answer = "01:30:59"
    Triple(
        first = "02:03:55",
        second = "00:14:15",
        third = arrayOf(
            "01:20:15-01:45:14",
            "00:40:31-01:00:00",
            "00:25:50-00:48:29",
            "01:30:59-01:53:29",
            "01:37:44-02:02:30",
        )
    ),

    // case 2
    // answer = "01:00:00"
    Triple(
        first = "99:59:59",
        second = "25:00:00",
        third = arrayOf(
            "69:59:59-89:59:59",
            "01:00:00-21:00:00",
            "79:59:59-99:59:59",
            "11:00:00-31:00:00",
        )
    ),

    // case 3
    // answer = "00:00:00"
    Triple(
        first = "50:00:00",
        second = "50:00:00",
        third = arrayOf(
            "15:36:51-38:21:49",
            "10:14:18-15:36:51",
            "38:21:49-42:51:45",
        )
    ),
)