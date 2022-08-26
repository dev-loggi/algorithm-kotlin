package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 4] 14611. [Pro] 계산 게임 (8/16)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_PRO_P04 {

    /**
     *  <br/>[제약사항]<br/><br/>
     *
     *  1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
     *  2. 각 테스트 케이스에서 putCards() 함수의 호출 횟수는 10,000회 이하이다.<br/>
     *  3. 각 테스트 케이스에서 전체 함수의 총 호출 횟수는 15,000회 이하이다.<br/>
     *  4. 주어지는 카드 5장 내에 포함된 조커 카드 개수의 제한은 없다.
     * */
    interface UserSolution {

        /**
         * 각 테스트 케이스의 처음에 호출된다.<br/>
         *
         * 처음 테이블에 나열할 카드 5장의 정보가 mNumbers 배열로 주어진다.<br/>
         * 나열 시에는 5장의 카드를 배열 내의 순서대로 왼쪽에서 오른쪽으로 나열한다.<br/>
         * 그리고, 조커 값이 mJoker로 주어진다.<br/>
         *
         * @param mJoker  조커 카드에서 사용할 조커 값 (1 ≤ mJoker ≤ 30)
         * @param mNumbers 주어지는 카드에 적혀있는 수 (1 ≤ mNumbers[] ≤ 30 or mNumbers[] = -1)
         * */
        void init(int mJoker, int[] mNumbers);

        /**
         * 테이블에 나열할 5장의 카드 정보가 mNumbers 배열에 주어진다.<br/>
         * 나열 시에는 5장의 카드를 배열 내의 순서대로 왼쪽에서 오른쪽으로 나열한다.<br/>
         *
         * mDir이 0인 경우 테이블에 나열되어 있는 카드들 가장 왼쪽에, 1인 경우 가장 오른쪽에 나열한다.<br/>
         *
         * @param mDir  카드를 나열할 방향 (0 ≤ mDir ≤ 1)
         * @param mNumbers 주어지는 카드에 적혀있는 수 (1 ≤ mNumbers[] ≤ 30 or mNumbers[] = -1)
         * */
        void putCards(int mDir, int[] mNumbers);

        /**
         * 테이블에 나열된 카드들 중에서 인접한 카드의 계산 결과가 mNum인 mNth 번째 카드를 찾고 그 카드와 오른쪽 3개의 카드에 적힌 수를 ret 배열에 기록한다.<br/>
         * 조건에 맞는 카드들을 찾은 경우 1을, 찾을 수 없는 경우 0을 반환한다.<br/>
         * 0을 반환하는 경우 ret 배열은 무시해도 된다.<br/>
         *
         * @param mNum  찾아야 하는 계산 결과값 (0 ≤ mNum ≤ 19)
         * @param mNth  찾아야 하는 결과값이 나와야 하는 횟수 (1 ≤ mNth ≤ 1,000)
         * @param ret 찾은 카드에 적힌 수 (조커 카드의 경우 -1)
         *
         * @return 조건에 맞는 카드를 찾았으면 1, 아니면 0
         * */
        int findNumber(int mNum, int mNth, int[] ret);

        /***
         * 조커 카드에서 사용할 조커 값을 mValue로 변경한다.<br/>
         *
         * @param mValue 변경할 조커 값 (1 ≤ mValue ≤ 30)
         * */
        void changeJoker(int mValue);
    }

    private static BufferedReader br;
    private static SWEA_PRO_P04_UserSolution usersolution = new SWEA_PRO_P04_UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_PUT = 200;
    private final static int CMD_FIND = 300;
    private final static int CMD_CHANGE = 400;

    private final static int MAX_CARD_NUM = 5;
    private final static int MAX_RET_NUM = 4;

    private final static int numbers[] = new int[MAX_CARD_NUM];
    private final static int ret_numbers[] = new int[MAX_RET_NUM];
    private final static int ans_numbers[] = new int[MAX_RET_NUM];

    private static boolean run() throws Exception {

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());
        int ret, ans;
        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");
            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                int joker = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < MAX_CARD_NUM; i++)
                    numbers[i] = Integer.parseInt(stdin.nextToken());
                usersolution.init(joker, numbers);
                ok = true;
            } else if (query == CMD_PUT) {
                int dir = Integer.parseInt(stdin.nextToken());
                for (int i = 0; i < MAX_CARD_NUM; i++)
                    numbers[i] = Integer.parseInt(stdin.nextToken());
                usersolution.putCards(dir, numbers);
            } else if (query == CMD_FIND) {
                int num, Nth;
                num = Integer.parseInt(stdin.nextToken());
                Nth = Integer.parseInt(stdin.nextToken());
                ans = Integer.parseInt(stdin.nextToken());
                ret = usersolution.findNumber(num, Nth, ret_numbers);
                if (ans != ret) {
                    ok = false;
                }
                if (ans == 1) {
                    for (int i = 0; i < MAX_RET_NUM; i++) {
                        ans_numbers[i] = Integer.parseInt(stdin.nextToken());
                        if (ans_numbers[i] != ret_numbers[i]) {
                            ok = false;
                        }
                    }
                }
            } else if (query == CMD_CHANGE) {
                int value = Integer.parseInt(stdin.nextToken());
                usersolution.changeJoker(value);
            }

        }
//        usersolution.printInfo();
        return ok;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(4)));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

//        T = 1;
        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}