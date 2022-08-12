package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 26] 4038. [Professional] 단어가 등장하는 횟수
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * [String Matching]
 * 1. Brute-Force
 * 2. 라빈-카프(Rabin-Karp) 알고리즘 - 해시 함수
 * 3. KMP(Knuth–Morris–Pratt) 알고리즘
 * 4. 보이어-무어(Boyer-Moore) 알고리즘
 *
 * https://bowbowbow.tistory.com/6
 * https://newdeal123.tistory.com/67 - 라빈 카프
 * https://www.youtube.com/watch?v=yWWbLrV4PZ8 - KMP
 * */
public class SWEA_P26 {

    private static final int BIG_PRIME = 100003;
//    private static final int SML_PRIME = 33;
    private static final int SML_PRIME = 103;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(26)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine()); // (1 ≤ T ≤ 20)
        //T = 4;
        for (int test_case = 1; test_case <= T; test_case++) {
            String content = sc.nextLine(); // 1 ≤ content length ≤ 500,000
            String word = sc.nextLine(); // 1 ≤ word length ≤ 100,000

            int answer = solutionWithRabinKarp(content.toCharArray(), word.toCharArray());

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    /**
     * KMP Algorithm
     * */
    private static int solutionWithKMP(char[] text, char[] pattern) {
        int count = 0;
        int[] lps = makeLpsTable(pattern);

        int j = 0;
        for (int i = 0; i < text.length; i++) {
            while (j > 0 && text[i] != pattern[j]) {
                j = lps[j - 1];
            }
            if (text[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    count++;
                    j = lps[j];
                } else {
                    j++;
                }
            }
        }

        return count;
    }

    private static int[] makeLpsTable(char[] pattern) {
        // longest proper prefix which is also suffix
        int[] lps = new int[pattern.length];

        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = lps[j - 1];
            }
            if (pattern[i] == pattern[j]) {
                lps[i] = ++j;
            }
        }

        return lps;
    }

    /**
     * Rabin-Karp Algorithm
     * */
    private static int solutionWithRabinKarp(char[] text, char[] pattern) {
        if (text.length < pattern.length)
            return 0;

        int count = 0;
        int patternLength = pattern.length;

        long patternHash = 0;
        long hash = 0;

        for (int i = 0; i < patternLength; i++) {
            patternHash = (patternHash * SML_PRIME + pattern[i]) % BIG_PRIME;
            hash = (hash * SML_PRIME + text[i]) % BIG_PRIME;
//            patternHash = ((patternHash << 1) + pattern[i]) % BIG_PRIME;
//            hash = ((hash << 1) + text[i]) % BIG_PRIME;
        }

        count += (hash == patternHash) && isMatched(text, pattern.length - 1, pattern) ? 1 : 0;

        long n = 1;

        for (int x = 0; x < patternLength - 1; x++) {
            n = (n * SML_PRIME) % BIG_PRIME;
//            n = (n << 1) % BIG_PRIME;
        }

        // rolling hash, sliding window
        for (int i = patternLength; i < text.length; i++) {
            int old = text[i - patternLength];
            int young = text[i];

            hash = ((hash - old * n) * SML_PRIME + young) % BIG_PRIME;
//            hash = (((hash - old * n) << 1) + young) % BIG_PRIME;

            if (hash < 0) {
                hash = hash + BIG_PRIME;
            }

            if (hash == patternHash) {
                count += isMatched(text, i, pattern) ? 1 : 0;
            }
        }

        return count;
    }

    public static boolean isMatched(char[] text, int end, char[] pattern) {
        for (int i = end - pattern.length + 1, j = 0; i <= end; i++, j++)
            if (text[i] != pattern[j]) return false;
        return true;
    }

    /**
     * Brute-Force
     * */
    private static int solutionWithBruteForce(String content, String word) {
        int count = 0;

        for (int i = 0; i <= content.length() - word.length(); i++) {
            if (content.charAt(i) != word.charAt(0))
                continue;

            for (int j = 1; j < word.length(); j++) {
                if (content.charAt(i + j) != word.charAt(j))
                    break;

                if (j == word.length() - 1)
                    count++;
            }
        }

        return count;
    }
}
