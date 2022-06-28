package programmers.practice.level1

import common.Solution

/**
 * 2022 KAKAO BLIND RECRUITMENT
 * 신고 결과 받기
 * https://programmers.co.kr/learn/courses/30/lessons/92334?language=kotlin
 * */
class GetReportResults : Solution {

    override fun execute() {
        //["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
        //["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]

        bestSolution(
            arrayOf("muzi", "frodo", "apeach", "neo"),
            arrayOf("muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"), 2
        ).let { println(it.contentToString()) } // [2,1,1,0]
        bestSolution(
            arrayOf("con", "ryan"),
            arrayOf("ryan con", "ryan con", "ryan con", "ryan con"), 3
        ).let { println(it.contentToString()) } // [0,0]
    }

    fun solution(id_list: Array<String>, report_list: Array<String>, k: Int): IntArray {

        val reportUsers = Array(id_list.size) { hashSetOf<Int>() }
        val reportCounts = IntArray(id_list.size) { 0 }

        for (report in report_list.distinct()) {
            val data = report.split(" ")

            val user_index = id_list.indexOf(data[0])
            val reported_index = id_list.indexOf(data[1])

            reportUsers[user_index].add(reported_index)
            reportCounts[reported_index]++
        }

        return IntArray(id_list.size) { i ->
            reportUsers[i].sumOf { j -> if (reportCounts[j] >= k) 1 else 0L }.toInt()
        }
    }

    fun bestSolution(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
        report.distinct()
            .map { it.split(" ") }
            .groupBy { it[1] }
            .asSequence()
            .map { it.value }
            .filter { it.size >= k }
            .flatten()
            .map { it[0] }
            .groupingBy { it }
            .eachCount()
            .run { id_list.map { getOrDefault(it, 0) }.toIntArray() }
}