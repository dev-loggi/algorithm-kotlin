package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;
import swExpertAcademy.basicLearning.userSolution.SWEA_P38_UserSolution;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 38] 13736. 사탕 분배
 *
 * 시간 : 150개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P38 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(38)));
        SWEA_P38_UserSolution user = new SWEA_P38_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            int A = Integer.parseInt(input[0]); // 나연이 사탕 갯수 (1 ≤ A ≤ 10^9)
            int B = Integer.parseInt(input[1]); // 다현이 사탕 갯수 (1 ≤ B ≤ 10^9)
            int K = Integer.parseInt(input[2]); // 쿼리 반복 횟수 (1 ≤ K ≤ 2 * 10^9)

            int answer = user.solution(A, B, K);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}
