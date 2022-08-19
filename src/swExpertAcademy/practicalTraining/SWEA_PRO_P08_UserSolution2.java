package swExpertAcademy.practicalTraining;

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
 * */
public class SWEA_PRO_P08_UserSolution2 implements SWEA_PRO_P08.UserSolution {

    static class Trie {

        static class Node {
            int code = -1;
            Node[] children = new Node[26];
        }

        private int nodeCount = 0;
        private Node root = new Node();

        public int insert(char[] text) {
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

            curr.code = nodeCount++;
            return curr.code;
        }

        public int getCode(char[] text) {
            Node curr = root;

            for (int i = 0; text[i] != '\0'; i++) {
                curr = curr.children[text[i] - 97];
            }

            return curr.code;
        }
    }

    private static final int MAX_LIST = 5010;
    private static final int MAX_LIST_LENGTH = 200_000;

    private Trie trie;
    private int[][] list;

    @Override
    public void init() {
        trie = new Trie();
        list = new int[MAX_LIST][];
    }

    @Override
    public void makeList(char[] mName, int mLength, int[] mListValue) {
        int[] newList = new int[mLength];
        System.arraycopy(mListValue, 0, newList, 0, mLength);

        list[trie.insert(mName)] = newList;
    }

    @Override
    public void copyList(char[] mDest, char[] mSrc, boolean mCopy) {
        int[] srcList = list[trie.getCode(mSrc)];

        if (mCopy) {
            int[] newList = new int[srcList.length];

            System.arraycopy(srcList, 0, newList, 0, srcList.length);

            list[trie.insert(mDest)] = newList;
        } else {
            list[trie.insert(mDest)] = srcList;
        }
    }

    @Override
    public void updateElement(char[] mName, int mIndex, int mValue) {
        list[trie.getCode(mName)][mIndex] = mValue;
    }

    @Override
    public int element(char[] mName, int mIndex) {
        return list[trie.getCode(mName)][mIndex];
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}