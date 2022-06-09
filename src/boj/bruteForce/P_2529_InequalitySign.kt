package boj.bruteForce

import common.Solution


/**
 * 2529
 * 부등호
 * Brute Force(완전 탐색)
 * https://www.acmicpc.net/problem/2529
 * */
class P_2529_InequalitySign : Solution {

    override fun execute() {
        // input
        // 2
        // < >

        // output
        // 897
        // 021

        // input
        // 9
        // > < < < > > > < <
        // 9876543210

        // output
        // 9567843012
        // 1023765489

        for (i in 0 until 2) main()
    }

    fun main() {
        val k = readLine()?.toIntOrNull() ?: return
        val operators = readLine()?.split(" ") ?: return

        if (!checkCondition(k, operators))
            return

        // 최댓값
        Array(k + 1) { 9 - it } // 9, 8, 7 ...
            .sortWith(operators)
            .joinToString("")
            .let { println(it) }

        // 최솟값
        Array(k + 1) { it } // 0, 1, 2 ...
            .sortWith(operators)
            .joinToString("")
            .let { println(it) }
    }

    private fun checkCondition(k: Int, operators: List<String>): Boolean {
        return k in 2..9 && k == operators.size && operators.all { it == ">" || it == "<" }
    }

    private fun Array<Int>.sortWith(operators: List<String>) = apply {
        for (i in operators.indices) {
            for (j in 0..i) {
                swapIf(operators[j], j, j + 1)
//                println("(i=$i, j=$j, op=${operators[j]}): ${this.contentToString()}")
            }
//            println("")
        }
        for (i in operators.lastIndex downTo 0) {
            for (j in operators.lastIndex downTo i) {
                swapIf(operators[j], j, j + 1)
//                println("(i=$i, j=$j, op=${operators[j]}): ${this.contentToString()}")
            }
//            println("")
        }
    }

    private fun Array<Int>.swapIf(op: String, i: Int, j: Int) {
        when (op) {
            ">" -> if (this[i] < this[j]) swap(i, j)
            "<" -> if (this[i] > this[j]) swap(i, j)
        }
    }

    private fun Array<Int>.swap(i: Int, j: Int) {
        val tmp = this[i]
        this[i] = this[j]
        this[j] = tmp
    }

}