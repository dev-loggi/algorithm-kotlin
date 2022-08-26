package swExpertAcademy.basic.userSolution;

/**
 * [No. 31] 12372. 기초 Partial Sort 연습
 *
 * 시간 : 5개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 1초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P31_UserSolution2 {

    private static final int MAX_USER = 100_000;

    private int[][] heap;
    private int last = 0;

    public void init() {
        // 최대 힙, 우선순위 큐
        // heap[1] 이 루트 노드(최댓값)
        // heap[n][0]: uID, maxHeap[n][1]: income
        heap = new int[MAX_USER + 1][2];
        last = 0;
    }

    /**
     * user 추가하는 함수
     *
     * @param uID user id, 0부터 시작해서 순차적으로 증가한다 (0 ≤ uID < 100000)
     * @param income user의 수입, 클수록 우선순위가 높다. 만약 수입이 동일한 경우 uID가 작은 user 의 우선순위가 높다.
     * */
    public void addUser(int uID, int income) {
        last += 1;

        heap[last][0] = uID;
        heap[last][1] = income;

        int parent = last / 2;
        int child = last;

        while (parent > 0) {
            if (compare(parent, child) == 1) { // parent < child
                swap(parent, child);
            } else {
                break;
            }

            parent /= 2;
            child /= 2;
        }
    }

    /**
     * 수입이 가장 큰 user 10명의 uID를 수입에 대해 내림차순으로 구하는 함수이다.<br/>
     * 총 user 의 수가 10명이 되지 않으면 존재하는 user의 uID를 수입에 대해 내림차순으로 구한다.
     *
     * @param result 수입이 큰 순서대로 10개의 uID를 저장한다. (1 ≤ result 개수 ≤ 10)
     * @return result 의 개수를 반환한다.
     * */
    public int getTop10(int[] result) {
        int[][] tmp = new int[10][2];

        int idx;
        for (idx = 0; idx < 10 && 0 < last; idx++) {
            tmp[idx][0] = heap[1][0];
            tmp[idx][1] = heap[1][1];

            heap[1][0] = heap[last][0];
            heap[1][1] = heap[last][1];
            last -= 1;

            int parent = 1;
            int bigChild = 2;
            int left = 2; int right = 3;

            while (left <= last) {
                right = right <= last ? right : -1;

                bigChild = compare(left, right) == 0 ? left : right;

                if (compare(parent, bigChild) == 0)
                    break;

                swap(parent, bigChild);

                parent = bigChild;
                left = bigChild * 2;
                right = bigChild * 2 + 1;
            }
        }

        for (int i = 0; i < idx; i++) {
            result[i] = tmp[i][0];

            addUser(tmp[i][0], tmp[i][1]);
        }

        return idx;
    }

    private void swap(int i, int j) {
        int a = heap[i][0];
        int b = heap[i][1];
        heap[i][0] = heap[j][0];
        heap[i][1] = heap[j][1];
        heap[j][0] = a;
        heap[j][1] = b;
    }

    private int compare(int i, int j) {
        if (j == -1) {
            return 0;
        } else {
            return heap[i][1] > heap[j][1] || (heap[i][1] == heap[j][1] && heap[i][0] < heap[j][0]) ? 0 : 1;
        }
    }

    private void printHeap() {
        log("heap(last=%d) => ", last);
        for (int i = 1; i <= last; i++) {
            log("(%d, %d), ", heap[i][0], heap[i][1]);
        }
        logln("");
    }

    private void log(String s, Object ... args) {
        System.out.printf(s, args);
    }

    private void logln(String s, Object ... args) {
        log(s + "\n", args);
    }
}