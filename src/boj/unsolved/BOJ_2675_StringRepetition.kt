package boj.unsolved

import Solution
import boj.BOJSolution


/**
 * 2675
 * 문자열 반복
 * https://www.acmicpc.net/problem/2675
 * */
class BOJ_2675_StringRepetition : BOJSolution() {

    override val info = _info
    override val testCases = _testCases

    override fun executeTestCases() {
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

private val _info = BOJSolution.Info(
    no = 2675,
    title = "문자열 반복",
    category = arrayOf(BOJSolution.MATERIALIZATION),
    url = "https://www.acmicpc.net/problem/2675"
)

private val _testCases = arrayOf(
    BOJSolution.TestCase(
        input = "2\n" +
                "3 ABC\n" +
                "5 /HTP",
        output ="AAABBBCCC\n" +
                "/////HHHHHTTTTTPPPPP"
    ),
)