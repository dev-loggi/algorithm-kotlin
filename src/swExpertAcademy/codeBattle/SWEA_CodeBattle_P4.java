package swExpertAcademy.codeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 4] 12303 - 기초 Single Linked List 연습
 *
 * 시간   : 3개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 1초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P4 {

    private final static String FILE_INPUT = "src/swExpertAcademy/_inputs/input_p4.txt";
    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int REMOVE = 4;
    private final static int PRINT = 5;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static void run() throws Exception {

        int cmd, data, num, ret;
        int[] output = new int[MAX_NODE];
        String str;
        StringTokenizer st;

        while (true) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd) {
                case ADD_HEAD:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Head(data);
                    break;

                case ADD_TAIL:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Tail(data);
                    break;

                case ADD_NUM:
                    data = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Num(data, num);
                    break;

                case REMOVE:
                    data = Integer.parseInt(st.nextToken());
                    usersolution.removeNode(data);
                    break;

                case PRINT:
                    ret = usersolution.getList(output);
                    for (int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case END:
                    return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        System.setIn(new java.io.FileInputStream(FILE_INPUT));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            System.out.println("#" + tc);
            usersolution.init();
            run();
            System.out.println();
        }
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static class UserSolution {

        private final static int MAX_NODE = 10000;

        private Node[] node = new Node[MAX_NODE];
        private int nodeCnt = 0;
        private Node head;

        public Node getNode(int data) {
            node[nodeCnt] = new Node(data);
            return node[nodeCnt++];
        }

        public void init() {
            head = null;
            for (int i = 0; i < nodeCnt; i++) {
                node[i].next = null;
                node[i] = null;
            }
            nodeCnt = 0;
        }

        public void addNode2Head(int data) {
            if (checkDuplicated(data))
                return;

            Node second = head;
            head = new Node(data);
            head.next = second;
            node[nodeCnt++] = head;
        }

        public void addNode2Tail(int data) {
            if (checkDuplicated(data))
                return;

            Node newNode = new Node(data);

            if (nodeCnt == 0) {
                head = newNode;
            } else {
                Node node1 = head;

                while (node1.next != null) {
                    node1 = node1.next;
                }

                Node tail = new Node(data);
                node1.next = tail;
            }

            node[nodeCnt++] = newNode;
        }

        public void addNode2Num(int data, int num) {
            if (checkDuplicated(data))
                return;

            int i = 0;
            Node front = null;
            Node rear = head;

            while (rear != null && i < num - 1) {
                front = rear;
                rear = rear.next;
                i++;
            }

            Node newNode = new Node(data);
            newNode.next = rear;
            if (front == null) {
                head = newNode;
            } else {
                front.next = newNode;
            }

            node[nodeCnt++] = newNode;
        }

        public void removeNode(int data) {
            Node front = null;
            Node target = head;

            while (target.data != data) {
                front = target;
                target = target.next;

                if (target == null)
                    return;
            }

            if (front != null) {
                front.next = target.next;
            } else {
                head = target.next;
            }
            target.next = null;

            int idx = 0;
            for (int i = 0; i < nodeCnt; i++) { // 노드 배열에서 제거하기
                Node n = node[i];
                if (n.data == data) {
                    idx = i;
                    node[i] = null;
                }
            }
            for (int i = idx; i < nodeCnt - 1; i++) { // 배열에서 제거된 자리 채우기
                node[i] = node[i + 1];
            }
            nodeCnt--;
        }

        public int getList(int[] output) {
            int i = 0;
            Node node1 = head;
            while (node1 != null) {
                output[i++] = node1.data;
                node1 = node1.next;
            }

            return nodeCnt;
        }

        private boolean checkDuplicated(int data) {
            for (int i = 0; i < nodeCnt; i++) {
                if (node[i].data == data)
                    return true;
            }
            return false;
        }
    }
}