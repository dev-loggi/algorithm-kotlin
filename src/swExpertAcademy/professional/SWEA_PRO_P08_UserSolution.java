package swExpertAcademy.professional;

import java.util.HashMap;

/**
 * [No. 8] 14613. [Pro] 리스트 복사 (8/22)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 makeList() 함수의 호출 횟수는 10 이하이다.<br/>
 * 3. 각 테스트 케이스에서 copyList() 함수의 호출 횟수는 5,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 updateElement() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 5. 각 테스트 케이스에서 element() 함수의 호출 횟수는 400 이하이다.<br/>
 * 6. 리스트의 이름은 길이 1 이상, 20 이하의 알파벳 소문자로 이루어진 문자열이며, 문자열의 끝에는 ‘＼0’이 추가되어 있다.<br/>
 * 7. 메모리 제한은 256MB 이다.<br/>
 *
 * Trie, HashMap, List, Reference, DeepCopy
 *
 * (테스트 케이스 12번: 깊은 복사 이루어지기 전에 5만번 update 되는 최악의 경우)
 * */
public class SWEA_PRO_P08_UserSolution implements SWEA_PRO_P08.UserSolution {

    static class Trie {

        static class Node {
            private int code = -1;
            private final Node[] children = new Node[26];
        }

        private int specialTrieCode = 0;
        private int normalTrieCode = 10;
        private final Node root = new Node();

        public int insertSpecial(char[] text) {
            Node node = _insert(text);
            node.code = specialTrieCode++;
            return node.code;
        }

        public int insert(char[] text) {
            Node node = _insert(text);
            node.code = normalTrieCode++;
            return node.code;
        }

        public int getCode(char[] text) {
            Node curr = root;

            for (int i = 0; text[i] != '\0'; i++) {
                curr = curr.children[text[i] - 97];
            }

            return curr.code;
        }

        private Node _insert(char[] text) {
            Node curr = root;

            for (int i = 0; text[i] != '\0'; i++) {
                int chCode = text[i] - 97;

                Node next = curr.children[chCode];
                if (next == null) {
                    next = new Node();
                    curr.children[chCode] = next;
                }

                curr = next;
            }

            return curr;
        }
    }

    private static final int MAX_IMMUTABLE_LIST = 10;
    private static final int MAX_LIST = 5010;
    private static final int MAX_LIST_LENGTH = 200_000;

    private Trie trie;
    private int[][] immutableList;
    private boolean[] isDeepCopyImmutableList;

    private int[] listReference;
    private int[] historyReference;
    private HashMap<Integer, Integer>[] updateHistoryMap;

    @Override
    public void init() {
        trie = new Trie();
        immutableList = new int[MAX_IMMUTABLE_LIST][];
        isDeepCopyImmutableList = new boolean[MAX_IMMUTABLE_LIST];

        listReference = new int[MAX_LIST];
        historyReference = new int[MAX_LIST];
        updateHistoryMap = new HashMap[MAX_LIST];
    }

    @Override
    public void makeList(char[] mName, int mLength, int[] mListValue) {
        int trieCode = trie.insertSpecial(mName);

        immutableList[trieCode] = new int[mLength];
        System.arraycopy(mListValue, 0, immutableList[trieCode], 0, mLength);

        updateHistoryMap[trieCode] = new HashMap<>();

        historyReference[trieCode] = trieCode;
        listReference[trieCode] = trieCode;
    }

    @Override
    public void copyList(char[] mDest, char[] mSrc, boolean mCopy) {
        int dstTrieCode = trie.insert(mDest);
        int srcTrieCode = trie.getCode(mSrc);

        if (mCopy) { // 깊은 복사
            int historyRef = historyReference[srcTrieCode];

            if (historyRef < MAX_IMMUTABLE_LIST) {
                isDeepCopyImmutableList[historyRef] = true;
            }

            HashMap<Integer, Integer> history = updateHistoryMap[historyRef];

            updateHistoryMap[dstTrieCode] = new HashMap<>(history);

            historyReference[dstTrieCode] = dstTrieCode;
        } else { // 참조
            historyReference[dstTrieCode] = historyReference[srcTrieCode];
        }

        listReference[dstTrieCode] = listReference[srcTrieCode];
    }

    @Override
    public void updateElement(char[] mName, int mIndex, int mValue) {
        int trieCode = trie.getCode(mName);
        int historyRef = historyReference[trieCode];

        if (historyRef < MAX_IMMUTABLE_LIST && !isDeepCopyImmutableList[historyRef]) {
            immutableList[historyRef][mIndex] = mValue;
        } else {
            updateHistoryMap[historyRef].put(mIndex, mValue);
        }
    }

    @Override
    public int element(char[] mName, int mIndex) {
        int trieCode = trie.getCode(mName);
        int historyRef = historyReference[trieCode];

        Integer value = updateHistoryMap[historyRef].get(mIndex);

        if (value != null) {
            return value;
        } else {
            return immutableList[listReference[trieCode]][mIndex];
        }
    }

    private void log(String s, Object ... args) {
        System.out.printf(s+"\n", args);
    }
}
