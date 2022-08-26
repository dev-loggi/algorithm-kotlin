package swExpertAcademy.basic.userSolution;

import java.util.Arrays;

/**
 * [No. 39] 3998. [Professional] Inversion Counting
 *
 * 시간 : 20개 테스트케이스를 합쳐서 C의 경우 5초 / C++의 경우 5초 / Java의 경우 7초 / Python의 경우 14초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * merge sort 이용
 * */
public class SWEA_P39_UserSolution {

    private long count;
    private int[] a, tmp;

    public long solution(int[] arr) {
        count = 0;
        a = arr;
        tmp = Arrays.copyOf(arr, arr.length);

        mergeSort(0, arr.length - 1);

        return count;
    }

    private void mergeSort(int left, int right) {
        if (left >= right)
            return;

        int mid = (left + right) >> 1;

        mergeSort(left, mid);
        mergeSort(mid + 1, right);

        merge(left, mid, right);
    }

    private void merge(int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (a[i] < a[j]) {
                tmp[k++] = a[i++];
            } else {
                count += mid - i + 1;
                tmp[k++] = a[j++];
            }
        }

        while (i <= mid) tmp[k++] = a[i++];
        while (j <= right) tmp[k++] = a[j++];

        System.arraycopy(tmp, left, a, left, right - left + 1);
    }

    public int solution2(int[] arr) {
        return insertionSort(arr);
    }

    private int insertionSort(int[] arr) {
        int count = 0;

        for (int i = 1; i < arr.length; i++) {
            int n = arr[i];

            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= n)
                    break;

                arr[j + 1] = arr[j];
                arr[j] = n;

                count += 1;
            }
        }

        return count;
    }
}