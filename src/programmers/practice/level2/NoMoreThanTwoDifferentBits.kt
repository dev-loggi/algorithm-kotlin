package programmers.practice.level2

import programmers.Programmers
import kotlin.random.Random

/**
 * 2개 이하로 다른 비트
 * https://school.programmers.co.kr/learn/courses/30/lessons/77885
 *
 * 비트 연산
 * */
class NoMoreThanTwoDifferentBits : Programmers.Solution {

    override fun execute() {
        solution(longArrayOf(2, 7)).let { println(it.contentToString()) }
        solution(LongArray(10) { Random.nextLong(100, 500) }).let { println(it.contentToString()) }
    }

    fun solution(numbers: LongArray): LongArray {
        return numbers.map { x -> f(x) }.toLongArray()
    }

    private fun f(x: Long): Long {
        val t = x.inv() and (x + 1)
        val res = (x or t) and (t shr 1).inv()
        println("f($x)")
        println("    x=($x, ${x.bin})")
        println("        ${x.inv().bin}, ${x.inv() }")
        println("        ${(x + 1).bin}")
        println("    t=($t, ${t.bin})")
        println("        ${(x or t).bin}")
        println("        ${(t shr 1).inv().bin}")
        println("    r=($res, ${res.bin})")
        return res
    }

    private fun f2(x: Long): Long {
        return if (x % 2 == 0L) x + 1
        else {
            var t = 1L
            while (t and x != 0L) { t = t shl 1 }
            (x + t) - (t shr 1)
        }
    }

    private fun f3(x: Long): Long {
        var n = x
        while (true) {
            n += 1

            val tmp = n xor x
            var cnt = 0
            var bin = 1L
            while (bin < tmp && cnt <= 2) {
                if (bin and tmp != 0L)
                    cnt += 1
                bin = bin shl 1
            }
            if (cnt <= 2)
                break
        }
        return n
    }

    private fun f4(x: Long): Long {
        var n = x
        while ((++n xor x).toString(2).count { it == '1' } > 2);
        return n
    }

    private val Long.bin: String
        get() = toString(2)
}