package boj

import common.Solution


/**
 * 2675
 * 문자열 반복
 * https://www.acmicpc.net/problem/2675
 * */
class P_2675_StringRepetition : Solution {

    override fun execute() {
        main()
    }

    fun main() {
        val case = readLine()?.toIntOrNull() ?: return
        val repeats = mutableListOf<Int>()
        val stringList = mutableListOf<String>()

        for (i in 0 until case) {
            val line = readLine()?.split(" ") ?: continue
            val repeat = line[0].toIntOrNull() ?: continue
            val str = line[1]

            repeats.add(repeat)
            stringList.add(str)
        }

        for (i in stringList.indices) {
            val repeat = repeats[i]
            val str = stringList[i]

            str.map { c -> List(repeat) { c } }
                .flatten()
                .joinToString("")
                .let { println(it) }
        }
    }
}