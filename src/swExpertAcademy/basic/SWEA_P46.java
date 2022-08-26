package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [No. 46] 1256. [S/W 문제해결 응용] 6일차 - K번째 접미어
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 20초 / Java의 경우 40초 / Python의 경우 40초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Trie, Compressed Trie, Suffix Trie
 * */
public class SWEA_P46 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(46)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int K = Integer.parseInt(br.readLine());
            String text = br.readLine();

            String answer = solution(K, text);

            System.out.printf("#%d %s\n", test_case, answer);
        }

        br.close();
    }

    private static String solution(int K, String text) {
        String[] suffixArray = new String[text.length()];

        for (int i = 0; i < text.length(); i++) {
            suffixArray[i] = text.substring(i);
        }

        Arrays.sort(suffixArray);

        return suffixArray[K - 1];
    }
}