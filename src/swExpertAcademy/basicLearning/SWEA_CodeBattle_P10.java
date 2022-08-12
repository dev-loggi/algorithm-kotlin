package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * [No. 10] 1233. [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P10 {

    public static class Node {
        Node parent, left, right;
        int type = NONE;
    }

    private static final int NONE = -1;
    private static final int OP = 0;
    private static final int NUMBER = 1;
    private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(10)));

        Scanner sc = new Scanner(System.in);
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 노드 갯수 (1 ≤ N ≤ 200)

            Node[] nodes = new Node[N + 1];

            for (int i = 1; i <= N; i++) {
                String[] inputs = sc.nextLine().split(" ");

                Node node = new Node();
                node.parent = nodes[i / 2];

                String s = inputs[1];
                if (s.equals("-") || s.equals("+") || s.equals("*") || s.equals("/")) {
                    node.type = OP;
                } else if (PATTERN.matcher(s).matches()){
                    node.type = NUMBER;
                }

                nodes[i] = node;
            }

            for (int i = 1; i <= N; i++) {
                if (i * 2 <= N) nodes[i].left = nodes[i * 2];
                if (i * 2 + 1 <= N) nodes[i].right = nodes[i * 2 + 1];
            }

            int answer = solution(nodes[1]);
            System.out.printf("#%d %d\n", test_case, answer);

            releaseNodes(nodes);
        }
    }

    private static int solution(Node root) {
        return dfsByInOrder(root);
    }

    private static int dfsByInOrder(Node node) {
        int valid = 0;

        if (node.left == null || node.right == null)
            return (node.left == null) && (node.right == null) && node.type == NUMBER ? NUMBER : OP;

        // 왼쪽 서브 트리의 결과: 숫자
        valid += dfsByInOrder(node.left) == NUMBER ? 1 : 0;

        // 자신: 연산자
        valid += node.type == OP ? 1 : 0;

        // 오른쪽 서브 트리의 결과: 숫자
        valid += dfsByInOrder(node.right) == NUMBER ? 1 : 0;

        // 3개 모두 만족해야 숫자를 리턴
        return valid == 3 ? NUMBER : OP;
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