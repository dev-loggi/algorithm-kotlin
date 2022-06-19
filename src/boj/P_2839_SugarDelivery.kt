package boj

import common.Solution

/**
 * 2839
 * 설탕배달
 * https://www.acmicpc.net/problem/2839
 * DP
 * */
class P_2839_SugarDelivery : Solution {

/*
[case1]
18
[case1 answer]
4

[case2]
4
[case2 answer]
-1

[case3]
6
[case3 answer]
2

[case4]
9
[case4 answer]
3

[case5]
11
[case5 answer]
3
*/

    override fun execute() {
        for (i in 0 until 5) main()
    }

    fun main() {
        val N = readLine()?.toIntOrNull() ?: return

        // N = 5 * a + 3 * b
        var a = N / 5
        var b = 0

        while (a >= 0) {
            b = (N - 5 * a) / 3

            println("a=$a, b=$b: ${5 * a} + ${3 * b} =? $N")

            if (5 * a + 3 * b == N) {
                break
            } else {
                a -= 1
            }
        }

        if (a >= 0) {
            println(a + b)
        } else {
            println(-1)
        }
    }
}