package programmers.practice.level3;

//깊이 우선 탐색 dfs
//인접한 노드 담을때 기준은 알파벳순
//내 기준으로 인접한 노드가 있는 경우만 값에 담기
//한번 쓴 티켓은 삭제
import common.Solution;

import java.util.*;

public class TravelRouteJava implements Solution {
    ArrayList<String> routes = new ArrayList<>();
    String route = "";

    public TravelRouteJava() {}

    @Override
    public void execute() {
        String[] answer;

        // ["ICN", "JFK", "HND", "IAD"]
        String[][] testCase1 = {
                {"ICN", "JFK"},
                {"HND", "IAD"},
                {"JFK", "HND"},
        };
        answer = solution(testCase1);
        System.out.println(Arrays.toString(answer));

        // ["ICN", "ATL", "ICN", "SFO", "ATL", "SFO"]
        String[][] testCase2 = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"},
        };
        answer = solution(testCase2);
        System.out.println(Arrays.toString(answer));

        // ICN - BBB - ICN - BBB - CCC
        String[][] testCase3 = {
                {"ICN", "BBB"},
                {"ICN", "BBB"},
                {"BBB", "ICN"},
                {"BBB", "CCC"},
        };
        answer = solution(testCase3);
        System.out.println(Arrays.toString(answer));
    }

    public String[] solution(String[][] tickets) {
        routes.clear();
        route = "";

        boolean[] soldout = new boolean[tickets.length];
        dfs(tickets, "ICN", soldout, 0);
        Collections.sort(routes);
        route = routes.get(0);
        String[] answer = route.split(" ");
        return answer;
    }

    private void dfs(String[][] tickets, String start, boolean[] soldout, int depth) {
        route += start + " ";
        System.out.println(route);

        if (depth == tickets.length) {
            routes.add(route);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!soldout[i] && tickets[i][0].equals(start)) {
                soldout[i] = true;
                dfs(tickets, tickets[i][1], soldout, depth + 1);
                route = route.substring(0, route.length() - 4);
                soldout[i] = false;
            }
        }
    }
}
