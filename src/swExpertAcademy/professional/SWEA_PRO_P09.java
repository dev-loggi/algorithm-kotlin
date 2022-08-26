package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [No. 9] 14618. [Pro] 끝말잇기2 (8/23)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 playRound() 함수의 호출 횟수는 N을 넘지 않는다.<br/>
 * 3. playRound() 함수에서 전달하는 mCh 문자는 선택 가능한 단어들 중 하나의 첫번째 문자이다.<br/>
 * */
public class SWEA_PRO_P09 {

    interface UserSolution {

        /**
         * 각 테스트 케이스의 맨 처음에 호출된다.<br/>
         *
         * N 은 플레이어 수이다. M 은 주어지는 단어 리스트의 단어 수이다.<br/>
         *
         * mWords 는 끝말잇기 게임에서 사용 가능한 단어 리스트가 포함된 배열이다.<br/>
         * 단어 리스트는 mWords 배열의 인덱스 0 부터 순서대로 주어진다.<br/>
         * 각 단어는 길이가 2이상 10이하의 영어 알파벳 소문자로 구성되어 있고, ‘＼0’ 문자로 끝나는 문자열이다.<br/>
         * mWords 에 있는 M 개의 단어들은 서로 중복되지 않는다.<br/>
         *
         * @param N : 플레이어 수 ( 3 ≤ N ≤ 50,000 )
         * @param M : 단어 리스트의 단어 수 ( N ≤ M ≤ 50,000 )
         * */
        public void init(int N, int M, char[][] mWords);

        /**
         * mID는 해당 라운드에서 첫번째 턴을 진행할 플레이어 ID 이다.<br/>
         * mID는 아직 탈락하지 않은 플레이어임을 보장한다.<br/>
         * mCh는 알파벳 소문자이며 첫번째 플레이어는 단어 리스트에서 mCh 문자로 시작하는 단어를 선택해야 한다.<br/>
         *
         * 선택 가능한 단어들 중 mCh 문자로 시작하는 단어가 존재함을 보장한다.<br/>
         * 해당 라운드에서 탈락한 플레이어 ID를 반환한다.<br/>
         * 해당 함수의 호출 횟수는 N 을 넘지 않는다.<br/>
         *
         * @param mID : 첫번째 턴을 진행할 플레이어 ID ( 1 ≤ mID ≤ N )
         * @param mCh : 첫번째 플레이어가 선택할 단어의 첫문자
         *
         * @return 해당 라운드가 종료된 후 탈락한 플레이어 ID를 반환
         * */
        public int playRound(int mID, char mCh);
    }

    private static BufferedReader br;
    private static final SWEA_PRO_P09_UserSolution userSolution = new SWEA_PRO_P09_UserSolution();
//    private static final SWEA_PRO_P09_UserSolution_kt userSolution = new SWEA_PRO_P09_UserSolution_kt();

    private final static int MAX_M = 50000;
    private final static int MAX_LEN = 11;

    private static final char[][] mWords = new char[MAX_M][MAX_LEN];

    private static boolean run(int tc) throws Exception
    {
        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        boolean ok = true;
        int N = Integer.parseInt(stdin.nextToken());
        int M = Integer.parseInt(stdin.nextToken());

        for (int i = 0; i < M; i++)
        {
            String word = br.readLine();
            Arrays.fill(mWords[i], (char)0);
            word.getChars(0, word.length(), mWords[i], 0);
        }

        userSolution.init(N, M, mWords);

        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++)
        {
            stdin = new StringTokenizer(br.readLine(), " ");
            int mID, ret, ans;
            char mCh;

            mID = Integer.parseInt(stdin.nextToken());
            mCh = stdin.nextToken().charAt(0);
            ret = userSolution.playRound(mID, mCh);
            ans = Integer.parseInt(stdin.nextToken());
            if (ret != ans)
            {
                ok = false;
            }
//            log("    ▶ [%b] ans=%d, ret=%d", ans==ret, ans, ret);
        }

        return ok;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception
    {
        System.setIn(new java.io.FileInputStream(ProInputFile.number(9)));

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        int T, MARK;
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

//        T = 8;
        for (int tc = 1; tc <= T; tc++)
        {
            int score = run(tc) ? MARK : 0;
            System.out.printf("#%d %d\n", tc, score);
        }

        br.close();
    }
}