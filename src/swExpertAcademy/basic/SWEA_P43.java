package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;
import swExpertAcademy.basic.userSolution.SWEA_P43_UserSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 43] 9999. 광고 시간 정하기
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C의 경우 3초 / C++의 경우 3초 / Java의 경우 5초 / Python의 경우 5초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P43 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(43)));
        SWEA_P43_UserSolution user = new SWEA_P43_UserSolution();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int L = Integer.parseInt(br.readLine()); // 한나가 올리려는 광고의 시간 (1 ≤ L ≤ 10^8)
            int N = Integer.parseInt(br.readLine()); // 피크 시간대의 갯수 (1 ≤ N ≤ 10^5)

            // 스페이스 스퀘어 광고판의 피크 시간대
            // si: 시작시간, ei: 종료시간 (0 ≤ si < ei < 10^8)
            // ei < s(i+1) (1 ≤ i < N)
            int[][] peekTime = new int[N][3];
            int start, end, acc = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                acc += end - start;

                peekTime[i][0] = start;
                peekTime[i][1] = end;
                peekTime[i][2] = acc;
            }

            int answer = user.solution(L, N, peekTime);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        br.close();
    }
}
