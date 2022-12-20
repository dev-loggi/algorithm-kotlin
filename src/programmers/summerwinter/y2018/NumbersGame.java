package programmers.summerwinter.y2018;

import programmers.Programmers;

import java.util.Arrays;

/**
 * Summer/Winter Coding(~2018)
 * 숫자 게임
 *
 * A와 B의 길이는 같습니다.
 * A와 B의 길이는 1 이상 100,000 이하입니다.
 * A와 B의 각 원소는 1 이상 1,000,000,000 이하의 자연수입니다.
 * */
public class NumbersGame implements Programmers.Solution {

    @Override
    public void execute() {
        int answer;

        answer = solution(new int[]{5,1,3,7}, new int[]{2,2,6,8});
        System.out.printf("answer=%d\n", answer); // 3

        answer = solution(new int[]{2,2,2,2}, new int[]{1,1,1,1});
        System.out.printf("answer=%d\n", answer); // 0
    }

    public int solution(int[] A, int[] B) {
        boolean[] used = new boolean[B.length];
        Arrays.sort(A);
        Arrays.sort(B);

        int score = 0;
        int lastJ = -1;

        for (int i = 0; i < A.length; i++) {
            for (int j = lastJ + 1; j < B.length; j++, lastJ = j) {
                if (used[j] || B[j] <= A[i])
                    continue;

                used[j] = true;
                score += 1;
                break;
            }

            if (lastJ == B.length)
                break;
        }

        return score;
    }

    private int maxOf(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int n : arr) if (max < n) max = n;
        return max;
    }
}