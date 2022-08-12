package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.*;

/**
 * [No. 18] 1855. 영준이의 진짜 BFS
 *
 * 시간 : 61개 테스트케이스를 합쳐서 C++의 경우 4초 / Java의 경우 8초 / Python의 경우 16초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 최소 공통 조상(LCA, Lowest Common Ancestor)
 * */
public class SWEA_P18 {

    private static int[] depth;
    private static int[] parent;
    private static ArrayList<Integer>[] children;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(18)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int N = Integer.parseInt(sc.nextLine()); // 노드의 갯수 (1 ≤ N ≤ 10^5)

            depth = new int[N + 1];
            parent = new int[N + 1];
            children = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                children[i] = new ArrayList<>();
            }

            int[] parentInfo = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int n = 2; n <= N; n++) {
                int parentN = parentInfo[n - 2];

                parent[n] = parentN;
                children[parentN].add(n);
                depth[n] = depth[parentN] + 1;
            }

            System.out.printf("#%d %d\n", test_case, solution());
        }

        sc.close();
    }

    private static int solution() {
        int totalDistance = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // root

        int prev = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            totalDistance += getDistance(prev, curr);

            prev = curr;

            for (int child : children[curr]) {
                queue.offer(child);
            }
        }

        return totalDistance;
    }

    /** 공통 조상까지 거리의 합 */
    private static int getDistance(int N1, int N2) {
        int distance = 0;

        int n1 = depth[N1] <= depth[N2] ? N1 : N2;
        int n2 = depth[N1] > depth[N2] ? N1 : N2;

        while (depth[n1] < depth[n2]) {
            n2 = parent[n2];
            distance += 1;
        }

        while (n1 != n2) {
            n1 = parent[n1];
            n2 = parent[n2];
            distance += 2;
        }

        return distance;
    }

    private static void printInfo(int N) {
        System.out.print("\ndepth:  ");
        for (int i=0; i<=N; i++) System.out.printf("%d ", depth[i]);
        System.out.print("\nparent: ");
        for (int i=0; i<=N; i++) System.out.printf("%d ", parent[i]);
        System.out.print("\nchild:  ");
        for (int i=0; i<=N; i++) System.out.printf("%d ", i);
        System.out.println();

        for (int i=1; i<=N; i++) {
            System.out.printf("children[%d]: %s\n", i, children[i]);
        }
    }

    public static class Node implements Comparable<Node> {
        public int value, depth;
        private Node parent;
        ArrayList<Node> children = new ArrayList<>(); // sorted
        public boolean isSorted = false;

        public Node(int value) {
            this.value = value;
        }

        public void addChild(Node node) {
            node.parent = this;
            node.depth = depth + 1;
            children.add(node);
        }

        public void release() {
            value = 0;
            depth = 0;
            parent = null;
            children.clear();
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public static void main2(int N, String info) {
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
//            int[] parentInfo = Arrays.stream(sc.nextLine().split(" "))
//                    .mapToInt(Integer::parseInt)
//                    .toArray();

            String[] parentInfo = info.split(" ");

            Node[] nodes = new Node[N + 1];

            Node root = new Node(1);
            nodes[1] = root;

            for (int i = 2; i <= N; i++) {
                Node parent = nodes[Integer.parseInt(parentInfo[i - 2])];
                Node newNode = new Node(i);

                parent.addChild(newNode);
                nodes[i] = newNode;
            }

            for (int i = 1; i <= N; i++) {
                Node node = nodes[i];
                if (node.isSorted)
                    continue;

                Collections.sort(node.children);
            }

            int answer = solution2(root);

            System.out.printf("#%d %d\n", test_case, answer);

            for (int i = 1; i <= N; i++) {
                nodes[i].release();
                nodes[i] = null;
            }
        }
    }

    private static int solution2(Node root) {
        int count = 0;

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        Node prev = root;
        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            count += getDistance2(prev, curr);

            prev = curr;

            for (int i = 0; i < curr.children.size(); i++) {
                queue.offer(curr.children.get(i));
            }
        }

        return count;
    }

    /** 공통 조상까지 거리의 합 */
    private static int getDistance2(Node node1, Node node2) {
        int distance = 0;

        if (node1.value == node2.value)
            return distance;

        if (node1.depth < node2.depth)
            while (node1.depth != node2.depth) {
                node2 = node2.parent;
                distance += 1;
            }
        if (node1.depth > node2.depth)
            while (node1.depth != node2.depth) {
                node1 = node1.parent;
                distance += 1;
            }

        while (node1.value != node2.value) {
            node1 = node1.parent;
            node2 = node2.parent;
            distance += 2;
        }

        return distance;
    }

    private static void bfs2(Node root) {
        System.out.printf("root.children.size=%d\n", root.children.size());
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            System.out.printf("%d ", node.value);

            for (Node child : node.children) {
                queue.offer(child);
            }
        }
        System.out.println();
    }
}