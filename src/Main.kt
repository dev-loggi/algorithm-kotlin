import boj.BOJ
import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import utils.DataStructure
import woowahan.WoowahanTechCamp
import java.util.ArrayDeque


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
        boj.p2667VillageNumbering.execute()
    }
}