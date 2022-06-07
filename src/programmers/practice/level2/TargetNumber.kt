package programmers.practice.level2

import common.Solution

/**
 * 타겟 넘버
 * DFS/BFS
 * https://programmers.co.kr/learn/courses/30/lessons/43165
 * */
class TargetNumber : Solution {

    override fun execute() {
        var answer: Int = solution(intArrayOf(1, 1, 1, 1, 1), 3)
        println("answer=$answer\n") // 5

        answer = solution(intArrayOf(4, 1, 2, 3), 4)
        println("answer=$answer\n") // 2

        answer = solution(intArrayOf(1, 1, 4, 1, 2, 3), 4)
        println("answer=$answer\n") // 7

        answer = solution2(intArrayOf(1, 1, 1, 1, 1), 3)
        println("answer=$answer\n") // 5

        answer = solution2(intArrayOf(4, 1, 2, 3), 4)
        println("answer=$answer\n") // 2

        answer = solution2(intArrayOf(1, 1, 4, 1, 2, 3), 4)
        println("answer=$answer\n") // 7
    }

    fun solution(numbers: IntArray, target: Int): Int {
        // 내림차순 정렬해야 경우의 수가 줄어듦.
        return traverseByDFS(numbers.sortedArrayDescending(), target, +1, -1, 0)
    }

    private fun traverseByDFS(numbers: IntArray, target: Int, sign: Int, index: Int, sum: Int): Int {
        val newSum = sum + sign * (numbers.getOrNull(index) ?: 0) // 새로운 합

        if (index == numbers.lastIndex) { // 마지막 number 까지 연산 완료.
            return if (newSum == target) 1
            else 0
        }

        var count = 0
        val sumOfRest = numbers.drop(index + 1).sum()
        if (newSum !in target - sumOfRest..target + sumOfRest) // 남은 숫자들의 합을 통해 불가능 여부 미리 예측
            return 0

        count += traverseByDFS(numbers, target, +1, index + 1, newSum)
        count += traverseByDFS(numbers, target, -1, index + 1, newSum)

        return count
    }

    fun solution2(numbers: IntArray, target: Int): Int {
        val result = numbers.sortedArrayDescending().fold(listOf(0)) { list, num ->
            list.run { map { it + num } + map { it - num } }
        }

        println("size=${result.size}, $result")
        return result.count { it == target }
    }
}