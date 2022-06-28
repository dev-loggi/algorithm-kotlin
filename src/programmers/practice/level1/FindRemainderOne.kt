package programmers.practice.level1

import common.Solution


/**
 * 나머지가 1이 되는 수 찾기
 * https://programmers.co.kr/learn/courses/30/lessons/87389?language=kotlin
 * */
class FindRemainderOne : Solution {

    override fun execute() {
        solution(10).let { println(it) } // 3
        solution(12).let { println(it) } // 11
    }

    fun solution(n: Int): Int {
        var x = 1
        for (i in 1..n) {
            if (n % i == 1) {
                x = i
                break
            }
        }

        return x
    }
}