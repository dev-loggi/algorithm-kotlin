package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 21] 8935. 스팟마트
 *
 * 시간 : 15개 테스트케이스를 합쳐서 C의 경우 7초 / C++의 경우 7초 / Java의 경우 14초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P21 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(21)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int answer = solution();

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    public static int solution() {
        return 0;
    }
}
