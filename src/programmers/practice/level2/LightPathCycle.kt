package programmers.practice.level2

import programmers.Programmers.Solution

/**
 * 빛의 경로 싸이클
 * https://programmers.co.kr/learn/courses/30/lessons/86052?language=kotlin
 * */
class LightPathCycle : Solution {

    override fun execute() {
        // 1 ≤ grid 의 길이 ≤ 500
        // 1 ≤ grid 의 각 문자열의 길이 ≤ 500
        // grid 의 모든 문자열의 길이는 서로 같습니다.
        // grid 의 모든 문자열은 'L', 'R', 'S'로 이루어져 있습니다.

        solution(arrayOf(
            "SL", "LR"
        )).let { println(it.contentToString()) } // [16]

        solution(arrayOf(
            "S"
        )).let { println(it.contentToString()) } // [1,1,1,1]
        solution(arrayOf(
            "R", "R"
        )).let { println(it.contentToString()) } // [4,4]
        solution(arrayOf(
            "L", "L"
        )).let { println(it.contentToString()) } // [4,4]
        solution(arrayOf(
            "L", "R"
        )).let { println(it.contentToString()) } // [4,4]
        solution(arrayOf(
            "S", "S"
        )).let { println(it.contentToString()) } // [1, 1, 1, 1, 2, 2]
        solution(arrayOf(
            "RLSLRLSSSLRRLS",
            "RLSLLLRLSLRLSS",
            "RRLLLSLRLLSSRL",
            "SRLSRLSRLRLRLS",
            "LLRRLLLSRLSLSS",
            "SSSLLRLRLRSSSR",
            "SSSLLRLRLRSSSR",
            "SRLSRLSRLRLRLS",
        )).let { println(it.contentToString()) } // [4, 4, 6, 10, 174, 250]
    }

    private val move = mapOf(
        'S' to Array(4) { dir -> when (dir) { // S: 직진
            0 -> intArrayOf(0, -1, 0) // Left  -> Left
            1 -> intArrayOf(-1, 0, 1) // Up    -> Up
            2 -> intArrayOf(0, +1, 2) // Right -> Right
            3 -> intArrayOf(+1, 0, 3) // Down  -> Down
            else -> throw IndexOutOfBoundsException("dir=$dir")
        }},
        'L' to Array(4) { dir -> when (dir) { // L: 좌회전
            0 -> intArrayOf(+1, 0, 3) // Left  -> Down
            1 -> intArrayOf(0, -1, 0) // Up    -> Left
            2 -> intArrayOf(-1, 0, 1) // Right -> Up
            3 -> intArrayOf(0, +1, 2) // Down  -> Right
            else -> throw IndexOutOfBoundsException("dir=$dir")
        }},
        'R' to Array(4) { dir -> when (dir) { // R: 우회전
            0 -> intArrayOf(-1, 0, 1) // Left  -> Up
            1 -> intArrayOf(0, +1, 2) // Up    -> Right
            2 -> intArrayOf(+1, 0, 3) // Right -> Down
            3 -> intArrayOf(0, -1, 0) // Down  -> Left
            else -> throw IndexOutOfBoundsException("dir=$dir")
        }}
    )

    /**
     * ※ 주의할 점
     * 모든 지점, 모든 방향에서 사이클을 확인해야 한다.
     * 내부에서만 도는 사이클이 충분히 생길 수 있음.
     * */
    fun solution(grid: Array<String>): IntArray {
        val cycles = mutableListOf<Int>()

        // cycleInfo[r][c][d]
        // r: row, c: col, d: direction
        val cycleInfo = Array(grid.size) { Array(grid[0].length) { BooleanArray(4) }}

        for (r in cycleInfo.indices) for (c in cycleInfo[r].indices) for (d in cycleInfo[r][c].indices) {
            if (cycleInfo[r][c][d])
                continue

            cycles.add(grid.cycle(r, c, d, cycleInfo))
        }

        return cycles.sorted().toIntArray()
    }

    fun Array<String>.cycle(r0: Int, c0: Int, dir0: Int, cycleInfo: Array<Array<BooleanArray>>): Int {
        var r = r0
        var c = c0
        var dir = dir0
        var count = 0
        while (!cycleInfo[r][c][dir]) {
            cycleInfo[r][c][dir] = true

            val type = this[r][c]

            r = (r + move[type]!![dir][0]) % size
            c = (c + move[type]!![dir][1]) % this[0].length

            if (r < 0) r = lastIndex
            if (c < 0) c = this[0].lastIndex

            dir = move[type]!![dir][2]

            count++
        }

        return count
    }

    private fun Array<Array<BooleanArray>>.log() {
        forEach { a1 ->
            a1.forEach { a2 ->
                print(a2.map { if (it) 'T' else 'F' }.joinToString("") + " ")
            }
            println()
        }
        println()
    }

    private val Int.dir: Char
        get() = when (this) {
            0 -> 'L'
            1 -> 'U'
            2 -> 'R'
            3 -> 'D'
            else -> '#'
        }
}