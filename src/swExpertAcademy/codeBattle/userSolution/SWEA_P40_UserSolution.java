package swExpertAcademy.codeBattle.userSolution;


import java.util.Arrays;

/**
 * [No. 40] 10507. 영어 공부
 *
 * 시간 : 31개 테스트케이스를 합쳐서 C의 경우 2초 / C++의 경우 2초 / Java의 경우 4초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P40_UserSolution {

    private static final int MAX_DAY = 1_000_000;

    /**
     * 1 ≤ N, P ≤ 200,000
     * 0 ≤ studies[i] ≤ 1,000,000
     * */
    public int solution(int N, int P, int[] studies) {
        boolean[] isStudy = new boolean[MAX_DAY + 1];

        for (int day : studies) {
            isStudy[day] = true;
        }

        int lastDayOfStudy = studies[N - 1];
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int k = !isStudy[0] ? 1 : 0;

        while (k < P || isStudy[end + 1]) {
            if (!isStudy[++end]) k += 1;
        }

        maxLength = end - start;


        while (start < lastDayOfStudy && start < MAX_DAY) {
            if (!isStudy[start++])
                k -= 1;

            while (end < MAX_DAY) {
                if (k == P && !isStudy[end + 1])
                    break;

                if (!isStudy[++end])
                    k += 1;
            }

            int length = end - start;

            if (maxLength < length)
                maxLength = length;
        }

        return maxLength + 1;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}