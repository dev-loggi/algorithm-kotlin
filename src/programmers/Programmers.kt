package programmers

import programmers.kakao.y2019.*
import programmers.kakao.y2020.*
import programmers.kakao.y2021.KAKAO_BLIND_2021_P1
import programmers.mockExam.*
import programmers.practice.level1.*
import programmers.practice.level2.*
import programmers.practice.level3.*
import programmers.practice.level4.MazeEscape

object Programmers {

    interface Solution {
        fun execute()
    }

    val practice = Practice
    val skillCheck = SkillCheck
    val mockExam = MockExam

    object Practice {
        val levelOne = LevelOne
        val levelTwo = LevelTwo
        val levelThree = LevelThree
        val levelFour = LevelFour
        val levelFive = LevelFive

        object LevelOne {
            val addMissingNumbers: Solution get() = AddMissingNumbers()
            val addNegativePositiveNumbers: Solution get() = AddNegativePositiveNumbers()
            val cranePuppetGame: Solution get() = CranePuppetGame()
            val divisibleIntArray: Solution get() = DivisibleIntArray()
            val dotProduct: Solution get() = DotProduct()
            val findRemainderOne: Solution get() = FindRemainderOne()
            val getMutableList: Solution get() = GetMiddleLetter()
            val getReportResults: Solution get() = GetReportResults()
            val gymSuit: Solution get() = GymSuit()
            val kThNumber: Solution get() = KthNumber()
            val lotto: Solution get() = Lotto()
            val minRectangle: Solution get() = MinRectangle()
            val mockExam: Solution get() = MockExam()
            val numberOfDivisorsAndAddition: Solution get() = NumberOfDivisorsAndAddition()
            val sortingStringsMyOwnWay: Solution get() = SortingStringsMyOwnWay()
            val sortStringInDescendingOrder: Solution get() = SortStringInDescendingOrder()
            val sumBetweenTwoIntegers: Solution get() = SumBetweenTwoIntegers()
            val takeTwoNumbersAndSums: Solution get() = TakeTwoNumbersAndSums()
            val ternaryReversed: Solution get() = TernaryReversed()
            val year2016: Solution get() = Year2016()
        }

        object LevelTwo {
            val archeryCompetition: Solution get() = ArcheryCompetition()
            val carpet: Solution get() = Carpet()
            val checkSocialDistancing: Solution get() = CheckSocialDistancing()
            val delivery: Solution get() = Delivery()
            val disGuise: Solution get() = DisGuise()
            val fatigueLevel: Solution get() = FatigueLevel()
            val findPrimeNumber: Solution get() = FindPrimeNumber()
            val functionDevelopment: Solution get() = FunctionDevelopment()
            val hIndex: Solution get() = H_Index()
            val joyStick: Solution get() = JoyStick()
            val kDecimalPrimeNumber: Solution get() = KdecimalPrimeNumber()
            val largestNumber: Solution get() = LargestNumber()
            val lightPathCycle: Solution get() = LightPathCycle()
            val matrixEdgeRotation: Solution get() = MatrixEdgeRotation()
            val maximizeFormulas: Solution get() = MaximizeFormulas()
            val parenthesisConversion: Solution get() = ParenthesisConversion()
            val parkingFeeCalculation: Solution get() = ParkingFeeCalculation()
            val plainSquare: Solution get() = PlainSquare()
            val printer: Solution get() = Printer()
            val rotateParentheses: Solution get() = RotateParentheses()
            val stringZip: Solution get() = StringZip()
            val targetNumber: Solution get() = TargetNumber()
            val triangleSnail: Solution get() = TriangleSnail()
            val trucksCrossingBridge: Solution get() = TrucksCrossingBridge()
            val tuple: Solution get() = Tuple()
            val twoDifferentBits: Solution get() = TwoDifferentBits()
        }

        object LevelThree {
            val bestAlbum: Solution get() = BestAlbum()
            val connectingIslands: Solution get() = ConnectingIslands()
            val diskController: Solution get() = DiskController()
            val dualPriorityQueue: Solution get() = DualPriorityQueue()
            val farthestNode: Solution get() = FarthestNode()
            val immigration: Solution get() = Immigration()
            val network: Solution get() = Network()
            val sheepAndWolf: Solution get() = SheepAndWolf()
            val travelRoute: Solution get() = TravelRoute()
            val wordConversion: Solution get() = WordConversion()
        }

        object LevelFour {
            val mazeEscape: Solution get() = MazeEscape()
        }

        object LevelFive {

        }
    }

    object SkillCheck {

    }

    object MockExam {
        val p1_1: Pro_MockExam1_1 get() = Pro_MockExam1_1()
        val p1_2: Pro_MockExam1_2 get() = Pro_MockExam1_2()
        val p1_3: Pro_MockExam1_3 get() = Pro_MockExam1_3()
        val p1_4: Pro_MockExam1_4 get() = Pro_MockExam1_4()

        val p2_1: Pro_MockExam2_1 get() = Pro_MockExam2_1()
        val p2_2: Pro_MockExam2_2 get() = Pro_MockExam2_2()
        val p2_3: Pro_MockExam2_3 get() = Pro_MockExam2_3()
    }

    object KAKAO_BLIND {
        object Y2019 {
            val p1: Solution get() = KAKAO_BLIND_2019_P1()
            val p2: Solution get() = KAKAO_BLIND_2019_P2()
            val p3: Solution get() = KAKAO_BLIND_2019_P3()
            val p4: Solution get() = KAKAO_BLIND_2019_P4()
            val p5: Solution get() = KAKAO_BLIND_2019_P5()
            val p6: Solution get() = KAKAO_BLIND_2019_P6()
            val p7: Solution get() = KAKAO_BLIND_2019_P7()
        }
        object Y2020 {
            val p1: Solution get() = KAKAO_BLIND_2020_P1()
            val p2: Solution get() = KAKAO_BLIND_2020_P2()
            val p3: Solution get() = KAKAO_BLIND_2020_P3()
            val p4: Solution get() = KAKAO_BLIND_2020_P4()
            val p5: Solution get() = KAKAO_BLIND_2020_P5()
            val p6: Solution get() = KAKAO_BLIND_2020_P6()
            val p7: Solution get() = KAKAO_BLIND_2020_P7()
        }
    }
}