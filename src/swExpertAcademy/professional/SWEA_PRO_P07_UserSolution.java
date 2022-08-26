package swExpertAcademy.professional;

import java.util.*;

/**
 * [No. 7] 14634. [Pro] 우주자원개발 (8/19)<br/><br/>
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초<br/>
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내<br/>
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가, 종료 시 destroy() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 update() 함수는 최대 800 회 호출된다.<br/>
 * 3. 각 테스트 케이스에서 participate() 함수는 최대 800 회 호출된다.<br/>
 * 4. 각 테스트 케이스에서 cancel() 함수는 최대 200 회 호출된다.<br/>
 * 5. 각 테스트 케이스에서 calcProfit() 함수는 최대 100 회 호출된다.<br/>
 *
 * 트라이, 정렬
 * */
public class SWEA_PRO_P07_UserSolution implements SWEA_PRO_P07.UserSolution {

    public static class Trie {

        public static class Node {
            char ch;
            int code = -1;
            HashMap<Character, Node> children = new HashMap<>();

            public Node(char ch) { this.ch = ch; }
        }

        private final Node root = new Node('\0');
        private int stringPoolCount = 0;

        public int insert(char[] text) {
            Node curr = root;
            Node next = null;

            for (int i = 0; text[i] != '\0'; i++) {
                next = curr.children.get(text[i]);

                if (next == null) {
                    next = new Node(text[i]);
                    curr.children.put(text[i], next);
                }

                curr = next;
            }

            curr.code = stringPoolCount++;

            return curr.code;
        }

        public int getCode(char[] text) {
            Node node = root;

            for (int i = 0; text[i] != '\0'; i++) {
                node = node.children.get(text[i]);
            }

            return node.code;
        }
    }

    static class Company {
        int code;
        int r1, c1, r2, c2;
        int profit;

        public Company(int code, int r1, int c1, int r2, int c2, int profit) {
            this.code = code;
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.profit = profit;
        }
    }

    private final Comparator<Company> comparator = (o1, o2) -> o2.profit - o1.profit;

    private int N, K, M;
    private int[] resPrice;
    private int[][] profit;

    private Trie trie;
    private ArrayList<Company> companies;

    @Override
    public void init(int N, int K, int M, int[] mResPrice) {
        this.N = N;
        this.K = K;
        this.M = M;
        this.resPrice = mResPrice;
        profit = new int[N][N];

        trie = new Trie();
        companies = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                profit[r][c] = -10;
            }
        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update(int mBlockR, int mBlockC, int mCost, int[][] mResInfo) {
        for (int r = 0; r < K; r++) {
            int R = mBlockR * K + r;
            int C = mBlockC * K;

            for (int c = 0; c < K; c++) {
                int resourceInfo = mResInfo[r][c];
                int resource = 1 << (M - 1);

                int priceSum = 0;

                for (int m = 0; m < M; m++) {
                    if ((resource & resourceInfo) != 0)
                        priceSum += resPrice[m];

                    resource >>= 1;
                }

                int newProfit = priceSum - mCost;

                profit[R][C + c] = newProfit;
            }
        }
    }

    @Override
    public int participate(char[] mCompany, int mR1, int mC1, int mR2, int mC2) {
        int companyCode = trie.insert(mCompany);

        int totalProfit = 0;

        for (int r = mR1; r <= mR2; r++) {
            for (int c = mC1; c <= mC2; c++) {
                totalProfit += profit[r][c];
            }
        }

        companies.add(new Company(companyCode, mR1, mC1, mR2, mC2, totalProfit));

        if (totalProfit >= 0) return totalProfit;
        else return -1;
    }

    @Override
    public void cancel(char[] mCompany) {
        int companyCode = trie.getCode(mCompany);

        int companySize = companies.size();
        for (int i = 0; i < companySize; i++) {
            if (companies.get(i).code == companyCode) {
                companies.remove(i);
                break;
            }
        }
    }

    @Override
    public int calcProfit(int mTop) {
        int totalProfit = 0;

        for (Company company : companies) {
            int sum = 0;

            for (int r = company.r1; r <= company.r2; r++) {
                for (int c = company.c1; c <= company.c2; c++) {
                    sum += profit[r][c];
                }
            }

            company.profit = sum;
        }

        companies.sort(comparator);

        for (int i = 0; i < mTop && i < companies.size(); i++) {
            int profit = companies.get(i).profit;
            if (profit > 0)
                totalProfit += profit;
        }

        return totalProfit;
    }

    private int getHashCodeOfCompany(char[] company) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; company[i] != 0; i++)
            sb.append(company[i]);
        return sb.toString().hashCode();
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printParticipants() {
//        log("%s", participants);
    }

    private void printProfit() {
        for (int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%4d", profit[i][j]);
            }
            System.out.println();
        }
    }
}

