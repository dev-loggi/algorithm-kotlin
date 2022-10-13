package programmers.practice.level2

import programmers.Programmers
import java.util.ArrayDeque

/**
 * 큰 수 만들기
 * https://school.programmers.co.kr/learn/courses/30/lessons/42883
 *
 * 스택(Stack)
 * */
class MakingBigNumbers : Programmers.Solution {

    override fun execute() {
        println(solution("1924", 2)) // "94"
        println(solution("1231234", 3)) // "3234"
        println(solution("4177252841", 4)) // "775841"

        println(solution("12", 1)) // "2"
        println(solution("21", 1)) // "2"

        println(solution("123", 1)) // "23"
        println(solution("321", 1)) // "32"

        println(solution("123456789", 4)) // "23"
        println(solution("987654321", 4)) // "23"
    }

    fun solution(number: String, k: Int): String {
        val stack = ArrayDeque<Char>()
        var count = k

        for (n in number) {
            while (stack.isNotEmpty() && stack.first < n && 0 < count) {
                stack.pop()
                count -= 1
            }
            stack.push(n)
        }

        while (0 < count--) {
            stack.pop()
        }

        return buildString {
            while (stack.isNotEmpty()) {
                append(stack.removeLast())
            }
        }
    }

    fun solution2(number: String, k: Int): String {
        val exclude = BooleanArray(number.length)
        var count = k
        var i = 0

        while (count > 0) {
            if (!number.isMaxOf(number[i], i, count)) {
                exclude[i] = true
                count -= 1
            }

            i += 1
        }

        val sb = StringBuilder()
        for (i in number.indices) {
            if (exclude[i])
                continue

            sb.append(number[i])
        }

        return sb.toString()
    }

    private fun String.isMaxOf(n: Char, start: Int, length: Int): Boolean {
        // 지워야 할 개수가 뒤에 남은 숫자들 보다 많이 남은 경우
        if (lastIndex < start + length)
            return false

        for (j in (start + 1)..(start + length)) {
            if (n < this[j])
                return false
        }

        return true
    }

    fun solution3(number: String, k: Int): String {
        val r = number.length - k
        val compressed = number.compress(r)
        val visited = BooleanArray(compressed.length)
        var maxNumber = "0"

        fun dfs(depth: Int, start: Int, numArr: CharArray) {
            if (depth == r) {
                val num = numArr.joinToString("")
                if (maxNumber < num)
                    maxNumber = num
                return
            }

            for (i in start..number.lastIndex) {
                if (visited[i])
                    continue

                numArr[depth] = compressed[i]
                visited[i] = true
                dfs(depth + 1, i + 1, numArr)
                visited[i] = false
            }
        }

        dfs(0, 0, CharArray(r))
        return maxNumber
    }

    private fun String.compress(k: Int): String {
        val count = IntArray(10)
        val sb = StringBuilder()

        for (c in this) {
            val n = c.digitToInt()

            if (count[n] >= k)
                continue

            count[n] += 1
            sb.append(c)
        }

        return sb.toString()
    }
}