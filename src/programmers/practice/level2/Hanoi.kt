package programmers.practice.level2

import programmers.Programmers
import java.util.*

class Hanoi : Programmers.Solution {

    override fun execute() {
        solution(2).forEachIndexed { idx, arr -> println("[${idx + 1}]: ${arr.contentToString()}") }
        solution(3).forEachIndexed { idx, arr -> println("[${idx + 1}]: ${arr.contentToString()}") }
    }

    fun solution(n: Int): Array<IntArray> {
        return hanoi(n, 1, 3, 2)
    }

    fun hanoi(n: Int, from: Int, to: Int, via: Int): Array<IntArray> {
        return if (n == 1) arrayOf(intArrayOf(from, to))
        else hanoi(n - 1, from, via, to) + arrayOf(intArrayOf(from, to)) + hanoi(n - 1, via, to, from)
    }

    private fun hanoi2(n: Int): Array<IntArray> {
        println("hanoi($n)")
        val list = LinkedList<IntArray>()

        fun move(n: Int, from: Int, to: Int, via: Int, tab: String) {
            println("${tab}move($n): $from -> $to")
            if (n == 1) {
                list.add(intArrayOf(n, from, to))
                return
            }

            move(n - 1, from, via, to, "$tab    ")
            list.add(intArrayOf(n, from, to))
            move(n - 1, via, to, from, "$tab    ")
        }

        move(n, 1, 3, 2, "")
        return list.toTypedArray()
    }
}