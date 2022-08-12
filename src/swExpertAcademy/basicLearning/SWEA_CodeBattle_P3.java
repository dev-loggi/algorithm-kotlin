package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 3] 3316. 동아리실 관리하기
 *
 * 시간   : 20개 테스트케이스를 합쳐서 C의 경우 30초 / C++의 경우 30초 / Java의 경우 30초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * DP, Bit masking
 *
 * TODO: 다시 분석해보기.
 *
 * 비트 마스킹을 여러 개 사용하는 것
 * 비트 마스킹을 통한 문제풀이 공부
 *
 * 첫째 날의 조건: A가 키를 들고 참석, 책임자 한 명 참석
 * 다른 날의 조건: 전날 인원이 최소 한 명 겹치고, 책임자 한 명 참석
 *
 * dp[16]은 0000을 제외한 나머지 경우의 수다.
 * 각 자리는 A,B,C,D의 참석여부를 뜻한다.
 * 즉 1011이라면 A,C,D가 참석했다는 뜻.
 *
 * dp[16][day]의 day는 날짜를 뜻한다.
 * 편의상 0일부터 시작으로 친다.
 * */
public class SWEA_CodeBattle_P3 {

    private static final int DIVISOR = 1_000_000_007;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(3)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            char[] owners = sc.nextLine().toCharArray();

            int answer = solution(owners);

            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    private static int solution(char[] owners) {
        int[][] dp = new int[owners.length][16];

        int owner = 1 << (owners[0] - 'A');

        for (int i = 1; i < 16; i++) {
            if ((i & owner) != 0 && (i & 1) != 0)
                dp[0][i] = 1;
        }

        for (int day = 1; day < owners.length; day++) {
            owner = 1 << (owners[day] - 'A');

            for (int i = 1; i < 16; i++) {
                if (dp[day - 1][i] == 0)
                    continue;

                for (int j = 1; j < 16; j++) {
                    if ((j & i) == 0 || (j & owner) == 0)
                        continue;

                    dp[day][j] += dp[day - 1][i];
                    dp[day][j] %= DIVISOR;

                }
            }
        }

        int sum = 0;
        for (int i = 1; i < 16; i++) {
            sum += dp[dp.length - 1][i];
            sum %= DIVISOR;
        }

        return sum;
    }
}