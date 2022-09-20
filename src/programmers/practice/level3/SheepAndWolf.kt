package programmers.practice.level3

import programmers.Programmers.Solution
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max


/**
 * 양과 늑대
 * https://programmers.co.kr/learn/courses/30/lessons/92343?language=kotlin
 * 2022 KAKAO BLIND RECRUITMENT
 * */
class SheepAndWolf : Solution {

    override fun execute() {
        //[0,0,1,1,1,0,1,0,1,0,1,1]	[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]	5
        //[0,1,0,1,1,0,1,0,0,1,0]	[[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]	5

        solution(
            info = intArrayOf(0,0,1,1,1,0,1,0,1,0,1,1),
            edges = arrayOf(
                intArrayOf(0,1), intArrayOf(1,2), intArrayOf(1,4), intArrayOf(0,8), intArrayOf(8,7),
                intArrayOf(9,10), intArrayOf(9,11), intArrayOf(4,3), intArrayOf(6,5), intArrayOf(4,6), intArrayOf(8,9),
            )
        ).let { println("answer=$it") } // 5

        return
        solution(
            info = intArrayOf(0,1,0,1,1,0,1,0,0,1,0),
            edges = arrayOf(
                intArrayOf(0,1), intArrayOf(0,2), intArrayOf(1,3), intArrayOf(1,4), intArrayOf(2,5),
                intArrayOf(2,6), intArrayOf(3,7), intArrayOf(4,8), intArrayOf(6,9), intArrayOf(9,10),
            )
        ).let { println("answer=$it") } // 5

        solution(
            info = intArrayOf(
                0,0,1,1,1,
                0,1,0,1,0,
                1,1,1,0,0,
                1,0,0,0,0,
            ),
            edges = arrayOf(
                intArrayOf(0,1), intArrayOf(1,2), intArrayOf(1,4), intArrayOf(0,8), intArrayOf(8,7),
                intArrayOf(9,10), intArrayOf(9,11), intArrayOf(4,3), intArrayOf(6,5), intArrayOf(4,6),
                intArrayOf(8,9), intArrayOf(10,12), intArrayOf(12,13), intArrayOf(12,14), intArrayOf(2,15),
                intArrayOf(15,16), intArrayOf(16,17), intArrayOf(16,18), intArrayOf(17,19), intArrayOf(17,20)
            )
        ).let { println("answer=$it") } // 5
    }

    lateinit var INFO: IntArray
    lateinit var list: Array<ArrayList<Int>> // 인접 리스트
    var count = 0
    var answer: Int = 0
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        count = 0
        answer = 0
        INFO = info

        list = Array(info.size) { ArrayList<Int>()}
        for (edge in edges) {
            val start = edge[0]
            val end = edge[1]
            list[start].add(end)
        }
        val route: Queue<Int> = LinkedList<Int>()
        dfs(0, 0, 0, route, 0)
        println("count=$count")
        return answer
    }

    /**
     * @param cur 현재 노드
     * @param sheep 양의 수
     * @param wolf 늑대의 수
     * @param route 방문해야할 노드
     */
    fun dfs(cur: Int, sheep: Int, wolf: Int, route: Queue<Int>, depth: Int) {
        val depthStr = Array(depth) { "  " }.joinToString("")
        count++
        //if (count % 10000 == 0) println("dfs(): count=$count")

        // 1. 현재 양, 늑대의 수 갱신
        val ns = sheep + (INFO[cur] xor 1) // XOR연산, info값이 0일 때, 양의 개수 1 증가
        val nw = wolf + INFO[cur]


        println("${depthStr}dfs(): cur=$cur, sheep=$ns, wolf=$nw, route=$route, depth=$depth")

        // 2. 만약 (양 <= 늑대라면)
        if (nw >= ns) {
            //readLine()
            return // 현재 노드는 갈 수 없는 노드이므로 종료
        }


        // 3. 그렇지 않다면
        answer = max(answer, ns) // 양의 최대값 갱신

        val nextNode: Queue<Int> = LinkedList<Int>(route)
        // 4. 다음 노드 리스트에 현재 노드의 자식노드들을 추가
        for (node in list[cur]) {
            nextNode.offer(node)
        }

        println("$depthStr   nextNode=$nextNode")
        //readLine()

        //반복문 시작 (다음 노드 리스트의 사이즈 만큼)
        repeat(nextNode.size) {
            // ㄱ. 방문하려는 노드는 리스트에서 없애준다.
            val next = nextNode.poll()
            // ㄴ. 재귀 호출 DFS(다음노드, 갱신된 양, 갱신된 늑대, 다음노드 리스트)
            dfs(next, ns, nw, nextNode, depth + 1)
            // ㄷ. 다시 리스트에 아까 방문한 노드를 추가시키고 다음 노드를 방문할 준비를 한다
            //nextNode.offer(next)
        } // 반복문 종료
    }
}