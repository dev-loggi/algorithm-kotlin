package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * [No. 47] 1257. [S/W 문제해결 응용] 6일차 - K번째 문자열
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 20초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Trie, Suffix Trie, Suffix Array, LCP
 *
 * https://todaycode.tistory.com/104
 * */
public class SWEA_P47 {

    private static int K;
    private static String text;
    private static HashSet<String> substrings;

    private static String solution() {
        substrings = new HashSet<>();

        for (int len = 1; len <= text.length(); len++) {
            for (int s = 0; s <= text.length() - len; s++) {
                substrings.add(text.substring(s, s + len));
            }
        }

        List<String> sorted = substrings.stream()
                .sorted()
                .collect(Collectors.toList());

        if (K - 1 < sorted.size()) {
            return sorted.get(K - 1);
        } else {
            return "none";
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(47)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            K = Integer.parseInt(br.readLine());
            text = br.readLine();

            String answer = solution();

            System.out.printf("#%d %s\n", test_case, answer);
        }

        br.close();
    }
}