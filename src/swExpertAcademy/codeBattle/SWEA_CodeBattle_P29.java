package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * [No. 29] 9465. 메일서버
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P29 {

    public static class UserSolution {

        private static final int WORD_COUNT_MAX = 10;

        int N, K;
        private Trie trie;
        private int[][][] mailBoxes;
        private IndexQueue[] activeQueue;
        private IndexQueue[] waitingQueue;

        /**
         * @param N : 메일 서버 이용자의 수 (3 ≤ N ≤ 1,000)
         * @param K : 받은 메일함에 저장할 수 있는 메일의 개수 (3 ≤ K ≤ 300)
         * */
        public void init(int N, int K) {
//            log("\n★★★★★★★★★★Init(N=%d, K=%d)★★★★★★★★★★", N, K);
            this.N = N;
            this.K = K;
            trie = new Trie();
            mailBoxes = new int[N][K][];
            activeQueue = new IndexQueue[N];
            waitingQueue = new IndexQueue[N];

            for (int id = 0; id < N; id++) {
                activeQueue[id] = new IndexQueue(0);
                waitingQueue[id] = new IndexQueue(K);
            }
        }

        /**
         * uID 유저가 subject[] 제목의 메일 전송을 메일 서버에 요청한다.<br>
         * 메일 서버는 subject[] 제목의 메일을 rIDs[] 수신인들의 받은 메일함에 저장한다.<br>
         * 수신인들의 받은 메일함에 있는 메일의 개수가 K 개일 경우, 가장 오래된 메일이 삭제되고 subject[] 제목의 메일이 저장된다.<br>
         * subject[] 는 1개 이상 10개 이하의 단어로 구성되며, 단어가 2개 이상일 경우 각 단어의 사이는 빈 칸(‘ ‘) 하나로 이루어져 있다.<br>
         * subject[] 에 포함되는 각 단어는 영어 소문자로 구성되며, 길이는 3 이상 10 이하이다.<br>
         * subject[] 는 ‘\0’ 으로 끝나는 문자열이다.<br>
         * rIDs[] 는 모두 서로 다른 사용자의 id 이다.
         *
         * @param subject : 메일 제목 (1 ≤ subject[] 에 있는 단어의 개수 ≤ 10)
         * @param uID : 메일을 보내는 유저의 id (0 ≤ uID ≤ ( N - 1 ))
         * @param cnt : 메일을 받는 사람들의 수 (1 ≤ cnt ≤ 50)
         * @param rIDs : 메일을 받는 유저들의 id (0 ≤ rIDs[] ≤ ( N - 1 ))
         * */
        public void sendMail(char[] subject, int uID, int cnt, int[] rIDs) {
//            print();
            int[] codeArray = makeTrieCodeArray(subject);

            for (int i = 0; i < cnt; i++) {
                int receiverId = rIDs[i];
                int newIndex = waitingQueue[receiverId].isNotEmpty()
                        ? waitingQueue[receiverId].dequeue()
                        : activeQueue[receiverId].dequeue();

                mailBoxes[receiverId][newIndex] = codeArray;

                activeQueue[receiverId].enqueue(newIndex);
            }
//            log("sendMail(): cnt=%d, rIDs=%s, subject=[%s], code=%s", cnt, intArrayToString(rIDs, cnt), new String(subject), Arrays.toString(codeArray));
//            print();
//            log("");
        }

        /**
         * uID 유저의 받은 메일함에 있는 메일의 수를 반환한다.<br>
         * 반환되는 메일의 개수에 삭제된 메일은 포함되지 않는다.
         *
         * @param uID 받은 메일함을 확인하는 유저의 id
         * @return uID 유저의 받은 메일함에 있는 메일 개수
         * */
        public int getCount(int uID) {
//            log("getCount(uID=%d): %d", uID, activeQueue[uID].size());
            //print();
            return activeQueue[uID].size();
        }

        /**
         * uID 유저의 받은 메일함에서 subject[] 와 일치하는 제목을 가진 메일을 삭제하고, 삭제한 메일의 개수를 리턴한다.<br>
         * subject[] 는 영어 소문자와 빈칸으로 구성되며, ‘\0’ 으로 끝나고 ‘\0’ 을 포함한 전체 길이는 200 이하이다.
         *
         * @param uID       메일을 삭제하려는 유저의 id
         * @param subject   삭제할 메일의 제목
         * @return 삭제한 메일의 개수
         * */
        public int deleteMail(int uID, char[] subject) {
            int[] deleteCode = makeTrieCodeArray(subject);
//            log("    deleteCode=%s", Arrays.toString(searchCode));

            int count = activeQueue[uID].removeAllIf(
                    index -> {
                        int[] userCode = mailBoxes[uID][index];
//                        log("        userCode=%s", Arrays.toString(userCode));

                        for (int j = 0; j < WORD_COUNT_MAX; j++) {
                            if (deleteCode[j] == 0 || userCode[j] == 0)
                                break;

                            if (deleteCode[j] != userCode[j])
                                return false;
                        }

                        waitingQueue[uID].enqueue(index);
                        return true;
                    });

//            log("deleteMail(count=%d): uID=%d, subject=[%s], code=%s", count, uID, new String(subject), Arrays.toString(deleteCode));
            //print();
            //log("");
            return count;
        }

        /**
         * uID 유저의 받은 메일함에서 메일 제목에 text[] 단어가 포함되어 있는 메일을 찾고, 찾은 메일의 개수를 리턴한다.<br>
         * 메일 제목에 포함되어 있는 단어 중 하나와 text[] 단어가 일치해야만 검색이 되며, 일부분만 같을 경우 검색이 되지 않아 찾은 메일 개수에 포함되지 않는다.<br>
         * 예를 들어, 메일 제목이 “aaaa bbbb cccc” 이고, text[] = “aaa” 일 경우 해당 메일은 찾은 메일 개수에 포함되지 않는다.<br>
         * text[] 는 영어 소문자로 구성되며, ‘\0’ 으로 끝나고 ‘\0’ 을 포함한 전체 길이는 11 이하이다.
         *
         * @param uID : 받은 메일함에서 단어를 검색하려는 유저의 id
         * @param text : 검색할 단어
         * @return 검색된 메일의 개수
         * */
        public int searchMail(int uID, char[] text) {
            int code = makeTrieCode(text);

            int count = activeQueue[uID].countOf(index -> {
                int[] userCode = mailBoxes[uID][index];

                for (int j = 0; j < WORD_COUNT_MAX && userCode[j] != 0; j++) {
                    if (code == userCode[j])
                        return true;
                }
                return false;
            });

//            log("searchMail(count=%d): uID=%d, subject=[%s], code=%d", count, uID, new String(text), code);
            return count;
        }

        private int[] makeTrieCodeArray(char[] subject) {
            int[] codeArr = new int[WORD_COUNT_MAX];

            int start = 0, end = 0, wordIdx = 0;
            for (int i = 0; i < subject.length - 1; i++) {
                if (subject[i + 1] == ' ' || subject[i + 1] == '\0') {
                    end = i;

                    codeArr[wordIdx++] = trie.insert(subject, start, end);

                    start = i + 2;
                }

                if (subject[i + 1] == '\0')
                    break;
            }

            return codeArr;
        }

        private int makeTrieCode(char[] text) {
            int i;
            for (i = 0; i < text.length; i++) {
                if (text[i] == '\0') break;
            }

            return trie.insert(text, 0, i - 1);
        }

        public void print() {
            log("[print]============");
            for (int id = 0; id < N; id++) {
                IndexQueue queue = activeQueue[id];
                IndexQueue queue2 = waitingQueue[id];
                if (queue.isNotEmpty()) {
                    log("    [id=%d]: activeQueue(%d)=%s", id, queue.size, queue);
                    log("    [id=%d]: waitingQueue(%d)=%s", id, queue2.size, queue2);

                    int[][] mailBox = mailBoxes[id];
                    queue.forEach(index -> {
                        log("        [%d]: %s", index, Arrays.toString(mailBox[index]));
                    });
                }
            }
            log("[print end]============");
        }

        private void log(String s, Object ... args) {
            System.out.printf(s + "\n", args);
        }

        public static String intArrayToString(int[] a, int cnt) {
            if (a == null)
                return "null";
            int iMax = a.length - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(a[i]);
                if (i == iMax || i == cnt - 1)
                    return b.append(']').toString();
                b.append(", ");
            }
        }

        public static class IndexQueue {

            public static class Node {
                public int index;
                private Node next;

                public Node(int index) {
                    this.index = index;
                }

                @Override
                public String toString() {
                    return String.valueOf(index);
                }
            }

            private Node head, tail;

            private int size = 0;

            public IndexQueue(int initialSize) {
                Node prev = null;

                int last = initialSize - 1;
                for (int i = 0; i <= last; i++) {
                    Node newNode = new Node(i);

                    if (i == 0) head = newNode;
                    if (i == last) tail = newNode;
                    if (prev != null) prev.next = newNode;

                    prev = newNode;
                }

                size = initialSize;
            }

            public int size() {
                return size;
            }

            public boolean isNotEmpty() {
                return size != 0;
            }

            public void enqueue(int index) {
                if (tail == null) {
                    head = new Node(index);
                    tail = head;
                } else {
                    tail.next = new Node(index);
                    tail = tail.next;
                }
                size += 1;
            }

            public int dequeue() {
                Node node = head;
                head = head.next;
                size -= 1;

                if (size == 0) {
                    tail = null;
                }

                return node.index;
            }

            public int removeAllIf(Predicate<Integer> predicate) {
                int removeCount = 0;

                Node temp, prev = null, curr = head;
                while (curr != null) {
                    if (predicate.test(curr.index)) {
                        if (curr == head) head = curr.next;
                        if (curr == tail) tail = prev;

                        temp = curr;
                        if (prev != null) {
                            prev.next = curr.next;
                        }
                        curr = curr.next;
                        temp.next = null;

                        removeCount += 1;
                        size -= 1;
//                        System.out.printf("    prev=%s, curr=%s (removed=%s)\n", prev, curr, temp);
                    } else {
                        prev = curr;
                        curr = curr.next;
//                        System.out.printf("    prev=%s, curr=%s\n", prev, curr);
                    }
                }

                return removeCount;
            }

            public int countOf(Predicate<Integer> predicate) {
                int count = 0;
                Node node = head;
                while (node != null) {
                    if (predicate.test(node.index)) count += 1;
                    node = node.next;
                }
                return count;
            }

            public void forEach(Consumer<Integer> consumer) {
                Node node = head;
                while (node != null) {
                    consumer.accept(node.index);
                    node = node.next;
                }
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder("[");
                Node node = head;
                while (node != null) {
                    sb.append(node.index).append(", ");
                    node = node.next;
                }

                if (sb.length() >= 2) {
                    sb.delete(sb.length() - 2, sb.length());
                }
                sb.append(']');
                return sb.toString();
            }
        }

        public static class Trie {

            public static class Node {
                int order = -1;
                HashMap<Character, Node> children = new HashMap<>();
            }

            private final Node root = new Node();
            private int stringPoolCount = 0;

            public int insert(char[] text, int start, int end) {
                //System.out.printf("    insert(start=%d, end=%d): text=[%s]\n", start, end, new String(text));
                Node curr = root;
                Node next = null;

                for (int i = start; i <= end; i++) {
                    next = curr.children.get(text[i]);

                    if (next == null) {
                        next = new Node();
                        curr.children.put(text[i], next);
                    }

                    curr = next;
                }

                if (curr.order == -1) {
                    curr.order = ++stringPoolCount;
                }

                return curr.order;
            }

            public int stringOrderBy(char[] text, int start, int end) {
                Node node = root;

                for (int i = start; i <= end; i++) {
                    node = node.children.get(text[i]);

                    if (node == null)
                        return -1;
                }

                return (node.order != -1) ? node.order : -1;
            }
        }
    }

    private static BufferedReader br;
    private static UserSolution usersolution = new UserSolution();

    private final static int INIT         = 0;
    private final static int SENDMAIL     = 1;
    private final static int GETCOUNT     = 2;
    private final static int DELETEMAIL   = 3;
    private final static int SEARCHMAIL   = 4;

    private final static int MAX_WORD     = 10000;

    private static char Word[][] = new char [MAX_WORD][11];
    private static char subjectStr[] = new char [200];
    private static int rids[] = new int [50];

    private static int U, W, minR, maxR, SW;

    private static int mSeed;
    private static int pseudo_rand() {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    private static void make_string(int seed) {
        mSeed = seed;

        for (int i = 0; i < W; ++i) {
            int length = 5 + pseudo_rand() % 6;
            for (int k = 0; k < length; ++k) {
                Word[i][k] = (char)('a' + pseudo_rand() % 26);
            }
            Word[i][length] = '\0';
        }
    }

    private static void send_mail(int seed) {
        mSeed = seed;

        int pos = 0;
        int wcnt = 1 + pseudo_rand() % SW;
        for (int i = 0; i < wcnt; ++i) {
            int widx = pseudo_rand() % W;
            for (int k = 0; k < 10; ++k) {
                if (Word[widx][k] == '\0') break;
                subjectStr[pos++] = Word[widx][k];
            }
            subjectStr[pos++] = ' ';
        }
        subjectStr[pos - 1] = '\0';

        int uid = pseudo_rand() % U;
        int rcnt = minR + pseudo_rand() % (maxR - minR + 1);
        for (int i = 0; i < rcnt; ++i) {
            rids[i] = pseudo_rand() % U;
        }

        usersolution.sendMail(subjectStr, uid, rcnt, rids);
    }

    static int delete_mail(int uid, int seed) {
        mSeed = seed;

        int pos = 0;
        int wcnt = 1 + pseudo_rand() % SW;
        for (int i = 0; i < wcnt; ++i) {
            int widx = pseudo_rand() % W;
            for (int k = 0; k < 10; ++k) {
                if (Word[widx][k] == '\0') break;
                subjectStr[pos++] = Word[widx][k];
            }
            subjectStr[pos++] = ' ';
        }
        subjectStr[pos - 1] = '\0';

        return usersolution.deleteMail(uid, subjectStr);
    }

    private static int run(int answer) throws Exception {
        int Q, K, cmd, sample, seed, param1, param2, ret;
        int uid, rcnt;
        char buf[] = new char [30];

        String inputStr;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Q = Integer.parseInt(st.nextToken());
        sample = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (sample == 0) {
            W = Integer.parseInt(st.nextToken());
            minR = Integer.parseInt(st.nextToken());
            maxR = Integer.parseInt(st.nextToken());
            SW = Integer.parseInt(st.nextToken());
            seed = Integer.parseInt(st.nextToken());
            make_string(seed);
        }

        usersolution.init(U, K);

        for (int i = 1; i < Q; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case SENDMAIL:
                    if (sample == 1) {
                        inputStr = st.nextToken();
                        uid = Integer.parseInt(st.nextToken());
                        rcnt = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < rcnt; ++k) rids[k] = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < inputStr.length(); ++k) {
                            buf[k] = inputStr.charAt(k);
                            if (buf[k] == '_') buf[k] = ' ';
                        }
                        buf[inputStr.length()] = '\0';
                        usersolution.sendMail(buf, uid, rcnt, rids);
                    }
                    else {
                        seed = Integer.parseInt(st.nextToken());
                        send_mail(seed);
                    }
                    break;
                case GETCOUNT:
                    param1 = Integer.parseInt(st.nextToken());
                    param2 = Integer.parseInt(st.nextToken());
                    ret = usersolution.getCount(param1);
                    if (ret != param2) answer = 0;
                    break;
                case DELETEMAIL:
                    if (sample == 1) {
                        param1 = Integer.parseInt(st.nextToken());
                        inputStr = st.nextToken();
                        param2 = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < inputStr.length(); ++k) {
                            buf[k] = inputStr.charAt(k);
                            if (buf[k] == '_') buf[k] = ' ';
                        }
                        buf[inputStr.length()] = '\0';
                        ret = usersolution.deleteMail(param1, buf);
                        if (ret != param2) answer = 0;
                    }
                    else {
                        param1 = Integer.parseInt(st.nextToken());
                        seed = Integer.parseInt(st.nextToken());
                        param2 = Integer.parseInt(st.nextToken());
                        ret = delete_mail(param1, seed);
                        if (ret != param2) answer = 0;
                    }
                    break;
                case SEARCHMAIL:
                    param1 = Integer.parseInt(st.nextToken());
                    inputStr = st.nextToken();
                    param2 = Integer.parseInt(st.nextToken());
                    for (int k = 0; k < inputStr.length(); ++k) {
                        buf[k] = inputStr.charAt(k);
                    }
                    buf[inputStr.length()] = '\0';
                    ret = usersolution.searchMail(param1, buf);
                    if (ret != param2) answer = 0;
                    break;
                default:
                    break;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        int T, Mark;

        System.setIn(new java.io.FileInputStream(InputFile.number(29)));
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");
        T = Integer.parseInt(stinit.nextToken());
//        T = 1;
        Mark = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            System.out.println("#" + tc + " " + run(Mark));
            usersolution.print();
        }

        br.close();
    }

}
