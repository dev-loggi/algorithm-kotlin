package programmers.skillCheck.level1

import kotlin.math.sqrt

/**
 * [문제]
 * 주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 숫자들이 들어있는 배열 nums가 매개변수로 주어질 때,
 * nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한사항]
 * nums에 들어있는 숫자의 개수는 3개 이상 50개 이하입니다.
 * nums의 각 원소는 1 이상 1,000 이하의 자연수이며, 중복된 숫자가 들어있지 않습니다.
 */
class CombinationAndPrimeNumber {

    fun solution(numbers: IntArray): Int {
        var answer = 0

        combination(numbers, 3) { array ->
            if (isPrimeNumber(array.sum())) answer++
        }

        combination3(numbers) { array ->
            if (isPrimeNumber(array.sum())) answer++
        }

        return answer
    }

    // 0, 1, 2, 3, 4
    // 1, 2, 3, 4, 5
    // 1, 2, 3, 4, 5, 6, 7
    private fun combination3(array: IntArray, action: (array: IntArray) -> Unit) {
        var x = 0
        var y = 1
        var z = 2


    }

    private fun combination(array: IntArray, count: Int, action: (array: IntArray) -> Unit) {

    }

    private fun isPrimeNumber(number: Int): Boolean {
        var count = 0
        val sqrtNumber = sqrt(number.toFloat()).toInt()
        for (i in 1..sqrtNumber) {
            if (number % i == 0) count++
            if (count == 2 && i < sqrtNumber) return false
        }

        return count == 2
    }
}