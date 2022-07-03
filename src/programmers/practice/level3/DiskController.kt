package programmers.practice.level3

import Solution
import java.util.*

/**
 * 디스크 컨트롤러
 * 힙(Heap)
 * https://programmers.co.kr/learn/courses/30/lessons/42627?language=kotlin
 * */
class DiskController : Solution {

    override fun execute() {
        var answer: Int

        answer = solution(arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6),
        ))
        println("answer=$answer") // 9
//
        answer = solution(arrayOf(
            intArrayOf(0, 3),
            intArrayOf(1, 9),
            intArrayOf(2, 6),
            intArrayOf(1, 10),
            intArrayOf(3, 6),
            intArrayOf(12, 10),
            intArrayOf(12, 9),
            intArrayOf(13, 4),
            intArrayOf(18, 2),
            intArrayOf(50, 5),
        ))
        println("answer=$answer") // 19

        // [24, 10], [28, 39], [43, 20], [37, 5], [47, 22], [20, 47], [15, 34], [15, 2], [35, 43], [26, 1]
        answer = solution(arrayOf(
            intArrayOf(24, 10),
            intArrayOf(28, 39),
            intArrayOf(43, 20),
            intArrayOf(37, 5),
            intArrayOf(47, 22),
            intArrayOf(20, 47),
            intArrayOf(15, 34),
            intArrayOf(15, 2),
            intArrayOf(35, 43),
            intArrayOf(26, 1),
        ))
        println("answer=$answer") // 72

        answer = solution(arrayOf(
            intArrayOf(0, 5),
            intArrayOf(2, 10),
            intArrayOf(10000, 2),
        ))
        println("answer=$answer") // 6
    }

    class Job(arr: IntArray) : Comparable<Job> {
        val requestTime = arr[0]
        val duration = arr[1]

        var startTime = Int.MIN_VALUE
        val endTime: Int
            get() = startTime + duration

        override fun compareTo(other: Job): Int = duration - other.duration

        override fun toString(): String = "(t=$requestTime, d=$duration, s=$startTime, e=$endTime)"
    }

    fun solution(jobs: Array<IntArray>): Int {
        val waitingQueue = ArrayDeque(jobs
            .sortedBy { it[0] } // 요청 시간 순으로 정렬
            .map { Job(it) }
        )
        val diskHeap = PriorityQueue<Job>() // 디스크 힙
        val workingStack = ArrayDeque<Job>() // 작업이 진행중 or 종료된 작업 스택

        var currentTime = 0

        do {
            //Thread.sleep(10)
            //println("time=$currentTime")

            // 대기중인 큐
            if (waitingQueue.isNotEmpty()) {
                while (waitingQueue.isNotEmpty() && waitingQueue.peek().requestTime == currentTime) {
                    // 대기중인 작업큐에서 요청시간이 된 작업들을 디스크 힙에 추가
                    //println("☆ 디스크 힙에 추가!! ${waitingQueue.peek()}")
                    diskHeap.offer(waitingQueue.poll())
                }
            }

            // 작업 스택
            val workingJob: Job? =
                if (workingStack.isNotEmpty() && workingStack.last.endTime > currentTime) workingStack.last
                else null

            // 디스크 힙
            if (workingJob == null && diskHeap.isNotEmpty()) {
                // 현재 작업중인 Job 이 없을 때
                //println("★ 작업 스택에 추가!! ${diskHeap.peek()}")
                workingStack.offerLast(diskHeap.poll().apply {
                    startTime = currentTime
                })
            }

            //println("waitQueue=$waitingQueue")
            //println("diskHeap =$diskHeap")
            //println("jobStack =$workingStack\n")

            currentTime++
        } while (waitingQueue.isNotEmpty() || diskHeap.isNotEmpty())

        return workingStack.sumOf { (it.endTime - it.requestTime) } / workingStack.size
    }
}
