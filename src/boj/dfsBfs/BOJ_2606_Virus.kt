package boj.dfsBfs

import common.Solution
import java.util.ArrayDeque

/**
 * 2606
 * 바이러스
 * https://www.acmicpc.net/problem/2606
 * bfs
 * */
class BOJ_2606_Virus : Solution {

/*
[case1]
7
6
1 2
2 3
1 5
5 2
5 6
4 7
[case1 answer]
4
*/

    override fun execute() {
        main()
    }

    fun main() {
        val N = readLine()?.toIntOrNull() ?: return // 컴퓨터 갯수
        val M = readLine()?.toIntOrNull() ?: return // 네트워크 갯수

        val network = mutableMapOf<Int, MutableSet<Int>>()
        for (i in 1..N) {
            network[i] = mutableSetOf<Int>()
        }

        for (i in 0 until M) {
            val input = readLine()?.split(" ") ?: return
            val a = input[0].toIntOrNull() ?: return
            val b = input[1].toIntOrNull() ?: return

            network[a]?.add(b)
            network[b]?.add(a)
        }

        println(bfs(network))
    }

    private fun bfs(network: Map<Int, Set<Int>>): Int {
        val infected = mutableSetOf(1)
        val queue = ArrayDeque<Int>().apply { offer(1) }

        while (queue.isNotEmpty()) {
            val p = queue.poll()
            infected.add(p)

            network[p]?.forEach { q ->
                if (!infected.contains(q)) {
                    queue.offer(q)
                }
            }
        }

        return infected.size - 1
    }
}