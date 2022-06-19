import boj.BOJ
import codility.exercise.Codility_Ex9_BinaryGap
import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import test.NaverInternship2022
import woowahan.WoowahanTechCamp
import kotlin.math.min

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
        //boj.boj1175Delivery.execute()
        //programmers.practice.levelTwo.parenthesisConversion.execute()


        NaverInternship2022.apply {
            p2.execute()
        }

    }


}