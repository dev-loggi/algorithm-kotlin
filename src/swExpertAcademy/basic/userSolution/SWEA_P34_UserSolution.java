package swExpertAcademy.basic.userSolution;

/**
 * [No. 34] 3000. 중간값 구하기
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 10초 / C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P34_UserSolution {

    private int[] maxHeap;
    private int[] minHeap;
    private int maxHeapLast;
    private int minHeapLast;

    private int mid;

    public void init(int N, int A) {
        // 1 ≤ N ≤ 200,000
        // 1 ≤ A, X, Y ≤ 10^9
        maxHeap = new int[N + 3];
        minHeap = new int[N + 3];
        maxHeapLast = 0;
        minHeapLast = 0;
        mid = A;
    }

    public int add(int x, int y) {
        if (x > y) {
            int tmp = x;
            x = y;
            y = tmp;
        }


        if (mid < x) { // mid < x < y
            addMinHeap(x);
            addMinHeap(y);
            addMaxHeap(mid);

            mid = removeMinHeap();
        } else if (y < mid) { // x < y < mid
            addMaxHeap(x);
            addMaxHeap(y);
            addMinHeap(mid);

            mid = removeMaxHeap();
        } else { // x <= mid <= y
            addMaxHeap(x);
            addMinHeap(y);
        }

        return mid;
    }

    private void addMaxHeap(int n) {
        maxHeap[++maxHeapLast] = n;

        int parent = maxHeapLast / 2;
        int child = maxHeapLast;

        while (0 < parent) {
            if (maxHeap[parent] >= maxHeap[child])
                break;

            swap(maxHeap, parent, child);

            parent /= 2;
            child /= 2;
        }
    }

    private void addMinHeap(int n) {
        minHeap[++minHeapLast] = n;

        int parent = minHeapLast / 2;
        int child = minHeapLast;

        while (0 < parent) {
            if (minHeap[parent] <= minHeap[child])
                break;

            swap(minHeap, parent, child);

            parent /= 2;
            child /= 2;
        }
    }

    private int removeMaxHeap() {
        int max = maxHeap[1];
        maxHeap[1] = maxHeap[maxHeapLast--];

        int parent = 1;
        int child1 = 2;
        int child2 = 3;
        int bigChild;

        while (child1 <= maxHeapLast) {
            bigChild = (child2 <= maxHeapLast)
                    ? maxHeap[child1] >= maxHeap[child2] ? child1 : child2
                    : child1;

            if (maxHeap[parent] >= maxHeap[bigChild])
                break;

            swap(maxHeap, parent, bigChild);

            parent = bigChild;
            child1 = bigChild * 2;
            child2 = bigChild * 2 + 1;
        }

        return max;
    }

    private int removeMinHeap() {
        int min = minHeap[1];
        minHeap[1] = minHeap[minHeapLast--];

        int parent = 1;
        int child1 = 2;
        int child2 = 3;
        int bigChild;

        while (child1 <= minHeapLast) {
            bigChild = (child2 <= minHeapLast)
                    ? minHeap[child1] <= minHeap[child2] ? child1 : child2
                    : child1;

            if (minHeap[parent] <= minHeap[bigChild])
                break;

            swap(minHeap, parent, bigChild);

            parent = bigChild;
            child1 = bigChild * 2;
            child2 = bigChild * 2 + 1;
        }

        return min;
    }

    private void swap(int[] heap, int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public int[] getAll() {
        int size = maxHeapLast + minHeapLast + 1;
        int[] arr = new int[size];

        arr[size / 2] = mid;

        for (int i = maxHeapLast - 1; i >= 0; i--) {
            arr[i] = removeMaxHeap();
        }
        for (int i = minHeapLast + 1; i < size; i++) {
            arr[i] = removeMinHeap();
        }

        return arr;
    }

    private void logln(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}