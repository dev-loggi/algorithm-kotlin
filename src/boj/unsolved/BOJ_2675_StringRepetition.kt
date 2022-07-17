package boj.unsolved

import boj.BOJSolution


/**
 * 2675
 * 문자열 반복
 * https://www.acmicpc.net/problem/2675
 * */
class BOJ_2675_StringRepetition : BOJSolution(info(), testCases()) {

    override fun main() {
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

private fun info() = BOJSolution.Info(
    no = 2675,
    title = "문자열 반복",
    category = arrayOf(BOJSolution.MATERIALIZATION),
    problemUrl = "https://www.acmicpc.net/problem/2675"
)

private fun testCases() = arrayOf(
    BOJSolution.TestCase(
        input = "2\n" +
                "3 ABC\n" +
                "5 /HTP",
        output ="AAABBBCCC\n" +
                "/////HHHHHTTTTTPPPPP"
    ),
)