import kakao.y2021.KakaoInternship2021
import kakao.y2022.KakaoInternship2022
import programmers.Programmers
import utils.combination
import utils.combinationAll
import utils.factorial
import woowahan.WoowahanTechCamp


fun main() {
    Algorithm().execute()
}

class Algorithm {
    val woowahanTechCamp: WoowahanTechCamp
        get() = WoowahanTechCamp
    val kakaoInternship2022: KakaoInternship2022
        get() = KakaoInternship2022
    val kakaoInternship2021: KakaoInternship2021
        get() = KakaoInternship2021
    val programmers = Programmers

    fun execute() {
        programmers.practice.levelTwo.matrixEdgeRotation.execute()
    }
}