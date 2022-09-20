package programmers.practice.level1

import programmers.Programmers.Solution


/**
 * K번째 수
 * 정렬
 * https://programmers.co.kr/learn/courses/30/lessons/42748?language=kotlin
 * */
class KthNumber : Solution {

    override fun execute() {
        val answer: IntArray = solution(
            intArrayOf(1, 5, 2, 6, 3, 7, 4),
            arrayOf(
                intArrayOf(2, 5, 3),
                intArrayOf(4, 4, 1),
                intArrayOf(1, 7, 3),
            )
        )
        println("answer=${answer.contentToString()}") // 5, 6, 3
    }

    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = mutableListOf<Int>()

        commands.forEach { command ->
            val number = array.slice(command[0] - 1 until command[1])
                .sorted()[command[2] - 1]

            answer.add(number)
        }

        return answer.toIntArray()
    }

    fun bestSolution(array: IntArray, commands: Array<IntArray>): IntArray =
        commands.map { command ->
            array.slice(command[0] - 1 until command[1]).sorted()[command[2] - 1]
        }.toIntArray()
}