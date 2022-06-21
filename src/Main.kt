import boj.BOJ
import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import utils.exts.combination
import utils.exts.combinationAll
import utils.exts.homoCombination
import utils.exts.homoPermutation
import woowahan.WoowahanTechCamp

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

    fun execute() {
        //boj.boj16922MakingRomanNumerals.execute()
        //programmers.practice.levelTwo.parenthesisConversion.execute()

        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)

        for (r in 0..8) {
            list.combination(r).map { it.joinToString("") }.also {
                println("8C$r=${8.combination(r)}, size=${it.size} -> $it")
            }
        }

        println()

        var tmp = -1
        val list2 = listOf(1, 2, 3, 4)
        list2.combinationAll().map { it.joinToString("") }
            .forEach {
                if (tmp != it.length) {
                    tmp = it.length
                    println()
                }
                print("$it ")
            }


    }

    fun <T> List<T>.combination(r: Int): List<List<T>> {
        val result = mutableListOf<List<T>>()

        fun recursive(depth: Int, start: Int, list: List<T>) {
            if (depth == r) {
                result.add(list)
                return
            }

            for (i in start until size) {
                recursive(depth + 1, i + 1, list.toMutableList().also { it.add(this[i]) })
            }
        }

        recursive(0, 0, emptyList())
        return result
    }
}

