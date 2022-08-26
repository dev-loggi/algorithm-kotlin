package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 2] 14614. [Pro] 긴 사다리 게임 (8/11)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_PRO_P02 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.<br/>
         * 테스트 케이스의 시작 시, 모든 세로줄이 그어져 있다.<br/>
         * 테스트 케이스의 시작 시, 가로줄은 없다.
         * */
        void init();

        /**
         * Y 좌표가 mY인 가로줄이 존재하지 않음이 보장된다.<br/>
         * (mX, mY)와 (mX+1, mY)를 잇는 가로줄을 추가한다.
         *
         * @param mX : X 좌표 (1 ≤ mX ≤ 99)
         * @param mY : Y 좌표 (1 ≤ mY ≤ 999,999,999)
         * */
        void add(int mX, int mY);

        /**
         * (mX, mY)와 (mX+1, mY)를 잇는 가로줄이 존재함이 보장된다.<br/>
         * (mX, mY)와 (mX+1, mY)를 잇는 가로줄을 삭제한다.
         *
         * @param mX X 좌표 (1 ≤ mX ≤ 99)
         * @param mY Y 좌표 (1 ≤ mY ≤ 999,999,999)
         * */
        void remove(int mX, int mY);

        /**
         * 현재 맵에서 사다리 게임을 진행했을 때, mID번 참가자가 지나게 되는 가로줄의 개수를 반환한다.<br/>
         * mID번 참가자는 (mID, 0)에서 출발한다.
         *
         * @param mID 참가자의 번호 (1 ≤ mID ≤ 100)
         *
         * @return mID번 참가자가 지나게 되는 가로줄의 개수
         * */
        int numberOfCross(int mID);

        /**
         * 현재 맵에서 사다리 게임을 진행했을 때, (mX, mY)를 지나게 되는 참가자의 번호를 반환한다.<br/>
         * (mX, mY)는 가로줄과 세로줄이 만나는 지점이 아님이 보장된다.<br/>
         * (mX, mY)를 지나게 되는 참가자는 항상 존재하며, 유일하다.
         *
         * @param mX X 좌표 (1 ≤ mX ≤ 100)
         * @param mY Y 좌표 (1 ≤ mY ≤ 1,000,000,000)
         *
         * @return (mX, mY)를 지나게 되는 참가자의 번호
         * */
        int participant(int mX, int mY);
    }

    private final static int CMD_INIT				= 1;
    private final static int CMD_ADD				= 2;
    private final static int CMD_REMOVE				= 3;
    private final static int CMD_NUMBER_OF_CROSS	= 4;
    private final static int CMD_PARTICIPANT		= 5;

    private final static SWEA_PRO_P02_UserSolution userSolution = new SWEA_PRO_P02_UserSolution();

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;

        int mX, mY, mID;

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
                    userSolution.init();
                    isCorrect = true;
                    break;
                case CMD_ADD:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    userSolution.add(mX, mY);
                    break;
                case CMD_REMOVE:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    userSolution.remove(mX, mY);
                    break;
                case CMD_NUMBER_OF_CROSS:
                    mID = Integer.parseInt(st.nextToken());
                    userAns = userSolution.numberOfCross(mID);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                case CMD_PARTICIPANT:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    userAns = userSolution.participant(mX, mY);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(2)));

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
