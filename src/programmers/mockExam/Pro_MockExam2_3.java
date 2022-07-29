package programmers.mockExam;

import java.util.*;

public class Pro_MockExam2_3 {

    public void main() {
        int[] answer;

        answer = solution(
                3,
                new int[][] {
                        {1, 2}, {2, 3}
                },
                new int[] {2, 3},
                1
        );
        log("answer=%s", Arrays.toString(answer)); // [1, 2]

        answer = solution(
                5,
                new int[][] {
                        {1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}
                },
                new int[] {1, 3, 5},
                5
        );
        log("answer=%s", Arrays.toString(answer)); // [2, -1, 0]
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        ArrayList<Integer>[] graph = makeGraph(n, roads);

        int[] distances = new int[sources.length];

        for (int i = 0; i < sources.length; i++) {
            distances[i] = bfs(graph, n, sources[i], destination);
        }

        return distances;
    }

    private int bfs(
            ArrayList<Integer>[] graph,
            int n,
            int start,
            int destination
    ) {
        if (start == destination)
            return 0;

        if (graph[start] == null)
            return -1;

        int distance = -1;

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(start);
        visited[start] = true;

        boolean isArrived = false;

        while (!queue.isEmpty()) { // BFS

            distance++;

            int queueSize = queue.size();
            for (int j = 0; j < queueSize; j++) {
                int curr = queue.poll();

                ArrayList<Integer> nextList = graph[curr];
                if (nextList == null)
                    continue;

                for (int next : nextList) {
                    if (visited[next])
                        continue;

                    if (next == destination) {
                        distance++;
                        isArrived = true;
                        break;
                    }

                    visited[next] = true;
                    queue.offer(next);
                }

                if (isArrived)
                    break;
            }

            if (isArrived)
                break;
        }

        return isArrived ? distance : -1;
    }

    private ArrayList<Integer>[] makeGraph(int n, int[][] roads) {
        ArrayList<Integer>[] graph = new ArrayList[n + 1];

        for (int[] road : roads) {
            if (graph[road[0]] == null) {
                graph[road[0]] = new ArrayList<>();
            }
            graph[road[0]].add(road[1]);

            if (graph[road[1]] == null) {
                graph[road[1]] = new ArrayList<>();
            }
            graph[road[1]].add(road[0]);
        }
        return graph;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}