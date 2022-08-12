package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 15] 12318. 기초 BFS 연습
 *
 * 시간 : 1개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 1초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXrCU1l6Aj8DFATi&categoryId=AYIPD136YD8DFATO&categoryType=BATTLE&battleMainPageIndex=1
 * */
public class SWEA_P15 {

    public static class UserSolution {

        public static class Point {
            int x, y, d;
            public Point(int x, int y, int d) {
                this.x = x; this.y = y; this.d = d;
            }
        }

        private final int[] dx = {-1, 0, 1, 0};
        private final int[] dy = {0, -1, 0, 1};

        private boolean[][] visited;
        private int[][] map;
        int mapSize;

        public void bfs_init(int map_size, int[][] map) {
            mapSize = map_size;
            this.map = map;
        }

        public int bfs(int x1, int y1, int x2, int y2) {
            return bfs2(x1 - 1, y1 - 1, x2 - 1, y2 - 1);
        }

        public int bfs2(int x1, int y1, int x2, int y2) {
            int left = 0, right = -1; // 큐 포인터
            Point[] queue = new Point[50000];
            visited = new boolean[mapSize][mapSize];

            queue[++right] = new Point(x1, y1, 0);
            visited[x1][y1] = true;

            while (left <= right) { // queue is not empty
                Point p = queue[left++]; // dequeue

                if (p.x == x2 && p.y == y2)
                    return p.d;

                for (int i = 0; i < 4; i++) { // right, up, left, down
                    int x = p.x + dx[i];
                    int y = p.y + dy[i];

                    if (!check(x, y))
                        continue;

                    visited[y][x] = true;

                    queue[++right] = new Point(x, y, p.d + 1); // enqueue
                }
            }

            return -1;
        }

        private boolean check(int x, int y) {
            return 0 <= x && x < mapSize && 0 <= y && y < mapSize &&
                    !visited[y][x] && map[y][x] == 0;
        }
    }



    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int cmd_bfs() throws Exception {
        System.setIn(new java.io.FileInputStream(InputFile.number(15)));

        int score = 100;
        int N, M, x1, y1, x2, y2, dist, ans;
        int[][] map = new int[10][10];
        String str;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        usersolution.bfs_init(N, map);

        str = br.readLine();
        M = Integer.parseInt(str);

        for(int i = 1; i <= M; i++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            ans = Integer.parseInt(st.nextToken());

            dist = usersolution.bfs(x1, y1, x2, y2);

            if(dist != ans) {
                score = 0;
            }
        }
        br.close();
        return score;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("#total score : " + cmd_bfs());
    }
}