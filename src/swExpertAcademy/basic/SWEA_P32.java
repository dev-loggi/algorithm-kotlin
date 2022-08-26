package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 32] 2930. 힙
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P32 {

    private static final int MAX = 100_000;
    private static final int ADD = 1;
    private static final int REMOVE = 2;

    private static int[] heap;
    private static int last;

    private static String solution(int[][] commands) {
        int[] result = new int[MAX + 1];
        int resultSize = 0;

        heap = new int[MAX + 1];
        last = 0;

        for (int[] command : commands) {
            switch (command[0]) {
                case ADD:
                    addHeap(command[1]);
                    break;
                case REMOVE:
                    result[resultSize++] = last > 0
                            ? removeHeap()
                            : -1;
                    break;
            }
        }

        return resultToString(result, resultSize);
    }

    private static void addHeap(int n) {
        heap[++last] = n;

        int parent = last / 2;
        int child = last;

        while (0 < parent) {
            if (heap[parent] >= heap[child])
                break;

            swap(parent, child);

            parent /= 2;
            child /= 2;
        }
    }

    private static int removeHeap() {
        int removed = heap[1];

        heap[1] = heap[last--];

        int parent = 1;
        int child1 = 2;
        int child2 = 3;
        int bigChild = 2;

        while (child1 <= last) {
            bigChild = child2 <= last
                    ? heap[child1] >= heap[child2] ? child1 : child2
                    : child1;

            if (heap[parent] >= heap[bigChild])
                break;

            swap(parent, bigChild);

            parent = bigChild;
            child1 = bigChild * 2;
            child2 = bigChild * 2 + 1;
        }

        return removed;
    }

    private static void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    private static String resultToString(int[] result, int size) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++)
            builder.append(result[i]).append(' ');
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    private static void logln(String s, Object ... args) { System.out.printf(s + "\n", args); }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(32)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 연산의 수 (1 ≤ N ≤ 105)
            int[][] commands = new int[N][];

            for (int i = 0; i < N; i++) {
                commands[i] = Arrays.stream(sc.nextLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }

            String answer = solution(commands);

            System.out.printf("#%d %s\n", test_case, answer);
        }

        sc.close();
    }
}