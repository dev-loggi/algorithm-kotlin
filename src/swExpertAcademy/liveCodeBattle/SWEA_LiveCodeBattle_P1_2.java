package swExpertAcademy.liveCodeBattle;

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
 * */
public class SWEA_LiveCodeBattle_P1_2 {

    private static class UserSolution {

        private static final int MAX = 100_000;

        private int[] team;
        private int[] score;

        public void init() {
            team = new int[MAX + 1];
            score = new int[MAX + 1];
        }

        public void hire(int mID, int mTeam, int mScore) {
            // mID : 고유번호 (1 ≤ mID ≤ 100,000)
            // mTeam : 소속팀 (1 ≤ mTeam ≤ 5)
            // mScore : 평판 점수 (1 ≤ mScore ≤ 5)

            team[mID] = mTeam;
            score[mID] = mScore;
        }

        public void fire(int mID) {
            team[mID] = 0;
            score[mID] = 0;
        }

        public void updateSoldier(int mID, int mScore) {
            score[mID] = mScore;
        }

        public void updateTeam(int mTeam, int mChangeScore) { // 무조건 완전 탐색
            if (mChangeScore == 0) return;

            for (int id = 1; id <= MAX; id++) {
                if (team[id] == mTeam) {
                    int change = score[id] + mChangeScore;
                    score[id] = (change >= 5) ? 5 : ((change <= 1) ? 1 : change);
                }
            }
        }

        public int bestSoldier(int mTeam) { // 스코어 정렬, 중에 베스트 아이디
            int bestScore = 0;
            int bestId = 0;
            for (int id = MAX; id >= 1; id--) {
                if (team[id] == mTeam) {
                    if (bestScore < score[id]) {
                        bestScore = score[id];
                        bestId = id;
                    }
                }
            }

            return bestId;
        }
    }

    private final static int CMD_INIT				= 1;
    private final static int CMD_HIRE				= 2;
    private final static int CMD_FIRE				= 3;
    private final static int CMD_UPDATE_SOLDIER		= 4;
    private final static int CMD_UPDATE_TEAM		= 5;
    private final static int CMD_BEST_SOLDIER		= 6;

    private final static UserSolution usersolution = new UserSolution();

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
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("src/swExpertAcademy/_inputs/live_input_p1.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        //TC = 5;
        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }

    static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
