package swExpertAcademy.professional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * [No. 12] 13088. [Pro] 순위표
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가, 끝날 시 destroy() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 대회에 참가하는 선수의 수는 6,000명 이하이다.<br/>
 * 3. 각 테스트 케이스에서 changeProblemScore() 함수 호출 횟수는 1,000회 이하이다.<br/>
 * 4. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 최대 50,000 회이다.<br/>
 * 5. 각 함수에서 인자로 주어지는 모든 사용자와 문제 이름은 알파벳 대문자 또는 소문자로 구성되며, 길이는 1 이상 10 이하이고, ‘＼0’ 문자로 끝나는 문자열이다.<br/>
 *
 * 세그먼트 트리, 이분 탐색, AVL Tree
 * */
public class SWEA_PRO_P12_UserSolution implements SWEA_PRO_P12.UserSolution {

    static class Result {
        public int current_rank = 0, best_rank = 0, worst_rank = 0;

        public Result () {}

        public Result (int current, int best, int worst) {
            current_rank = current;
            best_rank = best;
            worst_rank = worst;
        }

        @Override
        public String toString() {
            return "Result[" + current_rank + ", " + best_rank + ", " + worst_rank + ']';
        }
    }

    static class Trie {

        private static class Node {
            int code = -1;
            private final HashMap<Character, Node> children = new HashMap<>();
        }

        private int count = 0;
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
                    ? curr.code = count++
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

    static class Player implements Comparable<Player> {
        int code, score;

        public Player(int code, int score) {
            this.code = code;
            this.score = score;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            return code == ((Player) o).code;
        }

        @Override
        public int hashCode() {
            return code;
        }

        @Override
        public int compareTo(Player o) {
            if (o.score != score) return o.score - score;
            else return code - o.code;
        }

        @Override
        public String toString() {
            return "Player[" + code + ", " + score + ']';
        }
    }

    private static final int MAX_PLAYER = 6_000;
    private static final int MAX_PROBLEM = 2_000;

    private static final int UNSOLVED = 0;
    private static final int WRONG = 1;
    private static final int CORRECT = 2;

    private Trie playerTrie, problemTrie;

    private Player[] playerInPerfectBaseTree;
    private Player[] playerInZeroBaseTree;
    private TreeSet<Player> perfectBaseTree; // 만점으로 시작하는 RBT
    private TreeSet<Player> zeroBaseTree; // 0점으로 시작하는 RBT

    private int totalScore; // 문제 점수 총합
    private int[] score; // 문제별 점수
    private byte[][] solved; // 선수별 문제 풀이 정보

    @Override
    public void init() {
        playerTrie = new Trie();
        problemTrie = new Trie();

        playerInPerfectBaseTree = new Player[MAX_PLAYER];
        playerInZeroBaseTree = new Player[MAX_PLAYER];
        perfectBaseTree = new TreeSet<>();
        zeroBaseTree = new TreeSet<>();

        totalScore = 0;
        score = new int[MAX_PROBLEM];
        solved = new byte[MAX_PLAYER][MAX_PROBLEM];
    }

    @Override
    public void destroy() {

    }

    @Override
    public void newPlayer(char[] name) {
        int playerCode = playerTrie.insert(name);

        // 만점 트리에 삽입
        playerInPerfectBaseTree[playerCode] = new Player(playerCode, totalScore);
        perfectBaseTree.add(playerInPerfectBaseTree[playerCode]);

        // 0점 트리에 삽입
        playerInZeroBaseTree[playerCode] = new Player(playerCode, 0);
        zeroBaseTree.add(playerInZeroBaseTree[playerCode]);
    }

    @Override
    public void newProblem(char[] name, int _score) {
        int problemCode = problemTrie.insert(name);

        score[problemCode] = _score;
        totalScore += _score;

        // 만점 트리에 있는 선수들의 score 모두 업데이트
        for (int player = 0; player < playerTrie.count; player++) {
            playerInPerfectBaseTree[player].score += _score;
        }
    }

    @Override
    public void changeProblemScore(char[] name, int new_score) {
        int problemCode = problemTrie.getCode(name);
        int old_score = score[problemCode];
        int diff_score = new_score - old_score;

        score[problemCode] = new_score;
        totalScore += diff_score;

        // 만점 트리
        for (int player = 0; player < playerTrie.count; player++) {
            if (solved[player][problemCode] != WRONG) { // 맞춘사람 or 아직 안푼사람
                playerInPerfectBaseTree[player].score += diff_score;
            }
        }

        // 0점 트리
        for (int player = 0; player < playerTrie.count; player++) {
            if (solved[player][problemCode] == CORRECT) { // 맞춘사람
                playerInZeroBaseTree[player].score += diff_score;
            }
        }

        // 트리 재배치
        perfectBaseTree.clear();
        zeroBaseTree.clear();

        for (int player = 0; player < playerTrie.count; player++) {
            perfectBaseTree.add(playerInPerfectBaseTree[player]);
            zeroBaseTree.add(playerInZeroBaseTree[player]);
        }
    }

