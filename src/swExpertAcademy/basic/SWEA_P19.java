package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 20] 3304. 최장 공통 부분 수열
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 최장 공통 부분 수열(LCS, Longest Common Subsequence)
 * https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
 * */
public class SWEA_P19 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(19)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            char[] s1 = (" " + input[0]).toCharArray();
            char[] s2 = (" " + input[1]).toCharArray();

            int answer = solution(s1, s2);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    private static int solution(char[] s1, char[] s2) {
        int[][] lcs = new int[s1.length][s2.length];
        int maxLength = -1;

        for (int i = 1; i < lcs.length; i++) for (int j = 1; j < lcs[i].length; j++) {
            if (s1[i] == s2[j]) {
                lcs[i][j] = lcs[i - 1][j - 1] + 1;
            } else {
                lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
            }

            maxLength = Math.max(maxLength, lcs[i][j]);
        }

        return maxLength;
    }
}