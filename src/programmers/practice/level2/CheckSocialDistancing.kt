package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 거리두기 확인하기
 * https://programmers.co.kr/learn/courses/30/lessons/81302?language=kotlin
 * */
class CheckSocialDistancing : Solution {

    override fun execute() {
        solution(arrayOf(
            arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
            arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
            arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
            arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
            arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
        )).let { println("answer=${it.contentToString()}") } // [1, 0, 1, 1, 1]
    }

    fun solution(places: Array<Array<String>>): IntArray {
        return IntArray(places.size) { i ->
            places[i].isDistancing()
        }
    }

    fun Array<String>.isDistancing(): Int {
        var isDistancing = true

        for (r in this.indices) {
            for (c in this[r].indices) {
                if (this[r][c] != 'P')
                    continue

                isDistancing = this.checkDistancingBy(r, c)

                if (!isDistancing) break
            }
            if (!isDistancing) break
        }

        return if (isDistancing) 1 else 0
    }

    fun Array<String>.checkDistancingBy(r0: Int, c0: Int): Boolean {
        val visited = Array(size) { BooleanArray(this[0].length) }

        fun dfs(depth: Int, r: Int, c: Int): Boolean {
            if (r !in indices || c !in this[0].indices || visited[r][c])
                return true

            if (depth == 1) {
                if (this[r][c] == 'P') return false
                if (this[r][c] == 'X') return true
            } else if (depth == 2) {
                return this[r][c] != 'P'
            }

            visited[r0][c0] = true

            if (!dfs(depth + 1, r, c - 1)) return false
            if (!dfs(depth + 1, r - 1, c)) return false
            if (!dfs(depth + 1, r, c + 1)) return false
            if (!dfs(depth + 1, r + 1, c)) return false

            return true
        }

        return dfs(0, r0, c0)
    }
}