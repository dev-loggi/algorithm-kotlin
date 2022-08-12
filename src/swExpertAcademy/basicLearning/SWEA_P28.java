package swExpertAcademy.basicLearning;

import swExpertAcademy.basicLearning._inputs.InputFile;

import java.util.*;

/**
 * [No. 28] 6134. 연락처 DataBase
 *
 * 시간 : 0개 테스트케이스를 합쳐서 C++의 경우 5초 / Java의 경우 5초 / Python의 경우 10초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 * */
public class SWEA_P28 {

    private static class UserSolution {

        private static final int MAX_RECORD = 50_000;

        private int recordIdx = -1;
        private String[][] records;
//        private HashMap<String, LinkedList<Integer>> indexMapping;

        private HashMap<String, LinkedList<Integer>> nameIndexMapping;
        private HashMap<String, LinkedList<Integer>> numberIndexMapping;
        private HashMap<String, LinkedList<Integer>> birthIndexMapping;
        private HashMap<String, LinkedList<Integer>> emailIndexMapping;
        private HashMap<String, LinkedList<Integer>> memoIndexMapping;
        private boolean[] removed;

        void InitDB() {
            recordIdx = -1;
            records = new String[MAX_RECORD][5];
//            indexMapping = new HashMap<>(MAX_RECORD * 5);
            removed = new boolean[MAX_RECORD];

            nameIndexMapping = new HashMap<>(MAX_RECORD);
            numberIndexMapping = new HashMap<>(MAX_RECORD);
            birthIndexMapping = new HashMap<>(MAX_RECORD);
            emailIndexMapping = new HashMap<>(MAX_RECORD);
            memoIndexMapping = new HashMap<>(MAX_RECORD);
        }

        void Add(String name, String number, String birthday, String email, String memo) {
            recordIdx += 1;

            records[recordIdx][0] = name;
            records[recordIdx][1] = number;
            records[recordIdx][2] = birthday;
            records[recordIdx][3] = email;
            records[recordIdx][4] = memo;

            LinkedList<Integer> indices;

            indices = nameIndexMapping.getOrDefault(name, new LinkedList<>());
            indices.add(recordIdx);
            nameIndexMapping.put(name, indices);

            indices = numberIndexMapping.getOrDefault(number, new LinkedList<>());
            indices.add(recordIdx);
            numberIndexMapping.put(number, indices);

            indices = birthIndexMapping.getOrDefault(birthday, new LinkedList<>());
            indices.add(recordIdx);
            birthIndexMapping.put(birthday, indices);

            indices = emailIndexMapping.getOrDefault(email, new LinkedList<>());
            indices.add(recordIdx);
            emailIndexMapping.put(email, indices);

            indices = memoIndexMapping.getOrDefault(memo, new LinkedList<>());
            indices.add(recordIdx);
            memoIndexMapping.put(memo, indices);

//            System.out.printf("Add(): name=%s, number=%s, birthday=%s, email=%s, memo=%s, mappingSize=%d\n",
//                    name, number, birthday, email, memo, indexMapping.size());
        }

        int Delete(int field, String str) {
            LinkedList<Integer> indices = removeIndexMappingBy(field, str);

            if (indices == null)
                return 0;

            for (int i : indices) {
                removed[i] = true;
            }

            return indices.size();
        }

        int Change(int field, String str, int changeField, String changeStr) {
            if (field == changeField && str.equals(changeStr))
                return 0;

            int count = 0;

            LinkedList<Integer> indices = getIndexMapping(field, str);

            if (indices == null)
                return 0;

            for (int i : indices) {
                if (removed[i]) continue;

                //removeIndexMappingBy(changeField, records[i][changeField]);
                LinkedList<Integer> value1 = getIndexMapping(changeField, records[i][changeField]);

                if (value1 != null) {
                    value1.remove((Integer) i);
                }

                LinkedList<Integer> value2 = getIndexMapping(changeField, changeStr);
                if (value2 == null) {
                    value2 = new LinkedList<>();
                }

                value2.add(i);

                putIndexMapping(changeField, changeStr, value2);

                records[i][changeField] = changeStr;

                count += 1;
            }

            return count;
        }

        Result Search(int field, String str, int returnField) {
            Result result = new Result();
            result.count = 0;

            LinkedList<Integer> indices = getIndexMapping(field, str);

            if (indices == null)
                return result;

            for (int i : indices) {
                if (removed[i]) continue;

                result.count += 1;

                if (result.count == 1) {
                    result.str = records[i][returnField];
                }
            }

            return result;
        }

