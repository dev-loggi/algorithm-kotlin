package programmers.practice.level1

import programmers.Programmers.Solution


/**
 * 약수의 갯수와 덧셈
 * https://programmers.co.kr/learn/courses/30/lessons/77884?language=kotlin
 * */
class NumberOfDivisorsAndAddition : Solution {

    override fun execute() {
        solution(13, 17).let { println(it) } // 43
        solution(24, 27).let { println(it) } // 52
    }

    fun solution(left: Int, right: Int): Int {
        return (left..right).sumOf { it * it.divisorsEvenOrOdd() }
    }

    fun Int.divisorsEvenOrOdd(): Int {
        val sqrt = Math.sqrt(this.toDouble()).toInt()
        var count = 0
        for (i in 1..sqrt) if (this % i == 0) count++
        count *= 2
        if (sqrt * sqrt == this) count++
        return if (count % 2 == 0) 1 else -1
    }
}