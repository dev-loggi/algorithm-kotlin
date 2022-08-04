package boj.dfsBfs

import boj.BOJSolution
import java.lang.StringBuilder
import java.util.ArrayDeque

/**
 * 1385
 * 벌집
 *
 * 시간 제한: 2초
 * 메모리 제한: 128MB
 * */
class BOJ_1385_HoneyComb : BOJSolution(info(), testCases()) {

    companion object {
        private const val MAX = 1_000_519
        private const val MAX_LEN = 577
    }

    private val graph: Array<IntArray> = makeGraph()

    override fun main() {
        // (1 ≤ from, to ≤ 1,000,000)
        val (from, to) = readln().split(" ").map { it.toInt() }.toIntArray()

        solution(from, to).let { println(it) }
    }

    private fun solution(from: Int, to: Int): String {
        if (from == to)
            return from.toString()

        val queue = ArrayDeque<Int>()
        val back = IntArray(graph.size)

        queue.offer(from)
        back[from] = -1

        while (queue.isNotEmpty()) {
            val curr = queue.poll()

            for (next in graph[curr]) {
                if (back[next] != 0)
                    continue

                back[next] = curr

                if (next == to) {
                    queue.clear()
                    break
                }

                queue.offer(next)
            }
        }

        return back.tracking(to)
    }

    private fun IntArray.tracking(last: Int): String {
        val sb = StringBuilder()
        var n = last
        while (n != -1) {
            sb.insert(0, "$n ")
            n = this[n]
        }
        return sb.deleteCharAt(sb.lastIndex).toString()
    }

    private fun makeGraph(): Array<IntArray> {
        val isVertex = BooleanArray(MAX + 1)

        // 꼭지점인 지역 체크
        for (start in 2..7) {
            var n = start // 시작점(2 ~ 7)
            var d = start + 5 // 공차

            while (n <= MAX) {
                isVertex[n] = true
                n += d
                d += 6
            }
        }

        val graph = Array(MAX + 1) { IntArray(6) }
        val idx = IntArray(MAX + 1)

        // 1번 노드와 1번째 형제 노드들과 연결
        for (n in 2..7) {
            graph[1][n - 2] = n
        }

        var preRange = 1..1
        var curRange = 2..7
        for (len in 1..MAX_LEN) {

            var pn1 = preRange.last
            var pn2 = preRange.first
            var n = curRange.first

            for (i in curRange) {

                // 부모(이전육각형) 노드와 그래프 연결
                if (isVertex[n]) { // 육각형의 꼭지점 노드
                    graph[n][idx[n]++] = pn1
                    graph[pn1][idx[pn1]++] = n
                } else { // 꼭지점 사이의 노드
                    graph[n][idx[n]++] = pn1
                    graph[n][idx[n]++] = pn2

                    graph[pn1][idx[pn1]++] = n
                    graph[pn2][idx[pn2]++] = n

                    pn1 = if (pn1 == preRange.last) preRange.first else pn1 + 1
                    pn2 += 1
                }

                // 형제(현재육각형) 노드와 그래프 연결
                graph[n][idx[n]++] = if (n - 1 !in curRange) curRange.last else n - 1
                graph[n][idx[n]++] = if (n + 1 !in curRange) curRange.first else n + 1

                n += 1
            }

            preRange = curRange
            curRange = (curRange.last + 1)..(sum(len + 1))
        }

        return graph
    }

    private fun makeHoneyCombSequence(maxLen: Int, maxNum: Int): Array<IntArray> {
        val sequence = Array(maxLen + 1) { intArrayOf() }
        sequence[0] = intArrayOf(1)

        var len = 1; var size = 6; var n = 2
        while (n <= maxNum) {
            sequence[len++] = IntArray(size) { n++ }
            size += 6
        }

        return sequence
    }

    /** n번째 육각형 그룹의 노드 갯수 */
    private fun size(n: Int): Int {
        return if (n == 0) 1 else 6 * n
    }

    /** n번째 육각형 그룹의 마지막 노드 번호(누적 합) */
    private fun sum(n: Int): Int {
        return 3 * n * (n + 1) + 1
    }

    private fun printHoneyCombSequence(sequence: Array<IntArray>, lastLen: Int) {
        for (len in 0..lastLen) { println(sequence[len].joinToString(" ")) }
    }

    private fun printHoneyCombGraph(graph: Array<IntArray>, max: Int) {
        for (n in 1..max) { println("graph[$n]=${graph[n].contentToString()}") }
    }
}

private fun info() = BOJSolution.Info(
    no = 1385,
    title = "벌집",
    category = arrayOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/1385",
    solutionUrl = ""
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "10 15",
        output = "10 3 4 14 15",
    ),
)

/*
1 -> 1개
2 3 4 5 6 7 -> 6개
8 9 10 11 12 13 14 15 16 17 18 19 -> 12개
20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 -> 18개

1 6 12 18
5 6 6

1
6 = 1*2 + 2*2
12 = 3*4
18 = 4*2 + 5*2

*
*/