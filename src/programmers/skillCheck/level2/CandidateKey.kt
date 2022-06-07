package programmers.skillCheck.level2

class CandidateKey {

    // [["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],
    // ["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]]
    // answer=2

    // ["a","1","aaa","c","ng"],
    // ["b","1","bbb","c","g"],
    // ["c","1","aaa","d","ng"],
    // ["d","2","bbb","d","ng"]
    // answer=3, [0, 23, 134]

    // ["a","1","aaa","c","ng"],
    // ["a","1","bbb","e","g"],
    // ["c","1","aaa","d","ng"],
    // ["d","2","bbb","d","ng"]
    // answer=5, [02, 03, 04, 13, 23]
    fun execute() {
        var answer = solution(
            arrayOf(
                arrayOf("100","ryan","music","2"),
                arrayOf("200","apeach","math","2"),
                arrayOf("300","tube","computer","3"),
                arrayOf("400","con","computer","4"),
                arrayOf("500","muzi","music","3"),
                arrayOf("600","apeach","music","2"),
            )
        )
        println("answer=$answer") // 2
    }

    fun solution(relation: Array<Array<String>>): Int {
        val superKeys = hashSetOf<Set<Int>>()

        val attributes = (0..relation.first().lastIndex).toList()

        // Attribute Combinations
        for (r in 1..attributes.size) {
            val out = mutableListOf<List<Int>>()
            combination(attributes, out, Array(attributes.size) { false }, 0, r)
            println("out=$out")

            // 1. 유일성을 만족하는 슈퍼키를 모두 찾는다.
            out.forEach { attrs ->
                if (canUniqueness(relation, attrs)) {
                    superKeys.add(attrs.toHashSet())
                }
            }
        }

        println("superKeys=$superKeys")


        // 2. 슈퍼키 중에서 최소성을 만족하는 후보키를 찾는다.
        return superKeys.filter { canMinimality(superKeys, it) }
            .also { println("candidateKeys=$it") }
            .size
    }

    private fun canUniqueness(relation: Array<Array<String>>, attributes: List<Int>): Boolean {
        val hashSet = hashSetOf<String>()

        for (raw in relation.indices) {

            val identifier = attributes.fold("") { acc, attrIndex ->
                acc + relation[raw][attrIndex]
            }

            hashSet.add(identifier)

            // 중복 튜플 확인
            if (hashSet.size <= raw) {
                return false
            }
        }

        return true
    }

    private fun canMinimality(superKeys: Set<Set<Int>>, target: Set<Int>): Boolean {
        return superKeys.count { target.containsAll(it) } < 2
    }

    private fun <T> combination(
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
        combination(source, out, visited, depth + 1, r - 1)

        visited[depth] = false
        combination(source, out, visited, depth + 1, r)
    }

    private fun bestSolution(relation: Array<Array<String>>): Int {

        fun <T> List<T>.powerSet(): List<List<T>> {
            var r = listOf(emptyList<T>())
            tailrec fun recursive(ll: List<T>) {
                if (ll.isEmpty())
                    return
                r = r.flatMap { listOf(it + ll.first(), it) }
                return recursive(ll.drop(1))
            }

            recursive(this)
            return r.toList()
        }

        val columnSize = relation.first().size
        val tupleSize = relation.size
        val powerSets = (1..columnSize).toList().powerSet().dropLast(1).sortedBy { it.size }

        val candidateKey = mutableListOf<List<Int>>()

        fun <T> List<T>.isPartOf(l: List<T>): Boolean = all { it in l }

        return powerSets.filter { set ->
            if (candidateKey.any { it.isPartOf(set) })
                false
            else {
                val keys = relation.map { tuple -> set.fold("") { acc, i -> acc + tuple[i - 1] } }

                if (keys.distinct().size == tupleSize) {
                    candidateKey.add(set)
                    true
                } else
                    false
            }
        }.size
    }
}