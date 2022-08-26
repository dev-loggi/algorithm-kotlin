package swExpertAcademy.basic;


import swExpertAcademy.basic._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * [No. 11] 1232. [S/W 문제해결 기본] 9일차 - 사칙연산
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P11 {

    public static class Node {
        int n, left_n, right_n; // 정점의 번호
        Node left, right;
        int type;
        String op;
        double number;
    }

    private static final int OP = 0;
    private static final int NUMBER = 1;

    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(11)));

        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 노드의 갯수 (1 ≤ N ≤ 1000)

            Node[] nodes = new Node[N + 1];

            for (int i = 1; i <= N; i++) {
                String[] inputs = sc.nextLine().split(" ");

                Node node = new Node();
                node.n = Integer.parseInt(inputs[0]);

                String s = inputs[1];
                if (s.equals(ADD) || s.equals(SUB) || s.equals(MUL) || s.equals(DIV)) {
                    node.type = OP;
                    node.op = s;
                    node.left_n = Integer.parseInt(inputs[2]);
                    node.right_n = Integer.parseInt(inputs[3]);
                } else if (PATTERN.matcher(s).matches()){
                    node.type = NUMBER;
                    node.number = Double.parseDouble(s);
                }

                nodes[node.n] = node;
            }

            for (int i = 1; i <= N; i++) {
                if (nodes[i].left_n != 0) nodes[i].left = nodes[nodes[i].left_n];
                if (nodes[i].right_n != 0) nodes[i].right = nodes[nodes[i].right_n];
            }

            int answer = solution(nodes[1]);
            System.out.printf("#%d %d\n", test_case, answer);

            releaseNodes(nodes);
        }
    }

    private static int solution(Node root) {
        return (int) dfsByInOrder(root);
    }

    private static double dfsByInOrder(Node node) {
        if (node.left == null || node.right == null)
            return node.number;

        return calculate(node.op, dfsByInOrder(node.left), dfsByInOrder(node.right));
    }

    private static double calculate(String op, double a, double b) {
        switch (op) {
            case ADD: return a + b;
            case SUB: return a - b;
            case MUL: return a * b;
            case DIV: return a / b;
        }
        throw new IllegalArgumentException("op is not valid (op=" + op + ")");
    }

    private static void releaseNodes(Node[] nodes) {
        for (int i = 1; i < nodes.length; i++) {
            Node node = nodes[i];
            node.left = null;
            node.right = null;
            nodes[i] = null;
        }
    }
}
