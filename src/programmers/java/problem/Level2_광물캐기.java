package programmers.java.problem;

import programmers.java.JavaSolution;

import java.util.*;

public class Level2_광물캐기 extends JavaSolution {

    private int[][] stress = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1},
    };

    @Override
    public void execute() {
        int answer;
        answer = solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}); // 12
        println("answer=%d", answer);
        answer = solution(new int[]{0, 1, 1}, new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}); // 50
        println("answer=%d", answer);
    }

    public int solution(int[] picks, String[] minerals) {
        // 1. 보유한 곡괭이로 캘 수 있는 광물 수 세기
        int tempSum = Arrays.stream(picks).sum() * 5;
        int availableCount = Math.min(minerals.length, tempSum);

        // 2. 광물을 숫자로 매핑
        int[] aMinerals = new int[availableCount];
        for (int i = 0; i < aMinerals.length; i++) {
            aMinerals[i] = switch (minerals[i]) {
                case "diamond" -> 0;
                case "iron" -> 1;
                case "stone" -> 2;
                default -> throw new IllegalStateException();
            };
        }

        // 3. 광물을 한 그룹 당 5개씩 구분지어 피로도 점수 구하기
        int[] sum = new int[(int) Math.ceil(aMinerals.length / 5.0)];
        for (int i = 0; i < aMinerals.length; i += 5) {
            int value = 0;
            for (int j = 0; j < 5 && i + j < aMinerals.length; j++) {
                int mineral = aMinerals[i + j];
                if (mineral == 0) {
                    value += 25;
                } else if (mineral == 1) {
                    value += 5;
                } else {
                    value += 1;
                }
            }
            sum[i / 5] = value;
        }

        // 4. 그룹별 피로도 순위 구하기
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < sum.length; i++) {
            minHeap.offer(new Node(sum[i], i));
        }

        int[] rank = new int[minHeap.size()];
        for (int i = 0; i < sum.length; i++) {
            Node node = minHeap.poll();
            rank[node.index] = i;
        }

        // 5. 피로도 순위별 곡괭이 사용 및 총 피로도 계산
        int answer = 0;
        for (int i = 0; i < aMinerals.length; i += 5) {
            int pick = getPick(picks, rank[i / 5] + 1);
            for (int j = 0; j < 5 && i + j < aMinerals.length; j++) {
                answer += stress[pick][aMinerals[i + j]];
            }
        }

        return answer;
    }

    private int getPick(int[] picks, int n) {
        int acc = 0;
        for (int i = 0; i < picks.length; i++) {
            if (n <= acc + picks[i]) {
                return i;
            } else {
                acc += picks[i];
            }
        }
        return -1;
    }

    class Node implements Comparable<Node> {

        int value, index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node o) {
            if (value != o.value) {
                return value - o.value;
            } else {
                return index - o.index;
            }
        }
    }
}