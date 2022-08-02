package swExpertAcademy.codeBattle;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * [No. 16] 1461. 프로세서 연결하기
 *
 * 시간   : 50개 테스트케이스를 합쳐서 C++의 경우 2초 / Java의 경우 4초 / Python의 경우 8초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P16 {


    public static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    private static final int CORE_MAX = 12;
    private static int[][] cases;
    private static boolean[] visited;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 보드의 크기 (7 ≤ N ≤ 12)

            int[][] board = new int[N][N];
            ArrayList<Point> cores = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String[] line = sc.nextLine().split(" ");

                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(line[j + 1]);

                    if (board[i][j] == 1) { // 코어 위치 저장
                        cores.add(new Point(i, j));
                    }
                }
            }

            int answer = solution(N - 2, board, cores.toArray(new Point[0]));

            System.out.printf("#%d %d", test_case, answer);
        }

        sc.close();
    }



    private static int solution(int N, int[][] board, Point[] cores) {


        return 0;
    }

    public static int[][] permutation(int n) {
        int size = factorial(n);
        cases = new int[size][n];
        visited = new boolean[n + 1];
        permutation(n, 0, new int[1]);
        return cases;
    }

    private static void permutation(int r, int depth, int[] k) {
        if (depth == r) {
            k[0]++;
            return;
        }

        for (int i = 1; i <= r; i++) {
            if (visited[i])
                continue;

            cases[k[0]][depth] = i;
            visited[i] = true;
            permutation(r, depth + 1, k);
            visited[i] = false;
        }
    }

    public static int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }
}