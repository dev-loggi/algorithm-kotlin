package programmers.practice.level2

import programmers.Programmers.Solution
import kotlin.math.sqrt


/**
 * 소수 찾기
 * 완전 탐색
 * https://programmers.co.kr/learn/courses/30/lessons/42839?language=kotlin
 * */
class FindPrimeNumber : Solution {

    override fun execute() {
        println("answer=${solution("17")}") // 3
        println("answer=${solution("011")}") // 2

//        val str = "00101"
//        println("$str=${str.toInt()}")
//        var arr = charArrayOf('1', '0','1','3')
//        println("arr=${arr.contentToString()}")
//        println("arr=${arr.joinToString("")}")
    }

    fun solution(numbers: String): Int {
        val set = hashSetOf<Int>()

        recursiveDFS(numbers.toCharArray(), "", set)

        println("set=$set")

        return set.count { isPrimeNumber(it) }
    }

    private fun recursiveDFS(numbers: CharArray, numberString: String, out: HashSet<Int>) {
        if (numberString.isNotBlank()) {
            out.add(numberString.toInt())
        }

        numberString
            .fold(numbers.toMutableList()) { list, ch ->
                list.remove(ch)
                list
            }
            .forEach { ch ->
                recursiveDFS(numbers, numberString + ch, out)
            }
    }

    private fun isPrimeNumber(number: Int): Boolean {
        return if (number <= 2) (number == 2)
        else {
            for (i in 2..sqrt(number.toDouble()).toInt() + 1) {
                if (number % i == 0) return false
            }
            true
        }
    }
}