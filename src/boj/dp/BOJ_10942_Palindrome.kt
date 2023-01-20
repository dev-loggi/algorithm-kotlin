package boj.dp

import boj.BOJSolution
import java.io.*
import java.util.*

/**
 * 10942 팰린드롬?
 *
 * 시간 제한: 2.5초(Kotlin)
 * 메모리 제한: 256 MB
 * */
class BOJ_10942_Palindrome : BOJSolution(info(), testCases()) {

    var n = 0
    lateinit var array: IntArray
    var m = 0
    lateinit var questions: Array<Pair<Int, Int>>

    override fun runEachSolution() {
        init()
        solve()
    }

    fun init() = BufferedReader(InputStreamReader(System.`in`)).use { reader ->
        n = reader.readLine().toInt()
        array = with(StringTokenizer(reader.readLine())) {
            IntArray(n) {
                nextToken().toInt()
            }
        }
        m = reader.readLine().toInt()
        questions = Array(m) {
            with(StringTokenizer(reader.readLine())) {
                Pair(nextToken().toInt() - 1, nextToken().toInt() - 1)
            }
        }
    }

    fun solve() = BufferedWriter(OutputStreamWriter(System.out)).use { writer ->
        val memo = Array(n) { BooleanArray(n) }
        for (cnt in 1..n) {
            for (i in 0 until n) {
                val j = i + cnt - 1
                if (j !in 0 until n) {
                    continue
                }
                memo[i][j] = when (cnt) {
                    1 -> true
                    2 -> array[i] == array[j]
                    else -> memo[i + 1][j - 1] && array[i] == array[j]
                }
            }
        }

        questions.forEach {
            writer.append("${if (memo[it.first][it.second]) 1 else 0}\n")
        }
    }

    private fun solution2() {
        val N = readln().toInt() // 수열의 크기 (1 ≤ N ≤ 2,000)
        val arr = readln().split(" ").map { it.toInt() }.toIntArray() // (1 ≤ arr[i] ≤ 100,000)

        val dp = Array(N) { IntArray(N) { -1 } }

        val M = readln().toInt()

        for (i in 0 until M) {
            val (start, end) = readln().split(" ").map { it.toInt() }

            println(isPalindrome(arr, dp, start - 1, end - 1))
        }
    }


    private fun isPalindrome(arr: IntArray, dp: Array<IntArray>, i: Int, j: Int): Int {
        if (dp[i][j] != -1)
            return dp[i][j]

        dp[i][j] =
            if (i == j) 1
            else if (i + 1 == j) if (arr[i] == arr[j]) 1 else 0
            else if (arr[i] == arr[j] && isPalindrome(arr, dp, i + 1, j - 1) == 1) 1
            else 0

        return dp[i][j]
    }

    private fun solution(sequence: IntArray, questions: Array<IntArray>): IntArray {
        val answer = IntArray(questions.size)

        val dp1 = IntArray(sequence.size) { -1 } // 홀수 길이 팰린드롬
        val dp2 = IntArray(sequence.size) { -1 } // 짝수 길이 팰린드롬

        for ((i, question) in questions.withIndex()) {
            val (start, end) = question

            answer[i] = when ((start + end) % 2) {
                0 -> isOddPalindrome(sequence, dp1, start, end)
                1 -> isEvenPalindrome(sequence, dp2, start, end)
                else -> throw IllegalStateException()
            }
        }

        return answer
    }

    private fun isOddPalindrome(sequence: IntArray, dp: IntArray, start: Int, end: Int): Int {
        val mid = (start + end) / 2

        if (dp[mid] != -1)
            return if (end - mid <= dp[mid]) 1 else 0

        var left = mid
        var right = mid

        while (left in 1..sequence.lastIndex && right in 1..sequence.lastIndex) {
            if (sequence[left--] != sequence[right++])
                break

            dp[mid] += 1
        }

        return if (end - mid <= dp[mid]) 1 else 0
    }

    private fun isEvenPalindrome(sequence: IntArray, dp: IntArray, start: Int, end: Int): Int {
        val mid1 = (start + end) / 2
        val mid2 = mid1 + 1

        if (dp[mid1] != -1)
            return if (mid1 - start <= dp[mid1]) 1 else 0

        var left = mid1
        var right = mid2

        while (left in 1..sequence.lastIndex && right in 1..sequence.lastIndex) {
            if (sequence[left--] != sequence[right++])
                break

            dp[mid1] += 1
        }

        return if (mid1 - start <= dp[mid1]) 1 else 0
    }
}

private fun info() = BOJSolution.Info(
    no = 10942,
    title = "팰린드롬?",
    category = listOf(BOJSolution.DP),
    problemUrl = "https://www.acmicpc.net/problem/10942"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "7\n" +
                "1 2 1 3 1 2 1\n" +
                "4\n" +
                "1 3\n" +
                "2 5\n" +
                "3 3\n" +
                "5 7",
        output = "1\n" +
                "0\n" +
                "1\n" +
                "1"
    )
)