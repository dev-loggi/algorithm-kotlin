package programmers.practice.level3

import Solution

/**
 * 베스트 앨범
 * 해시
 * https://programmers.co.kr/learn/courses/30/lessons/42579?language=kotlin
 * */
class BestAlbum : Solution
{
    override fun execute() {
        var answer: IntArray

        answer = solution(
            arrayOf("classic", "pop", "classic", "classic", "pop"),
            intArrayOf(500, 600, 150, 800, 2500)
        )
        println("answer=${answer.contentToString()}\n") // 4, 1, 3, 0

        answer = bestSolution(
            arrayOf("classic", "pop", "classic", "classic", "pop"),
            intArrayOf(500, 600, 150, 800, 2500)
        )
        println("answer=${answer.contentToString()}\n") // 4, 1, 3, 0
    }

    data class GenreGroup(val genre: String) {
        val musics = mutableListOf<Music>()
    }

    data class Music(val genre: String, val id: Int, val play: Int)

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer = mutableListOf<Int>()

        val genreGroups = mutableListOf<GenreGroup>()

        genres.forEachIndexed { index, genre ->
            val group = genreGroups.find { it.genre == genre }
            if (group == null) {
                genreGroups.add(GenreGroup(genre).apply {
                    musics.add(Music(genre, index, plays[index]))
                })
            } else {
                group.musics.add(Music(genre, index, plays[index]))
            }
        }

        genreGroups.forEach { group ->
            group.musics.sortByDescending { it.play }
        }

        genreGroups.sortByDescending { group ->
            group.musics.sumOf { it.play }
        }

        genreGroups.forEach { group ->
            answer.addAll(group.musics.take(2).map { it.id })
        }

        return answer.toIntArray()
    }

    fun bestSolution(genres: Array<String>, plays: IntArray): IntArray {
        return genres.indices.groupBy { genres[it] }
            .toList()
            .sortedByDescending { it.second.sumOf { id -> plays[id] } }
            .map { it.second.sortedByDescending { id -> plays[id] }.take(2) }
            .flatten()
            .toIntArray()
    }
}