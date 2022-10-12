package programmers

class LongestPalindrome {

    fun solution(s: String): Int {
        for (len in s.length downTo 2) {
            var start = 0

            while (start + len <= s.length) {
                var isPalindrome = true

                for (i in 0 until len / 2) {
                    if (s[start + i] != s[start + len - i - 1]) {
                        isPalindrome = false
                        break
                    }
                }

                if (isPalindrome)
                    return len

                start += 1
            }
        }

        return 1
    }
}