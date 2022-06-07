package utils

import java.util.*
import kotlin.collections.HashSet

object Util {

    fun log(arr: IntArray) {
        StringBuilder("IntArray[${arr.size}]: {").also { builder ->

            arr.forEachIndexed { index, number ->
                if (index < arr.lastIndex) {
                    builder.append("$number, ")
                } else {
                    builder.append("${number}}")
                }
            }

            println(builder.toString())
        }
    }

    fun getLog(arr: IntArray): String = StringBuilder("IntArray[size=${arr.size}]: {").also { builder ->
        arr.forEachIndexed { index, number ->
            if (index < arr.lastIndex) {
                builder.append("$number, ")
            } else {
                builder.append("${number}}")
            }
        }
    }.toString()

    fun <T> getLog(arr: Array<T>): String = StringBuilder("Array[size=${arr.size}]: {").also { builder ->
        arr.forEachIndexed { index, element ->
            if (index < arr.lastIndex) {
                builder.append("$element, ")
            } else {
                builder.append("$element")
            }
        }
        builder.append("}")
    }.toString()

    // 이진 탐색(이분 탐색)
    fun binarySearch(arr: IntArray, target: Int): Int {
        var low = 0
        var high = arr.lastIndex
        var mid = 0;

        while (low <= high) {
            mid = (low + high) / 2

            when {
                arr[mid] == target -> return mid
                arr[mid] > target -> high = mid - 1
                else -> low = mid + 1
            }
        }
        return -1
    }

    fun test() {
        val queue: Queue<Char> = LinkedList()
        val stack = Stack<String>()
    }

    // BFS, DFS: [https://www.youtube.com/watch?v=gl5RhtU2mF8]
    fun test2() {
        val graph: Array<Array<Char>> = arrayOf(
            arrayOf('A', 'B', 'C', 'D'),
            arrayOf('B', 'A', 'E'),
            arrayOf('C', 'A', 'D', 'E', 'F'),
            arrayOf('D', 'A', 'C', 'F'),
            arrayOf('E', 'B', 'C', 'G'),
            arrayOf('F', 'C', 'D'),
            arrayOf('G', 'E'),
        )

        val graphMap = hashMapOf<Char, Array<Char>>()
        graph.forEach {
            graphMap[it[0]] = it.sliceArray(1 until it.size)
        }

        val set = hashSetOf<Char>()
        val stack = Stack<Char>()

        stack.push('A')
        set.add('A')

        val result = mutableListOf<Char>()
        traverseByDFS(graph, stack, set, result)
        println("traverseByDFS() result=$result")

        result.clear()
        set.clear()
        stack.push('A')
        set.add('A')
        traverseByDFS2(graph, stack, set, result)
        println("traverseByDFS2() result=$result")

        val queue: Queue<Char> = LinkedList()
        result.clear()
        set.clear()
        queue.add('A')
        set.add('A')
        traverseByBFS(graph, queue, set, result)
        println("traverseByBFS() result=$result")


    }

    // DFS: (1) recursion
    private fun <T> traverseByDFS(graph: Array<Array<T>>, stack: Stack<T>, set: HashSet<T>, out: MutableList<T>) {
        val vertex = stack.pop()
        out.add(vertex)

        graph.find { it[0] == vertex }
            ?.forEach {
                if (!set.contains(it)) {
                    stack.push(it)
                    set.add(it)
                    traverseByDFS(graph, stack, set, out)
                }
            }
    }

    // DFS: (2) iteration
    private fun <T> traverseByDFS2(graph: Array<Array<T>>, stack: Stack<T>, set: HashSet<T>, out: MutableList<T>) {
        while (stack.isNotEmpty()) {
            val vertex = stack.pop()
            out.add(vertex)

            graph.find { it[0] == vertex }
                ?.forEach {
                    if (!set.contains(it)) {
                        stack.push(it)
                        set.add(it)
                    }
                }
        }
    }

    // BFS(Breadth First Search)
    private fun <T> traverseByBFS(graph: Array<Array<T>>, queue: Queue<T>, set: HashSet<T>, out: MutableList<T>) {
        while (queue.isNotEmpty()) {
            val vertex = queue.poll()
            out.add(vertex)

            graph.find { it[0] == vertex }
                ?.forEach {
                    if (!set.contains(it)) {
                        queue.add(it)
                        set.add(it)
                    }
                }
        }
    }
}