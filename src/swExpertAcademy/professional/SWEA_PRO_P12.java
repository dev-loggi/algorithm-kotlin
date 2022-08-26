package swExpertAcademy.professional;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * [No. 12] 13088. [Pro] 순위표
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가, 끝날 시 destroy() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 대회에 참가하는 선수의 수는 6,000명 이하이다.<br/>
 * 3. 각 테스트 케이스에서 changeProblemScore() 함수 호출 횟수는 1,000회 이하이다.<br/>
 * 4. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 최대 50,000 회이다.<br/>
 * 5. 각 함수에서 인자로 주어지는 모든 사용자와 문제 이름은 알파벳 대문자 또는 소문자로 구성되며, 길이는 1 이상 10 이하이고, ‘＼0’ 문자로 끝나는 문자열이다.<br/>
 *
 * 세그먼트 트리, 이분 탐색, AVL Tree
 * */
public class SWEA_PRO_P12 {

    interface UserSolution {

        /**
         * 각 테스트 케이스의 처음에 호출된다.
         * */
        void init ();

        /**
         * 각 테스트 케이스의 마지막에 호출된다.
         * 빈 함수로 두어도 채점에는 영향을 주지 않는다.
         * */
        void destroy ();

        /**
         * 이름이 mPlayerName 인 선수가 대회에 새로 참가한다.
         * 새로 참가하는 선수는 모든 문제를 시도하지 않은 상태로 시작한다.
         * 이미 참가하고 있는 선수와 동일 이름의 선수가 추가 되는 경우는 없다.
         *
         * @param name 추가되는 선수의 이름 ( 1 ≤ 길이 ≤ 10 )
         * */
        void newPlayer(char[] name);

        /**
         * 이름이 mProblemName 이고 배점이 mScore 인 문제가 대회에 추가된다.
         * 이미 추가된 문제와 동일한 이름의 문제가 추가 되는 경우는 없다.
         *
         * @param name 추가되는 문제의 이름 ( 1 ≤ 길이 ≤ 10 )
         * @param score 추가되는 문제의 배점 ( 1 ≤ mScore ≤ 100 )
         * */
        void newProblem(char[] name, int score);

        /**
         * 이름이 mProblemName 인 문제의 배점을 mNewScore 로 변경한다.
         * 배점이 변경되면 이미 성공한 선수의 총점도 변경 된다.
         * 이름이 mProblemName 인 문제가 존재함이 보장된다.
         *
         * @param name 배점을 변경할 문제의 이름 ( 1 ≤ 길이 ≤ 10 )
         * @param new_score 변경된 배점 ( 1 ≤ mNewScore ≤ 100 )
         * */
        void changeProblemScore(char[] name, int new_score);

        /**
         * 이름이 mPlayerName 인 선수가 이름이 mProblemName 인 문제를 시도하여, 그 결과가 mAttemptResult 로 주어진다.
         * mAttemptResult 의 값이 0 이면 해당 문제 풀이를 실패했다는 뜻이고, 1이면 해당 문제 풀이에 성공했다는 뜻이다.
         * 성공한 경우 선수의 총점에 해당 문제의 배점이 추가된다.
         * 이름이 mPlayerName 인 선수와 이름이 mProblemName 인 문제가 존재함이 보장된다.
         * 문제당 시도 기회는 단 한 번이며, 한 선수가 동일한 문제를 두 번 이상 시도하는 경우는 존재하지 않음이 보장된다.
         *
         * @param player_name 문제 풀이를 시도한 선수의 이름 ( 1 ≤ 길이 ≤ 10 )
         * @param problem_name 풀이를 시도한 문제의 이름 ( 1 ≤ 길이 ≤ 10 )
         * @param attempt_result 문제 풀이 결과 ( 0 또는 1 )
         * */
        void attemptProblem(char[] player_name, char[] problem_name, int attempt_result);

        /**
         * 이름이 mPlayerName 인 선수의 현재 순위를 세가지 방식으로 계산해 Result 구조체에 담아 반환한다.
         * 순위는 (자신보다 점수가 높은 선수의 수) + 1 로 계산하며 동점자는 같은 순위이다.
         *
         * (1) current_rank: 현재 순위. 현재까지 풀이에 성공한 문제들의 총점 기준 순위.
         * (2) best_rank: 최선의 순위. 대회가 종료될 때 가능한 최고 순위.
         * (3) worst_rank: 최악의 순위. 대회가 종료될 때 가능한 최저 순위.
         *
         * best_rank 와 worst_rank 를 계산할 때는 자신 뿐만 아니라 다른 선수들의 아직 시도하지 않은 문제들에 대한 결과를 고려해야 함에 유의하라.
         * 이름이 mPlayerName 인 선수가 존재함이 보장된다.
         *
         * @param player_name 순위를 계산할 선수의 이름 ( 1 ≤ 길이 ≤ 10 )
         *
         * @return 세가지 방식으로 계산한 순위를 Result 구조체에 담아 반환한다.
         * */
        SWEA_PRO_P12_UserSolution.Result getRank(char[] player_name);
    }

