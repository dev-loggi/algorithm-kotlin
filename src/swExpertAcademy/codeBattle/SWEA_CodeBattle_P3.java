package swExpertAcademy.codeBattle;

import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * [No. 3] 3316. 동아리실 관리하기
 *
 * 시간   : 20개 테스트케이스를 합쳐서 C의 경우 30초 / C++의 경우 30초 / Java의 경우 30초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P3 {

    private static final String INPUT_FILE = "src/swExpertAcademy/_inputs/input_p3.txt";

    private static final int UNIT = 1_000_000_007;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(INPUT_FILE));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            char[] owners = sc.nextLine().toCharArray();

            long answer = solution(owners);

            System.out.printf("#%d %d", test_case, answer);
        }
    }

    private static long solution(char[] owners) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();


        long count = 0;

        char owner = owners[0];
        char key = 'A';



        for (int n = 0; n < owners.length; n++) {

        }

        return count;
    }

    private static long combination(int r, int c) {
        return 0;
    }
}