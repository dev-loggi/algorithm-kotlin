package boj.dfsBfs

import boj.BOJSolution
import java.util.ArrayDeque

/**
 * 8111
 * 0과 1
 * https://www.acmicpc.net/problem/8111
 * https://velog.io/@hyeon930/BOJ-8111-0%EA%B3%BC1-Java
 * */
class BOJ_8111_ZeroAndOne : BOJSolution(info(), testCases()) {

    companion object {
        private const val MAX = 20000
    }

    override fun runEachSolution() {
        val T = readLine()!!.toInt() // 테스트 케이스 갯수(T < 10)
        val answers = mutableListOf<String>()

        repeat(T) {
            val N = readLine()!!.toInt() // 1 ≤ N ≤ 20,000

            answers.add(bfs(N))
        }

        answers.forEach { println(it) }
    }

    private fun bfs(N: Int): String {
        val answer = StringBuilder()

        val map = hashMapOf<Int, Char>()
        val visited = BooleanArray(MAX)
        val parent = IntArray(MAX)

        // 가장 앞에는 항상 1이 온다. 따라서 1부터 시작한다.
        val queue = ArrayDeque(listOf(1))
        val nextNumbers = IntArray(2)

        // 1이 루트라서 부모배열을 -1로 초기화한다.
        parent[1] = -1
        visited[1] = true

        // 1을 넣을때는 1을 붙였다.
        map[1] = '1'
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            // 뒤에 0을 붙이는 경우와 1을 붙이는 경우
            // 오버플로우 방지를 위해 mod N 연산을 계속해준다.
            // 원래 숫자에 0 또는 1을 붙인것과 mod연산 후에 0 또는 1을 붙인것과 mod연산을 하면 같다.
            nextNumbers[0] = cur * 10 % N
            nextNumbers[1] = (cur * 10 + 1) % N

            for (i in nextNumbers.indices) {
                val next = nextNumbers[i]

                // 같은 숫자가 나온적 있으면 반복할 필요가 없다.
                if (visited[next]) continue

                // 새로운 나머지가 나왔을 때 어떤 수를 뒤에 붙였는지 기록한다.
                // 숫자를 char형으로 바꾸기 위해서 + '0' 해준다.
                map[next] = (i + '0'.code).toChar()

                // 후에 출력을 위한 순서를 기록하기 위해 부모를 기억한다.
                parent[next] = cur
                visited[next] = true

                queue.offer(next)

                // 나누어 떨어지면 종료한다.
                if (next == 0) {
                    queue.clear()
                    break
                }
            }
        }

        // 부모를 계속 찾아가며 루트부터 말단까지 출력한다.
        fun backTracking(idx: Int) {
            if (idx == -1)
                return

            backTracking(parent[idx])
            answer.append(map[idx])
        }

        // 마지막에 나누어 떨어졌기 때문에 0을 인자로 넣는다.
        backTracking(0)

        return answer.toString()
    }
}

private fun info() = BOJSolution.Info(
    no = 8111,
    title = "0 과 1",
    category = listOf(BOJSolution.BFS),
    problemUrl = "https://www.acmicpc.net/problem/8111"
)

private fun testCases() = listOf(
    BOJSolution.TestCase(
        input = "6\n" +
                "17\n" +
                "11011\n" +
                "17\n" +
                "999\n" +
                "125\n" +
                "173",
        output = "11101\n" +
                "11011\n" +
                "11101\n" +
                "111111111111111111111111111\n" +
                "1000\n" +
                "1011001101"
    )
)