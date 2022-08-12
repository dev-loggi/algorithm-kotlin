package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 27] 7091. 은기의 아주 큰 그림
 *
 * 시간 : 11개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * (1) 비트를 1로 만드는 방법
 * bitwise OR 오퍼레이터 (|)를 사용합니다.
 * number |= << x; 는 x번째 비트를 1로 세팅합니다.
 *
 * (2) 비트를 0으로 만드는 방법
 * bitwise AND 오퍼레이터(&)를 사용합니다.
 * number &= ~(1<<x); 는 x번째 비트를 0으로 세팅합니다
 *
 * (3) 비트가 0이면 1, 1이면 0으로 만드는 방법
 * XOR 오퍼레이터 ()를 사용합니다.
 * number ^= 1 << x; 는 x번째 비트가 1이면 0, 0이면 1으로 바꿔줍니다
 *
 * (4) x번째 비트를 알아내는 방법
 * bitwise AND 오퍼레이터(&)를 사용합니다.
 * (number >> x ) & 1; 는 x번째 비트가 1이면 1을, 0면 0을 리턴합니다.
 * */
public class SWEA_CodeBattle_P27 {

    private static int H, W, N, M;
    private static int[][] picture;
    private static int[][] pattern;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(27)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int[] info = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            // ( 1 ≤ H ≤ N ≤ 2000, 1 ≤ W ≤ M ≤ 2000 )
            H = info[0]; // 은기가 꿈에서 본 그림의 세로 길이
            W = info[1]; // 은기가 꿈에서 본 그림의 가로 길이
            N = info[2]; // 선생님이 그린 그림의 세로 길이
            M = info[3]; // 선생님이 그린 그림의 가로 길이

            pattern = new int[H][];
            picture = new int[N][];

            for (int i = 0; i < H; i++) {
                pattern[i] = sc.nextLine().chars()
                        .map(n -> n == 111 ? 1 : 0)
                        .toArray();
            }

            for (int i = 0; i < N; i++) {
                picture[i] = sc.nextLine().chars()
                        .map(n -> n == 111 ? 1 : 0)
                        .toArray();
            }

            int answer = solution();

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    /** 라빈-카프 알고리즘 */
    private static int solution() {
        int count = 0;

        long patternHash = makeInitialHash(pattern);
        long hash = makeInitialHash(picture);
        long tempHash = hash;

        log("patternHash=%s, hash=%s", toBinary(patternHash), toBinary(hash));

        for (int r = 0; r <= N - H; r++) {
            if (r > 0) {
                hash = nextDownHash(tempHash, r);
                tempHash = hash;
            }

            if (hash == patternHash)
                count++;

            log("(r=%d, c=%d, cnt=%d): hash=%s", r, 0, count, toBinary(hash));

            for (int c = 1; c <= M - W; c++) {
                hash = nextRightHash(hash, r, c);

                if (hash == patternHash)
                    count++;

                log("    (r=%d, c=%d, cnt=%d): hash=%s", r, c, count, toBinary(hash));
            }
        }

        return count;
    }

    private static long makeInitialHash(int[][] arr) {
        long hash = 0;
        for (int i = 0; i < H; i++) for (int j = 0; j < W; j++) {
            hash = (hash << 1) + arr[i][j];
        }
        return hash;
    }

    private static long nextDownHash(long hash, int r) {
        int bitSize = W * H;
        for (int i = bitSize - W; i < bitSize; i++) { // set zero at all top side
            hash &= ~(1L << i);
        }

        for (int i = 0; i < W; i++)
            hash = (hash << 1) + picture[r + H - 1][i];

        return hash;
    }

    private static long nextRightHash(long hash, int r, int c) {
        int bitSize = W * H;
        for (int i = W - 1; i < bitSize; i += W) { // set zero at all left side
            hash &= ~(1L << i);
        }

        hash = hash << 1;

        long newHash = 0;
        for (int i = 0; i < H; i++) {
            newHash = (newHash << W) + picture[r + i][c + W - 1];
        }

        hash += newHash;
        return hash;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private static String toBinary(long n) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(n % 2);
            n /= 2;
        } while (n > 0);
        return sb.reverse().toString();
    }
}