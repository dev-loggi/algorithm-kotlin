package boj

import boj.bruteForce.*
import boj.dfsBfs.*
import boj.dp.BOJ_2839_SugarDelivery
import boj.etc.BOJ_11005_BaseConversion2
import boj.etc.BOJ_21921_Blog
import boj.etc.BOJ_2470_TwoSolutions
import boj.etc.BOJ_2902_WhyIsKMPaKMP
import boj.unsolved.*
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

object BOJ {

    fun execute() {
        print("BOJ 문제 번호를 입력해주세요: ")

        readLine()?.toIntOrNull()?.let { no ->
            println()
            println("=======================================================")
            getSolution(no)?.createInstance()?.execute()
            println("=======================================================")
        }
    }

    private fun getSolution(no: Int): KClass<out BOJSolution>? = when (no) {
        // 1000 ~ 1999
        1012 -> BOJ_1012_OrganicCabbage::class
        1175 -> BOJ_1175_Delivery::class
        1260 -> BOJ_1260_DFSandBFS::class
        1385 -> BOJ_1385_HoneyComb::class
        1525 -> BOJ_1525_Puzzle::class
        1913 -> BOJ_1913_Snail::class

        // 2000 ~ 2999
        2178 -> BOJ_2178_MazeExploration::class
        2210 -> BOJ_2210_NumPadJump::class
        2422 -> BOJ_2422_ItalyIcecream::class
        2470 -> BOJ_2470_TwoSolutions::class
        2529 -> BOJ_2529_InequalitySign::class
        2606 -> BOJ_2606_Virus::class
        2667 -> BOJ_2667_VillageNumbering::class
        2675 -> BOJ_2675_StringRepetition::class
        2745 -> BOJ_2745_BaseConversion::class
        2839 -> BOJ_2839_SugarDelivery::class
        2902 -> BOJ_2902_WhyIsKMPaKMP::class

        // 8000 ~ 8999
        8111 -> BOJ_8111_ZeroAndOne::class

        // 9000 ~ 9999
        9019 -> BOJ_9019_DSLR::class
        9328 -> BOJ_9328_Key::class

        // 11000 ~ 11999
        11005 -> BOJ_11005_BaseConversion2::class

        // 12000 ~ 12999
        12851 -> BOJ_12851_HideAndSeek2::class

        // 15000 ~ 15999
        15558 -> BOJ_15558_JumpGame::class
        15653 -> BOJ_15653_MarbleEscape4::class
        15683 -> BOJ_15683_Serveillance::class
        15686 -> BOJ_15686_ChickenDelivery::class

        // 16000 ~ 16999
        16637 -> BOJ_16637_AddParentheses::class
        16920 -> BOJ_16920_ExpansionGame::class
        16922 -> BOJ_16922_MakingRomanNumerals::class
        16924 -> BOJ_16924_FindCross::class
        16936 -> BOJ_16936_Division3Multiplication2::class
        16937 -> BOJ_16937_TwoStickers::class
        16938 -> BOJ_16938_CampReady::class
        16943 -> BOJ_16943_NumberReplacement::class
        16952 -> BOJ_16952_ChessboardTravel2::class
        16959 -> BOJ_16959_ChessboardTravel1::class
        16968 -> BOJ_16968_LicensePlate1::class
        16971 -> BOJ_16971_HalfSeasonedHalfFried::class
        16973 -> BOJ_16973_RectangleEscape::class

        // 17000 ~ 17999
        17071 -> BOJ_17071_HideAndSeek5::class
        17088 -> BOJ_17088_ArithmeticSequenceTransform::class
        17089 -> BOJ_17089_ThreeFriends::class
        17135 -> BOJ_17135_CastleDefense::class
        17281 -> BOJ_17281_Baseball::class
        17406 -> BOJ_17406_ArrayRotation4::class

        // 21000 ~ 21999
        21921 -> BOJ_21921_Blog::class

        else -> null
    }
}