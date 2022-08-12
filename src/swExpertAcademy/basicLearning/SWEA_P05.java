package swExpertAcademy.basicLearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 5] 12304. 기초 Double Linked List 연습
 *
 * 시간   : 3개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 1초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P05 {

    private final static String INPUT_FILE = "src/swExpertAcademy/_inputs/input_p5.txt";
    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int FIND = 4;
    private final static int REMOVE = 5;
    private final static int PRINT = 6;
    private final static int PRINT_REVERSE = 7;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    public static void run() throws Exception {
        int cmd, data, num, ret;
        int[] output = new int[MAX_NODE];
        String str;
        StringTokenizer st;

        while(true) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case ADD_HEAD :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Head(data);
                    break;

                case ADD_TAIL :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Tail(data);
                    break;

                case ADD_NUM :
                    data = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Num(data, num);
                    break;

                case FIND :
                    data = Integer.parseInt(st.nextToken());
                    num = usersolution.findNode(data);
                    System.out.println(num);
                    break;

                case REMOVE :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.removeNode(data);
                    break;

                case PRINT :
                    ret = usersolution.getList(output);
                    for(int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case PRINT_REVERSE :
                    ret = usersolution.getReversedList(output);
                    for(int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case END :
                    return;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int TC;
        System.setIn(new java.io.FileInputStream(INPUT_FILE));

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
        public Node prev;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static class UserSolution {

        private final static int MAX_NODE = 10000;

        private Node[] nodes = new Node[MAX_NODE];
        private int nodeCnt = 0;
        private Node head;
        private Node tail;

        public Node getNode(int data) {
            nodes[nodeCnt] = new Node(data);
            return nodes[nodeCnt++];
        }

        public void init() {
            for (int i = 0; i < nodeCnt; i++) {
                nodes[i].prev = null;
                nodes[i].next = null;
                nodes[i] = null;
            }
            nodeCnt = 0;
            head = null;
            tail = null;
        }

        public void addNode2Head(int data) {
            if (!checkValidation(data))
                return;

            Node node = new Node(data);

            if (head != null) {
                node.next = head;
                head.prev = node;
            } else {
                tail = node;
            }

            head = node;

            nodes[nodeCnt++] = node;
        }

        public void addNode2Tail(int data) {
            if (!checkValidation(data))
                return;

            Node node = new Node(data);

            if (tail != null) {
                tail.next = node;
                node.prev = tail;
            } else {
                head = node;
            }

            tail = node;

            nodes[nodeCnt++] = node;
        }

        public void addNode2Num(int data, int num) {
            if (!checkValidation(data))
                return;

            if (num == 1 || nodeCnt == 0) {
                addNode2Head(data);
                return;
            } else if (nodeCnt < num) {
                addNode2Tail(data);
                return;
            }

            Node node = new Node(data);
            Node prev;
            Node next;

            if (num <= nodeCnt / 2) {
                prev = null;
                next = head;

                for (int i = 1; i < num; i++) {
                    prev = next;
                    next = next.next;
                }

            } else {
                prev = tail;
                next = node;

                for (int i = nodeCnt; i >= num; i--) {
                    next = prev;
                    prev = prev.prev;
                }
            }

            prev.next = node;
            node.prev = prev;
            node.next = next;
            next.prev = node;

            nodes[nodeCnt++] = node;
        }

        public int findNode(int data) {
            Node node = head;
            int idx = 0;
            int i = 1;

            while (node != null) {
                if (node.data == data) {
                    idx = i;
                    break;
                }
                node = node.next;
                i++;
            }

            return idx;
        }

        public void removeNode(int data) {
            int idx = -1;
            for (int i = 0; i < nodeCnt; i++) {
                if (nodes[i].data == data) {
                    idx = i;
                }
            }

            if (idx == -1)
                return;

            Node node = nodes[idx];

            if (node == head) head = node.next;
            else if (node == tail) tail = node.prev;
            else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            node.prev = null;
            node.next = null;

            for (int i = idx; i < nodeCnt - 1; i++) {
                nodes[i] = nodes[i + 1];
            }
            nodeCnt--;
        }

        public int getList(int[] output) {
            int i = 0;
            Node node = head;
            while (node != null) {
                output[i++] = node.data;
                node = node.next;
            }

            return nodeCnt;
        }

        public int getReversedList(int[] output) {
            int i = 0;
            Node node = tail;
            while (node != null) {
                output[i++] = node.data;
                node = node.prev;
            }

            return nodeCnt;
        }

        private boolean checkValidation(int data) {
            if (nodeCnt == MAX_NODE)
                throw new IllegalStateException("Overcapacity(max=" + MAX_NODE + ")");

            for (int i = 0; i < nodeCnt; i++) {
                if (nodes[i].data == data)
                    return false;
            }
            return true;
        }
    }
}