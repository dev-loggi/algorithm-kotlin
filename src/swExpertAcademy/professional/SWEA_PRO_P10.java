package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 10] 14604. [Pro] AI로봇 (8/24)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가 한 번 호출된다.<br/>
 * 2. 로봇의 총 대수 N은 10 이상 50,000 이하의 정수이다.<br/>
 * 3. 시각 cTime 은 1 이상 1,000,000,000 이하의 정수이다.<br/>
 * 4. 각 테스트 케이스에서 init()을 제외한 함수가 호출될 때마다 cTime은 증가하는 값으로 주어진다.<br/>
 * 5. 각 테스트 케이스에서 callJob() 함수를 통해 투입되는 로봇 대수의 총합은 200,000 이하이다.<br/>
 * 6. 각 테스트 케이스에서 init() 을 제외한 함수의 호출 횟수의 총합은 50,000 이하이다.<br/>
 * */
public class SWEA_PRO_P10 {


    interface UserSolution {
        /**
         * 테스트 케이스에 대한 초기화하는 함수. 각 테스트 케이스의 처음에 1회 호출된다.
         * N은 로봇의 개수이다. 각 로봇은 1부터 N까지 서로 다른 고유 번호가 부여되어 있다.
         *
         * 현재 시각은 0이다.
         * 초기에 모든 로봇은 센터에 있고 지능지수는 0이다. 고장 난 로봇은 없다.
         *
         * @param N 로봇의 대수 (10 ≤ N ≤ 50,000)
         * */
        void init(int N);

        /**
         * 현재 시각이 cTime 이다. ID가 wID인 작업에 mNum 대의 로봇을 투입한다.
         * 해당 작업에 투입된 로봇들의 고유 번호를 모두 합한 값을 반환한다.
         *
         * mOpt = 0인 경우, 높은 지능 우선 방식으로 로봇을 투입한다.
         * mOpt = 1인 경우, 낮은 지능 우선 방식으로 로봇을 투입한다.
         *
         * 각 투입 방식의 자세한 설명은 본문 내용을 참조하라.
         * 고장 나거나 작업 중인 로봇은 해당 작업에 투입되지 않는다.
         * 작업에 투입 가능한 로봇의 수보다 더 많이 투입을 요청하는 경우는 발생하지 않음을 보장한다.
         *
         * 각 테스트 케이스에서 wID는 해당 함수가 처음 호출할 때 1이고 호출될 때마다 1씩 증가한다.
         *
         * @param cTime 현재 시각 (1 ≤ cTime ≤ 1,000,000,000)
         * @param wID 작업 ID (1 ≤ wID ≤ 50,000)
         * @param mNum 작업에 투입할 로봇의 대수 (1 ≤ mNum ≤ N)
         * @param mOpt 로봇을 투입하는 방식 (mOpt = 0 or 1)
         *
         * @return 투입된 로봇들의 고유 번호 총합
         * */
        int callJob(int cTime, int wID, int mNum, int mOpt);

        /**
         * 현재 시각이 cTime이다. ID가 wID인 작업에 투입된 로봇들이 센터로 복귀하고 트레이닝을 바로 시작한다.
         * callJob 함수로 호출된 적이 있는 wID 값만 입력으로 들어오는 것을 보장한다.
         * 같은 wID 값으로 두 번 이상 들어오는 경우도 없다.
         * 투입된 로봇들이 모두 고장 나 복귀하는 로봇이 없는 경우도 있다.
         *
         * @param cTime 현재 시각 (1 ≤ cTime ≤ 1,000,000,000)
         * @param wID 작업 ID (1 ≤ wID ≤ 50,000)
         * */
        void returnJob(int cTime, int wID);

        /**
         * 현재 시각이 cTime이다. 작업 중이고 고유 번호가 rID인 로봇이 고장 난다.
         * 해당 로봇은 즉시 센터로 복귀한다. 해당 로봇은 수리가 되기 전까지 작업에 투입할 수 없다.
         * 해당 로봇이 이미 고장 난 로봇이거나 센터에서 트레이닝 중인 경우 이 함수가 호출되면 무시된다.
         *
         * @param cTime 현재 시각 (1 ≤ cTime ≤ 1,000,000,000)
         * @param rID 로봇 번호 (1 ≤ rID ≤ N)
         * */
        void broken(int cTime, int rID);

