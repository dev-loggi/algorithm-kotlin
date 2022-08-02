package swExpertAcademy.codeBattle;

import swExpertAcademy._inputs.InputFile;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * [No. 8] 13501. 수열 편집
 *
 * 시간   : 10개 테스트 케이스를 합쳐서 C++의 경우 1초 / Java 의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_CodeBattle_P8 {

    public static class Command {
        public static final char INSERT = 'I';
        public static final char DELETE = 'D';
        public static final char CHANGE = 'C';

        char type;
        int index;
        int data;
    }

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(InputFile.number(8)));

        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine()); // 1 ≤ T ≤ 50

        for (int test_case = 1; test_case <= T; test_case++) {
            String[] inputs = sc.nextLine().split(" ");

            int N = Integer.parseInt(inputs[0]); // 수열의 길이 (5 ≤ N ≤ 1000)
            int M = Integer.parseInt(inputs[1]); // 명령어 갯수 (1 ≤ M ≤ 1000)
            int L = Integer.parseInt(inputs[2]); // 출력할 인덱스 번호 (6 ≤ L ≤ N + M)

            LinkedList<Integer> sequence = Arrays
                    .stream(sc.nextLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(LinkedList::new));

            Command[] commands = new Command[M];

            for (int i = 0; i < M; i++) {
                String[] commandLine = sc.nextLine().split(" ");

                Command cmd = new Command();
                cmd.type = commandLine[0].charAt(0);
                cmd.index = Integer.parseInt(commandLine[1]);
                if (cmd.type != Command.DELETE) {
                    cmd.data = Integer.parseInt(commandLine[2]);
                }

                commands[i] = cmd;
            }

            int answer = solution(N, M, L, sequence, commands);
            System.out.printf("#%d %d\n", test_case, answer);
        }

        sc.close();
    }

    public static int solution(
            int N, int M, int L,
            LinkedList<Integer> sequence, Command[] commands
    ) {
        for (Command command : commands) {
            switch (command.type) {
                case Command.INSERT:
                    sequence.add(command.index, command.data);
                    break;
                case Command.DELETE:
                    sequence.remove(command.index);
                    break;
                case Command.CHANGE:
                    sequence.set(command.index, command.data);
                    break;
            }
        }

        if (L < sequence.size()) {
            return sequence.get(L);
        } else {
            return -1;
        }
    }
}