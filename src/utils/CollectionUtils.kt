package utils

// https://minhamina.tistory.com/37 - 순열
// https://minhamina.tistory.com/38 - 조합
// https://notepad96.tistory.com/111 - 조합
object CollectionUtils {


    /**
     * 조합(Combination)
     * 1. 백트래킹
     * */
    fun <T> combinationByBackTracking(
        source: Collection<T>,
        out: MutableList<List<T>>,
        visited: Array<Boolean>,
        start: Int,
        r: Int
    ) {
        if (r == 0) {
            out.addAll(listOf(source.filterIndexed { index, t -> visited[index] }))
            return
        }
        for (i in start until source.size) {
            visited[i] = true
            combinationByBackTracking(source, out, visited, i + 1, r - 1)
            visited[i] = false
        }
    }

    /**
     * 조합(Combination) - 모든 경우
     * 1. 백트래킹
     * */
    fun <T> combinationAllByBackTracking(source: Collection<T>, out: MutableList<List<T>>) {
        for (r in 1..source.size) {
            val temp = mutableListOf<List<T>>()
            combinationByBackTracking(source, temp, Array(source.size) { false }, 0, r)
            out.addAll(temp)
        }
    }

    /**
     * 조합(Combination)
     * 2. 재귀(Recursion)
     * */
    fun <T> combinationByRecursion(
        source: Collection<T>,
        out: MutableList<List<T>>,
        visited: Array<Boolean>,
        depth: Int,
        r: Int
    ) {
        if (r == 0) {
            out.addAll(listOf(source.filterIndexed { index, t -> visited[index] }))
            return
        } else if (depth == source.size) {
            return
        }
        visited[depth] = true
        combinationByRecursion(source, out, visited, depth + 1, r - 1)

        visited[depth] = false
        combinationByRecursion(source, out, visited, depth + 1, r)
    }

    /**
     * 조합(Combination) - 모든 경우
     * 2. 재귀(Recursion)
     * */
    fun <T> combinationAllByRecursion(source: Collection<T>, out: MutableList<List<T>>) {
        for (r in 1..source.size) {
            val temp = mutableListOf<List<T>>()
            combinationByBackTracking(source, temp, Array(source.size) { false }, 0, r)
            out.addAll(temp)
        }
    }



//    fun <T> List<T>.combination(): List<List<T>> {
//
//
//        tailrec fun recursive() {
//            recursive()
//        }
//
//
//    }
}