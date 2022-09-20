import programmers.Programmers
import kotlin.system.measureNanoTime

fun main() {
    //BOJ.execute()

    measureNanoTime {
        Programmers.KAKAO.blind2019_P2.execute()
    }.let { println("${it / 1_000_000_000.0}초") }
}