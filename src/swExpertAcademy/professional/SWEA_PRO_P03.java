package swExpertAcademy.professional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 3] 14612. [Pro] 메모리 시스템 (8/12)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 *
 * */
public class SWEA_PRO_P03 {

    interface UserSolution {

        /**
         * 각 테스트 케이스의 처음에 호출된다.<br/>
         * 전체 메모리의 크기 N이 주어진다. 모두 빈 공간이다.
         *
         * @param N 메모리의 크기 (30 ≤ N ≤ 100,000,000)
         * */
        void init(int N);

        /**
         * mSize 크기의 메모리 할당이 요청된다.<br/>
         * 수용할 수 있는 빈 공간 중에서, 가장 작은 빈 공간에 할당한다. 즉, 최적 적합 할당(Best Fit Allocation) 방식을 사용한다.<br/>
         * 가장 작은 빈 공간이 여러 개 있을 경우에는, 가장 앞쪽에 있는 빈 공간에 할당한다.<br/>
         *
         * @param mSize 할당이 요청된 메모리 크기 (1 ≤ mSize ≤ N)
         *
         * @return  할당된 메모리 공간의 시작 주소 값을 반환한다.
         *          할당에 실패한 경우에는 -1을 반환한다.
         * */
        int allocate(int mSize);

        /**
         * mAddr 시작 주소 값을 갖는 메모리의 해제가 요청된다.<br/>
         * mAddr 값이 시작 주소 값이 아니거나, 이미 빈 공간일 경우에는 해제에 실패하고 -1을 반환한다.<br/>
         * 그렇지 않을 경우, 해당 메모리 공간을 빈 공간으로 바꾸고, 인접한 빈 공간이 있다면 병합한다. 그리고 해제된 메모리 공간의 크기를 반환한다.
         *
         * @param mAddr 해제가 요청된 메모리 주소 ( 0 ≤ mAddr ≤ N - 1 )
         *
         * @return  해제된 메모리 공간의 크기를 반환한다.
         *          해제에 실패한 경우에는 -1을 반환한다.
         * */
        int release(int mAddr);
    }

    private final static int CMD_INIT = 1;
    private final static int CMD_ALLOCATE = 2;
    private final static int CMD_RELEASE = 3;

    private final static SWEA_PRO_P03_UserSolution usersolution = new SWEA_PRO_P03_UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        int n, addr, size;
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    n = Integer.parseInt(st.nextToken());
                    usersolution.init(n);
                    okay = true;
                    break;
                case CMD_ALLOCATE:
                    size = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.allocate(size);
                    if (ret != ans)
                        okay = false;
                    break;
                case CMD_RELEASE:
                    addr = Integer.parseInt(st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.release(addr);
                    if (ret != ans)
                        okay = false;
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(3)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}
