package utils


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

fun <T> combinationAllByRecursion(source: Collection<T>, out: MutableList<List<T>>) {
    for (r in 1..source.size) {
        val temp = mutableListOf<List<T>>()
        combinationByRecursion(source, temp, Array(source.size) { false }, 0, r)
        out.addAll(temp)
    }
}


/**
 * All Combinations
 * flatMap 이용
 * */
fun <T> List<T>.combinationAll(): List<List<T>> {
    var r = listOf(emptyList<T>())

    tailrec fun recursive(ll: List<T>) {
        println("recursive(): $ll")
        if (ll.isEmpty())
            return
        println("   recursive(): r(전)=$r")
        r = r.flatMap { listOf(it + ll.first(), it) }
        println("   recursive(): r(후)=$r")
        return recursive(ll.drop(1))
    }

    recursive(this)
    return r.sortedBy { it.size }
}

// https://www.youtube.com/watch?v=HYKpunR1Nto
fun <T> List<T>.combination(r: Int): List<List<T>> {
    if (r !in 0..size) return emptyList()

    val result = mutableListOf<List<T>>()

    fun dfs(depth: Int, start: Int, basket: List<T>) {
        if (depth < r) {
            for (i in start until size) {
                val newBasket = mutableListOf<T>()
                newBasket.addAll(basket)
                newBasket.add(this[i])

                if (depth + (size - i) >= r) { // r개 가능한 것만
                    dfs(depth + 1, i + 1, newBasket)
                }
            }
        } else {
            result.add(basket)
        }
    }

    dfs(0, 0, emptyList())
    return result
}