package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;


/**
 * [No. 50] 14726. Segment Tree 연습 - 2
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 4초 / C++의 경우 4초 / Java의 경우 4초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Segment Tree
 *
 * https://www.youtube.com/watch?v=ahFB9eCnI6c
 * */
public class SWEA_P50 {

    static class SegmentTree {

        private static final int DEFAULT_VALUE = 0;

        private final int size;
        private final int[] tree;

        public SegmentTree(int[] arr, int size) {
            this.size = size;
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
            return left + right;
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(50)));

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 수열의 길이 (1 ≤ N ≤ 10^5)
            int Q = sc.nextInt(); // 쿼리의 개수 (1 ≤ Q ≤ 10^5)

            int[] arr = new int[N]; // (0 ≤ ai ≤ 10^9)
            for (int i = 0; i < N; i++) {
                if (i % 2 == 0)
                    arr[i] = sc.nextInt();
                else
                    arr[i] = -sc.nextInt();
            }

            SegmentTree segmentTree = new SegmentTree(arr, N);

            System.out.printf("#%d", test_case);

            int[] query = new int[3];
            for (int i = 0; i < Q; i++) {
                for (int j = 0; j < 3; j++)
                    query[j] = sc.nextInt();

                switch (query[0]) {
                    case 0:
                        if (query[1] % 2 == 1)
                            query[2] *= -1;

                        segmentTree.update(query[1], query[2]);
                        break;
                    case 1:
                        int sum = segmentTree.query(query[1], query[2] - 1);

                        if (query[1] % 2 == 1)
                            sum *= -1;

                        System.out.printf(" %d", sum);
                        break;
                }
            }

            System.out.println();
        }
    }

}
