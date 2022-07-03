package programmers

import Solution
import programmers.practice.level1.*
import programmers.practice.level2.*
import programmers.practice.level3.*
import programmers.practice.level4.MazeEscape

object Programmers {
    val practice = Practice
    val skillCheck = SkillCheck

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
            val dotProduct: Solution get() = DotProduct()
            val failureRate: Solution get() = FailureRate()
            val findRemainderOne: Solution get() = FindRemainderOne()
            val getReportResults: Solution get() = GetReportResults()
            val gymSuit: Solution get() = GymSuit()
            val kThNumber: Solution get() = KthNumber()
            val lotto: Solution get() = Lotto()
            val minRectangle: Solution get() = MinRectangle()
            val mockExam: Solution get() = MockExam()
            val numberOfDivisorsAndAddition: Solution get() = NumberOfDivisorsAndAddition()
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
}