        private LinkedList<Integer> getIndexMapping(int field, String data) {
            switch (field) {
                case Field.NAME: return nameIndexMapping.get(data);
                case Field.NUMBER: return numberIndexMapping.get(data);
                case Field.BIRTHDAY: return birthIndexMapping.get(data);
                case Field.EMAIL: return emailIndexMapping.get(data);
                case Field.MEMO: return memoIndexMapping.get(data);
            }
            throw new IllegalArgumentException("field=" + field);
        }

        private void putIndexMapping(int field, String key, LinkedList<Integer> value) {
            switch (field) {
                case Field.NAME: nameIndexMapping.put(key, value); return;
                case Field.NUMBER: numberIndexMapping.put(key, value); return;
                case Field.BIRTHDAY: birthIndexMapping.put(key, value); return;
                case Field.EMAIL: emailIndexMapping.put(key, value); return;
                case Field.MEMO: memoIndexMapping.put(key, value); return;
            }
            throw new IllegalArgumentException("field=" + field);
        }

        private LinkedList<Integer> removeIndexMappingBy(int field, String data) {
            switch (field) {
                case Field.NAME: return nameIndexMapping.remove(data);
                case Field.NUMBER: return numberIndexMapping.remove(data);
                case Field.BIRTHDAY: return birthIndexMapping.remove(data);
                case Field.EMAIL: return emailIndexMapping.remove(data);
                case Field.MEMO: return memoIndexMapping.remove(data);
            }
            throw new IllegalArgumentException("field=" + field);
        }

        public void test() {
//            int removedCount = 0;
//            for (int i=0; i<=recordIdx; i++) if (removed[i]) removedCount++;
//            System.out.printf("recordIdx=%d, removedCount=%d ", recordIdx, removedCount);
//            for (Map.Entry<String, LinkedList<Integer>> entry : indexMapping.entrySet()) {
//                StringBuilder sb = new StringBuilder();
//                for (int i : entry.getValue()) sb.append(Arrays.toString(records[i]));
//                System.out.printf("%s, \t%s\n", entry, sb);
//            }
        }
        public void test2() {
//            for (int i=0; i<=recordIdx; i++) {
//                if (!removed[i]) continue;
//
//                StringBuilder sb = new StringBuilder();
//                sb.append('[');
//                for (int f=0; f<5; f++) sb.append(indexMapping.get(records[i][f])).append(", ");
//                sb.append(']');
//
//                System.out.printf("removed[%d]: \tmapping=%s, \trecord=%s\n", i, sb, Arrays.toString(records[i]));
//            }
        }
    }

    interface Field {
        public final static int NAME     = 0;
        public final static int NUMBER   = 1;
        public final static int BIRTHDAY = 2;
        public final static int EMAIL    = 3;
        public final static int MEMO     = 4;
    }

    private final static int CMD_INIT   = 0;
    private final static int CMD_ADD    = 1;
    private final static int CMD_DELETE = 2;
    private final static int CMD_CHANGE = 3;
    private final static int CMD_SEARCH = 4;

    static class Result {
        public int count;
        public String str;
    }

    private static Scanner sc;
    private static UserSolution userSolution = new UserSolution();

    private static int Score;
    private static int ScoreIdx;
    private static String name, number, birthday, email, memo;

    private static String lastname[] = { "kim", "lee", "park", "choi", "jung", "kang", "cho", "oh", "jang", "lim" };
    private static int lastname_length[] = { 3, 3, 4, 4, 4, 4, 3, 2, 4, 3 };
    private static int cmd_count[];
    private static int cmd_incorrect[];

    private static int mSeed;
    private static int mrand(int num) {
        mSeed = mSeed * 1103515245 + 37209;
        if (mSeed < 0) mSeed *= -1;
        return ((mSeed >> 8) % num);
    }

