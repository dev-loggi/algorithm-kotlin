package programmers.kakao.y2020

import programmers.Programmers

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 4] 기둥과 보 설치
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60061
 * */
class KAKAO_BLIND_2020_P4 : Programmers.Solution {

    override fun execute() {
        testCases().forEach { case ->
            val (n, build_frame) = case

            val answer = solution(n, build_frame)
            println("★ answer:: ")
            answer.forEach { println("[${it[0]}, ${it[1]}, ${it[2]}(${if (it[2]==COL) "COL" else "ROW"})]") }
            println()
        }
    }

    companion object {
        private const val DELETE = 0
        private const val INSERT = 1
        private const val COL = 0 // 기둥
        private const val ROW = 1 // 보
    }

    private lateinit var col: Array<BooleanArray>
    private lateinit var row: Array<BooleanArray>

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        col = Array(n + 1) { BooleanArray(n + 1) }
        row = Array(n + 1) { BooleanArray(n + 1) }

        for (build in build_frame) {
            val (x, y, a, b) = build
            println("build: ($x, $y), ${if (a==COL) "COL(기둥)" else "ROW(보)"}, ${if (b==INSERT) "설치" else "삭제"}")

            if (b == INSERT) { // 설치
                if (a == COL) { // 기둥 설치
                    if (canCol(x, y))
                        col[y][x] = true
                } else { // 보 설치
                    if (canRow(x, y))
                        row[y][x] = true
                }
            } else { // 삭제
                if (a == COL) { // 기둥 삭제
                    col[y][x] = false

                    // 1. 위 기둥
                    val condition1 =
                        if (col.notExists(x, y + 1)) true
                        else canCol(x, y + 1)
                    // 2. 위왼쪽 보
                    val condition2 =
                        if (row.notExists(x - 1, y + 1)) true
                        else canRow(x - 1, y + 1)
                    // 3. 위 보
                    val condition3 =
                        if (row.notExists(x, y + 1)) true
                        else canRow(x, y + 1)

                    if (!condition1 || !condition2 || !condition3)
                        col[y][x] = true

                } else { // 보 삭제
                    row[y][x] = false

                    // 1. 왼쪽 기둥
                    val condition1 =
                        if (col.notExists(x, y)) true
                        else canCol(x, y)
                    // 2. 오른쪽 기둥
                    val condition2 =
                        if (col.notExists(x + 1, y)) true
                        else canCol(x + 1, y)
                    // 3. 왼쪽 보
                    val condition3 =
                        if (row.notExists(x - 1, y)) true
                        else canRow(x - 1, y)
                    // 4. 오른쪽 보
                    val condition4 =
                        if (row.notExists(x + 1, y)) true
                        else canRow(x + 1, y)

                    if (!condition1 || !condition2 || !condition3 || !condition4)
                        row[y][x] = true
                }
            }
        }

        return makeAnswer()
    }

    private fun canCol(x: Int, y: Int): Boolean {
        // 바닥 위 or 기둥 위 or 보 위
        return y == 0 || col.exists(x, y - 1) || row.exists(x - 1, y) || row.exists(x, y)
    }

    private fun canRow(x: Int, y: Int): Boolean {
        // 기둥 위 or 양쪽에 보가 둘 다 존재
        return col.exists(x, y - 1) || col.exists(x + 1, y - 1) || (row.exists(x - 1, y) && (row.exists(x + 1, y)))
    }

    private fun Array<BooleanArray>.exists(x: Int, y: Int): Boolean {
        return getOrNull(y)?.getOrNull(x) ?: false
    }

    private fun Array<BooleanArray>.notExists(x: Int, y: Int): Boolean {
        return !exists(x, y)
    }

    private fun makeAnswer(): Array<IntArray> {
        val indices = col.indices
        val structures = mutableListOf<IntArray>()

        for (y in indices) for (x in indices) {
            if (col[y][x])
                structures.add(intArrayOf(x, y, COL))
            if (row[y][x])
                structures.add(intArrayOf(x, y, ROW))
        }

        structures.sortWith(
            compareBy({ it[0] }, { it[1] }, { it[2] })
        )

        return structures.toTypedArray()
    }
}

private fun testCases(): Array<Pair<Int, Array<IntArray>>> = arrayOf(
    Pair(
        first = 5,
        second = arrayOf(
            intArrayOf(1,0,0,1),
            intArrayOf(1,1,1,1),
            intArrayOf(2,1,0,1),
            intArrayOf(2,2,1,1),
            intArrayOf(5,0,0,1),
            intArrayOf(5,1,0,1),
            intArrayOf(4,2,1,1),
            intArrayOf(3,2,1,1),
        )
    ),
    // answer =
    // [1,0,0],
    // [1,1,1],
    // [2,1,0],
    // [2,2,1],
    // [3,2,1],
    // [4,2,1],
    // [5,0,0],
    // [5,1,0]

    Pair(
        first = 5,
        second = arrayOf(
            intArrayOf(0,0,0,1),
            intArrayOf(2,0,0,1),
            intArrayOf(4,0,0,1),
            intArrayOf(0,1,1,1),
            intArrayOf(1,1,1,1),
            intArrayOf(2,1,1,1),
            intArrayOf(3,1,1,1),
            intArrayOf(2,0,0,0),
            intArrayOf(1,1,1,0),
            intArrayOf(2,2,0,1),
        )
    ),
    // answer =
    // [0,0,0],
    // [0,1,1],
    // [1,1,1],
    // [2,1,1],
    // [3,1,1],
    // [4,0,0]
)