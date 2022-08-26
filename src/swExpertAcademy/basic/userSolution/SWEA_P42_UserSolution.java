package swExpertAcademy.basic.userSolution;

import java.util.Arrays;

/**
 * [No. 42] 11446. 사탕 가방
 *
 * 시간 : 200개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 이분 탐색
 *
 *
 * */
public class SWEA_P42_UserSolution {

    /**
     * 수열의 길이 (1 ≤ N ≤ 100)
     * 가방에 들어가야할 사탕의 갯수 (1 ≤ M ≤ 10^18)
     * (1 ≤ Ai ≤ 10^18)
     */
    public long solution(int N, long M, long[] candies) {
        logln("solution(): N=%d, M=%d, candies=%s", N, M, Arrays.toString(candies));
        long min = 1;
        long max = Arrays.stream(candies).max().orElse(1);

        logln("min=%d, max=%d", min, max);
        // 최소, 최댓값이 같을 때까지만
        while (min <= max) {
            long mid = (min + max) / 2; // 가방의 갯수(가방의 갯수를 이분 탐색으로 줄여나가는 것)
            long cnt = 0; // 가방 하나에 들어가는 사탕의 갯수

            for (long candy : candies) { // 사탕 갯수, 가방 갯수를 다 더하기
                cnt += (candy / mid);
            }

            if (cnt < M) { // 가방이 너무 많아서 들어갈 사탕이 없다.
                max = mid - 1;
            } else { // 가방이 너무 적다
                min = mid + 1;
            }

            logln("    min=%d, max=%d, mid=%d, cnt=%d", min, max, mid, cnt);
        }

        return max;
    }

    private void logln(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
