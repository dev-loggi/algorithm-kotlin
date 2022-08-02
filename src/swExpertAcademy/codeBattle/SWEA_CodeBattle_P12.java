package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * [No. 12] 1248. [S/W 문제해결 응용] 3일차 - 공통조상
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P12 {

    public static class Node {
        Node parent, child1, child2;
        int value;

        public int getDepth() {
            int depth = 0;
            Node node = parent;
            while (node != null) {
                node = node.parent;
                depth++;
            }
            return depth;
        }

        public int getTreeSize() {
            return (child1 != null ? child1.getTreeSize() : 0) +
                    (child2 != null ? child2.getTreeSize() : 0) + 1;
        }
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(12)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] info = sc.nextLine().split(" ");

            int V = Integer.parseInt(info[0]); // 정점의 갯수 (10 ≤ V ≤ 10000)
            int E = Integer.parseInt(info[1]); // 간선의 갯수 (11 ≤ V ≤ 10001)
            int v1 = Integer.parseInt(info[2]); // 찾아야 할 정점1
            int v2 = Integer.parseInt(info[3]); // 찾아야 할 정점2

            Node[] nodes = new Node[V + 1]; // root = nodes[1]
            String[] edge = sc.nextLine().split(" ");

            for (int i = 0; i < E * 2; i += 2) {
                int parentNumber = Integer.parseInt(edge[i]);
                int childNumber = Integer.parseInt(edge[i + 1]);

                Node parent = nodes[parentNumber] != null
                        ? nodes[parentNumber]
                        : new Node();

                Node child = nodes[childNumber] != null
                        ? nodes[childNumber]
                        : new Node();

                parent.value = parentNumber;

                if (parent.child1 == null) {
                    parent.child1 = child;
                } else if (parent.child2 == null) {
                    parent.child2 = child;
                }

                child.value = childNumber;
                child.parent = parent;

                nodes[parentNumber] = parent;
                nodes[childNumber] = child;
            }

            String answer = solution(nodes[1], v1, v2);
            System.out.printf("#%d %s\n", test_case, answer);

            releaseNodes(nodes);
        }
        sc.close();
    }

    private static String solution(Node root, int v1, int v2) {
        Node node1 = dfs(root, v1);
        Node node2 = dfs(root, v2);

        int depth1 = node1.getDepth();
        int depth2 = node2.getDepth();

        // depth 맞추기
        for (int i = 0; i < depth1 - depth2; i++)
            node1 = node1.parent;
        for (int i = 0; i < depth2 - depth1; i++)
            node2 = node2.parent;

        if (node1.value == node2.value)
            return node1.value + " " + node1.getTreeSize();

        // 한 단계씩 올라가며 공통 부모 찾기
        while (node1 != null && node2 != null && node1.value != node2.value) {
            node1 = node1.parent;
            node2 = node2.parent;
        }

        return node1.value + " " + node1.getTreeSize();
    }

    private static Node dfs(Node node, int v) {
        if (node.value == v)
            return node;

        Node res = (node.child1 != null)
                ? dfs(node.child1, v)
                : null;

        if (res != null)
            return res;

        res = (node.child2 != null)
                ? dfs(node.child2, v)
                : null;

        return res;
    }

    private static void releaseNodes(Node[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            Node node = nodes[i];

            if (node == null)
                continue;

            node.parent = null;
            node.child1 = null;
            node.child2 = null;

            nodes[i] = null;
        }
    }
}