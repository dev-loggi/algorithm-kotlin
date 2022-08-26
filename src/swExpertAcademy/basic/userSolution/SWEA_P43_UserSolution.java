package swExpertAcademy.basic.userSolution;

/**
 * [No. 43] 9999. 광고 시간 정하기
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C의 경우 3초 / C++의 경우 3초 / Java의 경우 5초 / Python의 경우 5초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 이분 탐색
 * */
public class SWEA_P43_UserSolution {

    public int solution(int L, int N, int[][] peekTimes) {
        int max = -1;

        for (int[] peek : peekTimes) {
            int begin = peek[0];
            int end = begin + L;

            int[] lastPeek = findPeek(peekTimes, end);

            int sum = lastPeek[2] - peek[2] + peek[1] - peek[0];

            if (lastPeek[1] > end && lastPeek[0] < end) {
                sum -= lastPeek[1] - end;
            } else if (lastPeek[1] > end) {
                sum -= lastPeek[1] - lastPeek[0];
            }

            max = Math.max(max, sum);
        }

        return max;
    }

    private int[] findPeek(int[][] peekTimes, int target) {
        int start = 0;
        int end = peekTimes.length - 1;

        while (end > start) {
            int mid = (start + end) / 2;

            if (peekTimes[mid][1] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return peekTimes[end];
    }
}