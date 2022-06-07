package programmers

import common.Solution
import programmers.practice.level1.CranePuppetGame
import programmers.practice.level1.KthNumber
import programmers.practice.level1.Lotto
import programmers.practice.level1.MockExam
import programmers.practice.level2.*
import programmers.practice.level3.*

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
            val cranePuppetGame: Solution get() = CranePuppetGame()
            val kThNumber: Solution get() = KthNumber()
            val lotto: Solution get() = Lotto()
            val mockExam: Solution get() = MockExam()
        }

        object LevelTwo {
            val carpet: Solution get() = Carpet()
            val findPrimeNumber: Solution get() = FindPrimeNumber()
            val functionDevelopment: Solution get() = FunctionDevelopment()
            val hIndex: Solution get() = H_Index()
            val kDecimalPrimeNumber: Solution get() = KdecimalPrimeNumber()
            val largestNumber: Solution get() = LargestNumber()
            val matrixEdgeRotation: Solution get() = MatrixEdgeRotation()
            val plainSquare: Solution get() = PlainSquare()
            val printer: Solution get() = Printer()
            val stringZip: Solution get() = StringZip()
            val targetNumber: Solution get() = TargetNumber()
            val triangleSnail: Solution get() = TriangleSnail()
            val trucksCrossingBridge: Solution get() = TrucksCrossingBridge()
            val tuple: Solution get() = Tuple()
        }

        object LevelThree {
            val bestAlbum: Solution get() = BestAlbum()
            val diskController: Solution get() = DiskController()
            val dualPriorityQueue: Solution get() = DualPriorityQueue()
            val farthestNode: Solution get() = FarthestNode()
            val immigration: Solution get() = Immigration()
            val network: Solution get() = Network()
            val travelRoute: Solution get() = TravelRoute()
            val travelRouteJava: Solution get() = TravelRouteJava()
            val wordConversion: Solution get() = WordConversion()
        }

        object LevelFour {

        }

        object LevelFive {

        }
    }

    object SkillCheck {

    }
}