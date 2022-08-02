package swExpertAcademy.codeBattle;


import swExpertAcademy._inputs.InputFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 14] 12317. 기초 DFS 연습
 *
 * 시간   : 50개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 1초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXrCUo46AioDFATi&categoryId=AYIPD136YD8DFATO&categoryType=BATTLE&battleMainPageIndex=1
 * */
public class SWEA_CodeBattle_P14 {

    public static class UserSolution {

        private int[][] relations;

        public void dfs_init(int N, int[][] path) {
            relations = new int[100][5];

            for (int i = 0; i < N - 1; i++) {
                int parent = path[i][0];
                int child = path[i][1];

                for (int j = 0; j < 5; j++) {
                    if (relations[parent][j] == 0) {
                        relations[parent][j] = child;
                        break;
                    }
                }
            }
        }

        public int dfs(int K) {
            return recursive(K, K);
        }

        private int recursive(int king, int heir) {
            if (king < heir)
                return heir;

            for (int i = 0; i < 5 && relations[heir][i] > 0; i++) {
                int newHeir = recursive(king, relations[heir][i]);
                if (newHeir > -1)
                    return newHeir;
            }

            return -1;
        }
    }

    private final static int MAX_N = 40;
    private final static int MAX_K = 98;
    private final static int MIN_N = 2;
    private final static int MAX_CHILD = 5;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static long seed = 12345;

    private static int[][] path = new int[MAX_N][2];
    private static int[] p = new int[MAX_K+2];
    private static int[] c = new int[MAX_K+2];

    public static long pseudo_rand(int max) {
        seed = (seed * 1103515245 + 12345) & 0x7fffffffL;
        return seed % max;
    }

    public static void makeTree(int N) {

        boolean[] check = new boolean[MAX_K+2];

        for(int i = 1; i < MAX_K + 2; i++) {
            p[i] = c[i] = -1;
        }
        c[(int)(pseudo_rand(MAX_K+1)+1)] = 0;

        for(int i = 0; i < N; i++) {
            int pi = (int)(pseudo_rand(MAX_K+1)+1);
            while(c[pi] < 0 || c[pi] >= MAX_CHILD) {
                pi++;
                if(pi == MAX_K+2) pi = 1;
            }

            int ci = (int)(pseudo_rand(MAX_K+1)+1);
            while(c[ci] >= 0) {
                ci++;
                if(ci == MAX_K+2) ci = 1;
            }

            p[ci] = pi;
            c[pi]++;
            c[ci] = 0;
        }

        for(int i = 0; i < N; i++) {
            int e = (int)(pseudo_rand(MAX_K+1)+1);
            while(check[e] || c[e] < 0 || p[e] == -1) {
                e++;
                if(e == MAX_K + 2) e = 1;
            }
            check[e] = true;
            path[i][0] = p[e];
            path[i][1] = e;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream(InputFile.number(14)));

        int TC, N, Q, K, ans, ret;
        int totalScore = 0, score;
        String str;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");

            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
            seed = Long.parseLong(st.nextToken());

            makeTree(N-1);
            usersolution.dfs_init(N, path);

            score = 100;
            for(int i = 1; i <= Q; i++) {
                str = br.readLine();
                st = new StringTokenizer(str, " ");

                K = Integer.parseInt(st.nextToken());
                ans = Integer.parseInt(st.nextToken());

                ret = usersolution.dfs(K);

                if(ret != ans) score = 0;
            }

            System.out.println("#" + tc + " : " + score);
            totalScore += score;
        }
        br.close();
        System.out.println("#totalScore score : " + totalScore / TC);
    }
}
