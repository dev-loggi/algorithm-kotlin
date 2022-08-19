package swExpertAcademy.practicalTraining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [2022-07-29] 22 하계 대학생 S/W 알고리즘 특강 1차 라이브 코드배틀
 *
 * 13072. [Pro] 병사관리
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 hire() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 3. 각 테스트 케이스에서 fire() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 updateSoldier() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 5. 각 테스트 케이스에서 updateTeam() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 6. 각 테스트 케이스에서 bestSoldier() 함수의 호출 횟수는 100 이하이다.<br/>
 *
 * 원형 더블 연결 리스트(순환 구조)
 * */
public class SWEA_PRO_P01 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.
         * 테스트 케이스의 시작 시 고용된 병사는 없다.
         * */
        void init();

        /**
         * 고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore 인 병사를 고용한다.
         * 한 테스트 케이스 내에서 동일한 mID를 가진 병사가 여러 번 고용되는 경우는 없음이 보장된다.
         *
         * @param mID 고유번호 (1 ≤ mID ≤ 100,000)
         * @param mTeam 소속팀 (1 ≤ mTeam ≤ 5)
         * @param mScore 평판 점수 (1 ≤ mScore ≤ 5)
         * */
        void hire(int mID, int mTeam, int mScore);

        /**
         * 고유번호가 mID인 병사를 해고한다.
         * fire() 함수 호출 시, 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
         *
         * @param mID 고유번호 (1 ≤ mID ≤ 100,000)
         * */
        void fire(int mID);

        /**
         * 고유번호가 mID인 병사의 평판 점수를 mScore 로 변경한다.
         * 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
         *
         * @param mID 고유번호 (1 ≤ mID ≤ 100,000)
         * @param mScore 평판 점수 (1 ≤ mScore ≤ 5)
         * */
        void updateSoldier(int mID, int mScore);

        /**
         * 소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
         * 소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
         *
         * updateTeam() 함수에서의 평판 점수 변경은 아래의 규칙에 따른다.
         *
         * ‘변경 전 평판 점수 + mChangeScore’가 5보다 클 경우, 평판 점수를 5로 변경한다.
         * ‘변경 전 평판 점수 + mChangeScore’가 1보다 작을 경우, 평판 점수를 1로 변경한다.
         * 그 외의 경우, 평판 점수를 ‘변경 전 평판 점수 + mChangeScore’로 변경한다.
         *
         * @param mTeam 소속팀 (1 ≤ mTeam ≤ 5)
         * @param mChangeScore 평판 점수의 변화량 (-4 ≤ mChangeScore ≤ 4)
         * */
        void updateTeam(int mTeam, int mChangeScore);

        /**
         * 소속팀이 mTeam 인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
         * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
         * 소속팀이 mTeam 인 병사가 한 명 이상 고용되어 있음이 보장된다.
         *
         * @param mTeam 소속팀 (1 ≤ mTeam ≤ 5)
         *
         * @return 평판 점수가 가장 높은 병사의 고유번호
         * */
        int bestSoldier(int mTeam);
    }

    private final static int CMD_INIT				= 1;
    private final static int CMD_HIRE				= 2;
    private final static int CMD_FIRE				= 3;
    private final static int CMD_UPDATE_SOLDIER		= 4;
    private final static int CMD_UPDATE_TEAM		= 5;
    private final static int CMD_BEST_SOLDIER		= 6;

    private final static SWEA_PRO_P01_UserSolution usersolution = new SWEA_PRO_P01_UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;
        int numQuery;
        int mID, mTeam, mScore, mChangeScore;
        int userAns, ans;
        boolean isCorrect = false;
        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q) {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_HIRE:
                    mID = Integer.parseInt(st.nextToken());
                    mTeam = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.hire(mID, mTeam, mScore);
                    break;
                case CMD_FIRE:
                    mID = Integer.parseInt(st.nextToken());
                    usersolution.fire(mID);
                    break;
                case CMD_UPDATE_SOLDIER:
                    mID = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.updateSoldier(mID, mScore);
                    break;
                case CMD_UPDATE_TEAM:
                    mTeam = Integer.parseInt(st.nextToken());
                    mChangeScore = Integer.parseInt(st.nextToken());
                    usersolution.updateTeam(mTeam, mChangeScore);
                    break;
                case CMD_BEST_SOLDIER:
                    mTeam = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestSoldier(mTeam);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
//                    logln("    [%b] CMD_BEST_SOLDIER: Ans=%d, user=%d\n", ans==userAns, ans, userAns);
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    private static void logln(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(1)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

//        TC = 1;
        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}
