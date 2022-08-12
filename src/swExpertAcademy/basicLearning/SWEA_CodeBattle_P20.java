package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 20] 3282. 0/1 Knapsack
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * DP
 * https://www.youtube.com/watch?v=rhda6lR5kyQ
 * */
public class SWEA_CodeBattle_P20 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(20)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] info = sc.nextLine().split(" ");
            int N = Integer.parseInt(info[0]); // 물건의 갯수 (1 ≤ N ≤ 100)
            int K = Integer.parseInt(info[1]); // 최대 부피 (1 ≤ K ≤ 1000)

            int[][] items = new int[N + 1][2];

            for (int i = 1; i <= N; i++) {
                items[i] = Arrays.stream(sc.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            int answer = solution2(N, K, items);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    /** DP - Top Down */
    private static int solution1(int N, int K, int[][] items) {
        return subSolution(N, K, items);
    }

    private static int subSolution(int n, int w, int[][] items) {
        if (n == 0 || w <= 0) {
            return 0;
        } else {
            int sub1 = (items[n][0] <= w)
                    ? subSolution(n - 1, w - items[n][0], items) + items[n][1]
                    : 0;

            int sub2 = subSolution(n - 1, w, items);

            return Math.max(sub1, sub2);
        }
    }

    /** DP - Bottom Up, Memoization */
    private static int solution2(int N, int K, int[][] items) {
        int[][] dp = new int[N + 1][K + 1];

        for (int n = 1; n <= N; n++) for (int w = 1; w <= K; w++) {
            int sub1 = items[n][0] <= w
                    ? dp[n - 1][w - items[n][0]] + items[n][1]
                    : 0;

            int sub2 = dp[n - 1][w];

            dp[n][w] = Math.max(sub1, sub2);
        }

        return dp[N][K];
    }
}