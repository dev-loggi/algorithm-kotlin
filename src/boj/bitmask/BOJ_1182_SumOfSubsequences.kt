package boj.bitmask

import boj.BOJSolution

/**
 * [BOJ 1182] 부분수열의 합
 * https://www.acmicpc.net/problem/1182
 * */
class BOJ_1182_SumOfSubsequences : BOJSolution(info(), testCase()) {

    override fun runEachSolution() {
        // (1 ≤ N ≤ 20, |S| ≤ 1,000,000)
        val (N, S) = readln().split(" ").map { it.toInt() }

        var count = 0
        readln().split(" ")
            .map { it.toInt() }
            .toIntArray()
            .onCombinations { subArr ->
                if (subArr.sumOf { it } == S)
                    count += 1
            }

        println(count)
    }

    private fun IntArray.onCombinations(action: (arr: IntArray) -> Unit) {
        val visited = BooleanArray(size)

        fun dfs(depth: Int, start: Int, r: Int, arr: IntArray) {
            if (depth == r) {
                action(arr)
                return
            }

            for (i in start..lastIndex) {
                if (visited[i])
                    continue

                arr[depth] = this[i]
                visited[i] = true
                dfs(depth + 1, i + 1, r, arr)
                visited[i] = false
            }
        }

        for (r in 1..size) {
            dfs(0, 0, r, IntArray(r))
        }
    }
}

private fun info() = BOJSolution.Info(
    no = 1182,
    title = "부분수열의 합",
    category = listOf(BOJSolution.BRUTE_FORCE, BOJSolution.BITMASK, BOJSolution.DFS),
    problemUrl = "https://www.acmicpc.net/problem/1182",
)

private fun testCase() = listOf(
    BOJSolution.TestCase(
        input = "5 0\n" +
                "-7 -3 -2 5 8",
        output = "1",
    )
)