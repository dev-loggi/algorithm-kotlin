package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;
import swExpertAcademy.basicLearning.userSolution.SWEA_P34_UserSolution;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 34] 3000. 중간값 구하기
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 10초 / C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P34 {

    private static final int DIVISOR = 20171109;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(34)));

        SWEA_P34_UserSolution solution = new SWEA_P34_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] info = sc.nextLine().split(" ");

            int N = Integer.parseInt(info[0]);
            int A = Integer.parseInt(info[1]);

            solution.init(N, A);

            int sum = 0;

            for (int i = 0; i < N; i++) {
                String[] numbers = sc.nextLine().split(" ");

                int res = solution.add(
                    Integer.parseInt(numbers[0]),
                    Integer.parseInt(numbers[1])
                );
                System.out.printf("res=%d\n", res);
                sum = (sum + res) % DIVISOR;
            }

            System.out.printf("#%d %d\n", test_case, sum);
        }

        sc.close();
    }
}