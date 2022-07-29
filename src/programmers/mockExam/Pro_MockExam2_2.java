package programmers.mockExam;

import java.util.HashSet;

public class Pro_MockExam2_2 {

    public void main() {
        int answer;

        answer = solution(
                new int[] {1, 2, 1, 3, 1, 4, 1, 2}
        );
        log("answer=%d", answer); // 2

        answer = solution(
                new int[] {1, 2, 3, 1, 4}
        );
        log("answer=%d", answer); // 0
    }

    public int solution(int[] topping) {
        HashSet<Integer> setLtr = new HashSet<>();
        HashSet<Integer> setRtl = new HashSet<>();

        int[] accLtr = new int[topping.length];
        int[] accRtl = new int[topping.length];

        for (int i = 0; i < topping.length; i++) {
            setLtr.add(topping[i]);
            accLtr[i] = setLtr.size();
        }

        for (int i = topping.length - 1; i >= 0; i--) {
            setRtl.add(topping[i]);
            accRtl[i] = setRtl.size();
        }

        int count = 0;

        for (int i = 0; i < accLtr.length - 1; i++) {
            if (accLtr[i] == accRtl[i + 1])
                count++;
        }

        return count;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
