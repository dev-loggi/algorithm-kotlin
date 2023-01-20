import boj.BOJ
import kotlin.system.measureNanoTime

fun main() {
    measure(::runSolution)
}

private inline fun measure(block: () -> Unit) {
    val nanoTime = measureNanoTime(block)
    println("${nanoTime / 1_000_000_000.0}초")
}

private fun runSolution() {
    BOJ.execute()
}