package programmers.java.problem;

import programmers.java.JavaSolution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Level2_광물캐기2 extends JavaSolution {

    private static final int[][] Stress = {{1, 1, 1},{5, 1, 1},{25, 5, 1}};
    private static final Map<String, Integer> Mineral = new HashMap<>();

    private int[] picks;
    private Integer[] minerals;

    static {
        Mineral.put("diamond", 0);
        Mineral.put("iron", 1);
        Mineral.put("stone", 2);
    }

    public int solution(int[] picks, String[] minerals) {
        this.picks = picks;
        this.minerals = Arrays.stream(minerals).map(Mineral::get).toArray(Integer[]::new);



        return 0;
    }

    private void dfs() {

    }

    @Override
    public void execute() {
        int answer;
        answer = solution(new int[]{1, 3, 2}, new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"}); // 12
        println("answer=%d", answer);
        answer = solution(new int[]{0, 1, 1}, new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"}); // 50
        println("answer=%d", answer);
    }
}