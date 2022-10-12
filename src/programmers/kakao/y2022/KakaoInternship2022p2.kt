package programmers.kakao.y2022

import programmers.Programmers.Solution
import java.util.ArrayDeque
import kotlin.math.min

/**
 *
 * */
class KakaoInternship2022p2 : Solution {

    override fun execute() {
        var answer = -1

        answer = solution(
            intArrayOf(3, 2, 7, 2),
            intArrayOf(4, 6, 5, 1)
        )
        println("answer=$answer") // 2

        answer = solution(
            intArrayOf(1, 2, 1, 2),
            intArrayOf(1, 10, 1, 2)
        )
        println("answer=$answer") // 7

        answer = solution(
            intArrayOf(1, 1),
            intArrayOf(1, 5)
        )
        println("answer=$answer") // -1
    }

    fun solution(q1: IntArray, q2: IntArray): Int {
        var sumQ1 = q1.sumOf { it.toLong() }
        var sumQ2 = q2.sumOf { it.toLong() }

        val sumTotal = sumQ1 + sumQ2

        // 1. 모든 수의 합 홀수: 불가능(-1)
        if (sumTotal % 2 == 1L) return -1

        // 2. 하나의 원소가 (sumTotal / 2) 보다 큰 경우: 불가능(-1)
        if (q1.any { it > sumTotal / 2 }) return -1
        if (q2.any { it > sumTotal / 2 }) return -1

        // 3. 조합으로 (sumTotal / 2) 만들어 보기
        // if (!combinationAll((q1 + q2).toList(), sumTotal / 2)) return -1

        // 4. 임의의 제한(나올 수 있는 모든 경우의 수)
        var maxCount = (1..(q1.size + q2.size)).sum()
        maxCount = min(maxCount, 100000)

        val queue1 = ArrayDeque(q1.map { it.toLong() }.toList())
        val queue2 = ArrayDeque(q2.map { it.toLong() }.toList())

        var count = 0
        while (sumQ1 != sumQ2) {
            if (count == maxCount) {
                count = -1
                break
            }

            if (sumQ1 > sumQ2) {
                queue1.poll().let {
                    queue2.offer(it)
                    sumQ1 -= it
                    sumQ2 += it
                }
            } else {
                queue2.poll().let {
                    queue1.offer(it)
                    sumQ1 += it
                    sumQ2 -= it
                }
            }
            count++
        }

        return count
    }

    private fun combinationAll(source: List<Int>, sumHalf: Long): Boolean {
        for (r in 1..source.size) {
            val temp = mutableListOf<List<Int>>()
            combination(source, temp, Array(source.size) { false }, 0, r)

            if (temp.any { list -> list.sumOf { it.toLong() } == sumHalf })
                return true
        }
        return false
    }

    private fun <T> combination(
        source: Collection<T>,
        out: MutableList<List<T>>,
        visited: Array<Boolean>,
        start: Int,
        r: Int
    ) {
        if (r == 0) {
            out.addAll(listOf(source.filterIndexed { index, t -> visited[index] }))
            return
        }
        for (i in start until source.size) {
            visited[i] = true
            combination(source, out, visited, i + 1, r - 1)
            visited[i] = false
        }
    }
}