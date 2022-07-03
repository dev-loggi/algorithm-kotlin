package kakao

import Solution

class Test : Solution {



    override fun execute() {

        // ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
        var answer = solution(
            arrayOf("ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"),
            intArrayOf(2, 3, 4)
        )
        println("answer=${answer.contentToString()}\n") // ["AC", "ACDE", "BCFG", "CDE"]

        // ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]
        answer = solution(
            arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"),
            intArrayOf(2, 3, 5)
        )
        println("answer=${answer.contentToString()}\n") // ["ACD", "AD", "ADE", "CD", "XYZ"]

        // ["XYZ", "XWY", "WXA"]
        answer = solution(
            arrayOf("XYZ", "XWY", "WXA"),
            intArrayOf(2, 3, 4)
        )
        println("answer=${answer.contentToString()}\n") // 	["WX", "XY"]

    }

    // 2021 KAKAO BLIND RECRUITMENT
    // 메뉴 리뉴얼
    // https://programmers.co.kr/learn/courses/30/lessons/72411
    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        val answer = mutableListOf<String>()
        val courseCountMap = hashMapOf<String, Int>()

        orders.forEach { order ->
            course.forEach { r ->
                val temp = mutableListOf<List<Char>>()
                combination(
                    order.toCharArray().toList(),
                    temp,
                    Array(order.length) { false },
                    0, r
                )

                temp.map { it.sorted().joinToString("") }
                    .forEach { courseCountMap[it] = courseCountMap.getOrDefault(it, 0) + 1 }
            }
        }

        println(courseCountMap)

        course.forEach { numberOfCourse ->
            val subMap = courseCountMap.filter { it.key.length == numberOfCourse }
            if (subMap.isNotEmpty()) {
                val max = subMap.maxOf { it.value }

                if (max >= 2) {
                    subMap.filter { it.value == max }
                        .also { answer.addAll(it.keys) }
                }
            }
        }

        return answer.sorted().toTypedArray()
    }

    private fun combination(source: List<Char>, out: MutableList<List<Char>>, visited: Array<Boolean>, start: Int, r: Int) {
        if (r == 0) {
            out.addAll(listOf(source.filterIndexed { index, t -> visited[index] }))
            return
        }
        for (i in start until source.size) {
            visited[i] = true
            combination(source, out, visited, i + 1, r - 1)
            visited[i] = false
        }
    }

    private fun s2(orders: Array<String>, course: IntArray) {

        val courseSet = hashSetOf<String>()
        val courseCountMap = hashMapOf<String, Int>()

        orders.forEach { order ->
            course.forEach { r ->
                val temp = mutableListOf<List<Char>>()
                combination(
                    order.toCharArray().toList(),
                    temp,
                    Array(order.length) { false },
                    0, r
                )
                temp.map { it.joinToString("") }
                    .forEach { courseCountMap[it] = courseCountMap.getOrDefault(it, 0) + 1 }
//                    .forEach { courseSet.add(it) }
            }
        }

        mutableListOf<String>().toTypedArray()

        course.forEach { numberOfCourse ->
            courseCountMap.filter { it.key.length == numberOfCourse }
                .also {
                    val max = it.maxOf { map -> map.value }
                    it.filter { map -> map.value == max }
                }

            val max = courseCountMap.filter { it.key.length == numberOfCourse }
                .toList()
                .maxOf { it.second }

            val courses = courseCountMap.filter { it.key.length == numberOfCourse }
                .filter { it.value == max }

            courses.keys
        }




        val a = orders.map { it.toCharArray() }
            .flatMap { it.toList() }
            .groupBy { it }
            .mapValues {
                it.value.size
            }
            .filter {
                it.value >= 2
            }

    }

//    private fun combination(source: List<Char>, out: MutableList<List<Char>>, visited: Array<Boolean>, start: Int, r: Int) {
//        if (r == 0) {
//            out.addAll(listOf(source.filterIndexed { index, t -> visited[index] }))
//            return
//        }
//        for (i in start until source.size) {
//            visited[i] = true
//            combination(source, out, visited, i + 1, r - 1)
//            visited[i] = false
//        }
//    }

    private fun abcd(info: Array<String>) {
        val infos = info.map { it.split(" ") }


        val a = ""
        val b = ""
        val c = ""

        mutableListOf<Int>().toIntArray()

        infos.find {
            (if (a != "-") it[0] == a else true) &&
            (if (b != "-") it[1] == b else true)
        }
    }
}