package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * [No. 17] 1868. 파핑파핑 지뢰찾기
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P17 {

    private static final int[] dr8 = {-1,-1,-1,0,1,1,1,0};
    private static final int[] dc8 = {-1,0,1,1,1,0,-1,-1};

    private static int N;
    private static int[][] board;
    private static boolean[][] visited;
    private static Deque<int[]> queue;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(17)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(sc.nextLine()); // 보드의 크기 (1 ≤ N ≤ 300)

            board = new int[N][N];

            for (int i = 0; i < N; i++) {
                char[] line = sc.nextLine().toCharArray();

                for (int j = 0; j < N; j++) {
                    if (line[j] == '.') // '.' = 0
                        continue;

                    board[i][j] = -1; // 지뢰 = -1
                }
            }

            System.out.printf("#%d %d\n", test_case, solution());
        }

        sc.close();
    }


    private static int solution() {
        queue = new ArrayDeque<>();
        visited = new boolean[N][N];

        int count = 0;

        // 1. 지뢰(-1) 주변 +1
        for (int r = 0; r < N; r++) for (int c = 0; c < N; c++) {
            if (board[r][c] != -1) { // 지뢰 없는 곳 카운트
                count++;
                continue;
            }

            for (int i = 0; i < 8; i++) { // 지뢰 주변 +1
                int rn = r + dr8[i];
                int cn = c + dc8[i];

                if (!checkRange(rn, cn) || board[rn][cn] == -1)
                    continue;

                board[rn][cn]++;
            }
        }

        // 2. 0인 섬의 갯수 + 섬에 둘러 쌓인 숫자들의 갯수
        for (int r = 0; r < N; r++) for (int c = 0; c < N; c++) {
            if (board[r][c] != 0 || visited[r][c])
                continue;

            count = count - bfs(r, c) + 1;
        }

        return count;
    }

    private static int bfs(int r0, int c0) {
        queue.offer(new int[] {r0, c0});
        visited[r0][c0] = true;

        int count = 1;

        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            for (int i = 0; i < 8; i++) {
                int r = p[0] + dr8[i];
                int c = p[1] + dc8[i];

                if (!checkRange(r, c) || visited[r][c])
                    continue;

                visited[r][c] = true;

                if (board[r][c] > 0) {
                    count++;
                } else if (board[r][c] == 0) {
                    queue.offer(new int[] {r, c});
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    private static void printBoard() {
        System.out.print("\n    ");
        for (int j=0; j<N; j++) System.out.printf(" %d", j % 1000 / 100);
        System.out.print("  ");
        for (int j=0; j<N; j++) System.out.print(j % 1000 / 100);

        System.out.print("\n    ");
        for (int j=0; j<N; j++) System.out.printf(" %d", j % 100 / 10);
        System.out.print("  ");
        for (int j=0; j<N; j++) System.out.print(j % 100 / 10);

        System.out.print("\n    ");
        for (int j=0; j<N; j++) System.out.printf(" %d", j % 10);
        System.out.print("  ");
        for (int j=0; j<N; j++) System.out.print(j % 10);

        System.out.println("\n");
        for (int i=0; i<N; i++) {
            System.out.printf("%d%d%d ", i % 1000 / 100, i % 100 / 10, i % 10);

            for (int j=0; j<N; j++) {
                if (board[i][j] == -1) System.out.printf("%d", board[i][j]);
                else System.out.printf(" %d", board[i][j]);
            }
            System.out.print("  ");
            for (int j=0; j<N; j++) {
                System.out.print(visited[i][j] ? 1 : 0);
            }
            System.out.println();
        }
        System.out.println();
    }
}