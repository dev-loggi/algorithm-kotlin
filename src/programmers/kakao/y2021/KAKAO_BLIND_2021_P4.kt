package programmers.kakao.y2021

import programmers.Programmers
import java.util.*

/**
 * 2021 KAKAO BLIND RECRUITMENT
 * [No. 4] 순위 검색
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72412
 * */
class KAKAO_BLIND_2021_P4 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            val (info, queries) = case
            println("case $index ->")
            println("[info]")
            info.forEach { println("    $it") }
            println("[queries]")
            queries.forEach { println("    $it") }

            val answer = solution(info, queries)
            println("♠ answer=${answer.contentToString()}")
        }
    }

    class Record(val id: Int, val score: Int) : Comparable<Record> {
        override fun compareTo(other: Record): Int {
            val diffScore = score - other.score
            return if (diffScore != 0) diffScore
            else id - other.id
        }
    }

    private val languageMap = mapOf("-" to 0, "java" to 1, "python" to 2, "cpp" to 3)
    private val jobMap = mapOf("-" to 0, "backend" to 1, "frontend" to 2)
    private val careerMap = mapOf("-" to 0, "junior" to 1, "senior" to 2)
    private val soulFoodMap = mapOf("-" to 0, "chicken" to 1, "pizza" to 2)

    fun solution(info_data: Array<String>, queries: Array<String>): IntArray {
        val groups = Array(4) { Array(3) { Array(3) { Array(3) {
            mutableListOf<Record>()
        }}}}

        for (id in info_data.indices) {
            val info = info_data[id].split(" ")

            val lan = languageMap[info[0]]!!
            val job = jobMap[info[1]]!!
            val crr = careerMap[info[2]]!!
            val sfd = soulFoodMap[info[3]]!!
            val score = info[4].toInt()

            for (l in 0..3) for (j in 0..2) for (c in 0..2) for (s in 0..2) {
                if (l != 0 && l != lan) continue
                if (j != 0 && j != job) continue
                if (c != 0 && c != crr) continue
                if (s != 0 && s != sfd) continue

                groups[l][j][c][s].add(Record(id, score))
            }
        }

        for (l in 0..3) for (j in 0..2) for (c in 0..2) for (s in 0..2) {
            groups[l][j][c][s].sort()
        }

        return IntArray(queries.size) { i ->
            val query = queries[i]

            val (q0, q1, q2, qq) = query.split(" and ")
            val (q3, q4) = qq.split(" ")
            val score = q4.toInt()

            val group = groups[languageMap[q0]!!][jobMap[q1]!!][careerMap[q2]!!][soulFoodMap[q3]!!]
            val index = group.lowerBound(score)

            group.size - index
        }
    }

    private fun MutableList<Record>.lowerBound(key: Int): Int {
        var left = 0
        var right = lastIndex
        var mid: Int

        while (left <= right) {
            mid = (left + right) shr 1

            if (this[mid].score < key)
                left = mid + 1
            else
                right = mid - 1
        }

        return left
    }

    fun solution2(info_data: Array<String>, queries: Array<String>): IntArray {
        val groups = Array(4) { Array(3) { Array(3) { Array(3) {
            TreeSet<Record>()
        }}}}

        for (id in info_data.indices) {
            val info = info_data[id].split(" ")

            val lan = languageMap[info[0]]!!
            val job = jobMap[info[1]]!!
            val crr = careerMap[info[2]]!!
            val sfd = soulFoodMap[info[3]]!!
            val score = info[4].toInt()

            for (l in 0..3) for (j in 0..2) for (c in 0..2) for (s in 0..2) {
                if (l != 0 && l != lan) continue
                if (j != 0 && j != job) continue
                if (c != 0 && c != crr) continue
                if (s != 0 && s != sfd) continue

                groups[l][j][c][s].add(Record(id, score))
            }
        }

        return IntArray(queries.size) { i ->
            val query = queries[i]

            val (q0, q1, q2, qq) = query.split(" and ")
            val (q3, q4) = qq.split(" ")
            val score = q4.toInt()

            val group = groups[languageMap[q0]!!][jobMap[q1]!!][careerMap[q2]!!][soulFoodMap[q3]!!]
            val iter = group.iterator()

            var count = 0
            while (iter.hasNext()) {
                if (score <= iter.next().score)
                    break

                count += 1
            }

            group.size - count
        }
    }

    fun solution3(info_data: Array<String>, queries: Array<String>): IntArray {
        val infos = Array(info_data.size) { i ->
            val data = info_data[i].split(" ")

            intArrayOf(languageMap[data[0]]!!, jobMap[data[1]]!!, careerMap[data[2]]!!, soulFoodMap[data[3]]!!, data[4].toInt())
        }

        infos.sortBy { it[4] }

        return IntArray(queries.size) { i ->
            val query = queries[i]

            val (a, b, c, d0) = query.split(" and ")
            val (d, e) = d0.split(" ")


            val lan = languageMap[a]
            val job = jobMap[b]
            val crr = careerMap[c]
            val sfd = soulFoodMap[d]
            val score = e.toInt()

            var count = 0

            for (j in infos.lastIndex downTo 0) {
                val info = infos[j]

                if (info[4] < score)
                    break

                val b1 = if (lan == 0) true else (info[0] == lan)
                val b2 = if (job == 0) true else (info[1] == job)
                val b3 = if (crr == 0) true else (info[2] == crr)
                val b4 = if (sfd == 0) true else (info[3] == sfd)

                if (b1 && b2 && b3 && b4)
                    count += 1
            }

            count
        }
    }

    fun solution4(info: Array<String>, queries: Array<String>): IntArray {
        var answer = mutableListOf<Int>()

        val infos = info.map { it.split(" ") }

        queries.map { it.split(" and ") }
            .forEach { query ->
                val language = query[0]
                val job = query[1]
                val career = query[2]
                val food = query[3].split(" ")[0]
                val score = query[3].split(" ")[1].toInt()

                //println("$language, $job, $career, $food, $score")

                val count = infos.count {
                    (if (language != "-") it[0] == language else true) &&
                            (if (job != "-") it[1] == job else true) &&
                            (if (career != "-") it[2] == career else true) &&
                            (if (food != "-") it[3] == food else true) &&
                            it[4].toInt() >= score
                }

                answer.add(count)
            }

        return answer.toIntArray()
    }
}

private fun testCases(): Array<Pair<Array<String>, Array<String>>> = arrayOf(
    // case 1
    // answer = [1,1,1,1,2,4]
    Pair(
        first = arrayOf(
            "java backend junior pizza 150",
            "python frontend senior chicken 210",
            "python frontend senior chicken 150",
            "cpp backend senior pizza 260",
            "java backend junior chicken 80",
            "python backend senior chicken 50",
        ),
        second = arrayOf(
            "java and backend and junior and pizza 100",
            "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250",
            "- and backend and senior and - 150",
            "- and - and - and chicken 100",
            "- and - and - and - 150"
        ),
    ),
)