package swExpertAcademy.practicalTraining;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 7] 14634. [Pro] 우주자원개발 (8/19)<br/><br/>
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초<br/>
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내<br/>
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가, 종료 시 destroy() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 update() 함수는 최대 800 회 호출된다.<br/>
 * 3. 각 테스트 케이스에서 participate() 함수는 최대 800 회 호출된다.<br/>
 * 4. 각 테스트 케이스에서 cancel() 함수는 최대 200 회 호출된다.<br/>
 * 5. 각 테스트 케이스에서 calcProfit() 함수는 최대 100 회 호출된다.<br/>
 * */
public class SWEA_PRO_P07 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.
         * 개발 지역의 한 변의 길이 N, 구역의 크기 K, 자원의 종류의 수 M이 주어진다.
         * N은 K의 배수임이 보장된다.
         * 초기에는 모든 구역의 단위 면적당 개발 비용은 10이다.
         * 자원의 종류의 수는 M이고, 자원의 가격이 순서대로 주어진다.
         * mResPrice[0] 가 첫번째 자원의 가격이고, mResPrice[M-1]이 마지막 자원의 가격이다.
         *
         * @param N 지역의 크기 (4 ≤ N ≤ 1,000)
         * @param K 구역의 크기 (2 ≤ K ≤ 100, 1 ≤ N/K ≤ 10)
         * @param M 자원의 종류의 수 (1 ≤ M ≤ 10)
         * @param mResPrice 자원의 가격 (1 ≤ mResPrice[] ≤ 100)
         * */
        void init(int N, int K, int M, int[] mResPrice);

        /**
         * 각 테스트 케이스의 마지막에 호출된다.
         * 빈 함수로 두어도 채점에는 영향을 주지 않는다.
         * */
        void destroy();

        /**
         * 구역 (mBlockR, mBlockC) 의 단위 면적당 개발비용과 자원 정보가 변경된다.
         * mCost는 구역 (mBlockR, mBlockC) 의 단위 면적당 개발 비용이며,
         * 구역 (mBlockR, mBlockC) 의 모든 지점에 동일하게 적용된다.
         *
         * 각 지점에 자원 정보는 자원의 유무가 하위 M개 비트에 순차적으로 나열된다.
         * 이때 하위 M비트중 최상위 비트가 첫번째 자원의 유무에 대한 정보이다.
         *
         * 만약 M이 2인 경우, mResInfo[][] 에 입력된 값이
         * 3 인 경우, 2진수로 11 이므로 첫번째 자원과 두번째 자원이 존재한다.
         * 2 인 경우, 2진수로 10 이므로 첫번째 자원만 존재한다.
         * 1 인 경우, 2진수로 01 이므로 두번째 자원만 존재한다.
         * 0 인 경우, 아무 자원도 없음을 의미한다.
         *
         * @param mBlockR 구역의 행 번호 (0 ≤ mBlockR < N/K)
         * @param mBlockC 구역의 열 번호 (0 ≤ mBlockC < N/K)
         * @param mCost 구역 내의 단위 지점의 개발 비용 (1 ≤ mCost ≤ 100)
         * @param mResInfo 자원의 정보 (0 ≤ mResInfo[][] < 2M)
         * */
        void update(int mBlockR, int mBlockC, int mCost, int[][] mResInfo);

        /**
         * mCompany회사가 전체 지역 좌표를 기준으로 (mR1, mC1), (mR2, mC2)를 위 왼쪽, 아래 오른쪽으로 하는 직사각형 영역에 대한 개발을 제안한다.
         * 제안한 영역에 대한 예상 개발이익을 반환한다. 개발이익이 음수일 경우 -1을 반환한다.
         * mR1 ≤ mR2 , mC1 ≤ mC2 임이 보장된다.
         * 현재 mCompany 회사의 입찰이 없음이 보장된다.
         * 이전에 입찰을 취소한 업체가 다시 입찰에 참여 할 수 있다.
         * mCompany 는 길이가 1 이상 10 이하의 영문 소문자이고, ‘＼0’ 문자로 끝나는 문자열이다.
         *
         * @param mCompany 회사 이름 (1 ≤ 길이 ≤ 10)
         * @param mR1 제안 영역의 위쪽 좌표 (0 ≤ mR1 ≤ N-1)
         * @param mC1 제안 영역의 왼쪽 좌표 (0 ≤ mC1 ≤ N-1)
         * @param mR2 제안 영역의 아래쪽 좌표 (0 ≤ mR2 ≤ N-1)
         * @param mC2 제안 영역의 오른쪽 좌표 (0 ≤ mC2 ≤ N-1)
         *
         * @return 제안한 영역에 대한 예상 개발이익, 이익이 음수 일 경우 -1
         * */
        int participate(char[] mCompany, int mR1, int mC1, int mR2, int mC2);

        /**
         * mCompany 회사의 입찰을 취소한다.
         * 현재 mCompany 회사의 입찰이 있음이 보장된다.
         * mCompany 는 길이가 1 이상 10 이하의 영문 소문자이고, ‘＼0’ 문자로 끝나는 문자열이다.
         *
         * @param mCompany 회사 이름 (1 ≤ 길이 ≤ 10)
         * */
        void cancel(char[] mCompany);

        /**
         * 현재 입찰에 참여중인 회사 중 현재 기준으로 개발이익이 가장 큰 상위 mTop개 회사의 제안의 예상 이익의 합계를 반환한다.
         * mTop 보다 입찰 업체수가 적은 경우 현재 입찰한 업체의 이익의 합을 반환한다.
         * 이익이 음수인 제안은 합계에서 제외한다.
         * 이익의 합계가 10억을 넘는 경우는 없다.
         *
         * @param mTop 회사의 수 (1 ≤ mTop ≤ 5)
         *
         * @return 상위 mTop 개 회사의 제안으로 얻을 수 있는 개발이익의 합계
         * */
        int calcProfit(int mTop);
    }

    private static Scanner sc;
    private static SWEA_PRO_P07_UserSolution userSolution = new SWEA_PRO_P07_UserSolution();

    private static int mSeed;
    private static int pseudo_rand()
    {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    static final int CMD_INIT = 100;
    static final int CMD_DESTROY = 200;
    static final int CMD_UPDATE = 300;
    static final int CMD_PARTICIPATE = 400;
    static final int CMD_CANCEL = 500;
    static final int CMD_CALCPROFIT = 600;

    private static void String2Char(char[] buf, String str) {
        Arrays.fill(buf, (char)0);
        for (int i = 0; i < str.length(); ++i)
            buf[i] = str.charAt(i);
        buf[str.length()] = '\0';
    }

    private static int run() throws IOException {
        int isOK = 0;

        int mN, mK = 0, mM;
        int[] mResPrice = new int[10];
        int ResMod=0;
        int mBlockR, mBlockC, mCost;
        int [][] mResInfo= new int [100][100];
        int mR1, mC1, mR2, mC2;
        char[] mCompany = new char[11];
        int mTop;

        int N = sc.nextInt();
        int S = sc.nextInt();

        int cmd, result, check;

        int inputOrder = 0;

        for (int i = 0; i < N; ++i) {

            cmd =  sc.nextInt();
            switch (cmd) {
                case CMD_INIT:
                    mSeed = S;
                    mN = sc.nextInt();
                    mK = sc.nextInt();
                    mM = sc.nextInt();
                    ResMod = 1 << mM;
                    for (int m = 0; m < mM; m++) {
                        mResPrice[m] =  sc.nextInt();
                    }
                    userSolution.init(mN, mK, mM, mResPrice);
                    inputOrder = 0;
                    isOK = 1;
                    break;

                case CMD_UPDATE:
                    mBlockR = sc.nextInt();
                    mBlockC = sc.nextInt();
                    mCost = sc.nextInt();
                    if (S != -1) {
                        if(inputOrder % 6 == 0){
                            for (int r = 0; r < mK; r++) {
                                for (int c = 0; c < mK; c++) {
                                    mResInfo[r][c] =  sc.nextInt();
                                }
                            }
                        }else {
                            for (int r = 0; r < mK; r++) {
                                for (int c = 0; c < mK; c++) {
                                    mResInfo[r][c] = pseudo_rand() % ResMod;
                                }
                            }
                        }
                    } else {
                        mResInfo[0][0] = sc.nextInt();
                        mResInfo[0][1] = sc.nextInt();
                        mResInfo[1][0] = sc.nextInt();
                        mResInfo[1][1] = sc.nextInt();
                    }
                    userSolution.update(mBlockR, mBlockC, mCost, mResInfo);
                    inputOrder++;

                    break;

                case CMD_PARTICIPATE:
                    String2Char(mCompany,  sc.next());
                    mR1 = sc.nextInt();
                    mC1 = sc.nextInt();
                    mR2 = sc.nextInt();
                    mC2 = sc.nextInt();
                    result = userSolution.participate(mCompany, mR1, mC1, mR2, mC2);
                    check = sc.nextInt();
                    if (result != check)
                        isOK = 0;
//                    log("[%c] CMD_PARTICIPATE: Ans=%d, res=%d\n", result==check?'O':'X', check, result);
                    break;

                case CMD_CANCEL:
                    String2Char(mCompany,  sc.next());
                    userSolution.cancel(mCompany);
                    break;

                case CMD_CALCPROFIT:
                    mTop = sc.nextInt();
                    result = userSolution.calcProfit(mTop);
                    check = sc.nextInt();
                    if (result != check)
                        isOK = 0;
//                    log("[%c] CMD_CALC_PROFIT: Ans=%d, res=%d\n", result==check?'O':'X', check, result);
                    break;
            }
        }
        userSolution.destroy();
        return isOK;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public static void main(String[] args) throws Exception {
        int T, MARK;
        System.setIn(new java.io.FileInputStream(ProInputFile.number(7)));
        sc = new Scanner(System.in);

        T = sc.nextInt();
        MARK = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            if (run() == 1)
                System.out.println("#" + tc + " " + MARK);
            else
                System.out.println("#" + tc + " 0");
        }
        sc.close();
    }
}
