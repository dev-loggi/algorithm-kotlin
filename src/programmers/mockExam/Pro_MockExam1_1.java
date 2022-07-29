package programmers.mockExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Pro_MockExam1_1 {

    public void main() {
        String answer;
        answer = solution("100", "2345");
        log("answer1=%s\n", answer); // -1

        answer = solution("100", "203045");
        log("answer2=%s\n", answer); // 0

        answer = solution("100", "123450");
        log("answer3=%s\n", answer); // 10

        answer = solution("12321", "42531");
        log("answer4=%s\n", answer); // 321

        answer = solution("5525", "1255");
        log("answer5=%s\n", answer); // 552

        answer = solution("34456", "1223345567");
        log("answer5=%s\n", answer); // 552

        answer = solution("1223345567", "34456");
        log("answer5=%s\n", answer); // 552

        answer = solution("157799", "23556889");
        log("answer5=%s\n", answer); // 552
    }

    public String solution(String X, String Y) {
        char[] arrX = X.toCharArray();
        char[] arrY = Y.toCharArray();

        Arrays.sort(arrX);
        Arrays.sort(arrY);

        log("X=%s", Arrays.toString(arrX));
        log("Y=%s", Arrays.toString(arrY));

        ArrayList<Character> numbers = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < arrX.length; i++) {
            char x = arrX[i];

            for (; j < arrY.length; j++) {
                char y = arrY[j];

                if (x < y) break;
                if (x != y) continue;

                numbers.add(x);
                j++;
                break;
            }
        }

        log("numbers=%s", numbers);

        numbers.sort(Collections.reverseOrder());

        int countZero = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : numbers) {
            sb.append(c);
            if (c == '0') countZero++;
        }

        if (sb.length() == 0) {
            return "-1";
        } else if (sb.length() == countZero) {
            return "0";
        } else {
            return sb.toString();
        }
    }

    public int test() {
        return Character.digit('2', 10);
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}