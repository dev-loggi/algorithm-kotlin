package programmers.mockExam;

import java.util.HashMap;
import java.util.Map;

public class Pro_MockExam1_2 {

    public void main() {
        int answer;
        answer = solution(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{
                        "chicken", "apple", "apple", "banana", "rice", "apple", "pork",
                        "banana", "pork", "rice", "pot", "banana", "apple", "banana",
                }
        );
        log("answer=%d", answer); // 3

        answer = solution(
                new String[]{"apple"},
                new int[]{10},
                new String[]{
                        "banana", "banana", "banana", "banana", "banana",
                        "banana", "banana", "banana", "banana", "banana",
                }
        );
        log("answer=%d", answer); // 0
    }

    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> itemMap = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            itemMap.put(want[i], i);
        }

        int[][] quantities = new int[discount.length][want.length];

        for (int i = 0; i < discount.length; i++) {
            for (int j = i; j < i + 10 && j < discount.length; j++) {
                Integer itemIdx = itemMap.get(discount[j]);
                if (itemIdx == null)
                    continue;

                quantities[i][itemIdx]++;
            }
        }

        int count = 0;
        for (int[] quantity : quantities) {

            for (int i = 0; i < quantity.length; i++) {
                if (quantity[i] < number[i])
                    break;

                if (i == quantity.length - 1)
                    count++;
            }
        }

        return count;
    }


    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