    @Override
    public void attemptProblem(char[] player_name, char[] problem_name, int attempt_result) {
        attempt_result += 1;

        int playerCode = playerTrie.getCode(player_name);
        int problemCode = problemTrie.getCode(problem_name);

        solved[playerCode][problemCode] = (byte) attempt_result;

        Player player;
        if (attempt_result == WRONG) { // 틀렸을 때: 만점 트리에서 player 점수 내리기
            player = playerInPerfectBaseTree[playerCode];

            perfectBaseTree.remove(player);
            player.score -= score[problemCode];
            perfectBaseTree.add(player);
        } else { // 맞췄을 때: 0점 트리에서 player 점수 올리기
            player = playerInZeroBaseTree[playerCode];

            zeroBaseTree.remove(player);
            player.score += score[problemCode];
            zeroBaseTree.add(player);
        }
    }

    @Override
    public Result getRank(char[] player_name) {
//        printInfo();
        int playerCode = playerTrie.getCode(player_name);
//        log("♠ getRank(): player=%s(code=%d)", newString(player_name), playerCode);

        // current_rank: 0점 트리에서의 현재 랭크
        int current_rank = calcRank(zeroBaseTree, playerInZeroBaseTree[playerCode], false);

        Player player1, player2;

        // best_rank
        // 1. 0점 트리에서 해당 player 잠시 제거
        // 2. 만점 트리에 있는 해당 player 를 0점 트리에 삽입 후 순위 확인
        // 3. 원상복귀
        player1 = playerInZeroBaseTree[playerCode];
        zeroBaseTree.remove(player1);

        player2 = playerInPerfectBaseTree[playerCode];
        zeroBaseTree.add(player2);

        int best_rank = calcRank(zeroBaseTree, player2, false);

        zeroBaseTree.remove(player2);
        zeroBaseTree.add(player1);

        // worst_rank
        // 1. 만점 트리에서 해당 player 잠시 제거
        // 2. 0점 트리에 있는 해당 player 를 만점 트리에 삽입 후 순위 확인
        // 3. 원상복귀
        player1 = playerInPerfectBaseTree[playerCode];
        perfectBaseTree.remove(player1);

        player2 = playerInZeroBaseTree[playerCode];
        perfectBaseTree.add(player2);

        int worst_rank = calcRank(perfectBaseTree, player2, true);

        perfectBaseTree.remove(player2);
        perfectBaseTree.add(player1);

        return new Result(current_rank, best_rank, worst_rank);
    }

    private int calcRank(TreeSet<Player> tree, Player player, boolean isReverse) {
        if (player.score == totalScore)
            return 1;
        if (player.score == 0) {
            int count = 0;

            Iterator<Player> it = tree.descendingIterator();
            while (it.hasNext()) {
                if (it.next().score != 0)
                    break;
                count++;
            }

            return playerTrie.count - count + 1;
        }

        int rank = 0;
        int count = 0;
        int prevScore = Integer.MIN_VALUE;

        if (!isReverse) {
            for (Player p : tree) {
                if (prevScore == p.score) count += 1;
                else count = 0;

                prevScore = p.score;

                rank += 1;

                if (player.code == p.code)
                    break;
            }
            rank -= count;
        } else {
            Iterator<Player> it = tree.descendingIterator();
            while (it.hasNext()) {
                Player p = it.next();

                if (player.score < p.score)
                    break;

                count += 1;
            }

            rank = playerTrie.count - count + 1;
        }

        return rank;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printInfo() {
        log("========== printInfo() ==========");
        log("    문제 점수표 >>");
        System.out.print("        ");
        for (int i = 0; score[i] != 0; i++)
            System.out.printf("%3d ", i);
        System.out.print("\n        ");
        for (int i = 0; score[i] != 0; i++)
            System.out.printf("%3d ", score[i]);
        log("");
        log("    문제 solved Info >>");
        for (int p = 0; p < playerTrie.count; p++) {
            System.out.printf("  (%3d):", p);
            for (int b = 0; b < problemTrie.count; b++)
                System.out.printf("%3d ", solved[p][b]);
            System.out.println();
        }

        log("    만점트리 >>");
        for (Player player : perfectBaseTree)
            log("        %s", player);
        log("    0점트리 >>");
        for (Player player : zeroBaseTree)
            log("        %s", player);
        log("=================================");
    }

    private String newString(char[] name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; name[i] != '\0'; i++)
            sb.append(name[i]);
        return sb.toString();
    }

    private void printTimeInfo() {

    }
}