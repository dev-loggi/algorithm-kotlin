package swExpertAcademy.professional;

import java.util.*;

/**
 * [No. 9] 14618. [Pro] 끝말잇기2 (8/23)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 playRound() 함수의 호출 횟수는 N을 넘지 않는다.<br/>
 * 3. playRound() 함수에서 전달하는 mCh 문자는 선택 가능한 단어들 중 하나의 첫번째 문자이다.<br/>
 *
 * 해시 함수, AVL Tree, HashSet
 * */
public class SWEA_PRO_P09_UserSolution implements SWEA_PRO_P09.UserSolution {

    private int N, M;
    private boolean[] loser;

    private long[] hashOfWord;
    private int[] first;
    private int[] end;
    private TreeMap<Long, Integer>[] firstMap;

    private HashSet<Long> usedWordSet;
    private final ArrayDeque<Integer> usedQueue = new ArrayDeque<>();

    @Override
    public void init(int N, int M, char[][] mWords) {
        this.N = N;
        this.M = M;
        loser = new boolean[N + 1];

        hashOfWord = new long[M * 2];
        first = new int[M * 2];
        end = new int[M * 2];
        firstMap = new TreeMap[27];

        usedWordSet = new HashSet<>();

        for (int i = 1; i <= 26; i++) {
            firstMap[i] = new TreeMap<>();
        }

        for (int idx = 0; idx < M; idx++) {
            char[] word = mWords[idx];

            // 원래 단어의 해시
            int len = 0;
            long hash = 0;
            for (int i = 0; word[i] != '\0'; i++, len++) {
                hash = (hash << 5) | (word[i] - 96);
            }
            hash = hash << (10 - len) * 5;

            // 뒤집은 단어의 해시
            long hashReverse = 0;
            for (int i = len - 1; i >= 0; i--) {
                hashReverse = (hashReverse << 5) | (word[i] - 96);
            }
            hashReverse = hashReverse << (10 - len) * 5;

            first[idx] = word[0] - 96;
            end[idx] = word[len - 1] - 96;
            hashOfWord[idx] = hash;

            first[idx + M] = word[len - 1] - 96;
            end[idx + M] = word[0] - 96;
            hashOfWord[idx + M] = hashReverse;

            firstMap[first[idx]].put(hash, idx);
            usedWordSet.add(hash);
        }
    }

    @Override
    public int playRound(int mID, char mCh) {
        Map.Entry<Long, Integer> curr = firstMap[mCh - 96].pollFirstEntry();
        int word = curr.getValue();
        long wordHash = curr.getKey();

        usedQueue.offer(word);

        int loserId;
        for (int nextId = mID + 1; ;nextId++) {
            if (nextId > N) nextId = 1;
            if (loser[nextId])
                continue;

            Map.Entry<Long, Integer> next = firstMap[end[word]].pollFirstEntry();

            if (next == null) {
                loserId = nextId;
                break;
            }

            word = next.getValue();
            wordHash = next.getKey();

            usedQueue.offer(word);
            usedWordSet.add(wordHash);
        }

        while (!usedQueue.isEmpty()) {
            word = usedQueue.poll();

            if (M <= word)
                continue;

            // 단어 뒤집기
            word += M;
            wordHash = hashOfWord[word];

            if (usedWordSet.contains(wordHash))
                continue;

            firstMap[first[word]].put(hashOfWord[word], word);
        }

        loser[loserId] = true;
        return loserId;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printArrays() {
        log("    loser: %s", Arrays.toString(loser));
//        log("    hashOfWord: %s", Arrays.toString(hashOfWord));
        log("    firstOfWord: %s", Arrays.toString(first));
        log("    endOfWord: %s", Arrays.toString(end));
        for (int i = 1; i <= 26; i++) {
            if (firstMap[i].isEmpty()) continue;
            log("    wordMapFirstOf[%c, %d]: %s", (char) (i + 96), i, firstMap[i]);
        }
    }
}