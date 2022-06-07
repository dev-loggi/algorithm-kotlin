package boj

import common.Solution
import java.util.*


/**
 * 2667
 * 단지 번호 붙이기
 * https://www.acmicpc.net/problem/2667
 * */
class P_2667_VillageNumbering : Solution {

    override fun execute() {
        // 3, 7, 8, 9
        var answer: IntArray = solution(7, arrayOf(
            intArrayOf(0, 1, 1, 0, 1, 0, 0),
            intArrayOf(0, 1, 1, 0, 1, 0, 1),
            intArrayOf(1, 1, 1, 0, 1, 0, 1),
            intArrayOf(0, 0, 0, 0, 1, 1, 1),
            intArrayOf(0, 1, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 1, 1, 1, 1, 0),
            intArrayOf(0, 1, 1, 1, 0, 0, 0),
        ))
        println("answer=${answer.contentToString()}")
    }

    fun solution(n: Int, map: Array<IntArray>): IntArray {
        val answer = mutableListOf(0)
        val visited = Array(n) { Array(n) { false } }

        map.forEachIndexed { i, arr ->
            arr.forEachIndexed { j, exist ->
                if (!visited[i][j] && exist == 1) {
                    answer[0]++
                    answer.add(
                        recursiveDFS(map, visited, n, i, j)
                    )
                    //logVisited(visited)
                }
                visited[i][j] = true
            }
        }

        return answer.toIntArray()
    }

    private fun recursiveDFS(map: Array<IntArray>, visited: Array<Array<Boolean>>, n: Int, i: Int, j: Int): Int {
        var count = 1
        visited[i][j] = true

        count += visit(map, visited, n, i = i, j = j + 1) // 우
        count += visit(map, visited, n, i = i + 1, j = j) // 하
        count += visit(map, visited, n, i = i, j = j - 1) // 좌
        count += visit(map, visited, n, i = i - 1, j = j) // 상

        return count
    }

    private fun visit(map: Array<IntArray>, visited: Array<Array<Boolean>>, n: Int, i: Int, j: Int): Int {
        var count = 0

        if (i in 0 until n && j in 0 until n) {
            if (!visited[i][j] && map[i][j] == 1) {
                //print("($i, $j) ")
                visited[i][j] = true
                count += recursiveDFS(map, visited, n, i, j)
            }
            visited[i][j] = true
        }

        return count
    }

    private fun logVisited(visited: Array<Array<Boolean>>) {
        visited.forEach {
            it.forEach { b ->
                if (b) print("1 ")
                else print("0 ")
            }
            println()
        }
    }

}