package programmers.practice.level3

import Solution
import utils.Util

// 입국심사
// 이분탐색
// [https://programmers.co.kr/learn/courses/30/lessons/43238?language=kotlin]
class Immigration : Solution {

    override fun execute() {
        val answer = solution(6, intArrayOf(7, 10))
        println("answer=$answer") // 28
    }

    fun solution(n: Int, times: IntArray): Long {
        var answer: Long = 0

        times.sort()

        var left = times[0].toLong()
        var right = times.last() * n.toLong() // 가장 최악의 경우의(오래 걸리는) 시간
        var mid = 0L
        var sum = 0L // 총 심사한 인원

        while (left <= right) {
            mid = (left + right) / 2
            sum = 0
            for (time in times) {
                sum += mid / time
            }

            if (sum < n) { // 해야할 인원보다 심사처리 못함 -> 시간 더 필요
                left = mid + 1
            } else { // 해야할 인원보다 심사처리 많이함 -> 시간을 줄여서 더 최고 경우의 시간을 만든다.
                right = mid - 1
                answer = mid
            }
        }

        println("Immigration.solution(): n=$n, ${Util.getLog(times)}, answer=$answer")
        return answer
    }

    fun solutionBest(n: Int, times: IntArray): Long {
        var minTime = 1L
        var maxTime = n.toLong() * times.maxOf { it }
        var result = maxTime

        while (minTime <= maxTime ) {
            val possibleAnswerTime = (minTime + maxTime) / 2
            val examinedPersonCount = times.fold(0L) { acc, eachDuration ->
                acc + possibleAnswerTime / eachDuration
            }

            if(examinedPersonCount >= n) {
                if(possibleAnswerTime < result) result = possibleAnswerTime
                maxTime = possibleAnswerTime - 1
            }
            else minTime = possibleAnswerTime + 1
        }
        return result
    }
}