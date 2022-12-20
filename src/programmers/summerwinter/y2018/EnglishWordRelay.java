package programmers.summerwinter.y2018;

import programmers.Programmers;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Summer/Winter Coding(~2018)
 * 영어 끝말잇기
 * */
public class EnglishWordRelay implements Programmers.Solution {

    @Override
    public void execute() {
        int[] answer;

        answer = solution(3, new String[]{"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"});
        System.out.printf("answer=%s\n", Arrays.toString(answer)); // [3,3]

        answer = solution(5, new String[]{"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"});
        System.out.printf("answer=%s\n", Arrays.toString(answer)); // [0,0]

        answer = solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
        System.out.printf("answer=%s\n", Arrays.toString(answer)); // [1,3]
    }

    public int[] solution(int n, String[] words) {
        HashSet<String> wordSet = new HashSet<>();
        int loser = 0;
        int round = 0;

        for (int i = 0, j = 1; j < words.length; i++, j++) {
            String prev = words[i];
            String next = words[j];

            if (prev.charAt(prev.length() - 1) != next.charAt(0) || wordSet.contains(next)) {
                round = j / n + 1;
                loser = j % n + 1;
                break;
            }

            wordSet.add(prev);
        }

        return new int[] { loser, round };
    }
}