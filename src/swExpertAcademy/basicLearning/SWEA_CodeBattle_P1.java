package swExpertAcademy.basicLearning;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

/**
 * [No. 1] 새로운 불면증 치료법
 *
 * 시간   : 100개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P1 {

    private static final String INPUT_FILE = "src/swExpertAcademy/_inputs/input_p1.txt";

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(INPUT_FILE));

        Scanner sc = new Scanner(System.in);
        long T = Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(sc.nextLine());

            int answer = solution(N);
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    private static int solution(Long N) {
        HashSet<Character> numberSet = new HashSet<>();

        long n = N;
        for (int i = 1; ;i++) {
            n = N * i;

            String s = String.valueOf(n);
            for (int j = 0; j < s.length(); j++) {
                numberSet.add(s.charAt(j));
            }

            if (numberSet.size() >= 10)
                break;
        }

        return (int) n;
    }
}