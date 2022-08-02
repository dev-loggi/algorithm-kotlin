package utils.test

import kotlin.system.measureNanoTime

class KotlinCollectionPerformance {

    companion object {
        private const val SIZE = 1_000_000
        private const val CYCLE = 1_000
    }

    fun init() {

    }

    fun test() {
        //val elements = IntArray(100_000_000) { it }.toList()

        val mutableList = MutableList(SIZE) { it }
        val arrayList = ArrayList<Int>()
        arrayList.addAll(mutableList)

        println("test start()")

        var tmp = 0
        measureNanoTime {
            repeat(CYCLE) { for (n in mutableList) tmp = n }
        }.let { println(it / 1_000_000_000.0) }
        measureNanoTime {
            repeat(CYCLE) { for (n in arrayList) tmp = n }
        }.let { println(it / 1_000_000_000.0) }
    }
}