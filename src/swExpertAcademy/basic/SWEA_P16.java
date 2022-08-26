package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * [No. 16] 1461. 프로세서 연결하기
 *
 * 시간   : 50개 테스트케이스를 합쳐서 C++의 경우 2초 / Java의 경우 4초 / Python의 경우 8초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P16 {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int N;
    private static int[][] board;
    private static int maxCore;   // 이전 연결된 코어 최대 개수
    private static int minLen;
    private static ArrayList<Core> list;

    public static class Core {
        int x;
        int y;

        Core(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static void solution(int currCore, int len, int index) {
        if (index == list.size()) {
            if (maxCore < currCore) {
                maxCore = currCore;
                minLen = len;
            } else if (maxCore == currCore) {
                minLen = Math.min(minLen, len);
            }
            return;
        }

        Core core = list.get(index);
        int curX = core.x;
        int curY = core.y;
        int curLen = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = curX;
            int nextY = curY;
            boolean isOk = true;

            while (true) {
                nextX += dx[i];
                nextY += dy[i];

                if (nextX >= 0 && nextY >= 0 && nextX <= N-1 && nextY <= N-1) { // range 체크
                    // 코어나 전선 위치가 아니고 가장자리에 닿았을 때
                    if (board[nextX][nextY] != 1 && (nextX == 0 || nextY == 0 || nextX == N-1 || nextY == N-1)) {
                        board[nextX][nextY] = 1;
                        curLen++;
                        break;
                    }
                    // 코어나 전선 위치가 아니고 다음 칸으로 이동 가능하면
                    if (board[nextX][nextY] != 1) {
                        board[nextX][nextY] = 1;
                        curLen++;

                        if (board[nextX][nextY] != 1 && (nextX == 0 || nextY == 0 || nextX == N-1 || nextY == N-1))
                            break;
                    } else {   // 코어 or 전선위치
                        isOk = false;
                        break;
                    }
                }

            }
            if(isOk) {
                solution(currCore+1, len+curLen, index+1);
            } else {
                solution(currCore, len, index+1);
            }

            int tempX = curX;
            int tempY = curY;

            while (tempX != nextX && tempY != nextY) {
                tempX += dx[i];
                tempY += dy[i];
                board[tempX][tempY] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream(InputFile.number(16)));

        Scanner sc = new Scanner(System.in);

        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(sc.nextLine());
            board = new int[N][N];
            maxCore = 0;
            minLen = 1000000;
            list = new ArrayList<>();   // 코어 위치 저장

            for (int i = 0; i < N; i++) {
                String[] str = sc.nextLine().split(" ");

                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(str[j]);

                    if (board[i][j] == 1 && i!=0 && i!=N-1 && j!=0 && j!=N-1) {   // 연결해야 할 코어 위치 저장
                        list.add(new Core(i, j));
                    }
                }
            }

            solution(0, 0, 0);

            System.out.printf("#%d %d\n", test_case, minLen);
        }
    }
}