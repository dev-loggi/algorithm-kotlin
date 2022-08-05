package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/*
 * 16959
 * 체스판 여행1
 *
 * 시간 제한: 2초
 * 메모리 제한: 512 MB
 * */
class BOJ_16959_ChessboardTravel1 : BOJSolution(info(), testCases()) {

    companion object {
        private const val NIGHT = 0
        private const val BISHOP = 1
        private const val ROOK = 2

        private val PIECES = NIGHT..ROOK

        private val MOVE = arrayOf(
            arrayOf( // NIGHT
                intArrayOf(1,-2), intArrayOf(-1,-2), intArrayOf(-2,-1), intArrayOf(-2,1),
                intArrayOf(-1,2), intArrayOf(1,2), intArrayOf(2,1), intArrayOf(2,-1)
            ),
            arrayOf( // BISHOP
                intArrayOf(-1,-1), intArrayOf(-1,1), intArrayOf(1,1), intArrayOf(1,-1)
            ),
            arrayOf( // ROOK
                intArrayOf(0,-1), intArrayOf(-1,0), intArrayOf(0,1), intArrayOf(1,0)
            ),
        )
    }

    data class Node(
        val n: Int,
        val piece: Int,
        val row: Int,
        val col: Int,
        val time: Int
    ) {
        val nextNum: Int
            get() = n + 1

        fun getNext(n: Int = this.n, piece: Int = this.piece, row: Int = this.row, col: Int = this.col): Node {
            return Node(n, piece, row, col, time + 1)
        }
    }

    private var N = 0
    private var destination = 1
    private lateinit var range: IntRange
    private lateinit var board: Array<IntArray>
    private lateinit var visited: Array<Array<Array<BooleanArray>>>
    private lateinit var queue: ArrayDeque<Node>

    override fun main() {
        N = readLine()!!.toInt() // 체스판의 크기 (3 ≤ N ≤ 10)

        destination = N * N
        range = 0 until N
        board = Array(N) { intArrayOf() }

        var r0 = -1 // 시작 위치 row
        var c0 = -1 // 시작 위치 col

        for (r in 0 until N) {
            board[r] = readLine()!!.split(" ")
                .map { it.toInt() } // 인덱스와 매핑하기 위해 모든 원소 -1
                .onEachIndexed { c, n -> if (n == 1) { r0 = r; c0 = c } }
                .toIntArray()
        }

        solution(r0, c0).let { println(it) }
        queue.clear()
    }

    fun solution(r0: Int, c0: Int): Int {
        // visited[n][p][r][c]
        // n: 체스 말의 출발 번호(0 ≤ n ≤ N^2 - 1)
        // p: piece(체스 말의 종류)
        // r: row
        // c: col
        visited = Array(destination + 1) { Array(3) { Array(N) { BooleanArray(N) } } }
        queue = ArrayDeque<Node>()

        for (p in PIECES) {
            visited[1][p][r0][c0] = true
            queue.offer(Node(1, p, r0, c0, 0))
        }

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.n == destination) {
                queue.clear()
                return cur.time
            }

            // 1. 피스 변경
            for (p in PIECES) {
                if (p == cur.piece || visited[cur.n][p][cur.row][cur.col])
                    continue

                visited[cur.n][cur.piece][cur.row][cur.col] = true
                queue.offer(cur.getNext(piece = p))
            }

            // 2. 피스 이동
            when (cur.piece) {
                NIGHT -> moveNight(cur)
                BISHOP, ROOK -> moveBishopOrRook(cur)
            }
        }

        return 0
    }

    private fun moveNight(cur: Node) {
        for (move in MOVE[cur.piece]) {
            val r = cur.row + move[0]
            val c = cur.col + move[1]

            if (r !in range || c !in range || visited[cur.n][cur.piece][r][c])
                continue

            visited[cur.n][cur.piece][r][c] = true

            if (board[r][c] == cur.nextNum) { // 다음 목적지 도착
                visited[cur.nextNum][cur.piece][r][c] = true

                queue.offer(cur.getNext(cur.nextNum, cur.piece, r, c))
            } else { // 미도착
                queue.offer(cur.getNext(row = r, col = c))
            }
        }
    }

    private fun moveBishopOrRook(cur: Node) {
        for (move in MOVE[cur.piece]) {
            var r = cur.row + move[0]
            var c = cur.col + move[1]

            while (r in range && c in range) {
                if (!visited[cur.n][cur.piece][r][c]) {
                    visited[cur.n][cur.piece][r][c] = true

                    if (board[r][c] == cur.nextNum) { // 다음 목적지 도착
                        visited[cur.nextNum][cur.piece][r][c] = true
                        queue.offer(cur.getNext(cur.nextNum, cur.piece, r, c))
                        break
                    } else { // 미도착
                        queue.offer(cur.getNext(row = r, col = c))
                    }
                }

                r += move[0]
                c += move[1]
            }
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 16959,
    title = "체스판 여행1",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/16959",
    solutionUrl = ""
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "3\n" +
                "1 9 3\n" +
                "8 6 7\n" +
                "4 2 5",
        output = "12"
    ),
    BOJSolution.TestCase(
        input = "3\n" +
                "1 5 8\n" +
                "9 2 4\n" +
                "3 6 7",
        output = "12"
    ),
    BOJSolution.TestCase(
        input = "4\n" +
                "5 4 1 13\n" +
                "8 3 6 16\n" +
                "15 9 14 12\n" +
                "11 2 7 10",
        output = "23"
    ),
    BOJSolution.TestCase(
        input = "5\n" +
                "21 14 2 3 12\n" +
                "19 8 16 18 7\n" +
                "9 17 10 15 4\n" +
                "24 5 1 23 11\n" +
                "25 13 22 6 20",
        output = "38"
    ),
)