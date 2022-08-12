package swExpertAcademy.practicalTraining;

import java.util.Arrays;

/**
 * [No. 4] 14611. [Pro] 계산 게임 (8/16)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 세그먼트 트리(Segment Tree)
 * */
public class SWEA_PRO_P4_UserSolution implements SWEA_PRO_P4.UserSolution {

    private static final int SIZE = 100_005;
    private static final int DIVISOR = 20;

    private final int[] numbers = new int[SIZE];
    private final int[] subSum = new int[SIZE];
    private final int[] jokerCount = new int[SIZE];
    private int start, end;

    private final int[] jokerSumOf = new int[5];
    private int joker;

    @Override
    public void init(int mJoker, int[] mNumbers) {
//        log("● init: mJoker=%d, mNumbers=%s", mJoker, Arrays.toString(mNumbers));
        start = 50_000;
        end = 50_004;

        int jokerCnt = 0;
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            int num = mNumbers[i];

            numbers[start + i] = num;

            if (i == 4) continue;

            if (num == -1) jokerCnt += 1;
            else sum += num;
        }

        jokerCount[start] = jokerCnt;
        subSum[start] = sum;

        jokerCnt -= mNumbers[0] == -1 ? 1 : 0;
        jokerCnt += mNumbers[4] == -1 ? 1 : 0;

        sum -= mNumbers[0] != -1 ? mNumbers[0] : 0;
        sum += mNumbers[4] != -1 ? mNumbers[4] : 0;

        jokerCount[start + 1] = jokerCnt;
        subSum[start + 1] = sum;

        changeJoker(mJoker);
//        printInfo();
    }

    @Override
    public void putCards(int mDir, int[] mNumbers) {
//        log("★ putCards: mDir=%d, mNumbers=%s", mDir, Arrays.toString(mNumbers));

        if (mDir == 0) { // 왼쪽 추가
            start -= 5;

            int jokerCnt = 0;
            int sum = 0;

            for (int i = 0; i < 5; i++) {
                int num = mNumbers[i];

                numbers[start + i] = num;

                if (i == 4) continue;

                if (num == -1) jokerCnt += 1;
                else sum += num;
            }

            jokerCount[start] = jokerCnt;
            subSum[start] = sum;

            fillSubSum(jokerCnt, sum, start + 1, start + 7);

        } else { // 오른쪽 추가
            System.arraycopy(mNumbers, 0, numbers, end + 1, 5);

            fillSubSum(jokerCount[end - 3], subSum[end - 3], end - 2, end + 3);

            end += 5;
        }
//        printInfo();
    }

    @Override
    public int findNumber(int mNum, int mNth, int[] ret) {
        int n = 0;
        int last = end - 2;

        for (int i = start; i < last; i++) {
            if ((subSum[i] + jokerSumOf[jokerCount[i]]) % DIVISOR == mNum) {
                n += 1;
            }
            if (n == mNth) {
                System.arraycopy(numbers, i, ret, 0, 4);
                break;
            }
        }

        return n == mNth ? 1 : 0;
    }

    @Override
    public void changeJoker(int mValue) {
        joker = mValue;
        int sum = mValue;
        for (int i = 1; i <= 4; i++) {
            jokerSumOf[i] = sum;
            sum += mValue;
        }
    }

    private void fillSubSum(int jokerCnt, int sum, int start, int end) {
        for (int i = start; i < end; i++) {
            int oldNumber = numbers[i - 1];
            int newNumber = numbers[i + 3];

            if (oldNumber == -1) jokerCnt -= 1;
            else sum -= oldNumber;

            if (newNumber == -1) jokerCnt += 1;
            else sum += newNumber;

            jokerCount[i] = jokerCnt;
            subSum[i] = sum;
        }
    }

    public void printInfo() {
        log("   ♣ printInfo: start=%d, end=%d, joker=%d", start, end, joker);
        log("       numbers=   %s", arrayToString(numbers, start, end));
        log("       subSum=    %s", arrayToString(subSum, start, end));
        log("       jokerCount=%s", arrayToString(jokerCount, start, end));
        log("       jokerSumOf=%s", arrayToString(jokerSumOf, 0, 4));
    }

    private String arrayToString(int[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder().append('[');
        for (int i = start; i <= end; i++) {
            sb.append(arr[i]).append(", ");
        }
        return sb.delete(sb.length() - 2, sb.length()).append(']').toString();
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}