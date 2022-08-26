package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;
import swExpertAcademy.basic.userSolution.SWEA_P35_UserSolution;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 35] 10806. 수 만들기
 *
 * 시간 : 100개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P35 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(35)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        SWEA_P35_UserSolution solution = new SWEA_P35_UserSolution();

        for (int test_case = 1; test_case <= T; test_case++) {
            // (1 ≤ N ≤ 10)
            int N = Integer.parseInt(sc.nextLine());

            // A1..An (2 ≤ Ai ≤ 10^9)
            int[] numbers = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // (1 ≤ K ≤ 10^9)
            int K = Integer.parseInt(sc.nextLine());

            int answer = solution.solution(numbers, N, K);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}
