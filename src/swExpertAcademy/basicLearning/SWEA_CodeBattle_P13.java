package swExpertAcademy.basicLearning;


import swExpertAcademy.basicLearning._inputs.InputFile;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * [No. 13] 9429. Directory
 *
 * 시간   : 10개 테스트케이스를 합쳐서 C++의 경우 1초 / Java의 경우 2초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AXAS3N-KBz0DFAXq&categoryId=AYIPD136YD8DFATO&categoryType=BATTLE&battleMainPageIndex=1
 * */
public class SWEA_CodeBattle_P13 {

    private static final UserSolution usersolution = new UserSolution();

    private final static int CMD_MKDIR		= 1;
    private final static int CMD_RM			= 2;
    private final static int CMD_CP			= 3;
    private final static int CMD_MV			= 4;
    private final static int CMD_FIND		= 5;

    private final static int NAME_MAXLEN	= 6;
    private final static int PATH_MAXLEN	= 1999;

    private static boolean run(Scanner sc, int m) throws Exception {

        boolean isAccepted = true;
        int cmd;
        char[] name;
        char[] path1;
        char[] path2;
        String inputStr = "";

        while (m-- > 0) {

            cmd = sc.nextInt();

            if (cmd == CMD_MKDIR) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                name = inputStr.toCharArray();
                name[inputStr.length() - 1] = '\0';

                usersolution.cmd_mkdir(path1, name);
            }
            else if (cmd == CMD_RM) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';

                usersolution.cmd_rm(path1);
            }
            else if (cmd == CMD_CP) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                path2 = inputStr.toCharArray();
                path2[inputStr.length() - 1] = '\0';

                usersolution.cmd_cp(path1, path2);
            }
            else if (cmd == CMD_MV) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                path2 = inputStr.toCharArray();
                path2[inputStr.length() - 1] = '\0';

                usersolution.cmd_mv(path1, path2);
            }
            else {
                int ret;
                int answer;

                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';

                ret = usersolution.cmd_find(path1);

                answer = sc.nextInt();

                isAccepted &= (ret == answer);
            }
        }
        return isAccepted;
    }

    public static void main(String[] args) throws Exception {
        int test, T;
        int n, m;

        System.setIn(new java.io.FileInputStream(InputFile.number(13)));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (test = 1; test <= T; ++test) {
            n = sc.nextInt();
            m = sc.nextInt();

            usersolution.init(n);

            if (run(sc, m)) {
                System.out.println("#" + test + " 100");
            }
            else {
                System.out.println("#" + test + " 0");
            }
        }

        sc.close();
    }

    public static class UserSolution {

        public static class Directory {
            Directory parent;
            ArrayList<Directory> children = new ArrayList<>(SUB_DIR_CAPACITY);
            String name;

            public int size() {
                return children.size();
            }

            public String path() {
                StringBuilder path = new StringBuilder();
                Directory dir = parent;
                while (dir != null) {
                    path.insert(0, dir.name + "/");
                    dir = dir.parent;
                }
                return path.toString();
            }

            public void add(Directory child) {
                if (children.size() >= SUB_DIR_CAPACITY)
                    return;

                for (Directory dir : children) {
                    if (dir.name.equals(child.name))
                        return;
                }

                child.parent = this;
                children.add(child);
            }

            public void remove(Directory child) {
                children.remove(child);
            }

            public Directory deepCopy() {
                Directory copy = new Directory();
                copy.name = name;
                copy.parent = parent;

                for (Directory child : children) {
                    copy.add(child.deepCopy());
                }

                return copy;
            }

            public void release() {
                for (Directory child : children) {
                    child.release();
                }
                children.clear();
                parent = null;
                name = null;
            }

            public int treeSize() {
                int size = 1;
                for (Directory child : children) {
                    size += child.treeSize();
                }
                return size;
            }
        }

        private static final int NAME_MAX_LEN	= 6;
        private static final int PATH_MAX_LEN	= 1999;
        private static final int SUB_DIR_CAPACITY = 30; // 하위 디렉터리 갯수 제한

        private final Directory rootDirectory = new Directory();

        public void init(int n) {
            rootDirectory.release();
            rootDirectory.name = "/";
        }

        public void cmd_mkdir(char[] path, char[] name) {
            Directory parent = findDirectory(path);
            if (parent == null)
                return;

            Directory dir = new Directory();
            dir.name = string(name);

            parent.add(dir);
        }

        public void cmd_rm(char[] path) {
            Directory dir = findDirectory(path);
            if (dir != null) {
                Directory parent = dir.parent;
                dir.release();
                parent.remove(dir);
            }
        }

        public void cmd_cp(char[] srcPath, char[] dstPath) {
            Directory src = findDirectory(srcPath).deepCopy();
            Directory dst = findDirectory(dstPath);

            if (src == null || dst == null)
                return;

            dst.add(src);
        }

        public void cmd_mv(char[] srcPath, char[] dstPath) {
            Directory src = findDirectory(srcPath);
            Directory dst = findDirectory(dstPath);

            if (src == null || dst == null)
                return;

            src.parent.remove(src);
            dst.add(src);
        }

        public int cmd_find(char[] path) {
            return findDirectory(path).treeSize() - 1;
        }

        public Directory findDirectory(char[] path) {
            String[] dirsInPath = Arrays.stream(string(path).split("/"))
                    .filter(s -> !s.isEmpty())
                    .toArray(String[]::new);

            Directory dir = rootDirectory;

            for (String s : dirsInPath) {

                int childIdx = -1;
                for (int i = 0; i < dir.children.size(); i++) {
                    if (dir.children.get(i).name.equals(s)) {
                        childIdx = i;
                        break;
                    }
                }

                if (childIdx == -1) {
                    dir = null;
                    break;
                }

                dir = dir.children.get(childIdx);
            }
            return dir;
        }

        private String string(char[] chars) {
            return new String(Arrays.copyOfRange(chars, 0, chars.length - 1));
        }

        public void printAllDirectories() {
            log("printAllDirectories(): totalSize=%d\n", rootDirectory.treeSize());
            ArrayDeque<Directory> q = new ArrayDeque<>();
            q.offer(rootDirectory);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i=0; i<size; i++) {
                    Directory dir = q.poll();
                    log(dir + ", ");
                    for (Directory child : dir.children)
                        q.offer(child);
                }
                log("\n");
            }
            log("\n");
        }

        private void log(String s, Object ... args) {
            System.out.printf(s, args);
        }
    }
}