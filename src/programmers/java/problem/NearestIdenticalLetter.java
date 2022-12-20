package programmers.java.problem;

import programmers.Programmers;
import programmers.java.JavaSolution;

import java.util.Arrays;

/**
 * 가장 가까운 같은 글자
 * https://school.programmers.co.kr/learn/courses/30/lessons/142086
 * */
public class NearestIdenticalLetter extends JavaSolution {

    @Override
    public void execute() {
        int[] answer;

        answer = solution("banana");
        println("answer=%s", Arrays.toString(answer)); // [-1, -1, -1, 2, 2, 2]
        answer = solution("foobar");
        println("answer=%s", Arrays.toString(answer)); // [-1, -1, 1, -1, -1, -1]
    }

    public int[] solution(String s) {
        char[] charArray = s.toCharArray();
        int[] answer = new int[s.length()];
        int[] index = new int[(int) 'z' + 1];

        Arrays.fill(answer, -1);
        Arrays.fill(index, -1);

        for (int i = 0; i < s.length(); i++) {
            char ch = charArray[i];

            if (index[ch] != -1) {
                answer[i] = i - index[ch];
            }
            index[ch] = i;
        }

        return answer;
    }
}
