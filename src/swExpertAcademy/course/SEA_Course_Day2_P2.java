package swExpertAcademy.course;

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
public class SEA_Course_Day2_P2 {

    private static final String FILE_INPUT = "src/swExpertAcademy/_inputs/input_course_day2_p2.txt";
    private static final int HEX_WIDTH_UNIT = 14;
    private static final int WIDTH = 56;
    private static final int HEIGHT_MIN = 5;
    private static final int HEIGHT_MAX = 50;

    private static final Map<Integer, Integer> CODE = new HashMap<>(10);
    private static final Map<Character, String> HEX = new HashMap<>(16);

    static {
        CODE.put(13, 0); CODE.put(25, 1); CODE.put(19, 2); CODE.put(61, 3); CODE.put(35, 4);
        CODE.put(49, 5); CODE.put(47, 6); CODE.put(59, 7); CODE.put(55, 8); CODE.put(11, 9);
        HEX.put('0', "0000"); HEX.put('1', "0001"); HEX.put('2', "0010"); HEX.put('3', "0011"); HEX.put('4', "0100");
        HEX.put('5', "0101"); HEX.put('6', "0110"); HEX.put('7', "0111"); HEX.put('8', "1000"); HEX.put('9', "1001");
        HEX.put('A', "1010"); HEX.put('B', "1011"); HEX.put('C', "1100"); HEX.put('D', "1101"); HEX.put('E', "1110"); HEX.put('F', "1111");
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(FILE_INPUT));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            if (test_case == 10) break;

            String[] input = sc.nextLine().split(" ");

            int N = Integer.parseInt(input[0]); // 배열의 세로 길이 (1 ≤ N < 50)
            int M = Integer.parseInt(input[1]); // 배열의 가로 길이 (1 ≤ M < 100)

            char[][] data = new char[N][M];

            for (int r = 0; r < N; r++) {
                data[r] = sc.nextLine().toCharArray();
            }

            int answer = solution(data);
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }

    private static int solution(char[][] data) {
        // 1. data 에서 올바른 2진 암호 코드 추출
        boolean[][] binaryCodes = extractBinaryCode(data);
        log(binaryCodes);
        if (true) return binaryCodes.length;

        // 2. 2진 코드 -> 10진 코드로 해독
        int[][] decimalCodes = decodeBinaryCode(binaryCodes);

        // 3. 10진 암호 코드 해독
        int sum = 0;
        for (int[] code : decimalCodes) {
            sum += decodeDecimalCode(code);
        }
        return sum;
    }

    private static boolean[][] extractBinaryCode(char[][] data) {
        List<boolean[]> binaryCodes = new LinkedList<>();
        boolean[][] visited = new boolean[data.length][data[0].length];

        for (int r = 0; r < data.length; r++) for (int c = 0; c < data[r].length; c++) {
            if (visited[r][c] || data[r][c] == '0')
                continue;

            visited[r][c] = true;

            int width = 0;
            int height = 0;
            for (int i = r; i < data.length && data[i][c] != '0'; i++) {
                width = 0;
                for (int j = c; j < data[r].length && data[i][j] != '0'; j++) {
                    visited[i][j] = true;
                    width++;
                }
                height++;
            }

            if (HEIGHT_MIN <= height && height < HEIGHT_MAX) {
                binaryCodes.add(hexToBinaryCode(Arrays.copyOfRange(data[r], c, c + width)));
            }
        }

        return binaryCodes.toArray(new boolean[0][0]);
    }

    public static void test() {
        boolean[] a = new boolean[4];
        boolean[] b = new boolean[4];

        int c = 3;
        int d = 10;
        int e = c | d;
    }

    public static boolean[] hexToBinaryCode(char[] hexCode) {
        boolean[] binary = new boolean[hexCode.length * 4];
        StringBuilder sb = new StringBuilder();

        for (char hex : hexCode) {
            sb.append(HEX.get(hex));
        }

        char[] arr = sb.toString().toCharArray();
        for (int i = 0; i < arr.length; i++) {
            binary[i] = (arr[i] == '1');
        }

        return binary;
    }

    public static int[][] decodeBinaryCode(boolean[][] code) {
        List<int[]> decimalCodes = new LinkedList<>();

        for (int k=0; k<code.length; k++) {
            //code[k].length
        }

        return new int[1][1];
    }

    private static int decodeDecimalCode(int[] code) {
        return ((code[0] + code[2] + code[4] + code[6]) * 3 + code[1] + code[3] + code[5] + code[7]) % 10 == 0
                ? code[0] + code[1] + code[2] + code[3] + code[4] + code[5] + code[6] + code[7]
                : 0;
    }

    private static void log(boolean[][] codes) {
        for (int i=0; i<codes.length; i++) {
            System.out.printf("[%d]: ", i);
            for (int j = 0; j < codes[i].length; j++) {
                System.out.printf("%d", codes[i][j] ? 1 : 0);
            }
            System.out.println("");
        }
    }
}