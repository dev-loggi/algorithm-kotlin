package programmers.kakao.y2020

import programmers.Programmers

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 5] 외벽 점검
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60062
 * */
class KAKAO_BLIND_2020_P5 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("test case $index → n=${case.first}, weak=${case.second.contentToString()}, dist=${case.third.contentToString()}")
            val answer = solution(case.first, case.second, case.third)
            println("answer=$answer\n")
        }
    }

    fun solution(n: Int, originWeak: IntArray, originDist: IntArray): Int {
        var min = Int.MAX_VALUE

        for (weak in originWeak.cycled(n)) {
            originDist.onPermutation(originDist.size) { dist ->
                val count = check(weak, dist)

                if (count != -1 && count < min) {
                    min = count
                }
            }
        }

        return if (min < Int.MAX_VALUE) min
        else -1
    }

    private fun check(weak: IntArray, dist: IntArray): Int {
        var i = 0
        var pos = weak[i]

        for (j in dist.indices) {
            pos += dist[j]

            while (i < weak.size && weak[i] <= pos) {
                i++
            }

            if (i == weak.size || weak.last() <= pos) {
                return j + 1
            }

            pos = weak[i]

        }

        return -1
    }

    private fun IntArray.cycled(n: Int): Array<IntArray> {
        val out = Array(size) { intArrayOf() }

        for (i in indices) {
            val arr = IntArray(size)
            for (j in indices) {
                arr[j] =
                    if (i + j < size) this[i + j]
                    else this[(i + j) % size] + n
            }
            out[i] = arr
        }

        return out
    }

    private fun IntArray.onPermutation(r: Int, action: (arr: IntArray) -> Unit) {
        val visited = BooleanArray(size)

        fun dfs(depth: Int, r: Int, out: IntArray) {
            if (depth == r) {
                action(out)
                return
            }

            for (i in indices) {
                if (visited[i])
                    continue

                out[depth] = this[i]

                visited[i] = true
                dfs(depth + 1, r, out)
                visited[i] = false
            }
        }

        dfs(0, r, IntArray(r))
    }
}

private fun testCases(): Array<Triple<Int, IntArray, IntArray>> = arrayOf(
    // case 1
    // answer = 2
    Triple(
        first = 12,
        second = intArrayOf(1, 5, 6, 10),
        third = intArrayOf(1, 2, 3, 4),
    ),

    // case 2
    // answer = 1
    Triple(
        first = 12,
        second = intArrayOf(1, 3, 4, 9, 10),
        third = intArrayOf(3, 5, 7),
    )
)