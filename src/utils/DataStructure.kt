package utils

import java.util.ArrayDeque

object DataStructure {

    fun testStackAndQueue() {
        // 스택(Stack)
        // 후입선출(LIFO, Last In First Out)
        // push, pop(=poll)
        println("스택(Stack)")
        val stack = ArrayDeque<Int>()

        for (i in 0..10) stack.push(i)
        println(stack)

        print("-> ")
        while (stack.isNotEmpty()) print("${stack.pop()} ")

        println()
        println()

        // 큐(Queue)
        // 선입선출(FIFO, First In First Out)
        // offer, poll
        println("큐(Queue)")
        val queue = ArrayDeque<Int>()

        for (i in 0..10) queue.offer(i)
        println(queue)

        print("-> ")
        while (queue.isNotEmpty()) print("${queue.poll()} ")
    }
}