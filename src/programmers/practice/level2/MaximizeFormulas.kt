package programmers.practice.level2

import Solution
import kotlin.math.absoluteValue


/**
 * 수식 최대화
 * https://programmers.co.kr/learn/courses/30/lessons/67257?language=kotlin
 * */
class MaximizeFormulas : Solution {

    override fun execute() {
        solution("100-200*300-500+20").let { println("answer=$it") } // 60420
        solution("50*6-3*2").let { println("answer=$it") } // 300
    }

    fun solution(expression: String): Long {
        val answers = mutableListOf<Long>()

        val operators = expression.filter { it == '*' || it == '+' || it == '-' }
            .toCharArray()
            .also { println(it.contentToString()) }

        val numbers = expression.split("*", "+", "-")
            .map { it.toLong() }
            .also { println(it) }

        operators.distinct()
            .permutation()
            .forEach { priority: List<Char> ->
                println("priority=$priority")
                val nums = numbers.toMutableList()
                val ops = operators.toMutableList()

                priority.forEach { op: Char ->
                    var i = 0
                    while (i < ops.size) {
                        if (ops[i] != op) {
                            i++
                            continue
                        }

                        val res = nums.calculate(op, i, i + 1)
                        nums[i] = res
                        nums.removeAt(i + 1)
                        ops.removeAt(i)
                        println("i=$i, op=$op, $nums, $ops")
                    }
                }
                answers.add(nums[0].absoluteValue)
            }

        println("answers=$answers")
        return answers.maxOf { it }
    }

    private fun <T> List<T>.permutation(): List<List<T>> {
        fun <T> recursive(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el): List<List<T>> {
            return if (sub.isEmpty()) listOf(fin)
            else sub.flatMap { recursive(el, fin + it, sub - it) }
        }
        return recursive(this)
    }

    private fun MutableList<Long>.calculate(op: Char, i: Int, j: Int): Long {
        return when (op) {
            '*' -> this[i] * this[j]
            '+' -> this[i] + this[j]
            '-' -> this[i] - this[j]
            else -> throw IllegalArgumentException("op=$op, i=$i, j=$j")
        }
    }
}