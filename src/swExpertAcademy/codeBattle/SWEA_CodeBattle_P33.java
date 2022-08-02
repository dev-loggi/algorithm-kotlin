package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 33] 3000. 중간값 구하기
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 10초 / C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P33 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(33)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            int answer = solution();

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    private static int solution() {
        return 0;
    }
}