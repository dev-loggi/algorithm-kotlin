package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 22] 1970. 쉬운 거스름돈
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 30초 / Java의 경우 30초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Greedy
 * */
public class SWEA_P22 {

    private static final int[] MONEY_UNIT = {
            50000, 10000, 5000, 1000, 500, 100, 50, 10
    };

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(22)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // (10 ≤ N ≤ 1,000,000)

            System.out.printf("#%d\n%s\n", test_case, solution(N));
        }

        sc.close();
    }

    public static String solution(int N) {
        int n = N;
        int[] moneys = new int[MONEY_UNIT.length];

        for (int i = 0; i < moneys.length; i++) {
            int unit = MONEY_UNIT[i];

            moneys[i] = n / unit;

            n = (n % unit);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : moneys) sb.append(num).append(" ");
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
