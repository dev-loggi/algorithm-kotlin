package programmers.practice.level2

import programmers.Programmers
import java.util.*
import kotlin.math.*

/**
 * 전력망을 둘로 나누기
 * https://school.programmers.co.kr/learn/courses/30/lessons/86971
 *
 * 그래프, 완전탐색, BFS, DFS
 * */
class SplitPowerGridInTwo : Programmers.Solution {

    override fun execute() {
        solution(9, arrayOf(
            intArrayOf(1,3),
            intArrayOf(2,3),
            intArrayOf(3,4),
            intArrayOf(4,5),
            intArrayOf(4,6),
            intArrayOf(4,7),
            intArrayOf(7,8),
            intArrayOf(7,9),
        )).let { println(it) } // 3
        solution(4, arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,3),
            intArrayOf(3,4),
        )).let { println(it) } // 0
        solution(7, arrayOf(
            intArrayOf(1,2),
            intArrayOf(2,7),
            intArrayOf(3,7),
            intArrayOf(3,4),
            intArrayOf(4,5),
            intArrayOf(6,7),
        )).let { println(it) } // 1
    }

    private lateinit var tree: MutableMap<Int, LinkedList<Int>>

    fun solution(n: Int, wires: Array<IntArray>): Int {
        tree = mutableMapOf()

        for ((v1, v2) in wires) {
            tree.computeIfAbsent(v1) { LinkedList() }.add(v2)
            tree.computeIfAbsent(v2) { LinkedList() }.add(v1)
        }

        var min = Int.MAX_VALUE
        for ((v1, v2) in wires) {
            min = min(min, disconnect(v1, v2))
        }

        return min
    }

    private fun disconnect(v1: Int, v2: Int): Int {

        fun bfs(v: Int): Int {
            val queue = ArrayDeque(listOf(v))
            val set = mutableSetOf(v1, v2)
            var count = 0

            while (queue.isNotEmpty()) {
                count += 1

                for (next in tree[queue.poll()]!!) {
                    if (set.contains(next))
                        continue

                    set.add(next)
                    queue.offer(next)
                }
            }

            return count
        }

        return (bfs(v1) - bfs(v2)).absoluteValue
    }
}