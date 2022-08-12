package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;
import swExpertAcademy.basicLearning.userSolution.SWEA_P40_UserSolution;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 40] 10507. 영어 공부
 *
 * 시간 : 31개 테스트케이스를 합쳐서 C의 경우 2초 / C++의 경우 2초 / Java의 경우 4초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P40 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(40)));
        SWEA_P40_UserSolution user = new SWEA_P40_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            int N = Integer.parseInt(input[0]); // 영어 공부한 날짜의 수 (1 ≤ N ≤ 200,000)
            int P = Integer.parseInt(input[1]); // 추가할 수 있는 날짜의 수 (1 ≤ P ≤ 200,000)
            int[] studies = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int answer = user.solution(N, P, studies);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

}