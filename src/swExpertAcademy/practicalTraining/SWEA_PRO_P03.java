package swExpertAcademy.practicalTraining;

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
