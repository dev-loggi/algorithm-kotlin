package programmers.mockExam;

import java.util.ArrayDeque;
import java.util.Deque;

public class Pro_MockExam1_3 {

//        int[] a = {4, 3, 1, 2, 5};
//        int[] b = {5, 4, 3, 2, 1};

    public void main() {
        int answer;

        answer = solution(new int[] {4, 3, 1, 2, 5});
        System.out.printf("answer=%d\n", answer); // 2

        answer = solution(new int[] {5, 4, 3, 2, 1});
        System.out.printf("answer=%d\n", answer); // 5
    }

    public int solution(int[] item) {
        int[] order = new int[item.length];

        for (int i = 0; i < item.length; i++) {
            order[item[i] - 1] = i + 1;
        }

        Deque<Integer> container = new ArrayDeque<>(); // 큐
        Deque<Integer> subContainer = new ArrayDeque<>(); // 스택

        for (int n : order) {
            container.offer(n);
        }

        int idx = 1;

        while (true) {
            if (!container.isEmpty()) { // 컨테이너에 물건 남음
                Integer a = container.peek();

                if (a == idx) {
                    container.poll();
                    idx++;
                } else {
                    Integer b = subContainer.peek();

                    if (b == null || b != idx) {
                        subContainer.push(container.poll());
                    } else {
                        subContainer.pop();
                        idx++;
                    }
                }
            } else { // 컨테이너 비었음
                Integer b = subContainer.peek();

                if (b == null || b != idx) {
                    break;
                } else {
                    subContainer.pop();
                    idx++;
                }
            }

            log("idx=%d", idx);
            log("container=    %s", container);
            log("subContainer= %s", subContainer);
            log("");
        }

        return idx - 1;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}
