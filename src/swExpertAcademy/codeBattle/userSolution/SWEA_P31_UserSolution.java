package swExpertAcademy.codeBattle.userSolution;

public class SWEA_P31_UserSolution {

    class Node {
        int id, income;
        Node prev, next;

        public Node(int id, int income) {
            this.id = id;
            this.income = income;
        }

        public boolean isGreaterThan(Node other) {
            return (income > other.income) || (income == other.income && id < other.id);
        }
    }

    private static final int MAX = 10;

    private Node head, tail;
    int size = 0;

    public void init() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * user 추가하는 함수
     *
     * @param uID user id, 0부터 시작해서 순차적으로 증가한다 (0 ≤ uID < 100000)
     * @param income user의 수입, 클수록 우선순위가 높다. 만약 수입이 동일한 경우 uID가 작은 user 의 우선순위가 높다.
     * */
    public void addUser(int uID, int income) {
        Node node = new Node(uID, income);

        if (size == 0) {
            head = node;
            tail = node;
        } else if (size == 1) {
            if (head.isGreaterThan(node)) {
                tail = node;
            } else {
                head = node;
            }

            head.next = tail;
            tail.prev = head;
        } else {
            Node prev = null;
            Node curr = head;

            while (curr != null) {
                if (node.isGreaterThan(curr)) {
                    if (prev == null) {
                        head = node;
                    } else {
                        prev.next = node;
                        node.prev = prev;
                    }

                    node.next = curr;
                    curr.prev = node;
                    break;
                } else if (curr == tail) {
                    tail = node;

                    curr.next = node;
                    node.prev = curr;
                    break;
                }

                prev = curr;
                curr = curr.next;
            }
        }

        size += 1;

        if (size == 11) {
            size = 10;

            Node removed = tail;

            tail = tail.prev;
            tail.next = null;

            removed.prev = null;
            removed.next = null;
        }
    }

    /**
     * 수입이 가장 큰 user 10명의 uID를 수입에 대해 내림차순으로 구하는 함수이다.<br/>
     * 총 user 의 수가 10명이 되지 않으면 존재하는 user의 uID를 수입에 대해 내림차순으로 구한다.
     *
     * @param result 수입이 큰 순서대로 10개의 uID를 저장한다. (1 ≤ result 개수 ≤ 10)
     * @return result 의 개수를 반환한다.
     * */
    public int getTop10(int[] result) {
        Node node = head;

        int idx = 0;
        for (idx = 0; idx < 10 && node != null; idx++) {
            result[idx] = node.id;
            node = node.next;
        }

        return idx;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s, args);
    }

    private void logln(String s, Object ... args) {
        log(s + "\n", args);
    }

}
