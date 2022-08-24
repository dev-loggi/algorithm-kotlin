package swExpertAcademy.practicalTraining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 11] 14605. [Pro] P2P 네트워크 (8/25)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 1번 호출된다.<br/>
 * 2. 각 테스트 케이스에서 share() 함수는 최대 5,000번 호출된다.<br/>
 * 3. 각 테스트 케이스에서 request() 함수는 최대 5,000번 호출된다.<br/>
 * 4. 각 테스트 케이스에서 complete() 함수는 최대 5,000번 호출된다.<br/>
 * 5. share()와 request()의 인자인 파일 이름(mFilename)은 길이 4 이상, 9 이하의 알파벳 소문자로 이루어진 문자열이며, 문자열의 끝에는 ‘＼0’이 추가되어 있다.<br/>
 * 6. 각 테스트케이스 내의 request 함수들에서 mTID는 중복되지 않는다.<br/>
 *
 * Trie, BFS, Graph
 * */
public class SWEA_PRO_P11 {

    interface UserSolution {
        /**
         * 이 함수는 각 테스트케이스가 시작되는 시점에 1번 호출 된다.
         *
         * 네트워크에 N 명의 사용자가 존재한다.
         * 사용자는 1, 2, 3, … , N의 ID를 갖는다.
         *
         * 링크는 N-1개이고 i = 0, 1, … ,N-2에 대해 i번째 링크는 ID가 mUID1[i], mUID2[i]인 사용자를 연결하고 그 대역폭은 mBandwidth[i]이다.
         * 링크는 서로 다른 두 사용자를 직접 연결하고, 두 사용자를 직접 연결하는 링크는 최대 1개이다.
         *
         * 테스트 케이스의 시작 시, 어떤 파일도 전송되고 있지 않기 때문에 각 링크의 대역폭은 사용되지 않은 상태이다.
         * 테스트 케이스의 시작 시, 모든 사용자는 자신의 어떤 파일도 공유 설정하지 않은 상태이다.
         *
         * @param N             네트워크의 사용자 수 (2 ≤ N ≤ 200)
         * @param mUID1         링크의 사용자 1의 ID (1 ≤ mUID1[i] ≤ N)
         * @param mUID2         링크의 사용자 2의 ID (1 ≤ mUID2[i] ≤ N)
         * @param mBandwidth    링크의 대역폭 (1 ≤ mBandwidth[i] ≤ 100,000)
         * */
        void init(int N, int[] mUID1, int[] mUID2, int[] mBandwidth);

        /**
         * ID가 mUID 인 사용자가 자신이 보유한 이름이 mFileName 인 파일을 공유 상태로 설정한다.
         *
         * 파일 크기는 mFileSize 이다.
         * 네트워크 내에서 모든 파일은 파일 이름이 같다면 파일 크기와 내용은 동일하다.
         * (ID가 mUID 인 사용자는 파일 mFileName 을 이미 공유하고 있을 수도 있다.)
         *
         * @param mUID      사용자의 ID (1 ≤ mUID ≤ N)
         * @param mFileName 파일의 이름 (4 ≤ 이름의 길이 ≤ 9)
         * @param mFileSize 파일의 크기 (1 ≤ mFilesize ≤ 1,000)
         *
         * @return 파일 mFileName 을 포함한 ID가 mUID 인 사용자가 공유 중인 서로 다른 파일의 수
         * */
        int share(int mUID, char[] mFileName, int mFileSize);

        /**
         * ID가 mUID 인 사용자가 이름이 mFileName 인 파일을 받고자 네트워크에 요청한다.
         *
         * 이 요청의 ID는 mTID 이다.
         * 네트워크 내에서 모든 파일은 파일 이름이 같다면 파일 크기와 내용은 동일하다.
         *
         * 다음과 같이 파일을 보내줄 사용자를 결정한다.
         *
         * 1. 파일을 공유 상태로 설정한 각 사용자들과의 전송 경로를 살펴본다.
         *    전송 경로 상의 모든 각 링크의 사용 가능한 대역폭이 파일의 크기 이상인 사용자를 선택한다.
         * 2. 그런 사용자가 여러 명일 경우 가장 적은 수의 링크를 통해 전송이 가능한 사용자를 선택한다.
         * 3. 그런 사용자가 여러 명일 경우 ID가 가장 작은 사용자를 선택한다.
         *
         * 파일을 보내줄 수 있는 사용자가 있으면 즉시 전송이 시작되며 전송 경로 상의 모든 링크들에 대해 해당 파일의 크기만큼 대역폭이 사용된다.
         * 파일을 보내줄 수 있는 사용자가 없으면 해당 요청은 무시하고 -1을 반환한다.
         *
         * 각 테스트케이스 내에서 호출되는 request 함수들에서 mTID 는 중복되지 않는다.
         * 본 함수가 호출되는 시점에 ID가 mUID 인 사용자는 파일 mFileName 을 가지고 있지 않음이 보장된다.
         * mUID 와 mFileName 이 동일하지만 mTID 가 다른 여러 request()가 호출될 수 있으며 그런 경우에도
         * (기본적으로 각 request()에 의한 파일 전송은 독립적으로 진행되기 때문에)
         * 각 request()가 별도의 링크 대역폭을 사용한다.
         *
         * @param mUID      사용자의 ID (1 ≤ mUID ≤ N)
         * @param mFileName 파일의 이름 (4 ≤ 이름의 길이 ≤ 9)
         * @param mTID      요청의 ID (1 ≤ mTID ≤ 5,000)
         *
         * @return  파일을 보내줄 수 있는 사용자가 있으면 그 사용자의 ID
         *          파일을 보내줄 수 있는 사용자가 없으면 -1,
         * */
        int request(int mUID, char[] mFileName, int mTID);

