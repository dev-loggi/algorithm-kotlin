package programmers.practice.level3

import programmers.Programmers.Solution
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet


/**
 * 여행 경로
 * DFS/BFS
 * https://programmers.co.kr/learn/courses/30/lessons/43164
 * */
class TravelRoute : Solution {

    override fun execute() {
        // ["ICN", "JFK", "HND", "IAD"]
        var answer = solution(arrayOf(
            arrayOf("ICN", "JFK"),
            arrayOf("HND", "IAD"),
            arrayOf("JFK", "HND"),
        ))
        println("answer=${answer.contentToString()}")

        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        answer = solution(arrayOf(
            arrayOf("ICN", "SFO"),
            arrayOf("ICN", "ATL"),
            arrayOf("SFO", "ATL"),
            arrayOf("ATL", "ICN"),
            arrayOf("ATL", "SFO"),
        ))
        println("answer=${answer.contentToString()}")


        // ["A", "B", "A", "B", "C"]
        answer = solution(arrayOf(
            arrayOf("ICN", "BBB"),
            arrayOf("ICN", "BBB"),
            arrayOf("BBB", "ICN"),
            arrayOf("BBB", "CCC"),
        ))
        println("answer=${answer.contentToString()}")
    }

    data class Ticket(val start: String, val destination: String) {
//        override fun toString(): String =
//            "[$start, $destination]"
        override fun toString(): String = destination
    }

    fun solution(tickets: Array<Array<String>>): Array<String> {
        val ticketGraph = makeTicketMap(tickets)
        val travelRoutes = mutableListOf<String>("ICN")

        val firstTicket = ticketGraph["ICN"]!!.first()

        println("ticketGraph=$ticketGraph")
        traverseByDFS(
            ticketGraph = ticketGraph,
            stack = Stack<Ticket>().apply { push(firstTicket) },
            set = hashSetOf(firstTicket),
            travelRoutes = travelRoutes
        )

        return travelRoutes.minByOrNull { it }!!
            .split(" ")
            .toTypedArray()
    }

    private fun traverseByDFS(
        ticketGraph: HashMap<String, MutableList<Ticket>>,
        stack: Stack<Ticket>,
        set: HashSet<Ticket>,
        travelRoutes: MutableList<String>
    ) {
        //println(stack)
        val ticket = stack.pop()
        println(ticket.start)

        ticketGraph[ticket.destination]?.forEach {
            if (!set.contains(it)) {
                stack.push(it)
                set.add(it)

                //travelRoutes.add(it.start)
                //println("travelRoutes=$travelRoutes")

                traverseByDFS(ticketGraph, stack, set, travelRoutes)
            }
        }
    }

    private fun makeTicketMap(tickets: Array<Array<String>>): HashMap<String, MutableList<Ticket>> =
        hashMapOf<String, MutableList<Ticket>>().also { graph ->
            tickets.forEach { ticket ->
                graph[ticket[0]] = (graph[ticket[0]] ?: mutableListOf()).apply {
                    add(Ticket(ticket[0], ticket[1]))
                }
            }
        }

//    fun solution2(tickets: Array<Array<String>>): Array<String> {
//        // ex: { ICN=[ATL, SFO], ATL=[ICN, SFO], SFO=[ATL] }
//        val ticketMap = makeTicketMap(tickets)
//        val ticketMapQueue = hashMapOf<String, Queue<String>>()
//        ticketMap.entries.forEach {
//            ticketMapQueue[it.key] = LinkedList(it.value.sorted())
//        }
//        println("ticketMapQueue=$ticketMapQueue")
//
//        return traverse(tickets.size, ticketMapQueue)
//    }
//
//    private fun traverse(n: Int, tickets: HashMap<String, Queue<String>>): Array<String> {
//        // tickets: HashMap<출발지, Queue<도착지>>
//        val travelRoute = mutableListOf("ICN")
//        var dest = "ICN"
//
//        for (i in 0 until n) {
//            dest = tickets[dest]!!.poll()
//            print("$dest, ")
//            travelRoute.add(dest)
//        }
//
//        return travelRoute.toTypedArray()
//    }


}