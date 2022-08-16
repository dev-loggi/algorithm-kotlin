package swExpertAcademy.practicalTraining;

import java.util.ArrayDeque;

/**
 * [No. 5] 14596. [Pro] 섬지키기 (8/17)<br/><br/>
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초<br/>
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내<br/>
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 numberOfCandidate() 함수의 호출 횟수는 150,000 이하이다.<br/>
 * 3. 각 테스트 케이스에서 maxArea() 함수의 호출 횟수는 50 이하이다.<br/>
 * 4. 각 테스트 케이스에서 maxArea() 함수의 구조물 mStructure를 설치할 수 있는 경우의 수의 총합은 5,000 이하이다.<br/>
 *
 * 배열, Hash, BFS, DP
 * */
public class SWEA_PRO_P05_UserSolution implements SWEA_PRO_P05.UserSolution {

    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    private static final int MAX = 23405;
    private static final int[] DR = {0, -1, 0, 1};
    private static final int[] DC = {-1, 0, 1, 0};

    private int N;
    private int[][] map;
    private int[][] tempMap;
    private boolean[][][][] dp;
    private int[] hashCount;

    @Override
    public void init(int N, int[][] mMap) {
        this.N = N;
        map = mMap;
        dp = new boolean[N][N][2][MAX + 1];
        hashCount = new int[MAX + 1];

        tempMap = new int[N + 2][N + 2];
        for (int r = 0; r < N; r++) {
            System.arraycopy(map[r], 0, tempMap[r + 1], 1, N);
        }

        int[] struct = new int[5];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {

                for (int size = 1; size <= 5; size++) {
                    if (c + size <= N)
                        horizontalStructure(r, c, size, struct);
                    if (r + size <= N)
                        verticalStructure(r, c, size, struct);
                }
            }
        }
    }

    @Override
    public int numberOfCandidate(int M, int[] mStructure) {
        if (M == 1)
            return N * N;

        int hash = 0;
        for (int i = 0; i < M; i++) {
            hash = (hash << 3) | mStructure[i];
        }

        int reversedHash = 0;
        for (int i = M - 1; i >= 0; i--) {
            reversedHash = (reversedHash << 3) | mStructure[i];
        }

        if (hash == reversedHash) {
            return hashCount[hash];
        } else {
            return hashCount[hash] + hashCount[reversedHash];
        }
    }

    @Override
    public int maxArea(int M, int[] mStructure, int mSeaLevel) {
        int max = -1;

        int hash = 0;
        for (int i = 0; i < M; i++) {
            hash = (hash << 3) | mStructure[i];
        }

        int reversedHash = 0;
        for (int i = M - 1; i >= 0; i--) {
            reversedHash = (reversedHash << 3) | mStructure[i];
        }

        for (int r = 0; r < N; r++) for (int c = 0; c < N; c++) {
            // 가로 방향
            if (dp[r][c][0][hash]) {
                for (int i = 0; i < M; i++) // 구조물 짓기
                    tempMap[r + 1][c + i + 1] += mStructure[i];

                max = Math.max(max, bfs(mSeaLevel));

                for (int i = c; i < c + M; i++) // 구조물 부수기
                    tempMap[r + 1][i + 1] = map[r][i];
            }

            // 가로 역방향
            if (hash != reversedHash && dp[r][c][0][reversedHash]) {
                for (int i = 0; i < M; i++) // 구조물 짓기
                    tempMap[r + 1][c + i + 1] += mStructure[M - i - 1];

                max = Math.max(max, bfs(mSeaLevel));

                for (int i = c + M - 1; i >= c; i--) // 구조물 부수기
                    tempMap[r + 1][i + 1] = map[r][i];
            }

            // 세로 방향
            if (dp[r][c][1][hash]) {
                for (int i = 0; i < M; i++) // 구조물 짓기
                    tempMap[r + i + 1][c + 1] += mStructure[i];

                max = Math.max(max, bfs(mSeaLevel));

                for (int i = r; i < r + M; i++) // 구조물 부수기
                    tempMap[i + 1][c + 1] = map[i][c];
            }

            // 세로 역방향
            if (hash != reversedHash && dp[r][c][1][reversedHash]) {
                for (int i = 0; i < M; i++) // 구조물 짓기
                    tempMap[r + i + 1][c + 1] += mStructure[M - i - 1];

                max = Math.max(max, bfs(mSeaLevel));

                for (int i = r + M - 1; i >= r; i--) // 구조물 부수기
                    tempMap[i + 1][c + 1] = map[i][c];
            }
        }

        return max;
    }

    private int bfs(int seaLevel) {
        int count = 0;

        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][N + 2];

        queue.offer(new Node(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int r = curr.r + DR[dir];
                int c = curr.c + DC[dir];

                if (r < 0 || r > N + 1 || c < 0 || c > N + 1 || visited[r][c])
                    continue;

                visited[r][c] = true;

                int level = tempMap[r][c];

                if (seaLevel <= level)
                    continue;

                if (0 < level)
                    count += 1;

                queue.offer(new Node(r, c));
            }
        }
        return N * N - count;
    }

    private void horizontalStructure(int r, int c, int size, int[] struct) {
        int min = map[r][c];
        int max = map[r][c];
        int last = c + size;
        for (int i = c + 1; i < last; i++) {
            if (min > map[r][i]) min = map[r][i];
            if (max < map[r][i]) max = map[r][i];
        }

        max += 1;

        // struct[] 초기값 설정
        for (int i = 0; i < size; i++) {
            struct[i] = max - map[r][c + i];
        }

        int count = 5 - (max - min);
        for (int i = 0; i <= count; i++) {
            int hash = 0;

            for (int j = 0; j < size; j++) {
                hash = (hash << 3) | struct[j];
                struct[j] += 1;
            }

            dp[r][c][0][hash] = true;
            hashCount[hash] += 1;
        }
    }

    private void verticalStructure(int r, int c, int size, int[] struct) {
        int min = map[r][c];
        int max = map[r][c];
        int last = r + size;
        for (int i = r + 1; i < last; i++) {
            if (min > map[i][c]) min = map[i][c];
            if (max < map[i][c]) max = map[i][c];
        }

        max += 1;

        // struct[] 초기값 설정
        for (int i = 0; i < size; i++) {
            struct[i] = max - map[r + i][c];
        }

        int count = 5 - (max - min);
        for (int i = 0; i <= count; i++) {
            int hash = 0;

            for (int j = 0; j < size; j++) {
                hash = (hash << 3) | struct[j];
                struct[j] += 1;
            }

            dp[r][c][1][hash] = true;
            hashCount[hash] += 1;
        }
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private String arrayToString(int[] arr, int size) {
        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            if (i != size - 1) sb.append(", ");
            else sb.append(']');
        }
        return sb.toString();
    }

    private void printMap() {
        for (int r = 0; r < N; r++) {
            log(arrayToString(map[r], N));
        }
    }

    private void printTempMap() {
        for (int r = 0; r < N + 2; r++) {
            log(arrayToString(tempMap[r], N + 2));
        }
    }
}