package swExpertAcademy.course;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.*;

/**
 * 2. 비트 연산 (7/19 화)
 *  *
 *  * [강의형] SW 문제해결 응용 - 01 알고리즘 개론
 *  * https://swexpertacademy.com/main/learn/course/subjectDetail.do?subjectId=AWG8AuUaDg0DFAVg#
 *
 * 1242. [S/W 문제해결 응용] 1일차 - 암호코드 스캔
 *
 * 시간   : 20개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SEA_Course_Day2_P2_2 {

    private static final String FILE_INPUT = "src/swExpertAcademy/_inputs/input_course_day2_p2.txt";
    private static final int UNIT = 7;
    private static final int LENGTH = 8;
    private static final int HEIGHT_MIN = 5;
    private static final int HEIGHT_MAX = 50;

    private static final Map<Integer, Integer> CODE = new HashMap<>();
    private static final Map<Character, byte[]> HEX = new HashMap<>();

    static {
        CODE.put(13, 0); CODE.put(25, 1); CODE.put(19, 2); CODE.put(61, 3); CODE.put(35, 4);
        CODE.put(49, 5); CODE.put(47, 6); CODE.put(59, 7); CODE.put(55, 8); CODE.put(11, 9);
        HEX.put('0', new byte[]{0,0,0,0}); HEX.put('1', new byte[]{0,0,0,1}); HEX.put('2', new byte[]{0,0,1,0});
        HEX.put('3', new byte[]{0,0,1,1}); HEX.put('4', new byte[]{0,1,0,0}); HEX.put('5', new byte[]{0,1,0,1});
        HEX.put('6', new byte[]{0,1,1,0}); HEX.put('7', new byte[]{0,1,1,1}); HEX.put('8', new byte[]{1,0,0,0});
        HEX.put('9', new byte[]{1,0,0,1}); HEX.put('A', new byte[]{1,0,1,0}); HEX.put('B', new byte[]{1,0,1,1});
        HEX.put('C', new byte[]{1,1,0,0}); HEX.put('D', new byte[]{1,1,0,1}); HEX.put('E', new byte[]{1,1,1,0});
        HEX.put('F', new byte[]{1,1,1,1});
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(FILE_INPUT));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            //if (test_case == 2) break;

            String[] input = sc.nextLine().split(" ");

            int N = Integer.parseInt(input[0]); // 배열의 세로 길이 (1 ≤ N < 2000)
            int M = Integer.parseInt(input[1]); // 배열의 가로 길이 (1 ≤ M < 500)

            char[][] data = new char[N][M];

            for (int i = 0; i < N; i++) {
                data[i] = sc.nextLine().toCharArray();
            }

            byte[][] binaryData = new byte[N][M * 4];

            for (int i=0; i<N; i++) for (int j=0; j<M; j++) for (int k=0; k<4; k++) {
                binaryData[i][j * 4 + k] = HEX.get(data[i][j])[k];
            }

            int answer = solution(binaryData);
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    private static int solution(byte[][] data) {
//        // 1. data 에서 올바른 2진 암호 코드 추출
//        byte[] binaryCode = extractBinaryCode(data);
//
//        if (binaryCode == null)
//            return 0;
//
//        // 2. 2진 코드 -> 10진 코드로 해독
//        int[] decimalCode = decodeBinaryCode(binaryCode);
//
//        if (decimalCode == null)
//            return 0;
//
//        // 3. 10진 암호 코드 해독
//        return decodeDecimalCode(decimalCode);
        return 0;
    }

//    private static byte[][] extractBinaryCode(byte[][] data) {
//        List<byte[]> binaryCodes = new LinkedList<>();
//        boolean[][] visited = new boolean[data.length][data[0].length];
//
//        for (int r = 0; r < data.length; r++) for (int c = data[r].length - 1; c >= 0; c--) {
//            // 암호 코드 박스의 우상단 '1' 찾기
//            if (data[r][c] != 0 || visited[r][c])
//                continue;
//
//            visited[r][c] = true;
//
//
//            for (int i = r; i < data.length && data[i][c] == 1; i++) {
//                for (int j = c; j >= 0; j--) {
//                }
//            }
//
//            int h = r + 1;
//            int hCnt = 1;
//            while (h < data.length && data[h][c] == 1) { h++; hCnt++; } // 세로 크기 확인
//
//            if (hCnt < HEIGHT_MIN || hCnt >= HEIGHT_MAX)
//                continue;
//
//
//
//        }
//
//        return binaryCodes.toArray(new byte[0][0]);
//    }
//
//    public static int[] decodeBinaryCode(boolean[] code) {
//        int[] decimal = new int[8];
//
//        for (int i=0; i<8; i++) {
//            int num = code[i*7] ? 1 : 0;
//
//            for (int j=i*7+1; j < i*7+7; j++) {
//                num = (num << 1);
//                num += code[j] ? 1 : 0;
//            }
//
//            if (CODE.get(num) == null)
//                return null;
//
//            decimal[i] = CODE.get(num);
//        }
//
//        return decimal;
//    }
//
//    private static int decodeDecimalCode(int[] code) {
//        return ((code[0] + code[2] + code[4] + code[6]) * 3 + code[1] + code[3] + code[5] + code[7]) % 10 == 0
//                ? code[0] + code[1] + code[2] + code[3] + code[4] + code[5] + code[6] + code[7]
//                : 0;
//    }
//
//    private static void log(boolean[][] data) {
//        for (int i=0; i< data.length; i++) {
//            for (int j=0; j<data[i].length; j++) {
//                System.out.printf("%d", data[i][j] ? 1 : 0);
//            }
//            System.out.println("");
//        }
//        System.out.println("");
//    }
}
