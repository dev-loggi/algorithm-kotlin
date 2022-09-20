package programmers.kakao.y2019

import programmers.Programmers

/**
 * 2019 KAKAO BLIND RECRUITMENT
 * [NO. 5] 무지의 먹방 라이브
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42891
 * */
class KAKAO_BLIND_2019_P5 : Programmers.Solution {

    override fun execute() {
        solution(intArrayOf(3, 1, 2), 5).let { println("answer=$it") } // 1
        solution(intArrayOf(9, 9, 9, 9, 9), 32).let { println("answer=$it") } // 3
        solution(intArrayOf(5, 9, 9, 9, 9), 32).let { println("answer=$it") } // 5
        solution(intArrayOf(5, 9, 9, 9, 9), 102).let { println("answer=$it") } // -1
    }

    private fun solution(foodTimes: IntArray, k: Long): Int {
        println("foodTimes=${foodTimes.contentToString()}, k=$k")

        val sortedTime = foodTimes.sortedArray()
        val totalFoodSize = foodTimes.size
        var remainingFoodSize = foodTimes.size

        var time = 0L
        var cycleCount = 0L

        while (remainingFoodSize > 0) {
            val remainingTime = k - time

            val oneCycleTime = remainingFoodSize
            val maxCycle = sortedTime[totalFoodSize - remainingFoodSize] - cycleCount

            val cycle = (remainingTime / oneCycleTime).coerceAtMost(maxCycle)

            time += cycle * oneCycleTime
            cycleCount += cycle
            remainingFoodSize -= 1

            println("    time=$time, cycleCount=$cycleCount")
            println("        oneCycleTime=$oneCycleTime, maxCycle=$maxCycle, cycle=$cycle")

            if (remainingTime / oneCycleTime < maxCycle)
                return find(foodTimes, cycleCount, time, k)
        }

        return -1
    }

    private fun find(
        foodTimes: IntArray,
        cycleCount: Long,
        currentTime: Long,
        k: Long,
    ): Int {
        val countDst = k - currentTime + 1
        var count = 0L

        println("            ★ find(): cycleCount=$cycleCount, currTime=$currentTime, countDst=$countDst")

        for (i in foodTimes.indices) {
            if (foodTimes[i] <= cycleCount)
                continue

            count += 1

            if (count == countDst)
                return i + 1
        }

        return -1
    }
}