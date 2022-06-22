package boj.bruteForce

import common.Solution

/**
 * 16637
 * 괄호 추가하기
 * https://www.acmicpc.net/problem/16637
 * 완전 탐색
 * */
class BOJ_16637_AddParentheses : Solution {

    override fun execute() {
        repeat(6) { main() }
    }

    fun main() {
        val N = readLine()!!.toInt()
        val equation = readLine()!!.toCharArray()

        solution(N, equation)
    }

    fun solution(N: Int, equation: CharArray) {
        val op = charArrayOf('+', '-', '*')
        val operandList = equation.fold(mutableListOf<Long>()) { list, c ->
            if (c.isDigit()) list.add(c.digitToInt().toLong())
            list
        }

        val operatorList = equation.fold(mutableListOf<Char>()) { list, c ->
            if (c in op) list.add(c)
            list
        }

        println(operandList)
        println(operatorList)

        val orders = IntArray(operatorList.size) { it }

        val maxResult = (0..operatorList.size)
            .map { r -> orders.combination(r, 2) } // operator 에서 r개를 2 step 씩 뛰어서 뽑기
            .flatten()
            .map { order: List<Int> -> // order: 괄호로 덮힌 operator 의 인덱스들
                val operands = operandList.toMutableList()
                val operators = operatorList.toMutableList()

                // 괄호 먼저 계산
                for (i in order.asReversed()) {
                    val res = calculate(operands[i], operands[i + 1], operators[i])

                    operands[i] = res
                    operands.removeAt(i + 1)
                    operators.removeAt(i)
                }

                // 나머지 계산
                var acc = operands[0]
                for (i in operators.indices) {
                    acc = calculate(acc, operands[i + 1], operators[i])
                }

                println("order=$order, acc=$acc")
                acc
            }
            .maxOfOrNull { it } ?: 0

        println(maxResult)
    }

    fun IntArray.combination(r: Int, step: Int): List<List<Int>> {
        val result = mutableListOf<List<Int>>()

        fun recursive(depth: Int, start: Int, list: List<Int>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }

                recursive(depth + 1, i + step, newList)
            }
        }

        recursive(0, 0, emptyList())
        return result
    }

    fun calculate(a: Long, b: Long, op: Char): Long {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            else -> throw IllegalArgumentException("op=$op")
        }
    }
}

/*
[case1]
9
3+8*7-9*2
[case1 answer]
136

[case2]
5
8*3+5
[case2 answer]
64

[case3]
7
8*3+5+2
[case3 answer]
66

[case4]
19
1*2+3*4*5-6*7*8*9*0
[case4 answer]
0

[case5]
19
1*2+3*4*5-6*7*8*9*9
[case5 answer]
426384

[case6]
19
1-9-1-9-1-9-1-9-1-9
[case6 answer]
24


4
2+2+2+2

* */