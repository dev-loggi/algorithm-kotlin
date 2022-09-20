package programmers.kakao.y2019

import programmers.Programmers
import java.util.*

/**
 * 2019 KAKAO BLIND RECRUITMENT
 * [No. 7] 블록 게임
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42894
 * */
class KAKAO_BLIND_2019_P7 : Programmers.Solution {

    override fun execute() {
        testCases().forEach { testCase ->
            solution(testCase).let { println("answer=$it\n") }
        }
    }

    class Block(val type: Int, val r: Int, val c: Int) {
        override fun toString(): String {
            return "Block(type=$type, r=$r, c=$c)"
        }
    }

    private val blockInfos = arrayOf(
        arrayOf(intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, 2)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(2, -1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(2, 0), intArrayOf(2, 1)),
        arrayOf(intArrayOf(1, 0), intArrayOf(1, -1), intArrayOf(1, -2)),
        arrayOf(intArrayOf(1, -1), intArrayOf(1, 0), intArrayOf(1, 1)),
    )

    private var N = -1
    private lateinit var board: Array<IntArray>
    private lateinit var visited: Array<BooleanArray>

    fun solution(board: Array<IntArray>): Int {
        this.N = board.size
        this.board = board
        this.visited = Array(N) { BooleanArray(N) }

        // 없앨 수 있는 블록 리스트
        val blockList = LinkedList<Block>()

        for (r in board.indices) for (c in board[r].indices) {
            if (board[r][c] == 0 || visited[r][c])
                continue

            getBlock(r, c)?.let {
                blockList.add(it)
            }
        }

        blockList.forEach { println("    $it") }

        val listInitSize = blockList.size

        while (blockList.isNotEmpty()) {
            var removeCount = 0
            val it = blockList.listIterator()

            while (it.hasNext()) {
                val block = it.next()

                if (isPossibleRemoveBlock(block)) {
                    removeCount += 1
                    removeBlock(block)
                    it.remove()
                }
            }

            if (removeCount == 0)
                break
        }

        printBoard()
        return listInitSize - blockList.size
    }
    
    private fun getBlock(r: Int, c: Int): Block? {
        var block: Block? = null
        val blockNumber = board[r][c]

        for (type in blockInfos.indices) {
            val blockInfo = blockInfos[type]
            var matchCount = 0

            for (info in blockInfo) {
                val nr = r + info[0]
                val nc = c + info[1]

                if (nr !in 0 until N || nc !in 0 until N || board[nr][nc] != blockNumber)
                    break

                visited[nr][nc] = true
                matchCount += 1
            }

            if (matchCount == 3) {
                block = Block(type, r, c)
                visited[r][c] = true
                break
            }
        }

        return block
    }
    
    private fun isPossibleRemoveBlock(block: Block): Boolean {
        var r: Int
        var c: Int

        when (block.type) {
            0 -> {
                r = block.r
                c = block.c + 1
                while (r >= 0) if (board[r--][c] != 0) return false
                r = block.r
                c = block.c + 2
                while (r >= 0) if (board[r--][c] != 0) return false
            }
            1 -> {
                r = block.r + 1
                c = block.c - 1
                while (r >= 0) if (board[r--][c] != 0) return false
            }
            2 -> {
                r = block.r + 1
                c = block.c + 1
                while (r >= 0) if (board[r--][c] != 0) return false
            }
            3 -> {
                r = block.r
                c = block.c - 1
                while (r >= 0) if (board[r--][c] != 0) return false
                r = block.r
                c = block.c - 2
                while (r >= 0) if (board[r--][c] != 0) return false
            }
            4 -> {
                r = block.r
                c = block.c - 1
                while (r >= 0) if (board[r--][c] != 0) return false
                r = block.r
                c = block.c + 1
                while (r >= 0) if (board[r--][c] != 0) return false
            }
        }

        return true
    }

    private fun removeBlock(block: Block) {
        board[block.r][block.c] = 0

        for (info in blockInfos[block.type]) {
            board[block.r + info[0]][block.c + info[1]] = 0
        }
    }

    private fun printBoard() {
        println("printBoard")
        board.forEach { println("    ${it.joinToString("")}") }
    }
}

private fun testCases(): Array<Array<IntArray>> {
    return arrayOf(
        // test case 1
        arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,4,0,0,0),
            intArrayOf(0,0,0,0,0,4,4,0,0,0),
            intArrayOf(0,0,0,0,3,0,4,0,0,0),
            intArrayOf(0,0,0,2,3,0,0,0,5,5),
            intArrayOf(1,2,2,2,3,3,0,0,0,5),
            intArrayOf(1,1,1,0,0,0,0,0,0,5),
        ), // answer = 2

        // test case 2
        arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,9,9),
            intArrayOf(0,0,0,0,0,0,4,0,9,6),
            intArrayOf(0,0,0,0,0,4,4,0,9,6),
            intArrayOf(0,7,0,0,3,0,4,0,6,6),
            intArrayOf(7,7,7,2,3,0,0,0,5,5),
            intArrayOf(1,2,2,2,3,3,0,8,0,5),
            intArrayOf(1,1,1,0,0,0,8,8,8,5),
        ), // answer = 3
    )
}