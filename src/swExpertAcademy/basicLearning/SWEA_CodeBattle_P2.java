package swExpertAcademy.basicLearning;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 2] 10726. 이진수 표현
 *
 * 시간   : 10000개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P2 {

    private static final String INPUT_FILE = "src/swExpertAcademy/_inputs/input_p2.txt";

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(INPUT_FILE));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for(int test_case = 1; test_case <= T; test_case++) {
            String[] inputs = sc.nextLine().split(" ");

            // (1 ≤ N ≤ 30 , 0 ≤ M ≤ 10^8)
            int N = Integer.parseInt(inputs[0]);
            int M = Integer.parseInt(inputs[1]);

            String answer = solution(N, M);

            System.out.printf("#%d %s\n", test_case, answer);
        }
    }

    public static String solution(int N, int M) {
        StringBuilder binary = new StringBuilder();

        int m = M;
        do {
            binary.append(m % 2);
            m /= 2;
        } while (m > 0);

        if (binary.length() < N)
            return "OFF";

        char[] reversedBinary = binary.toString().toCharArray();
        for (int i = 0; i < N; i++) {
            if (reversedBinary[i] == '0')
                return "OFF";
        }

        return "ON";
    }
}