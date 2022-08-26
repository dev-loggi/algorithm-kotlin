package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 49] 14726. Segment Tree 연습 - 1
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 4초 / C++의 경우 4초 / Java의 경우 4초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Segment Tree
 *
 * https://www.youtube.com/watch?v=ahFB9eCnI6c
 * */
public class SWEA_P49 {

    static class SegmentTree {

        public static final int MAX = 0;
        public static final int MIN = 1;

        private final int DEFAULT_VALUE;
        private final int type, size;
        private final int[] tree;

        public SegmentTree(int[] arr, int size, int type) {
            this.size = size;
            this.type = type;

            DEFAULT_VALUE = (type == MAX)
                    ? Integer.MIN_VALUE
                    : Integer.MAX_VALUE;

            tree = new int[size * 4];

            buildRec(arr, 1, 0, size - 1);
        }

        public int query(int left, int right) { // inclusive
            return queryRec(left, right, 1, 0, size - 1);
        }

        public int update(int index, int newValue) {
            return updateRec(index, newValue, 1, 0, size - 1);
        }

        private int queryRec(int left, int right, int node, int nodeLeft, int nodeRight) {
            if (right < nodeLeft || nodeRight < left)
                return DEFAULT_VALUE; // default value

            if (left <= nodeLeft && nodeRight <= right)
                return tree[node];

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

            return merge(queryRec(left, right, node * 2, nodeLeft, mid),
                         queryRec(left, right, node * 2 + 1, mid + 1, nodeRight));
        }

        private int updateRec(int index, int newValue, int node, int nodeLeft, int nodeRight) {
            if (index < nodeLeft || nodeRight < index)
                return tree[node];

            if (nodeLeft == nodeRight)
                return tree[node] = newValue;

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            int leftValue = updateRec(index, newValue, node * 2, nodeLeft, mid);
            int rightValue = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftValue, rightValue);
        }

        private int buildRec(int[] arr, int node, int nodeLeft, int nodeRight) {
            if (nodeLeft == nodeRight)
                return tree[node] = arr[nodeLeft];

            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            int leftValue = buildRec(arr, node * 2, nodeLeft, mid);
            int rightValue = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftValue, rightValue);
        }

        private int merge(int left, int right) {
            if (type == MAX) {
                return Math.max(left, right);
            } else {
                return Math.min(left, right);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(49)));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 수열의 길이 (1 ≤ N ≤ 10^5)
            int Q = sc.nextInt(); // 쿼리의 개수 (1 ≤ Q ≤ 10^5)

            int[] arr = new int[N]; // (0 ≤ ai ≤ 10^9)
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            SegmentTree maxSegTree = new SegmentTree(arr, N, SegmentTree.MAX);
            SegmentTree minSegTree = new SegmentTree(arr, N, SegmentTree.MIN);

            System.out.printf("#%d", test_case);

            int[] query = new int[3];
            for (int i = 0; i < Q; i++) {
                for (int q = 0; q < 3; q++)
                    query[q] = sc.nextInt();

                switch (query[0]) {
                    case 0:
                        maxSegTree.update(query[1], query[2]);
                        minSegTree.update(query[1], query[2]);
                        break;
                    case 1:
                        int max = maxSegTree.query(query[1], query[2] - 1);
                        int min = minSegTree.query(query[1], query[2] - 1);

                        System.out.printf(" %d", max - min);
                        break;
                }
            }

            System.out.println();
        }
    }

}