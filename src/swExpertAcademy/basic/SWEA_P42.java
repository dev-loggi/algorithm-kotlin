package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;
import swExpertAcademy.basic.userSolution.SWEA_P42_UserSolution;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 42] 11446. 사탕 가방
 *
 * 시간 : 200개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P42 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(42)));
        SWEA_P42_UserSolution user = new SWEA_P42_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            int N = Integer.parseInt(input[0]); // 수열의 길이 (1 ≤ N ≤ 100)
            long M = Long.parseLong(input[1]); // 가방에 들어가야할 사탕의 갯수 (1 ≤ M ≤ 10^18)

            // (1 ≤ Ai ≤ 10^18)
            long[] candy = Arrays.stream(sc.nextLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .toArray();

            long answer = user.solution(N, M, candy);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}
