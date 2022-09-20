package programmers.practice.level3

import programmers.Programmers.Solution
import java.util.*
import kotlin.random.Random

/**
 * 이중 우선순위 큐.
 * 힙(Heap)
 * https://programmers.co.kr/learn/courses/30/lessons/42628?language=kotlin
 *
 * 1. maxHeap, minHeap
 * 2. 이분 탐색
 * 3.
 * */
class DualPriorityQueue : Solution {

    override fun execute() {
        //testHeap()

        var answer: IntArray

        answer = solution(
            arrayOf("I 16", "D 1")
        )
        println("answer=${answer.contentToString()}") // 0, 0

        answer = solution(
            arrayOf("I 7", "I 5", "I -5", "D -1")
        )
        println("answer=${answer.contentToString()}") // 7, 5

        answer = solution(
            arrayOf("I 7", "I 5", "I -5", "D -1", "D 1")
        )
        println("answer=${answer.contentToString()}") // 5, 5
    }

    fun solution(operations: Array<String>): IntArray {
        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        val minHeap = PriorityQueue<Int>()

        operations.forEach {
            val operation = it.split(" ")
            val command = operation[0][0]
            val number = operation[1].toInt()

            when (command) {
                'I' -> {
                    maxHeap.offer(number)
                    minHeap.offer(number)
                }
                'D' -> {
                    if (number > 0) { // 최댓값 삭제
                        minHeap.remove(maxHeap.poll())
                    } else { // 최솟값 삭제
                        maxHeap.remove(minHeap.poll())
                    }
                }
            }
        }

        return intArrayOf(maxHeap.poll() ?: 0, minHeap.poll() ?: 0)
    }

    private fun testHeap() {
        data class Person(val age: Int) : Comparable<Person> {

            override fun hashCode(): Int {
                println("${age}.hashCode()")
                return super.hashCode()
            }

            override fun equals(other: Any?): Boolean {
                println("${age}.equals($other)")
                return super.equals(other)
            }

            override fun compareTo(other: Person): Int {
                println("$age, ${other.age}")
                return age.compareTo(other.age)
            }

            override fun toString(): String = age.toString()
        }

        val heap = PriorityQueue<Person>()
        val stack = ArrayDeque<Person>()

        val list = listOf<Int>()
        list.binarySearch { 1 }
        list.binarySearch { 2 }
        Collections.binarySearch(list, 3)

        while (true) {
            print("★ command: ")

            when (readLine()) {
                "a", null -> {
                    val person = Person(Random.nextInt(10, 100))
                    println("add: $person")
                    heap.offer(person)
                    stack.offer(person)
                }
                "p" -> {
                    val removed = heap.poll()
                    println("poll: ${removed.age}")
                    stack.remove(removed)
                }
                "r" -> {
                    val removed = stack.pollLast()
                    println("remove: ${removed.age}")
                    heap.remove(removed)
                }
                "e", "exit" -> {
                    break
                }
            }

            println("heap=$heap\n")
        }

//        for (i in 0..10) {
//            heap.add(Person(Random.nextInt(10, 30)))
//        }
//
//        println("heap=$heap")
//
//        while (heap.isNotEmpty()) {
//            heap.poll()
//        }
    }

    class DualQueue<T> : PriorityQueue<T>() {
        override fun remove(element: T): Boolean {

            return super.remove(element)
        }

        override fun offer(e: T): Boolean {
            return super.offer(e)
        }
    }
}