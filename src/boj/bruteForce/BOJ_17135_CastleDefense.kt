package boj.bruteForce

import common.Solution
import kotlin.math.abs

/**
 * 17135
 * 캐슬 디펜스
 * 설명: 성에 배치된 궁수로 적을 쓰러뜨리는 게임
 * https://www.acmicpc.net/problem/17135
 * 완전 탐색
 * */
class BOJ_17135_CastleDefense : Solution {

    override fun execute() {
        repeat(6) { main() }
    }

    fun main() {
        val input = readLine()!!.split(" ")
        val N = input[0].toInt() // 격자판 세로 크기(3 ≤ N ≤ 15)
        val M = input[1].toInt() // 격자판 가로 크기(3 ≤ M ≤ 15)
        val D = input[2].toInt() // 궁수 사거리(1 ≤ D ≤ 10)

        val map = (0 until N).map {
            readLine()!!.split(" ").map { it.toInt() }.toIntArray()
        }.toTypedArray()

        solution(N, M, D, map)
    }

    fun solution(N: Int, M: Int, D: Int, originMap: Array<IntArray>) {
        val answer = Array(M) { i -> intArrayOf(N, i) }
            .combination(3)
            .maxOf { archers ->
                val map = originMap.deepCopy()
                var count = 0

                do {
                    count += map.attacks(archers, D)
                    //println("acc count=$count")
                    readLine()
                } while (map.next())

                //println("★★총 count=$count")
                count
            }

        println(answer)
    }

    fun Array<IntArray>.deepCopy(): Array<IntArray> {
        return Array(size) { r -> IntArray(this[r].size) { c -> this[r][c] } }
    }

    fun Array<IntArray>.next(): Boolean {
        var isExists = false

        for (i in lastIndex downTo 0) {
            this[i] = if (i > 0) {
                this[i - 1]
            } else {
                IntArray(this[i].size) { 0 }
            }

            if (!isExists) {
                isExists = this[i].any { it == 1 }
            }
        }

        return isExists
    }

    fun Array<IntArray>.attacks(archers: List<IntArray>, D: Int): Int {
        val map = this

        // Array[n][d][r][c]
        // n: 궁수 번호, d: 적군과의 거리, r: row, c: column
        val everyoneEnemies: Array<Array<MutableList<IntArray>>> =
            Array(archers.size) {
                Array(D) { mutableListOf() }
            }

        for (r in lastIndex downTo 0) for (c in map[r].indices) {
            if (map[r][c] < 1)
                continue

            // 궁수별 적군과의 거리가 같은 것끼리 분류
            for (i in archers.indices) {
                val archer = archers[i]
                val d = abs(r - archer[0]) + abs(c - archer[1])

                if (d > D)
                    continue

                everyoneEnemies[i][d - 1].add(intArrayOf(r, c))
            }
        }

        for (eachEnemies in everyoneEnemies) { // 궁수별 적군들
            for (enemiesByDistance in eachEnemies) { // 궁수별 거리별 적군들
                if (enemiesByDistance.isEmpty())
                    continue

                // 거리가 가장 가깝고, 가장 왼쪽에 있는 적군
                val firstEnemy = enemiesByDistance.minByOrNull { it[1] }!!
                //enemiesByDistance.map { it.joinToString(" ") }.let { println(it) }

                map[firstEnemy[0]][firstEnemy[1]]++
                break
            }
        }

        //map.log()
        //archers.log(map[0].size)

        var count = 0
        for (r in indices) for (c in map[r].indices) {
            if (map[r][c] < 2) // 궁수에게 1회 이상 공격받은 적군
                continue

            map[r][c] = 0
            count++
        }

        //println("a=$count")


        return count
    }

    fun <T> Array<T>.combination(r: Int): List<List<T>> {
        val result = mutableListOf<List<T>>()

        fun recursive(depth: Int, start: Int, list: List<T>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                val newList = list.toMutableList()
                    .also { it.add(this[i]) }

                recursive(depth + 1, i + 1, newList)
            }
        }

        recursive(0, 0, emptyList())
        return result
    }

    fun Array<IntArray>.log() {
        forEach { println(it.map { n -> if (n >= 1) n.digitToChar() else '.' }.joinToString("")) }
    }

    fun List<IntArray>.log(n: Int) {
        val arr = CharArray(n) { '.' }
        forEach {
            arr[it[1]] = 'G'
        }
        println(arr.joinToString(""))
    }
}
/*
[case1]
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
[case1 answer]
3

[case2]
5 5 1
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
[case2 answer]
3

[case3]
5 5 2
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 1 1 1 1
0 0 0 0 0
[case3 answer]
5

[case4]
5 5 5
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
1 1 1 1 1
[case4 answer]
15

[case5]
6 5 1
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
[case5 answer]
9

[case6]
6 5 2
1 0 1 0 1
0 1 0 1 0
1 1 0 0 0
0 0 0 1 1
1 1 0 1 1
0 0 1 0 0
[case6 answer]
14

[case7]
10 10 5
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1


* */