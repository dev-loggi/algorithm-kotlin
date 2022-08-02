package swExpertAcademy.liveCodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * [2022-07-29] 22 하계 대학생 S/W 알고리즘 특강 1차 라이브 코드배틀
 *
 * 13072. [Pro] 병사관리
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_LiveCodeBattle_P1_3 {

    private static final int SIZE = 10_000_000;

    public static void test1() {
        int[] arr = new int[SIZE];
        for (int i=0; i<SIZE; i++) {
            arr[i] = i;
        }
    }
    public static void test2() {
        //int tmp = 10;
        LinkedList<Integer> list = new LinkedList<>();
        for (int i=0; i<SIZE; i++) {
            list.add(i);
        }
    }

    private static class UserSolution {

        private static class RedBlackTree {

            public static class Node {
                int id, score;
                private int color;

                Node left;
                Node right;
                Node parent;

                Node(int id, int score) {
                    this.id = id;
                    this.score = score;
                    color = BLACK;

                    left = null;
                    right = null;
                    parent = null;
                }

                Node() {
                    this(-1, -1);
                }

                int getValue() {
                    return score;
                }

                String getColor() {
                    return color == RED ? "RED" : "BLACK";
                }

                void setColor(int color) {
                    this.color = color;
                }
            }

            private static final int BLACK = 0;
            private static final int RED = 1;

            private Node root;

            public RedBlackTree() {
                root = null;
            }

            public void printTree(Node node) {
                if (node == null)
                    return;

                System.out.println(node.getValue() + "(" + node.getColor() + ")");
                printTree(node.left);
                printTree(node.right);
            }

            public Node findNode(Node goal, Node node) {
                if (node == null)
                    return null;

                if (goal.getValue() < node.getValue()) {
                    if (node.left != null)
                        return findNode(goal, node.left);
                }
                else if (goal.getValue() > node.getValue()) {
                    if (node.right != null)
                        return findNode(goal, node.right);
                }
                else {
                    return node;
                }

                return null;
            }

            public void insertNode(Node node) {
                if (root == null) {
                    root = node;
                }
                else {
                    Node parent = root;

                    node.setColor(RED);

                    while (true) {
                        if (parent.getValue() < node.getValue()) {
                            if (parent.right == null) {
                                parent.right = node;
                                node.parent = parent;
                                break;
                            }
                            else {
                                parent = parent.right;
                            }
                        }
                        else {
                            if (parent.left == null) {
                                parent.left = node;
                                node.parent = parent;
                                break;
                            }
                            else {
                                parent = parent.left;
                            }
                        }
                    }

                    recolorTree(node);
                }
            }

            private void recolorTree(Node node) {
                while (node.parent != null && "RED".equals(node.parent.getColor())) {
                    Node next = null;

                    if (node.parent == node.parent.parent.left) {
                        next = node.parent.parent.right;

                        if (next != null && "RED".equals(next.getColor())) {
                            node.parent.setColor(BLACK);
                            next.setColor(BLACK);
                            node.parent.parent.setColor(RED);
                            node = node.parent.parent;
                            continue;
                        }

                        if (node == node.parent.right) {
                            node = node.parent;

                            rotateLeft(node);
                        }

                        node.parent.setColor(BLACK);
                        node.parent.parent.setColor(RED);

                        rotateRight(node.parent.parent);
                        break;
                    }
                    else {
                        next = node.parent.parent.left;

                        if (next != null && "RED".equals(next.getColor())) {
                            node.parent.setColor(BLACK);
                            next.setColor(BLACK);
                            node.parent.parent.setColor(RED);
                            node = node.parent.parent;
                            continue;
                        }

                        if (node == node.parent.left) {
                            node = node.parent;

                            rotateRight(node);
                        }

                        node.parent.setColor(BLACK);
                        node.parent.parent.setColor(RED);

                        rotateLeft(node.parent.parent);
                        break;
                    }
                }

                root.setColor(BLACK);
            }

            private void rotateLeft(Node node) {
                if (node.parent == null) {
                    Node right = root.right;
                    root.right = root.right.left;
                    right.left = new Node();
                    right.left.parent = root;
                    root.parent = right;
                    right.left = root;
                    right.parent = null;
                    root = right;
                }
                else {
                    if (node == node.parent.left)
                        node.parent.left = node.right;
                    else
                        node.parent.right = node.right;

                    node.right.parent = node.parent;
                    node.parent = node.right;

                    if (node.right.left != null)
                        node.right.left.parent = node;

                    node.right = node.right.left;
                    node.parent.left = node;
                }
            }

            private void rotateRight(Node node) {
                if (node.parent == null) {
                    Node left = root.left;
                    root.left = root.left.right;
                    left.right = new Node();
                    left.right.parent = root;
                    root.parent = left;
                    left.right = root;
                    left.parent = null;
                    root = left;
                }
                else {
                    if (node == node.parent.left)
                        node.parent.left = node.left;
                    else
                        node.parent.right = node.left;

                    node.left.parent = node.parent;
                    node.parent = node.left;

                    if (node.left.right != null)
                        node.left.right.parent = node;

                    node.left = node.left.right;
                    node.parent.right = node;
                }
            }

        }

        private static final int MAX = 100_000;

        private int[] team;
        private RedBlackTree[] soldiers;

        public void init() {
            team = new int[MAX + 1];
            soldiers = new RedBlackTree[6];

            for (int t = 1; t <= 5; t++) {
                soldiers[t] = new RedBlackTree();
            }
        }

        public void hire(int mID, int mTeam, int mScore) {
            // mID : 고유번호 (1 ≤ mID ≤ 100,000)
            // mTeam : 소속팀 (1 ≤ mTeam ≤ 5)
            // mScore : 평판 점수 (1 ≤ mScore ≤ 5)

            team[mID] = mTeam;
            soldiers[mTeam].insertNode(new RedBlackTree.Node(mID, mScore));
        }

        public void fire(int mID) {
            team[mID] = 0;
        }

        public void updateSoldier(int mID, int mScore) {

        }

        public void updateTeam(int mTeam, int mChangeScore) { // 무조건 완전 탐색
            if (mChangeScore == 0) return;

        }

        public int bestSoldier(int mTeam) { // 스코어 정렬, 중에 베스트 아이디
            return 0;
        }
    }

    private final static int CMD_INIT				= 1;
    private final static int CMD_HIRE				= 2;
    private final static int CMD_FIRE				= 3;
    private final static int CMD_UPDATE_SOLDIER		= 4;
    private final static int CMD_UPDATE_TEAM		= 5;
    private final static int CMD_BEST_SOLDIER		= 6;

    private final static UserSolution usersolution = new UserSolution();

    private static boolean run(BufferedReader br) throws Exception {
        StringTokenizer st;
        int numQuery;
        int mID, mTeam, mScore, mChangeScore;
        int userAns, ans;
        boolean isCorrect = false;
        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q) {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case CMD_INIT:
                    usersolution.init();
                    isCorrect = true;
                    break;
                case CMD_HIRE:
                    mID = Integer.parseInt(st.nextToken());
                    mTeam = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.hire(mID, mTeam, mScore);
                    break;
                case CMD_FIRE:
                    mID = Integer.parseInt(st.nextToken());
                    usersolution.fire(mID);
                    break;
                case CMD_UPDATE_SOLDIER:
                    mID = Integer.parseInt(st.nextToken());
                    mScore = Integer.parseInt(st.nextToken());
                    usersolution.updateSoldier(mID, mScore);
                    break;
                case CMD_UPDATE_TEAM:
                    mTeam = Integer.parseInt(st.nextToken());
                    mChangeScore = Integer.parseInt(st.nextToken());
                    usersolution.updateTeam(mTeam, mChangeScore);
                    break;
                case CMD_BEST_SOLDIER:
                    mTeam = Integer.parseInt(st.nextToken());
                    userAns = usersolution.bestSoldier(mTeam);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans) {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream("src/swExpertAcademy/_inputs/live_input_p1.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        //TC = 5;
        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }

    static void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
