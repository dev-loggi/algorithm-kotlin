package programmers.practice.level2

import programmers.Programmers

/**
 * 연속 부분 수열 합의 개수
 * https://school.programmers.co.kr/learn/courses/30/lessons/131701
 * */
class NumberOfConsecutiveSubsequencesSums : Programmers.Solution {

    override fun execute() {
        println(solution(intArrayOf(7,9,1,1,4)))
    }

    fun solution(elements: IntArray): Int {
        val sumSet = mutableSetOf<Int>()
        val arr = elements + elements

        for (len in 1..elements.size) for (start in 0 until elements.size) {
            var sum = 0

            for (i in start until start + len)
                sum += arr[i]

            sumSet.add(sum)
        }

        return sumSet.size
    }
}