        /**
         * 현재 시각이 cTime이다. 번호가 rID인 로봇의 수리가 완료된다.
         * 해당 로봇은 다시 작업에 투입될 수 있다.
         * 수리가 완료된 로봇은 지능지수가 0이 되고 트레이닝을 시작한다.
         * 해당 로봇이 고장 난 로봇이 아닌 경우 이 함수가 호출되면 무시된다.
         *
         * @param cTime 현재 시각 (1 ≤ cTime ≤ 1,000,000,000)
         * @param rID 로봇 번호 (1 ≤ rID ≤ N)
         * */
        void repair(int cTime, int rID);

        /**
         * 현재 시각이 cTime이다. 고유 번호가 rID인 로봇의 상태를 확인한다.
         * 로봇의 상태에 따라 다음 중 한가지 값을 반환한다.
         *
         *   1) 만약 로봇이 고장 난 상태이면 0을 반환한다.
         *   2) 만약 로봇이 작업에 투입되어 있으면, (투입된 작업의 ID) * (-1)을 반환한다.
         *   3) 그 외의 경우는 로봇의 지능지수를 반환한다.
         *
         * @param cTime 현재 시각 (1 ≤ cTime ≤ 1,000,000,000)
         * @param rID 로봇의 고유 번호 (1 ≤ rID ≤ N)
         *
         * @return 로봇의 상태를 나타내는 값
         * */
        int check(int cTime, int rID);
    }

    private final static int CALL_JOB				= 100;
    private final static int RETURN_JOB				= 200;
    private final static int BROKEN					= 300;
    private final static int REPAIR 				= 400;
    private final static int CHECK					= 500;

    private static final SWEA_PRO_P10_UserSolution userSolution = new SWEA_PRO_P10_UserSolution();

    private static int run (BufferedReader br, int score) throws Exception
    {
        int N, Q;
        int wIDCnt = 1;
        int cTime, mNum, rID, wID, mOpt;
        int res = -1, ans;

        N = Integer.parseInt(br.readLine());
        userSolution.init(N);

        Q = Integer.parseInt(br.readLine());

        while (Q-- > 0)
        {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
            {
                case CALL_JOB:
                    cTime = Integer.parseInt(st.nextToken());
                    mNum = Integer.parseInt(st.nextToken());
                    mOpt = Integer.parseInt(st.nextToken());
                    res = userSolution.callJob(cTime, wIDCnt, mNum, mOpt);
                    ans = Integer.parseInt(st.nextToken());
                    if (ans != res)
                        score = 0;
                    wIDCnt++;
//                    log("    ☞ CALL_JOB[%b] ans=%d, res=%d\n", ans==res, ans, res);
                    break;
                case RETURN_JOB:
                    cTime = Integer.parseInt(st.nextToken());
                    wID = Integer.parseInt(st.nextToken());
                    userSolution.returnJob(cTime, wID);
                    break;
                case BROKEN:
                    cTime = Integer.parseInt(st.nextToken());
                    rID = Integer.parseInt(st.nextToken());
                    userSolution.broken(cTime, rID);
                    break;
                case REPAIR:
                    cTime = Integer.parseInt(st.nextToken());
                    rID = Integer.parseInt(st.nextToken());
                    userSolution.repair(cTime, rID);
                    break;
                case CHECK:
                    cTime = Integer.parseInt(st.nextToken());
                    rID = Integer.parseInt(st.nextToken());
                    res = userSolution.check(cTime, rID);
                    ans = Integer.parseInt(st.nextToken());;
                    if (ans != res)
                        score = 0;
//                    log("    ☞ CHECK[%b] ans=%d, res=%d\n", ans==res, ans, res);
                    break;
                default:
                    score = 0;
                    break;
            }
        }
        //userSolution.printInfo();
        return score;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception
    {
        System.setIn(new java.io.FileInputStream(ProInputFile.number(10)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int Ans = Integer.parseInt(line.nextToken());

//        TC = 1;
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            System.out.println("#" + testcase + " " + run(br, Ans));
        }

        br.close();
    }
}