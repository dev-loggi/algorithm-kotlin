package programmers.kakao.y2019

import programmers.Programmers

/**
 * 2019 KAKAO BLIND RECRUITMENT
 * [NO. 4] 길 찾기 게임
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42892
 * */
class KAKAO_BLIND_2019_P4 : Programmers.Solution {

    override fun execute() {
        solution(arrayOf(
            intArrayOf(5 ,3),
            intArrayOf(11 ,5),
            intArrayOf(13 ,3),
            intArrayOf(3 ,5),
            intArrayOf(6 ,1),
            intArrayOf(1 ,3),
            intArrayOf(8 ,6),
            intArrayOf(7 ,2),
            intArrayOf(2 ,2),
        )).let {
            // [[7,4,6,9,1,8,5,2,3],[9,6,5,8,1,4,3,2,7]]
            println("answer=${it[0].contentToString()}")
            println("answer=${it[1].contentToString()}")
        }
    }

    class Node(val x: Int, val y: Int, val value: Int) : Comparable<Node> {
        var left: Node? = null
        var right: Node? = null

        override fun compareTo(other: Node): Int {
            return if (y != other.y) other.y - y
            else x - other.x
        }
    }

    fun solution(nodeInfo: Array<IntArray>): Array<IntArray> {
        val nodes = nodeInfo
            .asSequence()
            .mapIndexed { index, arr -> Node(arr[0], arr[1], index + 1) }
            .sorted()
            .toList()

        val root = nodes[0]

        for (i in 1..nodes.lastIndex) {
            val node = nodes[i]

            var parent = root

            while (true) {
                if (node.x < parent.x) {
                    if (parent.left != null) {
                        parent = parent.left!!
                    } else {
                        parent.left = node
                        break
                    }
                } else {
                    if (parent.right != null) {
                        parent = parent.right!!
                    } else {
                        parent.right = node
                        break
                    }
                }
            }
        }

        val preOrder = IntArray(nodes.size)
        val postOrder = IntArray(nodes.size)
        var preOrderIdx = 0
        var postOrderIdx = 0

        fun traverseByPreOrder(node: Node?) {
            if (node == null)
                return

            preOrder[preOrderIdx++] = node.value

            traverseByPreOrder(node.left)
            traverseByPreOrder(node.right)
        }

        fun traverseByPostOrder(node: Node?) {
            if (node == null)
                return

            traverseByPostOrder(node.left)
            traverseByPostOrder(node.right)

            postOrder[postOrderIdx++] = node.value
        }

        traverseByPreOrder(root)
        traverseByPostOrder(root)

        return arrayOf(preOrder, postOrder)
    }
}