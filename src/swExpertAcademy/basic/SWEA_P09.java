package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * [No. 9] 1231. [S/W 문제해결 기본] 9일차 - 중위순회
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P09 {

    public static class Node {
        Node parent, left, right;
        char data;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(9)));

        Scanner sc = new Scanner(System.in);
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 노드의 갯수 (1 ≤ N ≤ 100)

            Node[] nodes = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                String[] line = sc.nextLine().split(" ");

                int n = Integer.parseInt(line[0]); // n번째 노드
                char data = line[1].charAt(0);

                Node node = new Node();
                node.parent = nodes[n % 2];
                node.data = data;

                nodes[n] = node;
            }

            for (int i = 1; i <= N; i++) {
                if (i * 2 <= N)
                    nodes[i].left = nodes[i * 2];
                if (i * 2 + 1 <= N)
                    nodes[i].right = nodes[i * 2 + 1];
            }

            String answer = solution(nodes[1], N);
            System.out.printf("#%d %s\n", test_case, answer);

            releaseNodes(nodes);
        }

        sc.close();
    }

    private static String solution(Node root, int N) {
        ArrayDeque<Character> queue = new ArrayDeque<>();

        dfsByInOrder(root, queue);

        char[] charArray = new char[N];
        int i = 0;
        while (!queue.isEmpty()) {
            charArray[i++] = queue.poll();
        }

        return new String(charArray);
    }

    private static void dfsByInOrder(Node node, Deque<Character> queue) {
        if (node.left != null) dfsByInOrder(node.left, queue);
        queue.offer(node.data);
        if (node.right != null) dfsByInOrder(node.right, queue);
    }

    private static void releaseNodes(Node[] nodes) {
        for (int i = 1; i < nodes.length; i++) {
            Node node = nodes[i];
            node.parent = null;
            node.left = null;
            node.right = null;
            nodes[i] = null;
        }
    }
}