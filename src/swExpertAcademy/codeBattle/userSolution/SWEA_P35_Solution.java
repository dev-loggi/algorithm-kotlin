package swExpertAcademy.codeBattle.userSolution;

import java.util.Objects;
import java.util.PriorityQueue;

/**
 * [No. 35] 10806. 수 만들기
 *
 * 시간 : 100개 테스트케이스를 합쳐서 C의 경우 1초 / C++의 경우 1초 / Java의 경우 2초 / Python의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * 참고: https://nankisu.tistory.com/38
 * */
public class SWEA_P35_Solution {

    private static class Node implements Comparable<Node> {
        int count, k;

        public Node(int count, int k) {
            this.count = count; this.k = k;
        }

        @Override
        public int compareTo(Node o) {
            return count - o.count;
        }
    }

    public int solution(int[] numbers, int N, int K) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Node(0, K));

        while (!priorityQueue.isEmpty() && priorityQueue.peek().k != 0) {
            Node node = priorityQueue.poll();

            priorityQueue.offer(new Node(node.count + node.k, 0));

            for (int i = 0; i < N; i++) {
                priorityQueue.offer(new Node(node.count + node.k % numbers[i], node.k / numbers[i]));
            }
        }

        return Objects.requireNonNull(priorityQueue.peek()).count;
    }
}