package programmers.practice.level2

import common.Solution
import kotlin.math.min


/**
 * 행렬 테두리 회전하기
 * https://programmers.co.kr/learn/courses/30/lessons/77485?language=kotlin
 * */
class MatrixEdgeRotation : Solution {

    override fun execute() {
        solution(6, 6, arrayOf(
            intArrayOf(2,2,5,4),
            intArrayOf(3,3,6,6),
            intArrayOf(5,1,6,3)
        )).let { println("answer=${it.contentToString()}\n") } // [8, 10, 25]

        solution(3, 3, arrayOf(
            intArrayOf(1,1,2,2),
            intArrayOf(1,2,2,3),
            intArrayOf(2,1,3,2),
            intArrayOf(2,2,3,3)
        )).let { println("answer=${it.contentToString()}\n") } // [1, 1, 5, 3]

        solution(100, 97, arrayOf(
            intArrayOf(1,1,100,97),
        )).let { println("answer=${it.contentToString()}\n") } // [1]
    }

    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        println("solution(): rows=$rows, columns=$columns, queries=${queries.map { it.toList() }}")

        val matrix = Array(rows) { row ->
            IntArray(columns) { col -> (row * columns) + col + 1 }
        }

        return IntArray(queries.size) { i -> rotate(matrix, queries[i]) }
    }

    private fun rotate(matrix: Array<IntArray>, query: IntArray): Int {
        val rangeH = (query[1] - 1)..(query[3] - 1) // 가로 범위
        val rangeV = (query[0] - 1)..(query[2] - 1) // 세로 범위

        var min = matrix[rangeV.first][rangeH.first]
        val tmp = min

        for (row in rangeV.first until rangeV.last) { // Left, 위로 당기기
            matrix[row][rangeH.first] = matrix[row + 1][rangeH.first]
            min = min(min, matrix[row + 1][rangeH.first])
        }
        for (col in rangeH.first until  rangeH.last) { // Bottom, 왼쪽으로 당기기
            matrix[rangeV.last][col] = matrix[rangeV.last][col + 1]
            min = min(min, matrix[rangeV.last][col + 1])
        }
        for (row in rangeV.last downTo rangeV.first + 1) { // Right, 아래로 당기기
            matrix[row][rangeH.last] = matrix[row - 1][rangeH.last]
            min = min(min, matrix[row - 1][rangeH.last])
        }
        for (col in rangeH.last downTo rangeH.first + 1) { // Top, 오른쪽으로 당기기
            matrix[rangeV.first][col] = matrix[rangeV.first][col - 1]
            min = min(min, matrix[rangeV.first][col - 1])
        }

        matrix[rangeV.first][rangeH.first + 1] = tmp

        return min
    }
}