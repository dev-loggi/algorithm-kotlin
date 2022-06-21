package utils.exts

import kotlin.math.sqrt

/**
 * [조합]
 * 1. nCr = nPr / r! = n! / r!(n-r)!
 * 2. nCr = n-1Cr-1 + n-1Cr
 * 이항정리
 * 2^n = nC0 + nC1 + ... + nCn
 * 파스칼의 삼각형(이항계수의 성질)
 *
 * */
fun Int.combination(r: Int): Int {
    if (r !in 0..this) return 0
    val n = this
    return if (r == 0 || r == n) 1
    else (n - 1).combination(r - 1) + (n - 1).combination(r)
}

/**
 * [중복 조합]
 * nHr = n+r-1Cr
 *     = n+r-1Cn-1
 * */
fun Int.homoCombination(r: Int): Int {
    val n = this
    return (n + r - 1).combination(r)
}

/**
 * [순열]
 * nPr = n! / (n-r)!
 * */
fun Int.permutation(r: Int): Int {
    val n = this
    return (n.toLong().factorial() / (n - r).factorial().toLong()).toInt()
}


/**
 * [팩토리얼]
 * */
fun Int.factorial(): Int {
    tailrec fun recursive(n: Int, acc: Int): Int =
        if (n <= 0) acc
        else recursive(n - 1, acc * n)
    return recursive(this, 1)
}

/**
 * [팩토리얼]
 * */
fun Long.factorial(): Long {
    tailrec fun recursive(n: Long, acc: Long): Long =
        if (n <= 0) acc
        else recursive(n - 1, acc * n)
    return recursive(this, 1)
}


/**
 * [최대공약수(GCD)]
 * 유클리드 호제법
 * A = BQ + R
 * gcd(A, B) = gcd(B, R)
 * */
tailrec fun gcd(a: Int, b: Int): Int {
    return if (b == 0) a
    else gcd(b, a % b)
}

/**
 * [최소공배수(LCM)]
 * AB = LG (L: 최소공배수, G: 최대공약수)
 * */
fun lcm(a: Int, b: Int): Int {
    return a * b / gcd(a, b)
}

/**
 * [모든 약수 구하기]
 * */
fun Int.divisors(): Set<Int> {
    val n = this
    val divisors = hashSetOf<Int>()

    for (i in 1..sqrt(n.toDouble()).toInt() + 1) {
        if (n % i == 0) {
            divisors.add(i)
            divisors.add(n / i)
        }
    }

    return divisors
}

/**
 * [모든 약수 "쌍" 구하기]
 * */
fun Int.pairOfDivisors(): List<Pair<Int, Int>> {
    val n = this
    val result = mutableListOf<Pair<Int, Int>>()

    for (i in 1..sqrt(n.toDouble()).toInt() + 1) {
        if (n % i == 0) {
            result.add(i to n / i)
        }
    }
    return result
}

/**
 * [진법 변환]
 * */
fun Int.toDecimalOf(k: Int): String {
    // 코틀린에 라이브러리 함수 존재.
    // return toString(k)

    var result = ""
    var temp = this

    while (temp > 0) {
        result = "${temp % k}$result"
        temp /= k
    }

    return result
}

/**
 * [소수 판별]
 * 에라토스테네스의 체
 * */
fun Long.isPrimeNumber(): Boolean {
    if (this < 2) return false

    for (i in 2..sqrt(this.toDouble()).toInt()) {
        if (this % i == 0L)
            return false
    }

    return true
}