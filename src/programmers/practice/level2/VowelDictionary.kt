package programmers.practice.level2

import programmers.Programmers
import java.util.*

/**
 * 모음 사전
 * https://school.programmers.co.kr/learn/courses/30/lessons/84512
 *
 * DFS, 전위순회
 * */
class VowelDictionary : Programmers.Solution {

    override fun execute() {
        solution("AAAAE").let { println(it) }
        solution("AAAE").let { println(it) }
        solution("I").let { println(it) }
        solution("EIO").let { println(it) }
    }

    private val dictionary: Array<String> = makeDictionary()

    fun solution(word: String): Int {
        return dictionary.indexOf(word)
    }

    private fun makeDictionary(): Array<String> {
        val vowels: Array<Char> = arrayOf('A', 'E', 'I', 'O', 'U')
        val list = LinkedList<String>()
        list.add("")

        fun dfs(depth: Int, word: String) {
            if (depth == 5)
                return

            for (vowel in vowels) {
                val nextWord = word + vowel

                list.add(nextWord)
                dfs(depth + 1, nextWord)
            }
        }

        dfs(0, "")

        return list.toTypedArray()
    }
}