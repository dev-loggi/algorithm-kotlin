package swExpertAcademy.basic;

import swExpertAcademy.basic._inputs.InputFile;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.function.Consumer;

/**
 * [No. 30] 9467. 문자열 암호화
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P30 {

    public static class UserSolution {

        private static final int MAX_HASH = 26425;
        private static final int SUB_LEN = 3;

        private static final int[] CODE = makeCharCode();

        private int N;
        private int hashArraySize;
        private char[] text;
        private int[] hashArray;
        private IndexList[] hashToIndexMapping;
        private ArrayDeque<Integer> waitingIndexStackForProcessing;

        /**
         * @param N : 원본 문자열의 길이
         * @param init_string : 원본 문자열.
         * */
        public void init(int N, char[] init_string) {
            this.N = N;
            hashArraySize = N - 2;
            text = new char[N];
            hashArray = new int[hashArraySize];
            System.arraycopy(init_string, 0, text, 0, N);

            hashToIndexMapping = new IndexList[MAX_HASH + 1];
            waitingIndexStackForProcessing = new ArrayDeque<>();

            for (int hash = 0; hash <= MAX_HASH; hash++) {
                hashToIndexMapping[hash] = new IndexList();
            }

            int hash = makeHash(text, 0);
            hashArray[0] = hash;
            hashToIndexMapping[hash].addLast(0);

            // rolling hash
            for (int i = 3; i < N; i++) {
                hash ^= CODE[text[i - 3]] << 10;
                hash = (hash << 5) | CODE[text[i]];

                hashArray[i - 2] = hash;
                hashToIndexMapping[hash].addLast(i - 2);
            }
        }

        /**
         * string_A 와 일치하는 부분문자열을 string_B로 바꾼다.
         *
         * @param string_A 규칙을 이루는 문자열에서 찾아 변환시킬 문자열이다.
         * @param string_B string_A 를 대체할 문자열 (string_A, string_B) 의 각 길이는 3이다.
         *
         * @return 변경한 부분 문자열의 개수를 반환한다.
         * */
        public int change(char[] string_A, char[] string_B) {
            int hashA = makeHash(string_A, 0);
            int hashB = makeHash(string_B, 0);

            if (hashA == hashB)
                return hashToIndexMapping[hashA].size;

            int removedCount = hashToIndexMapping[hashA].removeSequentiallyButWithinTwoRangeSkipped(
                    index -> {
                        text[index] = string_B[0];
                        text[index + 1] = string_B[1];
                        text[index + 2] = string_B[2];
                        hashArray[index] = hashB;
                        hashToIndexMapping[hashB].addByAscending(index);

                        for (int i = index - 2; i <= index + 2; i++) {
                            if (i < 0 || i >= hashArraySize || i == index)
                                continue;

                            waitingIndexStackForProcessing.push(i);
                        }
                    }
            );

            while (!waitingIndexStackForProcessing.isEmpty()) {
                int index = waitingIndexStackForProcessing.pop();

                int originHash = hashArray[index];
                int changedHash = makeHash(text, index);


                if (originHash == changedHash)
                    continue;

                hashArray[index] = changedHash;

                hashToIndexMapping[originHash].remove(index);
                hashToIndexMapping[changedHash].addByAscending(index);
            }

            return removedCount;
        }

        /**
         * 최종 문자열 상태를 ret 배열에 반환한다.
         * 이 함수는 각 테스트케이스 마지막에 한번만 호출된다.
         * */
        public void result(char[] ret) {
            System.arraycopy(text, 0, ret, 0, N);
        }

        private int makeHash(char[] arr, int i) {
            return ((CODE[arr[i]] << 5) + CODE[arr[i + 1]] << 5) + CODE[arr[i + 2]];
        }

        private static int[] makeCharCode() {
            int[] code = new int[123];
            for (int i = 97; i <= 122; i++) {
                code[i] = i - 97;
            }
            return code;
        }

        private void log(String s, Object ... args) { System.out.printf(s, args); }
        private void logln(String s, Object ... args) { System.out.printf(s + "\n", args); }

        public static class IndexList {

            private static class Node {
                private int index;
                private Node next;
                public Node(int value) { index = value; }
                @Override public String toString() { return String.valueOf(index); }
            }

            private Node head, tail;
            private int size = 0;

            public void addLast(int index) {
                Node newNode = new Node(index);

                if (size == 0) {
                    head = newNode;
                } else {
                    tail.next = newNode;
                }

                tail = newNode;
                size += 1;
            }

            public void addByAscending(int index) {
                Node newNode = new Node(index);

                if (size == 0) {
                    head = newNode;
                    tail = newNode;
                } else {
                    Node prev = null;
                    Node curr = head;
                    while (curr != null) {
                        if (newNode.index <= curr.index) {
                            if (prev == null)
                                head = newNode;
                            else
                                prev.next = newNode;

                            newNode.next = curr;
                            break;
                        } else if (curr == tail) { // newNode 값이 제일 큼
                            tail.next = newNode;
                            tail = newNode;
                            break;
                        }

                        prev = curr;
                        curr = curr.next;
                    }
                }

                size += 1;
            }

            public void remove(int index) {
                Node prev = null;
                Node curr = head;
                while (curr != null) {
                    if (curr.index == index) {
                        if (curr == head) head = curr.next;
                        if (curr == tail) tail = prev;

                        if (prev != null) prev.next = curr.next;
                        curr.next = null;
                        size -= 1;
                        break;
                    }
                    prev = curr;
                    curr = curr.next;
                }
            }

            /** 앞에서 부터 순차적으로 제거하되, 직전에 삭제된 값보다 +2 이하인 것은 건너뛴다 */
            public int removeSequentiallyButWithinTwoRangeSkipped(Consumer<Integer> consumer) {
                int removedCount = 0;
                int removeCondition = -3;

                Node removed = null;
                Node prev = null;
                Node curr = head;

                while (curr != null) {
                    if (removeCondition < curr.index) { // 지워짐
                        consumer.accept(curr.index);

                        if (curr == head) head = curr.next;
                        if (curr == tail) tail = prev;

                        removed = curr;
                        if (prev != null) prev.next = curr.next;
                        curr = curr.next;
                        removed.next = null;

                        removeCondition = removed.index + 2;
                        removedCount += 1;
                        size -= 1;
                    } else { // 안 지워짐
                        prev = curr;
                        curr = curr.next;
                    }
                }

                return removedCount;
            }

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder("[");
                Node node = head;
                while (node != null) {
                    sb.append(node.index);
                    if (node.next != null) sb.append(", ");
                    node = node.next;
                }
                sb.append("] ");
                return sb.toString();
            }
        }
    }

    private static int mSeed = 5;

    private static final int MAXL = 50005;
    private static final int DUMMY_LEN = 5959;
    private static int score = 0;
    private static int total_score = 0;

    private static Scanner sc;
    private static UserSolution user = new UserSolution();

    private static char dummy1[] = new char[DUMMY_LEN];
    private static char init_string[] = new char[MAXL];
    private static char dummy2[] = new char[DUMMY_LEN];
    private static char result_string[] = new char[MAXL];
    private static char dummy3[] = new char[DUMMY_LEN];
    private static char user_ans_string[] = new char[MAXL];
    private static char dummy4[] = new char[DUMMY_LEN];
    private static char string_A[] = new char[4];
    private static char string_B[] = new char[4];
    private static int init_string_len = 0;
    private static int char_type = 0;
    private static int query_cnt = 0;

    private static int pseudo_rand() {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    public static void main(String[] args) throws Exception {
		System.setIn(new java.io.FileInputStream(InputFile.number(30)));

        sc = new Scanner(System.in);

        int T = sc.nextInt();
        total_score = 0;
//        T = 4;
        for (int TC = 1; TC <= T; TC++) {
            score = 100;
            mSeed = sc.nextInt();
            init_string_len = sc.nextInt();
            char_type = sc.nextInt();
            query_cnt = sc.nextInt();

            for (int i = 0; i < init_string_len; i++) {
                init_string[i] = (char)(pseudo_rand() % char_type + 'a');
            }
            init_string[init_string_len] = '\0';

            user.init(init_string_len, init_string);

            string_A[3] = string_B[3] = '\0';
            for (int i = 0; i < query_cnt; i++) {
                for (int k = 0; k < 3; k++) {
                    string_A[k] = (char) ((pseudo_rand() % char_type) + 'a');
                    string_B[k] = (char) ((pseudo_rand() % char_type) + 'a');
                }
                int user_ans = user.change(string_A, string_B);
                int ans = sc.nextInt();

                if (ans != user_ans) {
                    score = 0;
                }
//                System.out.printf(" => change answer(user=%d, answer=%d): isCorrect=%b, score=%d\n\n", user_ans, ans, user_ans == ans, score);
            }

            user.result(user_ans_string);


            sc.nextLine();
            String result_str_java = sc.nextLine();
            result_string = result_str_java.toString().toCharArray();

            for (int i = 0; i < init_string_len; i++)
            {
                if (result_string[i] != user_ans_string[i])
                {
                    score = 0;
                    break;
                }
            }

            total_score += score;
            System.out.println("#" + TC +" " + score);

        }
        System.out.println("Total score : " + total_score/T);
        sc.close();
    }
}