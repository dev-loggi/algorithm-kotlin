package kakao.y2021

import common.Solution
import java.util.*

// https://tech.kakao.com/2021/07/08/2021-%EC%B9%B4%EC%B9%B4%EC%98%A4-%EC%9D%B8%ED%84%B4%EC%8B%AD-for-tech-developers-%EC%BD%94%EB%94%A9-%ED%85%8C%EC%8A%A4%ED%8A%B8-%ED%95%B4%EC%84%A4/
// https://programmers.co.kr/learn/courses/30/lessons/81303
class KakaoInternship2021p3 : Solution {

    override fun execute() {
        var answer = ""

        // 8, 2
        // ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"]
        answer = solution(8, 2, arrayOf(
            "D 2","C","U 3","C","D 4","C","U 2","Z","Z"
        ))
        println("answer=$answer\n") // "OOOOXOOO"

        // 8, 2
        // ["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"]
        answer = solution(8, 2, arrayOf(
            "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
        ))
        println("answer=$answer") // "OOXOXOOO"
    }

    data class Record(val id: Int) {
        var lastIndex: Int = -1

        override fun toString(): String = id.toString()
    }

    fun solution(n: Int, k: Int, commands: Array<String>): String {

        val table = (0 until n).map { Record(it) }
            .toMutableList()
        val stack = ArrayDeque<Record>()

        var curPosition = k

        println("start: n=$n, pos=$curPosition")
        commands.forEach { command ->
            when (command[0]) {
                'U' -> { // 업
                    curPosition -= command.split(" ")[1].toInt()
                    if (curPosition < 0) curPosition = 0
                    print("${command[0]} ${command.split(" ")[1].toInt()}: pos=$curPosition")
                    println(" $table")
                }
                'D' -> { // 다운
                    curPosition += command.split(" ")[1].toInt()
                    if (curPosition > table.lastIndex) curPosition = table.lastIndex
                    print("${command[0]} ${command.split(" ")[1].toInt()}: pos=$curPosition")
                    println(" $table")
                }
                'C' -> { // 삭제
                    table.removeAt(curPosition).let {
                        it.lastIndex = curPosition
                        stack.offer(it)
                    }

                    if (curPosition > table.lastIndex) {
                        curPosition--
                    }
                    print("${command[0]}: pos=$curPosition")
                    println(" $table")
                }
                'Z' -> { // 복구
                    stack.poll().let {
                        table.add(it.lastIndex, it)
                        if (it.lastIndex <= curPosition) {
                            curPosition++
                        }
                    }
                    print("${command[0]}: pos=$curPosition")
                    println(" $table")
                }
            }
        }

        val deletedRecordSet = stack.map { it.id }
            .toHashSet()

        return Array(n) {
            if (deletedRecordSet.contains(it)) 'X'
            else 'O'
        }.joinToString("")
    }

//    fun solution(n: Int, k: Int, commands: Array<String>): String {
//        var answer: String = ""
//
//
//        val table = Array(n) { true }
//        var curPosition = k
//
//        val wasteStack = ArrayDeque<Int>()
//
//        commands.forEach { command ->
//            when (command[0]) {
//                'U' -> { // 업
//                    movePosition(table, -command.split(" ")[1].toInt())
//                }
//                'D' -> { // 다운
//                    movePosition(table, command.split(" ")[1].toInt())
//                }
//                'C' -> { // 삭제
//
//                }
//                'Z' -> { // 복구
//
//                }
//            }
//        }
//
//        return answer
//    }
//
//    private fun movePosition(table: Array<Boolean>, curPosition: Int, n: Int) {
//        var result = curPosition
//        val range =
//            if (n > 0) curPosition + 1.. curPosition + n
//            else curPosition downTo curPosition + n
//
//        for (i in range) {
//            if (table[i]) result++
//
//        }
//    }
}