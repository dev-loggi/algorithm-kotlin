package programmers.techCamp

class Solution2 {

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        if (N == 1) return 1
        return traverse(intArrayOf(1, 0, 0), 1, 0, k, road).distinct().size
    }

    private fun traverse(preRoad: IntArray, start: Int, totalDistance: Int, k: Int, roads: Array<IntArray>): HashSet<Int> {
        val countrySet = hashSetOf<Int>().apply {
            add(start)
        }

        roads
            .filter { nextRoad ->
                nextRoad[0] == start || nextRoad[1] == start
            }
            .filterNot { it.contentEquals(preRoad) }
            .forEach { nextRoad ->
                val newStart = if (nextRoad[0] != start) nextRoad[0] else nextRoad[1]

                val newDistance = nextRoad[2] + totalDistance
                if (newDistance <= k) {
                    countrySet.addAll(
                        traverse(nextRoad, newStart, newDistance, k, roads)
                    )
                }
            }

        return countrySet
    }


    private fun getLog(arr: IntArray): String =
        StringBuilder("[").apply {
            val lastIndex = arr.lastIndex
            arr.forEachIndexed { index, i ->
                if (index < lastIndex) append("$i, ")
                else append("${i}]")
            }
        }.toString()

}