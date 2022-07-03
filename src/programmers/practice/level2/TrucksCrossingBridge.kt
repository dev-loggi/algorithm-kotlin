package programmers.practice.level2

import Solution
import java.util.ArrayDeque

/**
 * 다리를 지나는 트럭
 * 스택/큐
 * https://programmers.co.kr/learn/courses/30/lessons/42583
 * */
class TrucksCrossingBridge : Solution {

    override fun execute() {
        var answer: Int

        answer = solution(2, 10, intArrayOf(7, 4, 5, 6))
        println("answer=$answer\n") // 8

        answer = solution(100, 100, intArrayOf(10))
        println("answer=$answer\n") // 101

        answer = solution(100, 100, intArrayOf(10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
        println("answer=$answer\n") // 110

        answer = solution(4, 10, intArrayOf(7, 4, 5, 6, 8, 2, 9, 9, 9))
        println("answer=$answer\n") // 8
    }

    data class Truck(val weight: Int, val entryTime: Int) {
        override fun toString(): String = "(w=$weight, t=$entryTime)"
    }

    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        val waitingTrucks = ArrayDeque(truck_weights.toList())
        val bridge = ArrayDeque<Truck>()

        var time = 0
        var totalWeightInBridge = 0

        do {
            time++

            if (bridge.isNotEmpty()) {
                if (time - bridge.first.entryTime == bridge_length) {
                    // 맨 앞 트럭 다리 탈출
                    totalWeightInBridge -= bridge.pop().weight
                }
            }

            if (waitingTrucks.isNotEmpty()) {
                if (totalWeightInBridge + waitingTrucks.first <= weight) {
                    // 새 트럭 다리 진입
                    val newTruck = Truck(waitingTrucks.poll(), time)
                    bridge.offer(newTruck)
                    totalWeightInBridge += newTruck.weight
                } else {
                    // 용량 초과로 대기
                    time = bridge.first.entryTime + bridge_length - 1
                }
            } else if (bridge.isNotEmpty()) {
                // 남은 트럭 x -> 시간 건너뛰기
                time = bridge.first.entryTime + bridge_length - 1
            }

            println("time=$time, $bridge, $waitingTrucks")

        } while (bridge.isNotEmpty() || waitingTrucks.isNotEmpty())

        return time
    }
}