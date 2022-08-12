package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.*;

/**
 * [No. 24] 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
 *
 * 시간 : 15개 테스트케이스를 합쳐서 C++의 경우 10초 / Java의 경우 20초 / Python의 경우 30초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * Greedy
 * */
public class SWEA_CodeBattle_P24 {

    private static int answer;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(24)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] input = sc.nextLine().split(" ");

            char[] number = input[0].toCharArray(); // 최대 6자리 숫자
            int K = Integer.parseInt(input[1]); // 교환 횟수 (K ≤ 10)

            solution(number, K);

            System.out.printf("#%d %s\n", test_case, answer);
        }

        sc.close();
    }

    private static void solution(char[] number, int K) {
        answer = 0;

        dfs(number, 0, K);
    }

    private static void dfs(char[] arr, int idx, int k) {
        if (k == 0) {
            answer = Math.max(answer, Integer.parseInt(new String(arr)));
            return;
        }

        int cnt = 0;
        for (int i = idx; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] <= arr[j]) {
                    swap(arr, i, j);
                    dfs(arr, i, k - 1);
                    swap(arr, i, j);
                    cnt++;
                }
            }
        }

        if (cnt == 0) {
            if (k % 2 == 1) {
                swap(arr, arr.length - 2, arr.length - 1);
                answer = Math.max(answer, Integer.parseInt(new String(arr)));
                swap(arr, arr.length - 2, arr.length - 1);
            } else {
                answer = Math.max(answer, Integer.parseInt(new String(arr)));
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}