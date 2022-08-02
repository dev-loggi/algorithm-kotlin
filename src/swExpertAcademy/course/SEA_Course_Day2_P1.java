package swExpertAcademy.course;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 2. 비트 연산 (7/19 화)
 *
 * [강의형] SW 문제해결 응용 - 01 알고리즘 개론
 * https://swexpertacademy.com/main/learn/course/subjectDetail.do?subjectId=AWG8AuUaDg0DFAVg#
 *
 * 1240. [S/W 문제해결 응용] 1일차 - 단순 2진 암호코드
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SEA_Course_Day2_P1 {

    private static final String FILE_INPUT = "src/swExpertAcademy/_inputs/input_course_day2_p1.txt";
    private static final int WIDTH = 56;
    private static final int HEIGHT_MIN = 5;
    private static final int HEIGHT_MAX = 50;

    private static final HashMap<Integer, Integer> CODE = new HashMap<>();

    static {
        CODE.put(13, 0); CODE.put(25, 1); CODE.put(19, 2); CODE.put(61, 3); CODE.put(35, 4);
        CODE.put(49, 5); CODE.put(47, 6); CODE.put(59, 7); CODE.put(55, 8); CODE.put(11, 9);
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(FILE_INPUT));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            int N = Integer.parseInt(input[0]); // 배열의 세로 길이 (1 ≤ N < 50)
            int M = Integer.parseInt(input[1]); // 배열의 가로 길이 (1 ≤ M < 100)

            boolean[][] data = new boolean[N][M];

            for (int r = 0; r < N; r++) {
                char[] arr = sc.nextLine().toCharArray();

                for (int c = 0; c < M; c++) {
                    data[r][c] = (arr[c] == '1');
                }
            }

            int answer = solution(data);
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    private static int solution(boolean[][] data) {
        // 1. data 에서 올바른 2진 암호 코드 추출
        boolean[] binaryCode = extractBinaryCode(data);

        if (binaryCode == null)
            return 0;

        // 2. 2진 코드 -> 10진 코드로 해독
        int[] decimalCode = decodeBinaryCode(binaryCode);

        if (decimalCode == null)
            return 0;

        // 3. 10진 암호 코드 해독
        return decodeDecimalCode(decimalCode);
    }

    private static boolean[] extractBinaryCode(boolean[][] data) {
        boolean[] binary = new boolean[WIDTH];

        for (int r = 0; r < data.length; r++) for (int c = data[r].length - 1; c >= 0; c--) {
            if (!data[r][c])
                continue;

            int h = r + 1;
            int hCnt = 1;
            while (h < data.length && data[h][c]) { h++; hCnt++; } // 세로 크기 확인

            if (hCnt < HEIGHT_MIN || hCnt >= HEIGHT_MAX)
                return null;

            if (c - WIDTH + 1 < 0) // 가로 크기 확인
                return null;

            for (int i = 0; i < WIDTH; i++) {
                binary[i] = data[r][c - WIDTH + 1 + i];
            }

            return binary;
        }

        return null;
    }

    public static int[] decodeBinaryCode(boolean[] code) {
        int[] decimal = new int[8];

        for (int i=0; i<8; i++) {
            int num = code[i*7] ? 1 : 0;

            for (int j=i*7+1; j < i*7+7; j++) {
                num = (num << 1);
                num += code[j] ? 1 : 0;
            }

            if (CODE.get(num) == null)
                return null;

            decimal[i] = CODE.get(num);
        }

        return decimal;
    }

    private static int decodeDecimalCode(int[] code) {
        return ((code[0] + code[2] + code[4] + code[6]) * 3 + code[1] + code[3] + code[5] + code[7]) % 10 == 0
                ? code[0] + code[1] + code[2] + code[3] + code[4] + code[5] + code[6] + code[7]
                : 0;
    }

    private static void log(boolean[][] data) {
        for (int i=0; i< data.length; i++) {
            for (int j=0; j<data[i].length; j++) {
                System.out.printf("%d", data[i][j] ? 1 : 0);
            }
            System.out.println("");
        }
        System.out.println("");
    }
}