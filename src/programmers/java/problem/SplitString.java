package programmers.java.problem;

import programmers.java.JavaSolution;

public class SplitString extends JavaSolution {

    @Override
    public void execute() {
        int answer;

        answer = solution("banana");
        println("answer=%s", answer); // 3
        answer = solution("abracadabra");
        println("answer=%s", answer); // 6
        answer = solution("aaabbaccccabba");
        println("answer=%s", answer); // 3
    }

    public int solution(String s) {
        char[] chars = s.toCharArray();
        int count = 0;

        for (int index = 0; index < chars.length; index++) {
            index = split(chars, index);
            count += 1;
        }

        return count;
    }

    private int split(char[] chars, int start) {
        char firstChar = chars[start];
        int i, x1 = 0, x2 = 0;

        for (i = start; i < chars.length; i++) {
            if (chars[i] == firstChar) {
                x1 += 1;
            } else {
                x2 += 1;
            }

            if (x1 == x2)
                break;
        }

        return i;
    }
}
