package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;
import swExpertAcademy.basic.userSolution.SWEA_P48_UserSolution;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 48] 14707. [Pro] 단어검색
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 삭제하거나 검색할 때 주어지는 단어는 와일드카드 문자 ‘?’가 최대 3개 포함될 수 있다.<br/>
 * 3. 각 테스트 케이스에서 add() 함수의 호출 횟수는 20,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 40,000 이하이다.<br/>
 *
 * Trie
 * */
public class SWEA_P48 {

    public interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.
         * 시스템에 단어가 없는 상태가 된다.
         * */
        void init();

        /**
         * 시스템에 단어 str 을 추가한 다음에, 시스템에 존재하는 단어 str 의 개수를 반환한다.
         * str 은 길이가 5이상 30이하의 영어 알파벳 소문자로 구성되어 있고, ‘＼0’ 문자로 끝나는 문자열이다.
         * 시스템에 이미 존재하는 단어도 추가해야 한다.
         *
         * @param str 추가되는 단어 ( 5 ≤ 단어의 길이 ≤ 30 )
         *
         * @return 시스템에 존재하는 단어 str의 개수를 반환한다.
         * */
        int add(char[] str);

        /**
         * 시스템에서 str 과 일치하는 단어를 모두 삭제하고, 삭제한 단어의 개수를 반환한다.
         * str 은 길이가 5이상 30이하의 영어 알파벳 소문자와 와일드카드 문자 ‘?’로 구성되어 있고, ‘＼0’ 문자로 끝나는 문자열이다.
         * str 에 포함될 수 있는 와일드카드 문자 ‘?’는 최대 3개이다.
         * 삭제한 단어가 없을 경우, 0을 반환한다.
         *
         * @param str 삭제하는 단어 ( 5 ≤ 단어의 길이 ≤ 30,  0 ≤ ‘?’의 개수 ≤ 3  )
         *
         * @return 시스템에서 삭제한 단어의 개수를 반환한다.
         * */
        int remove(char[] str);

        /**
         * 시스템에서 str과 일치하는 단어를 검색하고, 검색된 단어의 개수를 반환한다.
         * str 은 길이가 5이상 30이하의 영어 알파벳 소문자와 와일드카드 문자 ‘?’로 구성되어 있고, ‘＼0’ 문자로 끝나는 문자열이다.
         * str 에 포함될 수 있는 와일드카드 문자 ‘?’는 최대 3개이다.
         * 검색된 단어가 없을 경우, 0을 반환한다.
         *
         * @param str 검색하는 단어 ( 5 ≤ 단어의 길이 ≤ 30,  0 ≤ ‘?’의 개수 ≤ 3  )
         *
         * @return 시스템에서 검색된 단어의 개수를 반환한다.
         * */
        int search(char[] str);
    }

    private final static int MAX_LEN = 30;
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_REMOVE = 3;
    private final static int CMD_SEARCH = 4;

    private final static SWEA_P48_UserSolution userSolution = new SWEA_P48_UserSolution();

    private static void String2Char(char[] buf, String str) {
        for (int i = 0; i < str.length(); ++i)
            buf[i] = str.charAt(i);
        buf[str.length()] = '\0';
    }

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        char[] str = new char[MAX_LEN + 1];
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    userSolution.init();
                    okay = true;
                    break;
                case CMD_ADD:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.add(str);
                    if (ret != ans)
                        okay = false;
//                    log("    ▷ [%b] CMD_ADD: ans=%d, ret=%d\n", ans==ret, ans, ret);
                    break;
                case CMD_REMOVE:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.remove(str);
                    if (ret != ans)
                        okay = false;
//                    log("    ▷ [%b] CMD_REMOVE: ans=%d, ret=%d\n", ans==ret, ans, ret);
                    break;
                case CMD_SEARCH:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = userSolution.search(str);
                    if (ret != ans)
                        okay = false;
//                    log("    ▷ [%b] CMD_SEARCH: ans=%d, ret=%d\n", ans==ret, ans, ret);
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(InputFile.number(48)));

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