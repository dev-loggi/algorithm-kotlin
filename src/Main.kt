import boj.BOJ
import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import woowahan.WoowahanTechCamp
import kotlin.random.Random

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
        //boj.p17281.execute()
        programmers.practice.levelTwo.disGuise.execute()

//        (0 until 50).map { Random.nextInt(65, 80).toChar() }
//            .groupingBy { it }
//            .eachCount()
//            .also { println(it) }
//
//        "programmers.practice.levelOne.getReportResults.execute()".groupingBy { it }
//            .eachCount()
//            .also { println(it) }
//
//        listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6, 7))
//            .also {
//                println(it.map { it })
//            }
//            .also {
//                println(it.flatMap { it })
//            }
    }
}

