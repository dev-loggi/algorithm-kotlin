package swExpertAcademy.practicalTraining

import java.util.*
import kotlin.collections.HashSet

class SWEA_PRO_P09_UserSolution_kt : SWEA_PRO_P09.UserSolution {

    companion object {
        private const val NULL = 0.toChar()
    }

    private var lastId = 0
    private lateinit var loser: BooleanArray
    private lateinit var wordMap: Array<TreeSet<String>>
    private lateinit var usedWordSet: HashSet<String>
    private lateinit var usedWordQueue: ArrayDeque<String>

    override fun init(N: Int, M: Int, mWords: Array<out CharArray>) {
        lastId = N
        loser = BooleanArray(N + 1)
        wordMap = Array(26) { TreeSet() }
        usedWordSet = HashSet()
        usedWordQueue = ArrayDeque()

        for (i in 0 until M) {
            val word = mWords[i].toWord()
            wordMap[word[0].code - 97].add(word)
            usedWordSet.add(word)
        }
    }

    override fun playRound(mID: Int, mCh: Char): Int {
        var word = wordMap[mCh.code - 97].pollFirst()!!

        usedWordQueue.offer(word)
        usedWordSet.add(word)

        var nextId = mID
        while (true) {
            nextId++
            if (nextId > lastId) nextId = 1

            if (loser[nextId])
                continue

            word = wordMap[word.last().code - 97].pollFirst()
                ?: break

            usedWordQueue.offer(word)
            usedWordSet.add(word)
        }

        while (usedWordQueue.isNotEmpty()) {
            val reversedWord = usedWordQueue.poll().reversed()

            if (usedWordSet.contains(reversedWord))
                continue

            wordMap[reversedWord.first().code - 97].add(reversedWord)
        }

        loser[nextId] = true
        return nextId
    }

    private fun CharArray.toWord(): String {
        val sb = StringBuilder()
        var i = 0
        while (this[i] != NULL) sb.append(this[i++])
        return sb.toString()
    }
}