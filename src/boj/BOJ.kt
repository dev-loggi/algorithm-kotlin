package boj

import boj.bruteForce.*
import boj.dfsBfs.*
import boj.dp.BOJ_2839_SugarDelivery
import boj.etc.BOJ_11005_BaseConversion2
import boj.unsolved.*
import kotlin.reflect.full.createInstance

object BOJ {

    private val solutions = mapOf(
        // 1000 ~ 1999
        1012 to BOJ_1012_OrganicCabbage::class,
        1175 to BOJ_1175_Delivery::class,
        1260 to BOJ_1260_DFSandBFS::class,
        1525 to BOJ_1525_Puzzle::class,
        1913 to BOJ_1913_Snail::class,

        // 2000 ~ 2999
        2178 to BOJ_2178_MazeExploration::class,
        2210 to BOJ_2210_NumPadJump::class,
        2422 to BOJ_2422_ItalyIcecream::class,
        2529 to BOJ_2529_InequalitySign::class,
        2606 to BOJ_2606_Virus::class,
        2667 to BOJ_2667_VillageNumbering::class,
        2675 to BOJ_2675_StringRepetition::class,
        2745 to BOJ_2745_BaseConversion::class,
        2839 to BOJ_2839_SugarDelivery::class,

        // 8000 ~ 8999
        8111 to BOJ_8111_ZeroAndOne::class,

        // 9000 ~ 9999
        9019 to BOJ_9019_DSLR::class,

        // 11000 ~ 11999
        11005 to BOJ_11005_BaseConversion2::class,

        // 15000 ~ 15999
        15683 to BOJ_15683_Serveillance::class,
        15686 to BOJ_15686_ChickenDelivery::class,

        // 16000 ~ 16999
        16637 to BOJ_16637_AddParentheses::class,
        16922 to BOJ_16922_MakingRomanNumerals::class,
        16924 to BOJ_16924_FindCross::class,
        16936 to BOJ_16936_Division3Multiplication2::class,
        16937 to BOJ_16937_TwoStickers::class,
        16938 to BOJ_16938_CampReady::class,
        16943 to BOJ_16943_NumberReplacement::class,
        16968 to BOJ_16968_LicensePlate1::class,
        16971 to BOJ_16971_HalfSeasonedHalfFried::class,
        16973 to BOJ_16973_RectangleEscape::class,

        // 17000 ~ 17999
        17071 to BOJ_17071_HideAndSeek5::class,
        17088 to BOJ_17088_ArithmeticSequenceTransform::class,
        17089 to BOJ_17089_ThreeFriends::class,
        17135 to BOJ_17135_CastleDefense::class,
        17281 to BOJ_17281_Baseball::class,
        17406 to BOJ_17406_ArrayRotation4::class,
    )

    fun execute() {
        print("BOJ 문제 번호를 입력해주세요: ")

        readLine()?.toIntOrNull()?.let { no ->
            println()
            println("=======================================================")
            solutions[no]?.createInstance()?.execute()
            println("=======================================================")
        }
    }
}