package programmers.practice.level2

import programmers.Programmers

/**
 * N개의 최소공배수
 * https://school.programmers.co.kr/learn/courses/30/lessons/12953
 * */
class Nlcm : Programmers.Solution {

    override fun execute() {
        solution(intArrayOf(2, 6, 8, 14)).let { println(it) }
        solution(intArrayOf(1, 2, 3)).let { println(it) }
    }

    fun solution(arr: IntArray): Int {
        var lcm = 1

        for (num in arr) {
            lcm = lcm(lcm, num)
        }

        return lcm
    }

    fun lcm(a: Int, b: Int): Int {
        return a * b / gcm(a, b)
    }

    tailrec fun gcm(a: Int, b: Int): Int {
        return if (b == 0) a
        else gcm(b, a % b)
    }
}