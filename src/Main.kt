import boj.BOJ
import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import woowahan.WoowahanTechCamp
import java.text.DecimalFormat

fun main() {
    Algorithm().execute()
}

class Algorithm {
    private val woowahanTechCamp: WoowahanTechCamp
        get() = WoowahanTechCamp
    private val kakaoInternship2022: KakaoInternship2022
        get() = KakaoInternship2022
    private val kakaoInternship2021: KakaoInternship2021
        get() = KakaoInternship2021
    private val programmers: Programmers
        get() = Programmers
    private val boj: BOJ
        get() = BOJ

    var totalCount = 0

    fun execute() {
        boj.p17281.execute()
        //programmers.practice.levelTwo.parenthesisConversion.execute()

//        while (true) {
//            val size = readLine()?.toIntOrNull() ?: break
//
//            val start = System.currentTimeMillis()
//
//            var count = 0
//
//            IntArray(size).onPermutate(size, 0) {
//                count++
//            }
//
//            val end = System.currentTimeMillis()
//            println("count=$count")
//            println("start=$start")
//            println("end=$end")
//            println("gap=${end - start}")
//        }

    }

    fun IntArray.onPermutate(r: Int, fixed: Int, body: (IntArray) -> Unit) {
        val visited = BooleanArray(size)
        val arr = IntArray(size) { if (it == fixed) this[fixed] else -1 }
        visited[fixed] = true

        fun recursive(depth: Int) {
            //println("depth=$depth, visited=${visited.map { if (it) 'T' else 'F' }.joinToString(" ")}, arr=${arr.joinToString(" ")}")
            if (depth == fixed) {
                recursive(depth + 1)
            } else if (depth == r) {
                body(arr)
            } else {
                for (i in indices) {
                    if (visited[i])
                        continue

                    visited[i] = true
                    arr[depth] = this[i]
                    recursive(depth + 1)
                    visited[i] = false
                }
            }
        }

        recursive(0)
    }



    fun Array<IntArray>.log() {
        val formatter = DecimalFormat("00")
        forEach { println(it.map { n -> formatter.format(n) + " " }) }
    }
}

