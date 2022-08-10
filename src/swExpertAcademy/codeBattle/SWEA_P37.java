package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;
import swExpertAcademy.codeBattle.userSolution.SWEA_P37_UserSolution;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 37] 7701. 염라대왕의 이름 정렬
 *
 * 시간 : 50개 테스트케이스를 합쳐서 C의 경우 5초 / C++의 경우 5초 / Java의 경우 10초 / Python의 경우 10초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P37 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(37)));

        SWEA_P37_UserSolution user = new SWEA_P37_UserSolution();

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 이름의 갯수 (1 ≤ N ≤ 20,000)

            String[] names = new String[N];

            for (int i = 0; i < N; i++) {
                names[i] = sc.nextLine();
            }

            String[] answer = user.solution(names);

            System.out.printf("#%d\n", test_case);

            for (String s : answer) {
                System.out.println(s);
            }
        }

        sc.close();
    }
}
