package woowahan

import common.Solution
import java.util.ArrayDeque

class WoowahanTechCampP1 : Solution {

    override fun execute() {
        var answer = 0

        answer = solution(arrayOf(
            intArrayOf(2, 100),
            intArrayOf(3, 120),
            intArrayOf(4, 180),
            intArrayOf(7, 250),
        ), 6)
        println("answer=$answer")
    }

    class Person(index: Int, arr: IntArray) {
        val id = index
        val s1 = arr[0]
        val s2 = arr[1]
        val totalScore = s1 + s2
        var rank = 0
    }

    fun solution2(board: Array<String>) {

        val result = mutableListOf<Int>()

        for (i in board.indices) {
            val tempBoard = board.map { it.toCharArray() }
            val set = hashSetOf<Char>()

            println(set)
        }
    }

    data class Point(val x: Int, val y: Int)

    // c, r
    private fun bfs(board: Array<CharArray>, c: Int, r: Int) {
        val block = board[r][c]
        val visited = Array(board.size) { Array(board.size) { false } }
        val stack = ArrayDeque<Point>()
        val range = board.indices
        visited[r][c] = true
        stack.offer(Point(c, r))

        val visit = { x: Int, y: Int ->
            if (x in range && y in range) {
                if (!visited[y][x] && board[y][x] == block) {
                    visited[y][x] = true
                    stack.offer(Point(x, y))
                }
            }
        }

        while (stack.isNotEmpty()) {
            val point = stack.poll()

            visit(point.x + 1, point.y)
            visit(point.x, point.y + 1)
            visit(point.x - 1, point.y)
            visit(point.x, point.y - 1)
        }
    }

    private fun removeBlocks(board: List<CharArray>, blockSet: HashSet<Char>) {
        for (j in board.indices) {
            for (i in board[j].indices) {
                if (blockSet.contains(board[j][i])) {
                    board[j][i] = '-'
                }
            }
        }
        board.sumOf { it.count { c-> c == '-' } }

    }

    private fun getRemovedBlockSetByColumn(board: Array<CharArray>, column: Int): HashSet<Char> {
        val set = hashSetOf<Char>()
        for (j in board.indices) {
            for (i in board[j].indices) {
                if (i == column) {
                    set.add(board[j][i])
                    break
                }
            }
        }
        return set
    }

    private fun getRemovedBlockSetByRow(board: Array<CharArray>, row: Int): HashSet<Char> {
        val set = hashSetOf<Char>()
        for (j in board.indices) {
            if (j == row) {
                set.addAll(board[j].toList())
            }
        }
        return set
    }

    fun solution(salaries: Array<IntArray>, days: Int): Int {

        val sum1 = 0
        val sum2 = 2

        listOf<Int>().toIntArray()

        val comparator = when {
            sum1 > sum2 -> Comparator<Person> { o1, o2 ->
                val compareA = o2.totalScore.compareTo(o1.totalScore)
                val compareB = o2.s1.compareTo(o1.s1)
                val compareC = o2.id.compareTo(o1.id)
                if (compareA != 0) compareA else compareB
            }
            sum1 < sum2 -> Comparator<Person> { o1, o2 ->
                val compareA = o2.totalScore.compareTo(o1.totalScore)
                val compareB = o2.id.compareTo(o1.id)
                if (compareA == 0) compareA else compareB
            }
            else -> Comparator<Person> { o1, o2 ->
                val compareA = o2.totalScore.compareTo(o1.totalScore)
                val compareB = o2.id.compareTo(o1.id)
                if (compareA == 0) compareA else compareB
            }
        }

        salaries.mapIndexed { index, ints ->  }


        return 0
    }
}