package swExpertAcademy.basic;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * [No. 6] 1230. [S/W 문제해결 기본] 8일차 - 암호문3
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초 / Python의 경우 4초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P06 {

    private static final String INPUT_FILE = "src/swExpertAcademy/_inputs/input_p6.txt";
    private static final int LENGTH = 10;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream(INPUT_FILE));

        Scanner sc = new Scanner(System.in);

        for (int T = 1; sc.hasNext(); T++) {
            int length = Integer.parseInt(sc.nextLine());

            String[] sourceLine = sc.nextLine().split(" ");
            LinkedList<String> source = new LinkedList<>(Arrays.asList(sourceLine));

            int commandSize = Integer.parseInt(sc.nextLine());

            String[] commandLine = sc.nextLine().split(" ");
            ArrayList<String[]> commands = new ArrayList<>();

            for (int i = 0; i < commandLine.length; i++) {
                String type = commandLine[i];

                switch (type) {
                    case "I": {
                        int x = Integer.parseInt(commandLine[i + 1]);
                        int y = Integer.parseInt(commandLine[i + 2]);

                        String[] cmd = new String[y + 3];
                        cmd[0] = type;
                        cmd[1] = String.valueOf(x);
                        cmd[2] = String.valueOf(y);

                        for (int j = 0; j < y; j++) {
                            cmd[3 + j] = commandLine[i + 3 + j];
                        }

                        commands.add(cmd);
                        i += 2 + y;
                        break;
                    }
                    case "D": {
                        int x = Integer.parseInt(commandLine[i + 1]);
                        int y = Integer.parseInt(commandLine[i + 2]);

                        String[] cmd = new String[3];
                        cmd[0] = type;
                        cmd[1] = String.valueOf(x);
                        cmd[2] = String.valueOf(y);

                        commands.add(cmd);
                        i += 2;
                        break;
                    }
                    case "A": {
                        int y = Integer.parseInt(commandLine[i + 1]);

                        String[] cmd = new String[y + 2];
                        cmd[0] = type;
                        cmd[1] = String.valueOf(y);

                        for (int j = 0; j < y; j++) {
                            cmd[2 + j] = commandLine[i + 2 + j];
                        }

                        commands.add(cmd);
                        i += 1 + y;
                    }
                }
            }

            String answer = solution(source, commands);
            System.out.printf("#%d %s\n", T, answer);
        }

        sc.close();
    }

    private static String solution(LinkedList<String> source, ArrayList<String[]> commands) {
        for (String[] cmd : commands) {
            String type = cmd[0];

            switch (type) {
                case "I": { // 삽입
                    int x = Integer.parseInt(cmd[1]);
                    int y = Integer.parseInt(cmd[2]);

                    if (source.size() <= x)
                        break;

                    for (int i = cmd.length - 1; i > 2; i--) {
                        source.add(x, cmd[i]);
                    }

                    break;
                }
                case "D": { // 삭제
                    int x = Integer.parseInt(cmd[1]);
                    int y = Integer.parseInt(cmd[2]);

                    for (int i = 0; i < y && x < source.size(); i++) {
                        source.remove(x);
                    }

                    break;
                }
                case "A": { // 추가
                    int y = Integer.parseInt(cmd[1]);

                    for (int i = 0; i < y; i++) {
                        source.removeLast();
                    }

                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (String s : source) {
            if (count++ == 10)
                break;

            sb.append(s).append(" ");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
