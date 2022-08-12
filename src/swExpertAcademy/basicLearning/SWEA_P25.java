package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.io.FileInputStream;
import java.util.*;

/**
 * [No. 25] 2948. 문자열 교집합
 *
 * 시간 : 10개 테스트케이스를 합쳐서 C의 경우 4초 / C++의 경우 4초 / Java의 경우 8초 / Python의 경우 16초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P25 {

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(25)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] info = sc.nextLine().split(" ");
            int N = Integer.parseInt(info[0]); // (1 ≤ N ≤ 10^5)
            int M = Integer.parseInt(info[1]); // (1 ≤ M ≤ 10^5)

            // 각 문자열 원소의 길이 최댓값: 50
            String[] set1 = sc.nextLine().split(" ");
            String[] set2 = sc.nextLine().split(" ");

            int answer = solution(set1, set2);

            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    private static int solution(String[] arrA, String[] arrB) {
        HashSet<String> setB = new HashSet<>(Arrays.asList(arrB));

        int count = 0;
        for (String s : arrA) {
            if (setB.contains(s))
                count++;
        }

        return count;
    }
}
