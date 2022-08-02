package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 23] 4408. 자기 방으로 돌아가기
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P23 {

    private static final int ROOM_MAX = 400;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(23)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine().trim()); // (T ≤ 10)

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine().trim()); // 돌아가야 할 학생의 수 (N ≤ 400)
            int[][] students = new int[N][];

            for (int i = 0; i < N; i++) {
                students[i] = Arrays.stream(sc.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            int answer = solution(students);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    /**
     * Room      1 3 5 7 ... 397 399
     *
     * Corridor  1 2 3 4 ... 199 200
     *
     * Room      2 4 6 8 ... 398 400
     *
     * 복도(Corridor) 배열을 만들고
     * 학생마다 차지하는 복도 번호에 카운트 +1 하여 max 값을 찾으면 됨.
     * */
    private static int solution(int[][] students) {
        int[] corridor = new int[ROOM_MAX / 2 + 1];

        for (int[] student : students) {
            int from = (student[0] + 1) / 2;
            int to = (student[1] + 1) / 2;

            // from > to 인 상황에 주의
            for (int i = Math.min(from, to); i <= Math.max(from, to); i++) {
                corridor[i] += 1;
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < corridor.length; i++) {
            max = Math.max(max, corridor[i]);
        }

        return max;
    }
}