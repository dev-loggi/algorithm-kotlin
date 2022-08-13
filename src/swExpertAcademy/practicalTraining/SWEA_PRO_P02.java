package swExpertAcademy.practicalTraining;

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

    private final static int CMD_INIT				= 1;
    private final static int CMD_ADD				= 2;
    private final static int CMD_REMOVE				= 3;
    private final static int CMD_NUMBER_OF_CROSS	= 4;
    private final static int CMD_PARTICIPANT		= 5;

    private final static SWEA_PRO_P02_UserSolution usersolution = new SWEA_PRO_P02_UserSolution();

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
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_ADD:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    usersolution.add(mX, mY);
                    break;
                case CMD_REMOVE:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    usersolution.remove(mX, mY);
                    break;
                case CMD_NUMBER_OF_CROSS:
                    mID = Integer.parseInt(st.nextToken());
                    userAns = usersolution.numberOfCross(mID);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                case CMD_PARTICIPANT:
                    mX = Integer.parseInt(st.nextToken());
                    mY = Integer.parseInt(st.nextToken());
                    userAns = usersolution.participant(mX, mY);
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