    private final static SWEA_PRO_P12_UserSolution userSolution = new SWEA_PRO_P12_UserSolution();
    private static BufferedReader br;
    private static StringTokenizer line;
    private final static int INIT					= 0;
    private final static int NEW_PLAYER				= 1;
    private final static int NEW_PROBLEM			= 2;
    private final static int CHANGE_PROBLEM_SCORE 	= 3;
    private final static int ATTEMPT_PROBLEM		= 4;
    private final static int GET_RANK 		        = 5;
    private final static SWEA_PRO_P12_UserSolution.Result ans = new SWEA_PRO_P12_UserSolution.Result();
    private static int[] api;
    private static long[] time;

    private static void stringToArray(char dst[], String s){
        for(int i=0;i<s.length();i++) dst[i] = s.charAt(i);
        dst[s.length()] = 0;
    }

    private static int run (int tc_result) throws Exception
    {

        StringTokenizer line = new StringTokenizer(br.readLine(), " ");
        int N;
        N = Integer.parseInt(line.nextToken());

        long start, end;

        api[0]++;
        start = System.currentTimeMillis();
        userSolution.init();
        end = System.currentTimeMillis();
        time[0] += end - start;

        for (int i = 0; i < N; ++i) {

            line = new StringTokenizer(br.readLine(), " ");
            char name[] = new char[64], player_name[] = new char[64], problem_name[] = new char[64];
            int score, new_score, attempt_result;
            int current_rank_ans, best_rank_ans, worst_rank_ans;
            SWEA_PRO_P12_UserSolution.Result res;

            int cmd = Integer.parseInt(line.nextToken());
            api[cmd]++;
            switch (cmd) {
                case NEW_PLAYER:
                    stringToArray(name, line.nextToken());

                    start = System.currentTimeMillis();
                    userSolution.newPlayer(name);
                    end = System.currentTimeMillis();
                    time[cmd] += end - start;
                    break;

                case NEW_PROBLEM:
                    stringToArray(name, line.nextToken());
                    score = Integer.parseInt(line.nextToken());

                    start = System.currentTimeMillis();
                    userSolution.newProblem(name, score);
                    end = System.currentTimeMillis();
                    time[cmd] += end - start;
                    break;

                case CHANGE_PROBLEM_SCORE:
                    stringToArray(name, line.nextToken());
                    new_score = Integer.parseInt(line.nextToken());

                    start = System.currentTimeMillis();
                    userSolution.changeProblemScore(name, new_score);
                    end = System.currentTimeMillis();
                    time[cmd] += end - start;
                    break;

                case ATTEMPT_PROBLEM:
                    stringToArray(player_name, line.nextToken());
                    stringToArray(problem_name, line.nextToken());
                    attempt_result = Integer.parseInt(line.nextToken());

                    start = System.currentTimeMillis();
                    userSolution.attemptProblem(player_name, problem_name, attempt_result);
                    end = System.currentTimeMillis();
                    time[cmd] += end - start;
                    break;

                case GET_RANK:
                    stringToArray(player_name, line.nextToken());

                    start = System.currentTimeMillis();
                    res = userSolution.getRank(player_name);
                    end = System.currentTimeMillis();
                    time[cmd] += end - start;

                    current_rank_ans = Integer.parseInt(line.nextToken());
                    best_rank_ans = Integer.parseInt(line.nextToken());
                    worst_rank_ans = Integer.parseInt(line.nextToken());
                    ans.current_rank = current_rank_ans;
                    ans.best_rank = best_rank_ans;
                    ans.worst_rank = worst_rank_ans;
                    boolean collect = true;
                    if ( !(res.current_rank == current_rank_ans && res.best_rank == best_rank_ans && res.worst_rank == worst_rank_ans) ) {
                        tc_result = 0;
                        collect = false;
                    }
//                    log("    ▷ [%b] Ans=%s, res=%s", collect, ans, res);
                    break;
            }
        }

        userSolution.destroy();

        return tc_result;
    }

    private static void logInfo() {
        log("\n ♠ 실행 결과 >>");
        log("[INIT]                  api=%6d, time=%f 초", api[0], time[0] / 1000f);
        log("[NEW_PLAYER]            api=%6d, time=%f 초", api[1], time[1] / 1000f);
        log("[NEW_PROBLEM]           api=%6d, time=%f 초", api[2], time[2] / 1000f);
        log("[CHANGE_PROBLEM_SCORE]  api=%6d, time=%f 초", api[3], time[3] / 1000f);
        log("[ATTEMPT_PROBLEM]       api=%6d, time=%f 초", api[4], time[4] / 1000f);
        log("[GET_RANK]              api=%6d, time=%f 초", api[5], time[5] / 1000f);
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private static String cmdToString(int cmd) {
        switch (cmd) {
            case 0: return "INIT";
            case NEW_PLAYER: return "NEW_PLAYER";
            case NEW_PROBLEM: return "NEW_PROBLEM";
            case CHANGE_PROBLEM_SCORE: return "CHANGE_PROBLEM_SCORE";
            case ATTEMPT_PROBLEM: return "ATTEMPT_PROBLEM";
            case GET_RANK: return "GET_RANK";
        }
        return "";
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream(ProInputFile.number(12)));

        api = new int[6];
        time = new long[6];

        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(br.readLine(), " ");

        int TC = Integer.parseInt(line.nextToken());
        int Ans = Integer.parseInt(line.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            System.out.println("#" + testcase + " " + run(Ans));

        }

        logInfo();
    }
}
