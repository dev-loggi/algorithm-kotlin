package programmers.practice.level2

import common.Solution
import kotlin.math.ceil

/**
 * 주차 요금 계산
 * https://programmers.co.kr/learn/courses/30/lessons/92341?language=kotlin
 * */
class ParkingFeeCalculation : Solution {

    override fun execute() {
        solution(
            intArrayOf(180, 5000, 10, 600),
            arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
        ).let { println("answer=$it") } // [14600, 34400, 5000]

        solution(
            intArrayOf(120, 0, 60, 591),
            arrayOf("16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN")
        ).let { println("answer=$it") } // [0, 591]

        solution(
            intArrayOf(1, 461, 1, 10),
            arrayOf("00:00 1234 IN")
        ).let { println("answer=$it") } // [14841]
    }

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val defaultTime = fees[0]
        val baseFee = fees[1]
        val unitTime = fees[2].toDouble()
        val unitFee = fees[3]

        return records.map { it.split(" ") }
            .groupBy { it[1] }
            .toSortedMap()
            .also { println(it) }
            .entries
            .map {
                val carNumber = it.key
                val eachRecords = it.value
                var accTime = 0

                for (i in 0..eachRecords.lastIndex step 2) {
                    val timeIn = eachRecords[i][0].toTimeSeconds()
                    val timeOut =
                        if (i < eachRecords.lastIndex) eachRecords[i + 1][0].toTimeSeconds()
                        else "23:59".toTimeSeconds()

                    accTime += timeOut - timeIn
                }

                val price =
                    if (accTime < defaultTime) baseFee
                    else baseFee + ceil((accTime - defaultTime) / unitTime) * unitFee

                println("car=${it.key}, accTime=$accTime, price=$price")
                price.toInt()
            }.toIntArray()
    }

    private fun String.toTimeSeconds(): Int {
        val times = this.split(":")
        return times[0].toInt() * 60 + times[1].toInt()
    }
}