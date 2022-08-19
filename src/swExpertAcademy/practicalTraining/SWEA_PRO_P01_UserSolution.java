package swExpertAcademy.practicalTraining;

/**
 * [2022-07-29] 22 하계 대학생 S/W 알고리즘 특강 1차 라이브 코드배틀
 *
 * 13072. [Pro] 병사관리
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/>[제약사항]<br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 hire() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 3. 각 테스트 케이스에서 fire() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 updateSoldier() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 5. 각 테스트 케이스에서 updateTeam() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 6. 각 테스트 케이스에서 bestSoldier() 함수의 호출 횟수는 100 이하이다.<br/>
 *
 * 원형 더블 연결 리스트(순환 구조)
 * */
public class SWEA_PRO_P01_UserSolution implements SWEA_PRO_P01.UserSolution {

    static class Node {
        int team, id;
        Node prev, next;

        public Node(int team, int id) { this.team = team; this.id = id; }

        @Override public String toString() { return "Node["+team+", "+id+"]"; }
    }

    private static final int MAX_ID = 100_000;
    private static final int MAX_TEAM = 5;
    private static final int MAX_SCORE = 5;

    private Node[] soldiers;
    private Node[][] teamList; // 원형 더블 연결 리스트(순환 구조)

    @Override
    public void init() {
        soldiers = new Node[MAX_ID + 1];
        teamList = new Node[MAX_TEAM + 1][MAX_SCORE + 1];

        for (int team = 1; team <= MAX_TEAM; team++) {
            for (int score = 1; score <= MAX_SCORE; score++) {
                // 각 팀마다, 스코어마다 리스트(헤더) 생성
                Node head = new Node(team, -1);
                head.prev = head;
                head.next = head;
                teamList[team][score] = head;
            }
        }
    }

    @Override
    public void hire(int mID, int mTeam, int mScore) {
//        log("[▶1] hire: mID=%d, mTeam=%d. mScore=%d", mID, mTeam, mScore);
        Node newNode = new Node(mTeam, mID);

        soldiers[mID] = newNode;

        Node list = teamList[mTeam][mScore];

        Node tail = list.prev;
        newNode.prev = tail;
        newNode.next = list;
        tail.next = newNode;
        list.prev = newNode;
//        printSoldier(mTeam);
    }

    @Override
    public void fire(int mID) {
//        log("[▶2] fire: mID=%d", mID);
        Node node = soldiers[mID];
        node.prev.next = node.next;
        node.next.prev = node.prev;

        node.prev = null;
        node.next = null;
        soldiers[mID] = null;
    }

    @Override
    public void updateSoldier(int mID, int mScore) {
//        log("[▶3] updateSoldier: mID=%d, mScore=%d", mID, mScore);
        Node node = soldiers[mID];
        node.prev.next = node.next;
        node.next.prev = node.prev;

        Node list = teamList[node.team][mScore];
        Node tail = list.prev;

        tail.next = node;
        node.prev = tail;
        node.next = list;
        list.prev = node;
    }

    @Override
    public void updateTeam(int mTeam, int mChangeScore) {
//        log("[▶4] updateTeam: mTeam=%d, mChangeScore=%d", mTeam, mChangeScore);
        if (mChangeScore == 0)
            return;

        if (mChangeScore > 0) { // 점수 올리기
            for (int fromScore = MAX_SCORE - 1; fromScore >= 1; fromScore--) {
                int toScore = Math.min(fromScore + mChangeScore, 5);

                moveList(mTeam, fromScore, toScore);
            }
        } else { // 점수 내리기
            for (int fromScore = 2; fromScore <= MAX_SCORE; fromScore++) {
                int toScore = Math.max(fromScore + mChangeScore, 1);

                moveList(mTeam, fromScore, toScore);
            }
        }
    }

    @Override
    public int bestSoldier(int mTeam) {
//        log("[▶5] bestSoldier: mTeam=%d", mTeam);
//        printSoldier(mTeam);

        int maxId = 0;

        for (int score = MAX_SCORE; score >= 1; score--) {
            Node list = teamList[mTeam][score];

            if (list.next.id == -1) // empty list
                continue;

            Node node = list.next;

            while (node.id != -1) {
                if (maxId < node.id)
                    maxId = node.id;

                node = node.next;
            }

            break;
        }

        return maxId;
    }

    private void moveList(int team, int fromScore, int toScore) {
        Node fromList = teamList[team][fromScore];
        Node fromHead = fromList.next;
        Node fromTail = fromList.prev;

        if (fromHead.id == -1) // empty list
            return;

        Node toList = teamList[team][toScore];
        Node toTail = toList.prev;

        // to score list 의 꼬리에 이어 붙이기
        toTail.next = fromHead;
        fromHead.prev = toTail;
        fromTail.next = toList;
        toList.prev = fromTail;

        // from score list clear
        fromList.prev = fromList;
        fromList.next = fromList;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    private void printSoldier(int team) {
        log("    printSolider(team=%d)", team);
        for (int score = 1; score <= MAX_SCORE; score++) {
            log("        score=%d", score);
            Node node = teamList[team][score].next;
            if (node.id == -1)
                continue;

            while (node.id != -1) {
                log("            node=%s", node);
                node = node.next;
            }
        }
        log("");
    }
}
