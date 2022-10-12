package programmers

import java.math.BigDecimal

class Prac2 {

    fun execute() {
        // "110010101001"	[3,8]
        // "01110"	[3,3]
        // "1111111"	[4,1]
        println("answer=${solution("110010101001")}")
        println("answer=${solution("01110")}")
        println("answer=${solution("1111111")}")
    }

    fun solution(s: String): IntArray {


        val number = BigDecimal(s)



        var answer: IntArray = intArrayOf()
        return answer
    }


    private fun String.removeZero(): Pair<String, Int> {
        return "" to 1
    }

    private fun Int.toBinary(): String {
        val sb = StringBuilder()

        var num = this
        while (num > 0) {
            sb.append(num % 2)
            num /= 2
        }

        return sb.reversed().toString()
    }
}