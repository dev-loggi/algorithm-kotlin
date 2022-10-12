package utils.exts

/**
 * 순열
 * */
fun <T> List<T>.permutationAll(): List<List<T>> {
    fun recursive(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el): List<List<T>> {
        return if (sub.isEmpty()) listOf(fin)
        else sub.flatMap { recursive(el, fin + it, sub - it) }
    }
    return recursive(this)
}

/**
 * 순열
 * */
fun <T> List<T>.permutation(r: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()

    fun recursive(depth: Int, list: List<T>) {
        if (depth == r) {
            result.add(list)
            return
        }

        for (i in indices) {
            if (list.contains(this[i])) continue

            val newList = list.toMutableList()
                .also { it.add(this[i]) }

            recursive(depth + 1, newList)
        }
    }

    recursive(0, emptyList())
    return result
}

/**
 * 중복 순열
 * */
fun <T> List<T>.homoPermutation(r: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    fun recursive(depth: Int, list: List<T>) {
        if (depth == r) {
            result.add(list)
            return
        }
        for (e in this)
            recursive(depth + 1, list.toMutableList().also { it.add(e) })
    }
    recursive(0, emptyList())
    return result
}

/**
 * 조합
 * */
fun <T> List<T>.combination(r: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()

    fun recursive(depth: Int, start: Int, list: List<T>) {
        if (depth == r) {
            result.add(list)
            return
        }

        for (i in start until size) {
            recursive(depth + 1, i + 1, list.toMutableList().also { it.add(this[i]) })
        }
    }

    recursive(0, 0, emptyList())
    return result
}

/**
 * 중복 조합
 * */
fun <T> List<T>.homoCombination(r: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()

    fun recursive(depth: Int, start: Int, list: List<T>) {
        if (depth == r) {
            result.add(list)
            return
        }

        for (i in start until size) {
            recursive(depth + 1, i, list.toMutableList().also { it.add(this[i]) })
        }
    }

    recursive(0, 0, emptyList())
    return result
}


/**
 * 조합 All
 * */
fun <T> List<T>.combinationAll(): List<List<T>> {
    return (1..size).map { r -> this.combination(r) }.flatten()
}

/**
 * 조합 All
 * flatMap 이용
 * */
fun <T> List<T>.combinationAll2(): List<List<T>> {
    var r = listOf(emptyList<T>())

    tailrec fun recursive(ll: List<T>) {
        if (ll.isEmpty())
            return
        r = r.flatMap { listOf(it + ll.first(), it) }
        return recursive(ll.drop(1))
    }

    recursive(this)
    return r.sortedBy { it.size }
}

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