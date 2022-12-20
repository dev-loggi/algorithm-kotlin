package programmers.summerwinter.y2018;

import programmers.Programmers;

import java.util.Arrays;

/**
 * Summer/Winter Coding(~2018)
 * 쿠키 구입
 * */
public class BuyCookies implements Programmers.Solution {

    @Override
    public void execute() {
        int answer;
        answer = solution(new int[]{1,1,2,3});
        System.out.printf("answer=%d\n", answer); // 3
        answer = solution(new int[]{1,2,4,5});
        System.out.printf("answer=%d\n", answer); // 0
    }

    public int solution(int[] cookie) {
        int N = cookie.length - 1;
        int sumMax = 0;

        for (int m = 0; m < N; m++) {
            int x = m;
            int y = m + 1;
            int sumL = cookie[x];
            int sumR = cookie[y];

            while (true) {
                if (sumL == sumR)
                    sumMax = Math.max(sumMax, sumL);

                if (0 < x && sumL <= sumR) {
                    sumL += cookie[--x];
                } else if (y < N && sumL >= sumR) {
                    sumR += cookie[++y];
                } else {
                    break;
                }
            }
        }

        return sumMax;
    }

    public int wrongSolution(int[] cookie) {
        System.out.printf("solution(): cookie=%s\n", Arrays.toString(cookie));
        int N = cookie.length - 1;
        int maxSum = 0;

        int totalSum = 0;
        for (int n : cookie) {
            totalSum += n;
        }

        for (int x = 0; x < N; x++) {
            int sum = totalSum;
            println("x=%d, sum=%d", x, sum);

            for (int y = N; y > x; y--) {
                int sumL = cookie[x];
                int sumR = sum - cookie[x];
                println("    y=%d, sum=%d", y, sum);

                for (int m = x; m < y; m++) {
                    System.out.printf("        (%d, %d, %d), sum=(%d, %d)\n", x, m, y, sumL, sumR);
                    if (sumL == sumR)
                        maxSum = Math.max(maxSum, sumL);

                    sumL += cookie[x + 1];
                    sumR -= cookie[x + 1];
                }

                sum -= cookie[y];
            }

            totalSum -= cookie[x];
        }

        return maxSum;
    }

    private void println(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}