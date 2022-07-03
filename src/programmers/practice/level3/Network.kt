package programmers.practice.level3

import Solution
import java.util.Queue
import java.util.LinkedList
import kotlin.collections.HashSet


/**
 * 네트워크
 * DFS/BFS
 * https://programmers.co.kr/learn/courses/30/lessons/43162
 * */
class Network : Solution {

    override fun execute() {
        var answer = -1

        // answer = 2
        answer = solution(
            3, arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 0, 1),
            )
        )
        println("answer=$answer")

        // answer = 1
        answer = solution(
            3, arrayOf(
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 1),
                intArrayOf(0, 1, 1),
            )
        )
        println("answer=$answer")

        // answer = 1
        answer = solution(
            4, arrayOf(
                intArrayOf(1, 1, 0, 1),
                intArrayOf(1, 1, 0, 0),
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 0, 1, 1),
            )
        )
        println("answer=$answer")
    }

    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val set = HashSet<Int>((0 until n).toList())

        while (set.isNotEmpty()) {
            answer++
            set.removeAll(traverse(computers, set.first()))
        }

        return answer
    }

    private fun traverse(computers: Array<IntArray>, startNode: Int): HashSet<Int> {
        val queue: Queue<Int> = LinkedList<Int>().apply { add(startNode) }
        val set = hashSetOf(startNode)

        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()

            computers[currentNode].forEachIndexed { node, connect ->
                if (connect == 1 && !set.contains(node)) {
                    queue.add(node)
                    set.add(node)
                }
            }
        }

        return set
    }
}