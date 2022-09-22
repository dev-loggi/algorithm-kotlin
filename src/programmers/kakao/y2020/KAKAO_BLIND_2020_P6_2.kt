package programmers.kakao.y2020

import programmers.Programmers
import java.util.LinkedList
import java.util.ArrayDeque

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 6] 가사 검색
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60060
 * */
class KAKAO_BLIND_2020_P6_2 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("test case $index →")
            println("    words=${case.first.contentToString()}")
            println("    queries=${case.second.contentToString()}")

            val answer = solution(case.first, case.second)
            println("answer=${answer.contentToString()}")
        }
    }

    class Trie {

        class Node {
            val children: Array<Node?> = Array(26) { null }
            var isEnd = false
        }

        private val root = Node()

        fun insert(word: String) {
            var node = root

            val lastIndex = word.lastIndex
            for (i in word.indices) {
                val code = word[i].code - 97
                var child = node.children[code]

                if (child == null) {
                    child = Node()
                    node.children[code] = child
                }

                if (i == lastIndex)
                    child.isEnd = true

                node = child
            }
        }

        fun find(query: String): Int {
            var count = 0
            val lastIndex = query.lastIndex

            val queue = ArrayDeque<Node>()
            queue.offer(root)

            for (i in query.indices) {
                val code = query[i].code - 97

                for (q in queue.indices) {
                    val node = queue.poll()

                    if (code == -34) {
                        if (i == lastIndex) {
                            for (child in node.children) {
                                if (child?.isEnd == true)
                                    count++
                            }
                        } else {
                            for (child in node.children) {
                                if (child != null)
                                    queue.offer(child)
                            }
                        }
                    } else {
                        if (i == lastIndex) {
                            val child = node.children[code]
                            if (child?.isEnd == true)
                                count += 1
                        } else {
                            val child = node.children[code]
                            if (child != null) {
                                queue.offer(child)
                            }
                        }

                    }
                }
            }

            return count
        }
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val trie = Trie()
        words.forEach { trie.insert(it) }

        val queryMap = hashMapOf<String, Int>()
        val answer = IntArray(queries.size)

        for (i in queries.indices) {
            val query = queries[i]

            var count = queryMap[query]
            if (count == null) {
                count = trie.find(query)
                queryMap[query] = count
            }

            answer[i] = count
        }

        return answer
    }
}

private fun testCases(): Array<Pair<Array<String>, Array<String>>> = arrayOf(
    // case 1
    // answer = [3, 2, 4, 1, 0]
    Pair(
        first = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
        second = arrayOf("fro??", "????o", "fr???", "fro???", "pro?"),
    ),

    // case 2
    // answer =
    Pair(
        first = arrayOf("aa", "ac", "az", "a"),
        second = arrayOf("?", "a?", "b", "??", "aa?", "?aa", "?c")
    ),
)