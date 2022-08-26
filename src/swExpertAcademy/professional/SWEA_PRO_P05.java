package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
 * */
public class SWEA_PRO_P05 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.<br/>
         * 섬은 N x N 크기의 정사각형 모양이며, 1 x 1 크기의 정사각형 모양인 지역들로 이루어져 있다.<br/>
         *
         * @param N 섬의 한 변의 길이 (5 ≤ N ≤ 20)
         * @param mMap 섬의 각 지역의 고도 (1 ≤ mMap[][] ≤ 5)
         * */
        void init(int N, int[][] mMap);

        /**
         * 구조물 mStructure를 1개 설치했을 때, 나타날 수 있는 경우의 수를 반환한다.<br/>
         * 설치 지역이 모두 동일하면, 같은 경우로 취급한다.<br/>
         * 설치 지역이 1개라도 다르다면, 다른 경우로 취급한다.<br/>
         * 구조물 mStructure의 크기는 1 x M이며, 1 x 1 크기의 정사각형 모양인 부분들로 이루어져 있다.<br/>
         *
         * @param M 구조물의 크기 (1 ≤ M ≤ 5)
         * @param mStructure 구조물의 각 부분의 높이 (1 ≤ mStructure[] ≤ 5)
         *
         * @return 구조물을 설치할 수 있는 경우의 수
         * */
        int numberOfCandidate(int M, int[] mStructure);

        /**
         * 해수면이 mSeaLevel만큼 상승하여도 바다에 잠기지 않고 남아있는 지역의 개수가 최대가 되도록 구조물 mStructure를 1개 설치했을 때, 그 개수를 반환한다.<br/>
         * 구조물 mStructure를 설치할 방법이 없는 경우에는, -1을 반환한다.<br/>
         * 구조물 mStructure의 크기는 1 x M이며, 1 x 1 크기의 정사각형 모양인 부분들로 이루어져 있다.<br/>
         *
         * 실제로 설치하지는 않음에 유의하라.<br/>
         * 즉, 각 테스트 케이스에서 섬의 각 지역의 고도는 init()에서의 상태 그대로 유지된다.<br/>
         *
         * @param M 구조물의 크기 (1 ≤ M ≤ 5)
         * @param mStructure 구조물의 각 부분의 높이 (1 ≤ mStructure[] ≤ 5)
         * @param mSeaLevel 해수면의 상승 폭 (1 ≤ mSeaLevel ≤ 10)
         *
         * @return 최대 지역 개수
         * */
        int maxArea(int M, int[] mStructure, int mSeaLevel);
    }

    private final static int CMD_INIT					= 1;
    private final static int CMD_NUMBER_OF_CANDIDATE	= 2;
    private final static int CMD_MAX_AREA				= 3;

    private final static SWEA_PRO_P05_UserSolution userSolution = new SWEA_PRO_P05_UserSolution();

    private static int[][] mMap = new int[20][20];
    private static int[] mStructure = new int[5];

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;
        int N, M, mSeaLevel;
        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
            {
                case CMD_INIT:
                    N = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < N; i++)
                        for (int j = 0; j < N; j++)
                            mMap[i][j] = Integer.parseInt(st.nextToken());
                    userSolution.init(N, mMap);
                    isCorrect = true;
                    break;
                case CMD_NUMBER_OF_CANDIDATE:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++)
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    userAns = userSolution.numberOfCandidate(M, mStructure);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
//                    log("[%c] Ans=%d, userAns=%d", ans == userAns ? 'O' : 'X', ans, userAns);
//                    log("");
                    break;
                case CMD_MAX_AREA:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++)
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    mSeaLevel = Integer.parseInt(st.nextToken());
                    userAns = userSolution.maxArea(M, mStructure, mSeaLevel);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
//                    log("[%c] Ans=%d, userAns=%d", ans == userAns ? 'O' : 'X', ans, userAns);
//                    log("");
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(5)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

//        TC = 1;
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}
