package programmers.practice.level3

import programmers.Programmers.Solution
import java.util.Queue
import java.util.LinkedList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * 단어 변환
 * DFS/BFS
 * https://programmers.co.kr/learn/courses/30/lessons/43163
 * */
class WordConversion : Solution {

    override fun execute() {
        var answer = 0

        answer = solution("hit", "cog", arrayOf("hot", "dot", "dog", "lot", "log", "cog"))
        println("answer=$answer")

        answer = solution("hit", "cog", arrayOf("hot", "dot", "dog", "lot", "log"))
        println("answer=$answer")
    }

    fun solution(begin: String, target: String, words: Array<String>): Int {
        if (!words.contains(target))
            return 0

        val wordGraph = makeWordGraph(
            words.toMutableList().apply { add(begin) }
        )
        println("wordGraph=$wordGraph")

        return traverseWordGraph(begin, target, wordGraph)
    }

    private fun traverseWordGraph(begin: String, target: String, wordGraph: HashMap<String, HashSet<String>>): Int {
        val queue: Queue<String> = LinkedList<String>().apply { add(begin) }
        val set = hashSetOf(begin)
        val depths = hashMapOf(Pair(begin, 0))

        while (queue.isNotEmpty()) {
            val word = queue.poll()

            wordGraph[word]?.forEach {
                if (!set.contains(it)) {
                    queue.add(it)
                    set.add(it)
                    depths[it] = depths[word]!! + 1
                }
            }

            if (depths[target] != null)
                break
        }

        return depths[target] ?: 0
    }

    private fun makeWordGraph(words: List<String>): HashMap<String, HashSet<String>> =
        hashMapOf<String, HashSet<String>>().also { graph ->
            words.forEach { word ->
                graph[word] = hashSetOf<String>().also { set ->
                    words.filter { it.matchesAllButOneLetter(word) }
                        .also { set.addAll(it) }
                }
            }
        }

    private fun String.matchesAllButOneLetter(other: String): Boolean {
        var count = 0
        for (i in indices) if (this[i] != other[i]) count++
        return count == 1
    }
}