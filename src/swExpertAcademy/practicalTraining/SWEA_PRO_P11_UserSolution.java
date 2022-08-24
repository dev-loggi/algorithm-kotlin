package swExpertAcademy.practicalTraining;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * [No. 11] 14605. [Pro] P2P 네트워크 (8/25)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 1번 호출된다.<br/>
 * 2. 각 테스트 케이스에서 share() 함수는 최대 5,000번 호출된다.<br/>
 * 3. 각 테스트 케이스에서 request() 함수는 최대 5,000번 호출된다.<br/>
 * 4. 각 테스트 케이스에서 complete() 함수는 최대 5,000번 호출된다.<br/>
 * 5. share()와 request()의 인자인 파일 이름(mFileName)은 길이 4 이상, 9 이하의 알파벳 소문자로 이루어진 문자열이며, 문자열의 끝에는 ‘＼0’이 추가되어 있다.<br/>
 * 6. 각 테스트케이스 내의 request 함수들에서 mTID 는 중복되지 않는다.<br/>
 *
 * Trie, BFS, Graph
 * */
public class SWEA_PRO_P11_UserSolution implements SWEA_PRO_P11.UserSolution {

    private static class Trie {

        private static class Node {
            int code = -1;
            private final HashMap<Character, Node> children = new HashMap<>();
        }

        private int trieCodeCount = 0;
        private final Node root = new Node();

        public int insert(char[] text) {
            Node curr = root;
            for (int i = 0; text[i] != '\0'; i++) {
                Node next = curr.children.get(text[i]);
                if (next == null) {
                    next = new Node();
                    curr.children.put(text[i], next);
                }
                curr = next;
            }

            return curr.code == -1
                    ? curr.code = trieCodeCount++
                    : curr.code;
        }

        public int getCode(char[] text) {
            Node curr = root;
            for (int i = 0; text[i] != '\0'; i++) {
                curr = curr.children.get(text[i]);
                if (curr == null) return -1;
            }
            return curr.code;
        }
    }

    private static class Node {
        int id;
        Node prev;

        public Node(int id, Node prev) {
            this.id = id;
            this.prev = prev;
        }
    }

    private static final int MAX_FilE = 5000;

    private Trie trie;
    private HashMap<Integer, LinkedList<Integer>> network;

    private int N;
    private int[][] bandwidth;
    private int[] fileSizeOf;
    private int[] userSharedCount;

    private boolean[][] isShared;

    private HashMap<Integer, LinkedList<Integer>> task;
    private int[] fileCodeOfTask;

    @Override
    public void init(int N, int[] mUID1, int[] mUID2, int[] mBandwidth) {
        trie = new Trie();
        network = new HashMap<>();

        this.N = N;
        userSharedCount = new int[N + 1];
        bandwidth = new int[N + 1][N + 1];
        fileSizeOf = new int[MAX_FilE + 1];
        isShared = new boolean[N + 1][MAX_FilE + 1];

        task = new HashMap<>();
        fileCodeOfTask = new int[MAX_FilE + 1];

        int size = N - 1;
        for (int i = 0; i < size; i++) {
            int id1 = mUID1[i];
            int id2 = mUID2[i];
            bandwidth[id1][id2] = mBandwidth[i];
            bandwidth[id2][id1] = mBandwidth[i];
            network.computeIfAbsent(id1, key -> new LinkedList<>()).add(id2);
            network.computeIfAbsent(id2, key -> new LinkedList<>()).add(id1);
        }
    }

    @Override
    public int share(int mUID, char[] mFileName, int mFileSize) {
        int fileCode = trie.insert(mFileName);

        fileSizeOf[fileCode] = mFileSize;

        if (!isShared[mUID][fileCode]) {
            isShared[mUID][fileCode] = true;
            userSharedCount[mUID] += 1;
        }

        return userSharedCount[mUID];
    }

    @Override
    public int request(int mUID, char[] mFileName, int mTID) {
        int fileCode = trie.getCode(mFileName);
        if (fileCode == -1) // 파일명 존재하지 않음
            return -1;

        int fileSize = fileSizeOf[fileCode];

        ArrayDeque<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(new Node(mUID, null));
        visited[mUID] = true;

        Node dest = null;
        boolean isFind = false;

        while (!queue.isEmpty() && !isFind) {
            int queueSize = queue.size();

            for (int q = 0; q < queueSize; q++) {
                Node curr = queue.poll();

                if (isShared[curr.id][fileCode]) {
                    isFind = true;

                    if (dest == null || dest.id > curr.id)
                        dest = curr;
                }

                for (int nextID : network.get(curr.id)) {
                    if (visited[nextID] || bandwidth[curr.id][nextID] < fileSize)
                        continue;

                    visited[nextID] = true;
                    queue.offer(new Node(nextID, curr));
                }
            }
        }

        if (dest == null) // 사용자 존재 x
            return -1;

        LinkedList<Integer> path = new LinkedList<>();
        Node curr = dest;
        Node next = dest.prev;
        path.add(curr.id);

        while (next != null) {
            bandwidth[curr.id][next.id] -= fileSize;
            bandwidth[next.id][curr.id] -= fileSize;
            path.add(next.id);

            curr = next;
            next = next.prev;
        }

        task.put(mTID, path);
        fileCodeOfTask[mTID] = fileCode;

        return dest.id;
    }

    @Override
    public int complete(int mTID) {
        LinkedList<Integer> path = task.remove(mTID);
        Iterator<Integer> it = path.iterator();

        int fileCode = fileCodeOfTask[mTID];
        int fileSize = fileSizeOf[fileCode];

        int prevID = it.next();
        while (it.hasNext()) {
            int nextID = it.next();

            bandwidth[prevID][nextID] += fileSize;
            bandwidth[nextID][prevID] += fileSize;

            if (!isShared[nextID][fileCode]) {
                isShared[nextID][fileCode] = true;
                userSharedCount[nextID] += 1;
            }

            prevID = nextID;
        }

        return userSharedCount[prevID];
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}