package programmers.skillCheck.level1

/**
 * 2016년 1월 1일은 금요일입니다. 2016년 a월 b일은 무슨 요일일까요? 두 수 a ,b를 입력받아 2016년 a월 b일이 무슨 요일인지 리턴하는 함수, solution을 완성하세요. 요일의 이름은 일요일부터 토요일까지 각각 SUN,MON,TUE,WED,THU,FRI,SAT

입니다. 예를 들어 a=5, b=24라면 5월 24일은 화요일이므로 문자열 "TUE"를 반환하세요.

제한 조건
2016년은 윤년입니다.
2016년 a월 b일은 실제로 있는 날입니다. (13월 26일이나 2월 45일같은 날짜는 주어지지 않습니다)
 *
 * */
class DayOfWeeks {

    //5	24	"TUE"
    enum class DayOfWeek {
        SUN, MON, TUE, WED, THU, FRI, SAT;

        companion object {
            fun valueOf(ordinal: Int): DayOfWeek {
                return values()[ordinal]
            }
        }
    }

    fun solution(a: Int, b: Int): String {
        val days = (1..a).fold(0) { total, month ->
            if (month < a) total + getDaysBy(month)
            else total + b
        }

        return DayOfWeek.valueOf((days + 4) % 7).name
    }

    private fun getDaysBy(month: Int): Int = when (month) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        2 -> 29
        else -> throw IllegalArgumentException("month=$month")
    }
}