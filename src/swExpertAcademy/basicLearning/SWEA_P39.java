package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;
import swExpertAcademy.basicLearning.userSolution.SWEA_P39_UserSolution;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 39] 3998. [Professional] Inversion Counting
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C의 경우 5초 / C++의 경우 5초 / Java의 경우 7초 / Python의 경우 14초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P39 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(39)));
        SWEA_P39_UserSolution user = new SWEA_P39_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 수열의 길이 (2 ≤ N ≤ 100,000)

            int[] arr = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            long answer = user.solution(arr);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}
