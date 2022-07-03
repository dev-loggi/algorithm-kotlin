package programmers.practice.level2

import Solution


/**
 * 멀쩡한 사각형
 * https://programmers.co.kr/learn/courses/30/lessons/62048?language=kotlin
 * */
class PlainSquare : Solution {

    override fun execute() {
        solution(8, 12).let { println("answer=$it") }
    }

    fun solution(w: Int, h: Int): Long {

        val gcd = gcd(w, h)

        // (w, h)의 서로소 = (a, b)
        val a = w / gcd
        val b = h / gcd

        // 반복되는 패턴의 최소 단위 직사각형
        // 내의 지워진 단위 사각형의 갯수
        val unit = a + (b - 1)

        return w * h.toLong() - unit * gcd.toLong()
    }

    tailrec fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a
        else gcd(b, a % b)
    }
}