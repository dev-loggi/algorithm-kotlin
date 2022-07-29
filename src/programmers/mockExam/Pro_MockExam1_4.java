package programmers.mockExam;

public class Pro_MockExam1_4 {

    public void main() {
        int answer;

        answer = solution(
                new int[][] {
                        {0,1,0,0,0},
                        {1,0,1,0,1},
                        {0,1,1,1,0},
                        {1,0,1,1,0},
                        {0,1,0,1,0},
                },
                new int[][] {
                        {0,0,0,1,1},
                        {0,0,0,0,1},
                        {0,0,1,0,1},
                        {0,0,0,1,0},
                        {0,0,0,0,1},
                }
        );
        log("answer=%d", answer); // 5

        answer = solution(
                new int[][] {
                        {0,0,0},
                        {0,0,0},
                        {0,0,0},
                },
                new int[][] {
                        {1,0,1},
                        {0,0,0},
                        {0,0,0},
                }
        );
        log("answer=%d", answer); // -1
    }

    public int solution(int[][] beginning, int[][] target) {

        return -1;
    }

    private void log(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }
}