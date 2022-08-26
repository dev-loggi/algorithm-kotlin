package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 45] 3813. [Professional] 그래도 수명이 절반이 되어서는...
 *
 * 시간 : 50개 테스트케이스를 합쳐서 C의 경우 3초 / C++의 경우 3초 / Java의 경우 5초 / Python의 경우 10초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * [제약 사항]
 * 1. 플래시 드라이브의 블록 수 N은 최대 200,000이다. (1 ≤ N ≤ 200,000)
 * 2. 각 블록의 Wear Level 값은 1 이상 200,000이하의 자연수이다. (1 ≤ Wi ≤ 200,000)
 * 3. 초기 데이터의 덩어리 수 K는 1 이상 N 이하이다. (1 ≤ K ≤ N)
 * 4. 초기 데이터의 덩어리 당 블록 수의 합은 1 이상 N 이하이다. (1 ≤ S1 + S2 + … + Sk ≤ N)
 *
 * Binary Search, Parametric Search
 * */
public class SWEA_P45 {

    private static final int MAX_BLOCK = 200_000;

    private static int N, K;
    private static final int[] wear = new int[MAX_BLOCK + 1]; // Wear Level
    private static final int[] s = new int[MAX_BLOCK + 1]; // 블록 크기

    private static int solution() {
        int start = 1;
        int end = MAX_BLOCK;
        while (start < end) {
            int mid = (start + end) / 2;

            if (isPossible(mid))
                end = mid;
            else
                start = mid + 1;
        }
        return end;
    }

    private static boolean isPossible(int value) {
        int k = 1; // 첫번째부터 확인
        int count = 0;

        for (int i = 1; i <= N; i++) {
            if (wear[i] <= value)
                count += 1;
            else
                count = 0;

            if (count == s[k]) { // 덩어리 크기를 만족하는지 확인
                k += 1;
                count = 0;

                if (k > K) // 모든 덩어리를 만족하였는지 확인
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(45)));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt(); // 플래시 드라이브의 블록 수 (1 ≤ N ≤ 200,000)
            K = sc.nextInt(); // 초기 데이터의 덩어리 수 (1 ≤ K ≤ N)

            for (int i = 1; i <= N; i++)
                wear[i] = sc.nextInt();
            for (int i = 1; i <= K; i++)
                s[i] = sc.nextInt();

            int answer = solution();

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}