        /**
         * ID가 mTID 인 요청에 의해 시작된 전송이 정상적으로 종료된다.
         *
         * 그 전송 경로 상의 모든 링크에 대해 이 전송으로 인한 대역폭 사용을 해제한다.
         * mTID 를 요청한 사용자 뿐만 아니라 거쳐간 모든 사용자가 파일을 수신 받으며 그 파일들은 공유 상태로 설정된다.
         *
         * 예를 들어, 사용자 1에서 사용자 5로 파일을 전송하고 그 경로가 "1, 3, 4, 5"일 때 본 함수가 호출되면 해당 파일은 사용자 3, 4, 5가 모두 공유하게 된다.
         * ID가 mTID 인 요청은 현재 전송이 진행중인 요청임이 보장된다.
         *
         * @param mTID 정상적으로 종료된 요청의 ID (1 ≤ mTID ≤ 5,000)
         *
         * @return 전송받은 파일을 포함한 mTID 를 요청한 사용자가 공유 중인 서로 다른 파일의 수
         * */
        int complete(int mTID);
    }

    private static BufferedReader br;
    private static final SWEA_PRO_P11_UserSolution userSolution = new SWEA_PRO_P11_UserSolution();

    private final static int CMD_INIT = 100;
    private final static int CMD_SHARE = 200;
    private final static int CMD_REQUEST = 300;
    private final static int CMD_COMPLETE = 400;

    private static boolean run() throws Exception {
        int[] u = new int[200];
        int[] v = new int[200];
        int[] bandwidth = new int[200];
        char[] filename = new char[10];

        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");

        int query_num = Integer.parseInt(stdin.nextToken());

        boolean ok = false;

        for (int q = 0; q < query_num; q++) {
            stdin = new StringTokenizer(br.readLine(), " ");

            int query = Integer.parseInt(stdin.nextToken());

            if (query == CMD_INIT) {
                int N = Integer.parseInt(stdin.nextToken());
                for(int i = 0; i < N-1; i++)
                    u[i] = Integer.parseInt(stdin.nextToken());
                for(int i = 0; i < N-1; i++)
                    v[i] = Integer.parseInt(stdin.nextToken());
                for(int i = 0; i < N-1; i++)
                    bandwidth[i] = Integer.parseInt(stdin.nextToken());

                userSolution.init(N, u, v, bandwidth);
                ok = true;
            } else if (query == CMD_SHARE) {
                int uid = Integer.parseInt(stdin.nextToken());
                String inputStr = stdin.nextToken();
                for(int k = 0; k < inputStr.length(); ++k)
                    filename[k] = inputStr.charAt(k);
                filename[inputStr.length()] = '\0';
                int filesize = Integer.parseInt(stdin.nextToken());

                int ret = userSolution.share(uid, filename, filesize);
                int ans = Integer.parseInt(stdin.nextToken());
                if(ret != ans)
                    ok = false;
            } else if (query == CMD_REQUEST) {
                int uid = Integer.parseInt(stdin.nextToken());
                String inputStr = stdin.nextToken();
                for(int k = 0; k < inputStr.length(); ++k)
                    filename[k] = inputStr.charAt(k);
                filename[inputStr.length()] = '\0';
                int tid = Integer.parseInt(stdin.nextToken());

                int ret = userSolution.request(uid, filename, tid);
                int ans = Integer.parseInt(stdin.nextToken());
                if(ret != ans)
                    ok = false;
            } else if (query == CMD_COMPLETE) {
                int tid = Integer.parseInt(stdin.nextToken());

                int ret = userSolution.complete(tid);
                int ans = Integer.parseInt(stdin.nextToken());
                if(ret != ans)
                    ok = false;
            }
        }
        return ok;
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(11)));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            int score = run() ? MARK : 0;
            System.out.println("#" + tc + " " + score);
        }

        br.close();
    }
}
