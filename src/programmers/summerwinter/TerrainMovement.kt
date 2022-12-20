package programmers.summerwinter

import programmers.Programmers
import java.util.*
import kotlin.math.absoluteValue
import kotlin.random.Random

/**
 * Summer/Winter Coding(2019)
 *
 * 지형 이동
 *
 * [제한 사항]
 * land는 N x N크기인 2차원 배열입니다.
 * land의 최소 크기는 4 x 4, 최대 크기는 300 x 300입니다.
 * land의 원소는 각 격자 칸의 높이를 나타냅니다.
 * 격자 칸의 높이는 1 이상 10,000 이하인 자연수입니다.
 * height는 1 이상 10,000 이하인 자연수입니다.
 * */
class TerrainMovement : Programmers.Solution {

    override fun execute() {
        solution(
            land = arrayOf(
                intArrayOf(1, 4, 8, 10),
                intArrayOf(5, 5, 5, 5),
                intArrayOf(10, 10, 10, 10),
                intArrayOf(10, 10, 10, 20),
            ),
            height = 3
        ).let { println("answer=$it\n") } // 15
        solution(
            land = arrayOf(
                intArrayOf(10, 11, 10, 11),
                intArrayOf(2, 21, 20, 10),
                intArrayOf(1, 20, 21, 11),
                intArrayOf(2, 1, 2, 1),
            ),
            height = 1
        ).let { println("answer=$it\n") } // 18

        solution(
            land = arrayOf(
                intArrayOf(1, 4, 8, 10),
                intArrayOf(5, 6, 5, 5),
                intArrayOf(10, 10, 10, 10),
                intArrayOf(10, 10, 10, 20),
            ),
            height = 3
        ).let { println("answer=$it\n") } // 14
        solution(
            land = arrayOf(
                intArrayOf(10, 11, 10, 11),
                intArrayOf(2, 21, 20, 10),
                intArrayOf(1, 20, 21, 11),
                intArrayOf(2, 5, 2, 1),
            ),
            height = 1
        ).let { println("answer=$it\n") } // 24
    }

    data class Node(
        val r: Int,
        val c: Int,
        val diff: Int = 0
    ) : Comparable<Node> {

        override fun compareTo(other: Node): Int {
            return if (diff != other.diff) diff - other.diff
            else if (r != other.r) r - other.r
            else c - other.c
        }
    }

    private val dr = intArrayOf(0, -1, 0, 1)
    private val dc = intArrayOf(-1, 0, 1, 0)

    private var N = 0
    private var height = 0
    private lateinit var land: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>
    private lateinit var priorityQueue: TreeSet<Node>

    fun solution(land: Array<IntArray>, height: Int): Int {
        this.N = land.size
        this.height = height
        this.land = land
        this.visited = Array(N) { BooleanArray(N) }
        this.priorityQueue = TreeSet()

        return solve()
    }

    private fun solve(): Int {
        // 1. (0, 0)에서 "diff <= height"인 지역 탐색
        bfs(0, 0)
        visited[0][0] = true

        var cost = 0

        // 2. 탐색 과정에서 "height < diff"으로 이동하지 못한 지역들 중,
        //    diff 가 가장 작은 순으로 재탐색(BFS)
        while (priorityQueue.isNotEmpty()) {
            val (r, c, diff) = priorityQueue.pollFirst()!!

            if (visited[r][c])
                continue

            visited[r][c] = true
            cost += diff

            bfs(r, c)
        }

        return cost
    }

    private fun bfs(r0: Int, c0: Int) {
        val queue = ArrayDeque<Node>().apply { offer(Node(r0, c0)) }

        while (queue.isNotEmpty()) {
            val (r, c, _) = queue.poll()

            for (dir in 0..3) {
                val nr = r + dr[dir]
                val nc = c + dc[dir]

                if (nr !in 0 until N || nc !in 0 until N || visited[nr][nc])
                    continue

                val diff = (land[r][c] - land[nr][nc]).absoluteValue
                val next = Node(nr, nc, diff)

                // diff <= height 이면 탐색 진행, 그렇지 않으면 priorityQueue 에 추가
                if (diff <= height) {
                    visited[nr][nc] = true
                    queue.offer(next)
                } else {
                    priorityQueue.add(next)
                }
            }
        }
    }

    private fun Array<IntArray>.print() {
        forEach { println(it.contentToString()) }
    }
    private fun Array<BooleanArray>.print() {
        forEach { println(it.joinToString(", ") { b -> if (b) "T" else "F" }) }
    }
}