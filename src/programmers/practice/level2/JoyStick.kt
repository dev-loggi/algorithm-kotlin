package programmers.practice.level2

import Solution


/**
 * 조이스틱
 * https://programmers.co.kr/learn/courses/30/lessons/42860?language=kotlin
 * */
class JoyStick : Solution {

    override fun execute() {
        //"JEROEN"	56
        //"JAN"	23
        solution("JEROEN").let { println("answer=$it") } // 56
        solution("JAN").let { println("answer=$it") } // 23
    }

    fun solution(name: String): Int {
        println("solution(): $name")



        val builder = StringBuilder(Array(name.length) { 'A' }.joinToString(""))

        name.sumOf { it.code}
        val visited = BooleanArray(5) { false }

        visited.all { it }

        var answer = 0
        return answer
    }
}