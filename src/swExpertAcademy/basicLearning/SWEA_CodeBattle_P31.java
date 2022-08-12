package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;
import swExpertAcademy.basicLearning.userSolution.SWEA_P31_UserSolution;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [No. 31] 2930. 힙
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C/C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P31 {

    private final static int MAX_INPUT = 100000;
    private final static int MAX_NUM = 30000;

    private final static SWEA_P31_UserSolution usersolution = new SWEA_P31_UserSolution();

    private static BufferedReader br;

    private static int[] input = new int[MAX_INPUT];
    private static long seed = 13410;

    private static long pseudoRand() {
        seed = (seed * 214013 + 2531011) & 0xffffffffL;
        return (seed>>11) % MAX_NUM;
    }

    private static void makeInput(int inputLen) {
        for(int i = 0; i < inputLen; i++) {
            input[i] = (int)(pseudoRand());
        }
    }

    private static int run() throws Exception {
        int score = 100;
        int N, userNum, uID = 0, ret, sum, ans;
        int[] result = new int[10];
        String str;

        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            userNum = Integer.parseInt(str);
            makeInput(userNum);

            for(int j = 0; j < userNum; j++) {
                usersolution.addUser(uID++, input[j]);
            }
            ret = usersolution.getTop10(result);

            sum = 0;
            for(int j = 0; j < ret; j++) {
                sum += result[j];
            }

            str = br.readLine();
            ans = Integer.parseInt(str);

            System.out.printf("▶▶▶ ret=%d, sum=%d, ans=%d\n", ret, sum, ans);
            if(sum != ans) {
                score = 0;
            }
        }
        return score;
    }
    public static void main(String[] args) throws Exception {
        int TC;
        System.setIn(new java.io.FileInputStream(InputFile.number(31)));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);
//        TC = 3;
        for (int tc = 1; tc <= TC; tc++) {
            usersolution.init();
            System.out.println("#" + tc + " " + run());
        }
    }
}