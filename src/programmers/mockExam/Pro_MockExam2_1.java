package programmers.mockExam;

import java.util.Arrays;

public class Pro_MockExam2_1 {

    public void main() {
        int answer;
        answer = solution(new int[] {-2, 3, 0, 2, -5});
        log("answer=%d", answer); // 2

        answer = solution(new int[] {-3, -2, -1, 0, 1, 2, 3});
        log("answer=%d", answer); // 5

        answer = solution(new int[] {-1, 1, -1, 1});
        log("answer=%d", answer); // 0
    }

    public int solution(int[] number) {
        int n = number.length;

        int[] orders = new int[3];
        for (int i = 0; i < orders.length; i++) {
            orders[i] = i;
        }

        return combination(number, n, 3, 0, 0, orders);
    }

    private int combination(int[] number, int n, int r, int depth, int start, int[] arr) {
        if (depth == r) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += number[arr[i]];
            }
            return sum == 0 ? 1 : 0;
        }

        int count = 0;

        for (int i = start; i < n; i++) {
            int tmp = arr[depth];

            arr[depth] = i;
            count += combination(number, n, r, depth + 1, i + 1, arr);
            arr[depth] = tmp;
        }

        return count;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
