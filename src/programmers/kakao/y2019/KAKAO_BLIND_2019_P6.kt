package programmers.kakao.y2019

import programmers.Programmers
import java.util.*

/**
 * 2019 KAKAO BLIND RECRUITMENT
 * [No. 6] 매칭 점수
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42893
 * */
class KAKAO_BLIND_2019_P6 : Programmers.Solution {

    override fun execute() {
        solution(
            "blind",
            arrayOf(
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"
            )
        ).let { println("answer=$it") }
        solution(
            "Muzi",
            arrayOf(
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>",
                "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"
            )
        ).let { println("answer=$it") }
    }

    data class WebPage(
        val index: Int,
        val url: String,
        val outLinks: List<String>,
        val baseScore: Int
    ) : Comparable<WebPage> {

        var matchingScore = 0.0

        override fun compareTo(other: WebPage): Int {
            return if (matchingScore > other.matchingScore) -1
            else if (matchingScore < other.matchingScore) 1
            else index - other.index
        }
    }

    fun solution(word: String, pages: Array<String>): Int {
        val linkMap = hashMapOf<String, LinkedList<WebPage>>()

        return pages.mapIndexed { index, page -> parse(index, page, word) }
            .onEach { linkMap[it.url] = LinkedList() }
            .onEach { page ->
                page.outLinks.forEach { outLink ->
                    linkMap[outLink]?.add(page)
                }
            }
            .onEach {
                val linkScore = linkMap[it.url]!!.fold(0.0) { sum, page ->
                    sum + page.baseScore / page.outLinks.size.toFloat()
                }

                it.matchingScore = it.baseScore + linkScore
            }
            .sorted()[0].index
    }

    private fun parse(index: Int, page: String, word: String): WebPage {
        val metaIndexStart = page.indexOf("<meta property")

        val urlIndexStart = page.indexOf("content=", metaIndexStart) + 9
        val urlIndexEnd = page.indexOf('"', urlIndexStart)

        val url = page.substring(urlIndexStart, urlIndexEnd).lowercase()

        val bodyIndexStart = page.indexOf("<body>", urlIndexEnd) + 6
        val bodyIndexEnd = page.indexOf("</body>", bodyIndexStart)

        var bodyContent = page.substring(bodyIndexStart, bodyIndexEnd)

        val outLinks = mutableListOf<String>()
        val outLinkIndexStack = ArrayDeque<IntRange>()

        var aIndexStart = bodyIndexStart
        var aIndexEnd = bodyIndexStart

        while (true) {
            aIndexStart = page.indexOf("<a", aIndexEnd)

            if (aIndexStart == -1)
                break

            aIndexEnd = page.indexOf("</a>", aIndexStart) + 4

            val hrefIndexStart = page.indexOf("href=", aIndexStart)
            val hrefIndexEnd = page.indexOf('"', hrefIndexStart + 6)

            val outLink = page.substring(hrefIndexStart + 6, hrefIndexEnd).lowercase()

            outLinks.add(outLink)
            outLinkIndexStack.push(aIndexStart - bodyIndexStart until aIndexEnd - bodyIndexStart)
        }

        while (outLinkIndexStack.isNotEmpty()) {
            val range = outLinkIndexStack.pop()

            bodyContent = bodyContent.removeRange(range)
        }

        return WebPage(index, url, outLinks, getBaseScore(bodyContent, word))
    }

    private fun getBaseScore(body: String, word: String): Int {
        var (score, index) = 0 to 0

        while (true) {
            index = body.indexOf(word, index, true)

            if (index == -1)
                break

            val prev = body.getOrNull(index - 1) ?: ' '
            val next = body.getOrNull(index + word.length) ?: ' '

            if (prev.isNotLetter() && next.isNotLetter())
                score += 1

            index += word.length
        }
        println("getBaseScore()")
        println("    body=$body")
        println("    word=$word")
        println("    baseScore=$score")
        return score
    }

    private fun Char.isNotLetter(): Boolean {
        return code !in 65..90 && code !in 97..122
    }
}