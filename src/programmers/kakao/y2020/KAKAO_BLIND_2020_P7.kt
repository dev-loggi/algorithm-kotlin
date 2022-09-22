package programmers.kakao.y2020

import programmers.Programmers
import java.util.ArrayDeque

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 7] 블록 이동하기
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60063
 * */
class KAKAO_BLIND_2020_P7 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("test case $index → ")
            case.print()

            val answer = solution(case)
            println("★ answer=$answer\n")
        }
    }

    companion object {
        private const val HOR = 0
        private const val VER = 1
    }

    class Robot(val r0: Int, val c0: Int, val orientation: Int, val count: Int)

    private val dr = intArrayOf(0, -1, 0, 1)
    private val dc = intArrayOf(-1, 0, 1, 0)

    private val rotate_r = arrayOf(
        arrayOf( // HOR -> VER
            intArrayOf(-1, 0, -1, 0), // r0
            intArrayOf(0, 1, 0, 1), // r1
        ),
        arrayOf( // VER -> HOR
            intArrayOf(0, 1, 0, 1), // r0
            intArrayOf(0, 1, 0, 1), // r1
        ),
    )
    private val rotate_c = arrayOf(
        arrayOf( // HOR -> VER
            intArrayOf(0, 0, 1, 1), // c0
            intArrayOf(0, 0, 1, 1), // c1
        ),
        arrayOf( // VER -> HOR
            intArrayOf(-1, -1, 0, 0), // c0
            intArrayOf(0, 0, 1, 1), // c1
        ),
    )
    private val rotate_check_r = arrayOf(
        intArrayOf(-1, 1, -1, 1),   // HOR -> VER
        intArrayOf(1, 0, 1, 0),     // VER -> HOR
    )
    private val rotate_check_c = arrayOf(
        intArrayOf(1, 1, 0, 0),     // HOR -> VER
        intArrayOf(-1, -1, 1, 1),   // VER -> HOR
    )

    fun solution(board: Array<IntArray>): Int {
        val N = board.size - 1
        val visited = Array(N + 1) { Array(N + 1) { BooleanArray(2) } }

        val queue = ArrayDeque<Robot>()

        queue.offer(Robot(0, 0, HOR, 0))
        visited[0][0][HOR] = true

        while (queue.isNotEmpty()) {
            val curr = queue.poll()

            if (curr.orientation == HOR && curr.r0 == N && curr.c0 + 1 == N)
                return curr.count
            if (curr.orientation == VER && curr.r0 + 1 == N && curr.c0 == N)
                return curr.count

            // 이동
            for (d in 0..3) {
                val nr = curr.r0 + dr[d]
                val nc = curr.c0 + dc[d]

                if (curr.orientation == HOR) {
                    if (nr !in 0..N || nc !in 0..N || nc + 1 !in 0..N)
                        continue
                    if (board[nr][nc] == 1 || board[nr][nc + 1] == 1 || visited[nr][nc][HOR])
                        continue
                }

                if (curr.orientation == VER) {
                    if (nr !in 0..N || nr + 1 !in 0..N || nc !in 0..N)
                        continue
                    if (board[nr][nc] == 1 || board[nr + 1][nc] == 1 || visited[nr][nc][VER])
                        continue
                }

                visited[nr][nc][curr.orientation] = true
                queue.offer(Robot(nr, nc, curr.orientation, curr.count + 1))
            }

            // 회전
            if (curr.orientation == HOR) { // HOR -> VER
                for (rotate in 0..3) {
                    val nr0 = curr.r0 + rotate_r[HOR][0][rotate]
                    val nc0 = curr.c0 + rotate_c[HOR][0][rotate]

                    if (nr0 !in 0..N || nc0 !in 0..N || visited[nr0][nc0][VER] || board[nr0][nc0] == 1)
                        continue

                    val nr1 = curr.r0 + rotate_r[HOR][1][rotate]
                    val nc1 = curr.c0 + rotate_c[HOR][1][rotate]

                    if (nr1 !in 0..N || nc1 !in 0..N || board[nr1][nc1] == 1)
                        continue

                    val checkR = curr.r0 + rotate_check_r[HOR][rotate]
                    val checkC = curr.c0 + rotate_check_c[HOR][rotate]

                    if (checkR !in 0..N || checkC !in 0..N || board[checkR][checkC] == 1)
                        continue

                    visited[nr0][nc0][VER] = true
                    queue.offer(Robot(nr0, nc0, VER, curr.count + 1))
                }
            } else { // VER -> HOR
                for (rotate in 0..3) {
                    val nr0 = curr.r0 + rotate_r[VER][0][rotate]
                    val nc0 = curr.c0 + rotate_c[VER][0][rotate]

                    if (nr0 !in 0..N || nc0 !in 0..N || visited[nr0][nc0][HOR] || board[nr0][nc0] == 1)
                        continue

                    val nr1 = curr.r0 + rotate_r[VER][1][rotate]
                    val nc1 = curr.c0 + rotate_c[VER][1][rotate]

                    if (nr1 !in 0..N || nc1 !in 0..N || board[nr1][nc1] == 1)
                        continue

                    val checkR = curr.r0 + rotate_check_r[VER][rotate]
                    val checkC = curr.c0 + rotate_check_c[VER][rotate]

                    if (checkR !in 0..N || checkC !in 0..N || board[checkR][checkC] == 1)
                        continue

                    visited[nr0][nc0][HOR] = true
                    queue.offer(Robot(nr0, nc0, HOR, curr.count + 1))
                }
            }
        }

        return -1
    }

    private fun Array<IntArray>.print() {
        forEach { println("    ${it.contentToString()}") }
    }
}

private fun testCases(): Array<Array<IntArray>> = arrayOf(
    // case 1
    // answer = 7
    arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0),
    )
)