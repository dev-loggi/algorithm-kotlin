package programmers.java.problem;

import programmers.java.JavaSolution;

import java.util.*;

public class HallOfFame1 extends JavaSolution {

    @Override
    public void execute() {
        int[] answer;

        answer = bestSolution(3, new int[] {10, 100, 20, 150, 1, 100, 200});
        println("answer=%s", Arrays.toString(answer)); // [10, 10, 10, 20, 20, 100, 100]

        answer = bestSolution(4, new int[] {0, 300, 40, 300, 20, 70, 150, 50, 500, 1000});
        println("answer=%s", Arrays.toString(answer)); // [0, 0, 0, 0, 20, 40, 70, 70, 150, 300]
    }

    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];
        TreeSet<Singer> ranker = new TreeSet<>();

        for (int i = 0; i < score.length; i++) {
            ranker.add(new Singer(i, score[i]));

            answer[i] = rankerScoreOf(ranker, k);
        }

        return answer;
    }

    private int rankerScoreOf(TreeSet<Singer> ranker, int k) {
        int score = -1;
        Iterator<Singer> it = ranker.iterator();

        for (int i = 0; i < k && it.hasNext(); i++) {
            score = it.next().score;
        }

        return score;
    }

    static class Singer implements Comparable<Singer> {
        int id, score;

        public Singer(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Singer other) {
            if (score != other.score) {
                return other.score - score;
            } else {
                return id - other.id;
            }
        }
    }

    public int[] bestSolution(int k, int[] score) {
        int[] answer = new int[score.length];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < score.length; i++) {
            minHeap.offer(score[i]);

            if (minHeap.size() > k) {
                minHeap.poll();
            }

            answer[i] = minHeap.peek();
        }

        return answer;
    }

    public int[] solution2(int k, int[] score) {
        ArrayList<Integer> ranker = new ArrayList<>();

        int[] answer = new int[score.length];

        for (int i = 0; i < score.length; i++) {
            setRank(ranker, k, score[i]);

            if (ranker.size() < k) {
                answer[i] = ranker.get(ranker.size() - 1);
            } else {
                answer[i] = ranker.get(k - 1);
            }
        }

        return answer;
    }

    private void setRank(ArrayList<Integer> ranker, int k, int score) {
        if (ranker.isEmpty()) {
            ranker.add(score);
            return;
        }

        for (int i = 0; i < ranker.size() && i < k; i++) {
            if (ranker.get(i) < score) {
                ranker.add(i, score);
                break;
            }
        }

        if (ranker.size() > k)
            ranker.remove(k);
    }
}