    private static void make_field(int seed) {
        StringBuilder sbname = new StringBuilder();
        StringBuilder sbnumber = new StringBuilder();
        StringBuilder sbbirthday = new StringBuilder();
        StringBuilder sbemail = new StringBuilder();
        StringBuilder sbmemo = new StringBuilder();

        int name_length, email_length, memo_length;
        int num;

        mSeed = seed;

        name_length = 6 + mrand(10);
        email_length = 10 + mrand(10);
        memo_length = 5 + mrand(10);

        num = mrand(10);
        sbname.append(lastname[num]);
        for (int i = 0; i < name_length - lastname_length[num]; i++) sbname.append((char)('a' + mrand(26)));

        for (int i = 0; i < memo_length; i++) sbmemo.append((char)('a' + mrand(26)));

        for (int i = 0; i < email_length - 6; i++) sbemail.append((char)('a' + mrand(26)));
        sbemail.append("@");
        sbemail.append((char)('a' + mrand(26)));
        sbemail.append(".com");

        sbnumber.append("010");
        for (int i = 0; i < 8; i++) sbnumber.append((char)('0' + mrand(10)));

        sbbirthday.append("19");
        num = mrand(100);
        sbbirthday.append((char)('0' + num / 10));
        sbbirthday.append((char)('0' + num % 10));
        num = 1 + mrand(12);
        sbbirthday.append((char)('0' + num / 10));
        sbbirthday.append((char)('0' + num % 10));
        num = 1 + mrand(30);
        sbbirthday.append((char)('0' + num / 10));
        sbbirthday.append((char)('0' + num % 10));

        name = sbname.toString();
        number = sbnumber.toString();
        birthday = sbbirthday.toString();
        email = sbemail.toString();
        memo = sbmemo.toString();
    }

    private static void cmd_init() {
        ScoreIdx = Integer.parseInt(sc.next());

        userSolution.InitDB();
    }

    private static void cmd_add() {
        int seed = Integer.parseInt(sc.next());

        make_field(seed);

        userSolution.Add(name, number, birthday, email, memo);
        cmd_count[0]++;
    }

    private static void cmd_delete() {
        int field = Integer.parseInt(sc.next());
        String str = sc.next();
        int check = Integer.parseInt(sc.next());

        int result = userSolution.Delete(field, str);
        if (result != check) {
            cmd_incorrect[1]++;
            Score -= ScoreIdx;
        }
        cmd_count[1]++;
    }

    private static void cmd_change() {
        int field = Integer.parseInt(sc.next());
        String str = sc.next();
        int changefield = Integer.parseInt(sc.next());
        String changestr = sc.next();
        int check = Integer.parseInt(sc.next());

        int result = userSolution.Change(field, str, changefield, changestr);
        if (result != check) {
            cmd_incorrect[2]++;
            Score -= ScoreIdx;
        }
        cmd_count[2]++;
    }

    private static void cmd_search() {
        int field = Integer.parseInt(sc.next());
        String str = sc.next();
        int returnfield = Integer.parseInt(sc.next());
        String checkstr = sc.next();
        int check = Integer.parseInt(sc.next());

        Result result = userSolution.Search(field, str, returnfield);
        if (result.count != check || (result.count == 1 && !checkstr.equals(result.str))) {
            cmd_incorrect[3]++;
            Score -= ScoreIdx;
        }
        cmd_count[3]++;
    }

    private static void run() {
        int N = Integer.parseInt(sc.next());
        for (int i = 0; i < N; i++) {
            int cmd = Integer.parseInt(sc.next());
            switch (cmd) {
                case CMD_INIT:   cmd_init(); break;
                case CMD_ADD:    cmd_add(); break;
                case CMD_DELETE: cmd_delete(); break;
                case CMD_CHANGE: cmd_change(); break;
                case CMD_SEARCH: cmd_search(); break;
                default: break;
            }
        }
    }

    private static void init() {
        Score = 1000;
        ScoreIdx = 1;
        cmd_count = new int[4];
        cmd_incorrect = new int[4];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new java.io.FileInputStream(InputFile.number(28)));

        sc = new Scanner(System.in);

        int T = sc.nextInt();
        int TotalScore = 0;
        //T = 5;
        for (int tc = 1; tc <= T; tc++) {
            init();

            run();

            if (Score < 0)
                Score = 0;

            TotalScore += Score;

            //System.out.println("#" + tc + " " + Score);
            System.out.printf("#" + tc + " " + Score + " => count=%s, incorrect=%s\n", Arrays.toString(cmd_count), Arrays.toString(cmd_incorrect));
            //if (tc == 31) userSolution.test2();
        }
        System.out.println("TotalScore = " + TotalScore);
        sc.close();
    }
}