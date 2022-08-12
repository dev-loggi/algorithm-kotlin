package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * [No. 33] 1249. [S/W 문제해결 응용] 4일차 - 보급로
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P33 {

    private static class Node {
        int r, c;
        public Node(int r0, int c0) {
            r = r0; c = c0;
        }
    }

    private static final int[] DR = {0, -1, 0, 1};
    private static final int[] DC = {-1, 0, 1, 0};

    private static int N;
    private static int[][] map;

    private static int solution() {
        int minTime = Integer.MAX_VALUE;

        Deque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        int[][] time = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(time[i], Integer.MAX_VALUE);
        }

        queue.offer(new Node(0, 0));
        visited[0][0] = true;
        time[0][0] = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.r == N - 1 && cur.c == N - 1)
                minTime = Math.min(minTime, time[N - 1][N - 1]);

            if (minTime <= time[cur.r][cur.c])
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + DR[d];
                int nc = cur.c + DC[d];

                if (!checkRange(nr, nc))
                    continue;

                // 방문한 곳 &&
                // (nr, nc) 의 최소 시간 보다 같거나 크면
                if (visited[nr][nc] && time[nr][nc] <= time[cur.r][cur.c] + map[nr][nc])
                    continue;

                visited[nr][nc] = true;
                time[nr][nc] = time[cur.r][cur.c] + map[nr][nc];
                queue.offer(new Node(nr, nc));
            }
        }

        return minTime;
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(33)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(sc.nextLine()); // 지도의 크기 (N ≤ 100)

            map = new int[N][];

            for (int i = 0; i < N; i++) {
                map[i] = sc.nextLine().chars()
                        .map(c -> c - '0')
                        .toArray();
            }

            int answer = solution();

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }
}