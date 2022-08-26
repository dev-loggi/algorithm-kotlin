package swExpertAcademy.basic.userSolution;

import java.util.Arrays;

public class SWEA_P44_UserSolution {

    private int min, count;

    public int[] solution(int N, int M, int c1, int c2, int[] arrZ1, int[] arrZ2) {
        min = Integer.MAX_VALUE;
        count = 0;

        Arrays.sort(arrZ1);
        Arrays.sort(arrZ2);

        for (int z1 : arrZ1) {
            binarySearch(z1, arrZ2, 0,  M - 1);
        }

        return new int[] { min + Math.abs(c1 - c2), count };
    }

    private void binarySearch(int z1, int[] arr, int start, int end) {
        int mid = 0;
        while (start < end) {
            mid = (start + end) >> 1;

            if (arr[mid] == z1)
                break;
            else if (z1 < arr[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }

        check(Math.abs(z1 - arr[mid]));

        if (0 < mid) check(Math.abs(z1 - arr[mid - 1]));
        if (mid + 1 < arr.length) check(Math.abs(z1 - arr[mid + 1]));
    }

    private void check(int dist) {
        if (dist == min) {
            count += 1;
        } else if (dist < min) {
            min = dist;
            count = 1;
        }
    }
}