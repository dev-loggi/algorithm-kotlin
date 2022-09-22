package programmers.kakao.y2020

import programmers.Programmers

/**
 * 2020 KAKAO BLIND RECRUITMENT
 * [No. 6] 가사 검색
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/60060
 * */
class KAKAO_BLIND_2020_P6 : Programmers.Solution {

    override fun execute() {
        testCases().forEachIndexed { index, case ->
            println("test case $index →")
            println("    words=${case.first.contentToString()}")
            println("    queries=${case.second.contentToString()}")

            val answer = solution(case.first, case.second)
            println("answer=${answer.contentToString()}")
        }
    }

    class Trie(private val type: Int) {

        companion object {
            const val FRONT = 0
            const val BACK = 1
        }

        class Node {
            val children: Array<Node?> = Array(26) { null }
            var isEndChar: Boolean = false
            var childEndCharSize = 0

            fun treeSize(depthDst: Int, depth: Int): Int {
                if (depthDst == depth)
                    return childEndCharSize

                var sum = 0

                for (child in children) {
                    if (child == null)
                        continue

                    sum += child.treeSize(depthDst, depth + 1)
                }

                return sum
            }
        }

        private val root = Node()
        private var lengthCounter = IntArray(10_001)

        fun insert(word: String) {
            var node = root

            val indices =
                if (type == FRONT) word.indices
                else word.lastIndex downTo 0

            for (i in indices) {
                val code = word[i].code - 97
                var child = node.children[code]
                if (child == null) {
                    child = Node()
                    node.children[code] = child
                }

                if (i == indices.last) {
                    node.childEndCharSize++
                    child.isEndChar = true
                }

                node = child
            }

            lengthCounter[word.length] += 1
        }

        fun getCount(query: String): Int {
            if (query[0] == '?' && query.last() == '?')
                return lengthCounter[query.length]

            val indices =
                if (type == FRONT) query.indices
                else query.lastIndex downTo 0

            var node: Node? = root

            for (i in indices) {
                val code = query[i].code - 97

                if (code == -34) {
                    return if (type == FRONT) {
                        node!!.treeSize(indices.last, i)
                    } else {
                        node!!.treeSize(indices.first, indices.first - i)
                    }
                } else {
                    node = node!!.children[code]
                }

                if (node == null)
                    return 0
            }

            return if (node?.isEndChar == true) 1 else 0
        }
    }

    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val frontTrie = Trie(Trie.FRONT)
        val backTrie = Trie(Trie.BACK)

        for (word in words) {
            frontTrie.insert(word)
            backTrie.insert(word)
        }

        val queryResultMap = HashMap<String, Int>()
        val answer = IntArray(queries.size)

        for (i in queries.indices) {
            val query = queries[i]

            var count = queryResultMap.getOrDefault(query, -1)
            if (count == -1) {
                count = if (query[0] == '?') {
                    backTrie.getCount(query)
                } else {
                    frontTrie.getCount(query)
                }
                queryResultMap[query] = count
            }

            answer[i] = count
        }

        return answer
    }
}

private fun testCases(): Array<Pair<Array<String>, Array<String>>> = arrayOf(
    // case 1
    // answer = [3, 2, 4, 1, 0, 5]
    Pair(
        first = arrayOf("frodo", "front", "frost", "frozen", "frame", "kakao"),
        second = arrayOf("fro??", "????o", "fr???", "fro???", "pro?", "?????"),
    ),

    // case 2
    // answer =
    Pair(
        first = arrayOf("aa", "ac", "az", "a"),
        second = arrayOf("?", "a?", "b", "??", "aa?", "?aa", "?c")
    ),
)