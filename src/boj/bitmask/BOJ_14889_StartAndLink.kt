package boj.bitmask

import boj.BOJ
import boj.BOJSolution
import kotlin.math.absoluteValue

/**
 * [BOJ 14889] 스타트와 링크
 * https://www.acmicpc.net/problem/14889
 * */
class BOJ_14889_StartAndLink : BOJSolution(info(), testCase()) {

    override fun runEachSolution() {
        val N = readln().toInt() // 사람 수 (4 ≤ N ≤ 20, N은 짝수)
        val S = Array(N) {
            readln().split(" ").map { it.toInt() }.toIntArray()
        }

        println(solution(N, S))
    }

    private fun solution(N: Int, S: Array<IntArray>): Int {
        var min = Int.MAX_VALUE

        IntArray(N) { it }.onCombination { team1, team2 ->
            var sum1 = 0
            team1.onCombination(2) { arr ->
                sum1 += S[arr[0]][arr[1]] + S[arr[1]][arr[0]]
            }
            var sum2 = 0
            team2.onCombination(2) { arr ->
                sum2 += S[arr[0]][arr[1]] + S[arr[1]][arr[0]]
            }

            val diff = (sum1 - sum2).absoluteValue
            if (diff < min)
                min = diff
        }

        return min
    }

    private fun IntArray.onCombination(
        action: (team1: IntArray, team2: IntArray) -> Unit
    ) {
        val visited = BooleanArray(size)
        val teamSize = size / 2
        val team2 = IntArray(teamSize)

        fun dfs(depth: Int, start: Int, team1: IntArray) {
            if (depth == teamSize) {
                var j = 0
                for (i in 0..lastIndex) {
                    if (visited[i]) continue
                    team2[j++] = this[i]
                }
                action(team1, team2)
                return
            }

            for (i in start..lastIndex) {
                if (visited[i]) continue

                team1[depth] = this[i]
                visited[i] = true
                dfs(depth + 1, i + 1, team1)
                visited[i] = false
            }
        }

        dfs(0, 0, IntArray(teamSize))
    }

    private fun IntArray.onCombination(
        r: Int,
        action: (arr: IntArray) -> Unit
    ) {
        val visited = BooleanArray(size)

        fun dfs(depth: Int, start: Int, arr: IntArray) {
            if (depth == r) {
                action(arr)
                return
            }

            for (i in start..lastIndex) {
                if (visited[i])
                    continue

                arr[depth] = this[i]
                visited[i] = true
                dfs(depth + 1, i + 1, arr)
                visited[i] = false
            }
        }

        dfs(0, 0, IntArray(r))
    }
}

private fun info() = BOJSolution.Info(
    no = 14889,
    title = "스타트와 링크",
    category = listOf(BOJSolution.BRUTE_FORCE, BOJSolution.BITMASK, BOJSolution.DFS),
    problemUrl = "https://www.acmicpc.net/problem/14889",
)

private fun testCase() = listOf(
    BOJSolution.TestCase(
        input = "4\n" +
                "0 1 2 3\n" +
                "4 0 5 6\n" +
                "7 1 0 2\n" +
                "3 4 5 0",
        output = "0",
    ),
    BOJSolution.TestCase(
        input = "6\n" +
                "0 1 2 3 4 5\n" +
                "1 0 2 3 4 5\n" +
                "1 2 0 3 4 5\n" +
                "1 2 3 0 4 5\n" +
                "1 2 3 4 0 5\n" +
                "1 2 3 4 5 0",
        output = "2",
    ),
    BOJSolution.TestCase(
        input = "8\n" +
                "0 5 4 5 4 5 4 5\n" +
                "4 0 5 1 2 3 4 5\n" +
                "9 8 0 1 2 3 1 2\n" +
                "9 9 9 0 9 9 9 9\n" +
                "1 1 1 1 0 1 1 1\n" +
                "8 7 6 5 4 0 3 2\n" +
                "9 1 9 1 9 1 0 9\n" +
                "6 5 4 3 2 1 9 0",
        output = "1",
    ),
)