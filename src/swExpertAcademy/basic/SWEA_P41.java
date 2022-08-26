package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;
import swExpertAcademy.basic.userSolution.SWEA_P41_UserSolution;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 41] 9843. 촛불 이벤트
 *
 * 시간 : 100000개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P41 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(41)));
        SWEA_P41_UserSolution user = new SWEA_P41_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            long N = Long.parseLong(sc.nextLine()); // (1 ≤ N ≤ 10^18)

            long answer = user.solution(N);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}