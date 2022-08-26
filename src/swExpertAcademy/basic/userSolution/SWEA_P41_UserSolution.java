package swExpertAcademy.basic.userSolution;

/**
 * [No. 41] 9843. 촛불 이벤트
 *
 * 시간 : 100000개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P41_UserSolution {


    /**
     * 풀이 1)
     *
     * k(k+1)/2 = N
     * k(k+1) = 2N
     * k^2 < k(k+1) < (k+1)^2
     * k^2 < 2N < (k+1)^2
     *
     * ∴ k < √2N < k+1 (k는 양수)
     *
     * 위 부등호를 만족하는 k를 다시 맨 처음 식에 대입하여 등호가 성립하면 정답.
     * */
    public long solution(long N) {
        long k = (long) Math.sqrt(2 * N);

        return k * (k + 1) == 2 * N ? k : -1;
    }

    /**
     * 풀이 2)
     *
     * k(k+1)/2 = 2N 에서 양의 근
     * k = (-1 + sqrt(1 + 8N)) / 2
     *
     * 1 + 8N 이 제곱수가 되어야 한다.
     * */
    public long solution2(long N) {
        long D = 1 + 8 * N; // 이차식의 판별식

        if (isSquare(D)) {
            return -1;
        } else {
            return (long) (-1 + Math.sqrt(D)) / 2;
        }
    }

    private boolean isSquare(long n) {
        return !(Math.sqrt(n) - (long) Math.sqrt(n) > 0);
    }


    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}