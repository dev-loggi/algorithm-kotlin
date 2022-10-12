package programmers.kakao.y2022

import programmers.Programmers.Solution
import java.util.ArrayDeque

/**
 *
 * */
class KakaoInternship2022p3 : Solution {

    override fun execute() {
        var answer = -1

        answer = solution(10, 10, arrayOf(
            intArrayOf(10, 15, 2, 1, 2),
            intArrayOf(20, 20, 3, 3, 4),
        ))
        println("answer=$answer") // 15

        answer = solution(0, 0, arrayOf(
            intArrayOf(0, 0, 2, 1, 2),
            intArrayOf(4, 5, 3, 1, 2),
            intArrayOf(4, 11, 4, 0, 2),
            intArrayOf(10, 4, 0, 4, 2),
        ))
        println("answer=$answer") // 15
    }

    class Problem(arr: IntArray) {
        val alp_req = arr[0]
        val cop_req = arr[1]
        val alp_rwd = arr[2]
        val cop_rwd = arr[3]
        val cost = arr[4]

        override fun toString(): String =
            "($alp_req, $cop_req, $alp_rwd, $cop_rwd, $cost)"
    }

    fun solution(alp: Int, cop: Int, data: Array<IntArray>): Int {
        var curAlp = alp
        var curCop = cop
        var time = 0

        val solvedStack = ArrayDeque<Problem>()
        val problems = data.map { Problem(it) }
            .sortedBy { it.alp_req + it.cop_req }
            .also {
                // 맨 처음 문제
                val problem = it[0]
                if (problem.alp_req > curAlp) {
                    time += problem.alp_req - curAlp
                    curAlp += problem.alp_rwd
                }
                if (problem.cop_req > curCop) {
                    time += problem.cop_req - curCop
                    curCop += problem.cop_rwd
                }
                solvedStack.offer(problem)
            }
            .drop(1)
            .forEach { problem ->
                val lastSolvedProblem = solvedStack.peekLast()
                println("lastSolvedProblem=$lastSolvedProblem")

                if (problem.alp_req > curAlp) {
                    val distance = problem.alp_req - curAlp
                    val cost1 = (distance / lastSolvedProblem.alp_rwd) * lastSolvedProblem.cost // 직전 문제 풀기
                    val cost2 = distance - (distance / lastSolvedProblem.alp_rwd) * lastSolvedProblem.alp_rwd // 스스로 공부
                    time += cost1 + cost2
                    curAlp = problem.alp_req
                }

                if (problem.cop_req > curCop) {
                    val distance = problem.cop_req - curCop
                    val cost1 = (distance / lastSolvedProblem.cop_rwd) * lastSolvedProblem.cost // 직전 문제 풀기
                    val cost2 = distance - (distance / lastSolvedProblem.cop_rwd) * lastSolvedProblem.cop_rwd // 스스로 공부
                    time += cost1 + cost2
                    curCop = problem.cop_req
                }

                solvedStack.offer(problem)
                println("solvedStack=$solvedStack")
            }


        return time
    }

}