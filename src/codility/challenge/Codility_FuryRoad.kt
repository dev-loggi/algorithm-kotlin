package codility.challenge

import Solution

/**
 * https://app.codility.com/c/run/cert7UQYXD-VQVVY6DAEM8RZW78/
 * */
class Codility_FuryRoad : Solution {

    override fun execute() {
        // 스쿠터
        // A: 5, S: 40
        // 도보
        // A: 20, S: 30
        solution( "ASAASS").let { println("answer=$it") } // 115
        solution( "SSA").let { println("answer=$it") } // 80
        solution( "SSSSAAA").let { println("answer=$it") } // 175
    }

    val scooter = mapOf('A' to 5, 'S' to 40)
    val foot = mapOf('A' to 20, 'S' to 30)

    fun solution(road: String): Int {
        val arr1 = IntArray(road.length) { 0 } // 정방향 스쿠터 누적 시간
        val arr2 = IntArray(road.length) { 0 } // 역방향 도보 누적 시간
        val arr3 = IntArray(road.length + 1) { 0 } // 각 인덱스 별 시간

        road.foldIndexed(0) { idx, acc, roadType ->
            arr1[idx] = acc + scooter[roadType]!!
            arr1[idx]
        }

        road.reversed().foldIndexed(0) { idx, acc, roadType ->
            arr2[road.lastIndex - idx] = acc + foot[roadType]!!
            arr2[road.lastIndex - idx]
        }

        println(arr1.contentToString())
        println(arr2.contentToString())

        for (i in arr3.indices) {
            arr3[i] = arr1.getOrElse(i - 1) { 0 } + arr2.getOrElse(i) { 0 }
        }

        println(arr3.contentToString())

        var min = Int.MAX_VALUE
        for (t in arr3) {
            if (min > t) min = t
        }

        return min
    }

    fun solution2(road: String): Int {
        val reverseScooterAccTimes = IntArray(road.length) { 0 }
        val reverseFootAccTimes = IntArray(road.length) { 0 }

        var accScooter = 0
        var accFoot = 0
        for (i in road.lastIndex downTo 0) {
            accScooter += scooter[road[i]]!!
            accFoot += foot[road[i]]!!

            reverseScooterAccTimes[i] = accScooter
            reverseFootAccTimes[i] = accFoot
        }

        println("sc=${reverseScooterAccTimes.contentToString()}")
        println("ft=${reverseFootAccTimes.contentToString()}")

        var index = 0
        for (i in road.indices) {
            index = i
            if (reverseScooterAccTimes[i] >= reverseFootAccTimes[i])
                break
        }

        return road.foldIndexed(0) { i, acc, roadType ->
            acc + if (i < index) scooter[roadType]!! else foot[roadType]!!
        }
    }
}