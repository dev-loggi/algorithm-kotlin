package programmers

/**
 * 프로그래머스
 * level 2
 * 행렬의 곱셈
 * */
class Prac1 {

    fun execute() {
        var answer = solution(
            arrayOf(
                intArrayOf(1, 4),
                intArrayOf(3, 2),
                intArrayOf(4, 1),
            ),
            arrayOf(
                intArrayOf(3, 3),
                intArrayOf(3, 3),
            ),
        )
        println("♠ answer=")
        answer.forEach { println(it.contentToString()) }

        answer = solution(
            arrayOf(
                intArrayOf(2, 3, 2),
                intArrayOf(4, 2, 4),
                intArrayOf(3, 1, 4),
            ),
            arrayOf(
                intArrayOf(5, 4, 3),
                intArrayOf(2, 4, 1),
                intArrayOf(3, 1, 1),
            )
        )
        println("♠ answer=")
        answer.forEach { println(it.contentToString()) }
    }

    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        return Array(arr1.size) { r -> IntArray(arr2[0].size) { c ->
            var sum = 0
            for (i in arr1[r].indices)
                sum += arr1[r][i] * arr2[i][c]
            sum
        } }
    }

    private fun Array<IntArray>.multiply(arr2: Array<IntArray>): Array<IntArray> {
        val arr1 = this

        return Array(arr1.size) { r -> IntArray(arr2.size) { c ->
            var sum = 0
            for (i in arr2.indices)
                sum += arr1[r][i] * arr2[i][c]
            sum
        } }
    }
}