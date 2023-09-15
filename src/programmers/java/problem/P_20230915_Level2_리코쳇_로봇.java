package programmers.java.problem;

import programmers.java.JavaSolution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P_20230915_Level2_리코쳇_로봇 extends JavaSolution {

    class Node {
        int r, c, count;
        public Node(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }

    private static final int[] DR = new int[] {0, -1, 0, 1};
    private static final int[] DC = new int[] {-1, 0, 1, 0};
    private int N, M;
    private char[][] board;

    public int solution(String[] board) {
        this.N = board.length;
        this.M = board[0].length();
        this.board = Arrays.stream(board).map(String::toCharArray).toArray(char[][]::new);

        int r0 = 0, c0 = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (this.board[r][c] == 'R') {
                    r0 = r;
                    c0 = c;
                    break;
                }
            }
        }

        return bfs(r0, c0);
    }

    private int bfs(int r0, int c0) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(r0, c0, 0));

        boolean[][] visit = new boolean[N][M];
        visit[r0][c0] = true;

        while (!queue.isEmpty()) {
            Node prevNode = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                Node nextNode = move(prevNode, dir);

                if (nextNode == null || visit[nextNode.r][nextNode.c]) {
                    continue;
                }
                if (board[nextNode.r][nextNode.c] == 'G') {
                    return nextNode.count;
                }

                visit[nextNode.r][nextNode.c] = true;
                queue.offer(nextNode);
            }
        }

        return -1;
    }

    private Node move(Node node, int dir) {
        int nr = node.r, nc = node.c;
        for (int x = 1; ;x++) {
            nr += DR[dir];
            nc += DC[dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 'D') {
                nr -= DR[dir];
                nc -= DC[dir];
                break;
            }
        }
        if (node.r == nr && node.c == nc) {
            return null;
        } else {
            return new Node(nr, nc, node.count + 1);
        }
    }

    @Override
    public void execute() {
        int answer;
        answer = solution(new String[] {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."}); // 7
        println("answer=%d", answer);
        answer = solution(new String[] {".D.R", "....", ".G..", "...D"}); // -1
        println("answer=%d", answer);
    }
}
