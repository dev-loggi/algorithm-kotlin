package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 44] 8898. 3차원 농부
 *
 * 시간 : 27개 테스트케이스를 합쳐서 C의 경우 8초 / C++의 경우 8초 / Java의 경우 20초 / Python의 경우 20초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 이분 탐색
 * */
public class SWEA_P44 {

    private static int min, count;
    private static int[] cows;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(44)));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            min = Integer.MAX_VALUE;
            count = 0;

            int N = sc.nextInt(); // 소의 마리수 (1 ≤ N ≤ 500,000)
            int M = sc.nextInt(); // 말의 마리수 (1 ≤ M ≤ 500,000)

            int c1 = sc.nextInt(); // 소 x좌표 (-10^8 ≤ c1 ≤ 10^8)
            int c2 = sc.nextInt(); // 말 x좌표 (-10^8 ≤ c2 ≤ 10^8)

            // 소 z좌표 (-10^8 ≤ z1i ≤ 10^8)
            cows = new int[N];
            for (int i = 0; i < N; i++) {
                cows[i] = sc.nextInt();
            }
            Arrays.sort(cows);

            // 말 z좌표 (-10^8 ≤ z2i ≤ 10^8)
            for (int i = 0; i < M; i++) {
                int horse = sc.nextInt();
                int cowIdx = binarySearch(horse);

                if (0 <= cowIdx && cowIdx < N) {
                    check(cows[cowIdx] - horse);
                }

                cowIdx -= 1;

                if (0 <= cowIdx && cowIdx < N && cows[cowIdx] != horse) {
                    check(cows[cowIdx] - horse);
                }
            }

            int dx = Math.abs(c1 - c2);
            System.out.printf("#%d %d %d\n", test_case, dx + min, count);
        }
    }

    private static int binarySearch(int horse) {
        int start = 0;
        int end = cows.length - 1;
        int mid = (start + end) >> 1;

        if (horse < cows[start])
            return 0;
        if (cows[end] < horse)
            return end;

        while (start <= end) {
            mid = (start + end) >> 1;

            if (cows[mid] == horse) break;
            else if (cows[mid] < horse) start = mid + 1;
            else end = mid - 1;
        }

        if (cows[mid] < horse)
            mid += 1;

        return mid;
    }

    private static void check(int dist) {
        dist = Math.abs(dist);
        if (dist == min) {
            count += 1;
        } else if (dist < min) {
            min = dist;
            count = 1;
        }
    }
}