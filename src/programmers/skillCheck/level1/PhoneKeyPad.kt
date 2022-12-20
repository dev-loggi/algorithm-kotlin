package programmers.skillCheck.level1

import kotlin.math.abs

class PhoneKeyPad {

    companion object {
        private const val BASE_UNIT = 3
    }
    /**
     *
     * 1, 2, 3
     * 4, 5, 6
     * 7, 8, 9
     * *, 0, #
     *
     * 0, 1, 2
     * 3, 4, 5
     * 6, 7, 8
     * 9, 10, 11
     *
     * */

    private var leftHandPosition: Int = 9
    private var rightHandPosition: Int = 11

    fun initialize() {
        leftHandPosition = 9
        rightHandPosition = 11
    }

    fun solution(numbers: IntArray, hand: String): String {
        initialize()
        var answer: String = ""

        for (i in numbers.indices) {
            if (numbers[i] == 0) numbers[i] = 10
            else numbers[i] = numbers[i] - 1
        }

        numbers.forEach { number ->
            when (number % BASE_UNIT) {
                0 -> {
                    leftHandPosition = number
                    answer += "L"
                }
                1 -> {
                    val leftHandDistance = getDistance(number, leftHandPosition)
                    val rightHandDistance = getDistance(number, rightHandPosition)

                    when {
                        leftHandDistance < rightHandDistance -> {
                            leftHandPosition = number
                            answer += "L"
                        }
                        leftHandDistance > rightHandDistance -> {
                            rightHandPosition = number
                            answer += "R"
                        }
                        hand == "left" -> {
                            leftHandPosition = number
                            answer += "L"
                        }
                        hand == "right" -> {
                            rightHandPosition = number
                            answer += "R"
                        }
                    }
                }
                2 -> {
                    rightHandPosition = number
                    answer += "R"
                }
            }
        }


        return answer
    }

    private fun getDistance(number: Int, position: Int): Int {
        val distanceX = abs(number % BASE_UNIT - position % BASE_UNIT)
        val distanceY = abs(number / BASE_UNIT - position / BASE_UNIT)
        return distanceX + distanceY
    }
}