package swExpertAcademy.practicalTraining;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [No. 8] 14613. [Pro] 리스트 복사 (8/22)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * <br/><br/>[제약사항]<br/><br/>
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 makeList() 함수의 호출 횟수는 10 이하이다.<br/>
 * 3. 각 테스트 케이스에서 copyList() 함수의 호출 횟수는 5,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 updateElement() 함수의 호출 횟수는 100,000 이하이다.<br/>
 * 5. 각 테스트 케이스에서 element() 함수의 호출 횟수는 400 이하이다.<br/>
 * 6. 리스트의 이름은 길이 1 이상, 20 이하의 알파벳 소문자로 이루어진 문자열이며, 문자열의 끝에는 ‘＼0’이 추가되어 있다.<br/>
 * 7. 메모리 제한은 256MB 이다.<br/>
 *
 * Trie, HashMap, List, Reference, DeepCopy
 * */
public class SWEA_PRO_P08 {

    interface UserSolution {
        /**
         * 각 테스트 케이스의 처음에 호출된다.
         * 테스트 케이스의 시작 시 생성되어 있는 리스트는 없다.
         * */
        void init();

        /**
         * mName 리스트가 생성되어 있지 않음이 보장된다.
         * mName 리스트를 새로 생성한다.
         * mName 리스트의 원소 개수는 mLength 개가 된다.
         * mName 리스트의 i번째 원소의 값은 mListValue[i]가 된다. ( 0 ≤ i ≤ mLength - 1 )
         *
         * 수행해야할 작업을 본문의 예시와 같이 나타내면, 아래와 같다.
         * > mName = [mListValue[0], mListValue[1], …, mListValue[mLength - 1]]
         *
         * @param mName 리스트의 이름 ( 1 ≤ 길이 ≤ 20 )
         * @param mLength 리스트의 길이 ( 1 ≤ mLength ≤ 200,000 )
         * @param mListValue 원소들의 값 ( 0 ≤ mListValue[i] ≤ 32,767 )
         * */
        void makeList(char[] mName, int mLength, int[] mListValue);

        /**
         * mDest 리스트가 생성되어 있지 않음이 보장된다.
         * mSrc 리스트가 생성되어 있음이 보장된다.
         *
         * mDest 리스트를 새로 생성한다.
         * mSrc 리스트를 mDest 리스트에 복사한다.
         *
         * mCopy 가 true 인 경우, 값을 모두 복사하는 방식을 사용한다.
         * 이때 수행해야할 작업을 본문의 예시와 같이 나타내면, 아래와 같다.
         * > mDest = mSrc.copy()
         *
         * mCopy 가 false 인 경우, 주소만 복사하는 방식을 사용한다.
         * 이때 수행해야할 작업을 본문의 예시와 같이 나타내면, 아래와 같다.
         * > mDest = mSrc
         *
         * @param mDest  리스트의 이름 ( 1 ≤ 길이 ≤ 20 )
         * @param mSrc   리스트의 이름 ( 1 ≤ 길이 ≤ 20 )
         * @param mCopy  복사 방식 ( mCopy = true or false )
         * */
        void copyList(char[] mDest, char[] mSrc, boolean mCopy);

        /**
         * mName 리스트의 mIndex 번째 원소의 값을 mValue 로 변경한다.
         *
         * 수행해야할 작업을 본문의 예시와 같이 나타내면, 아래와 같다.
         * > mName[mIndex] = mValue
         *
         * mName 리스트가 생성되어 있음이 보장된다.
         *
         * @param mName  리스트의 이름 ( 1 ≤ 길이 ≤ 20 )
         * @param mIndex 원소의 인덱스 ( 0 ≤ mIndex ≤ mName 리스트의 길이 - 1 )
         * @param mValue 원소의 수정 후의 값 ( 0 ≤ mValue ≤ 32,767 )
         * */
        void updateElement(char[] mName, int mIndex, int mValue);

        /**
         * mName 리스트가 생성되어 있음이 보장된다.
         *
         * @param mName  리스트의 이름 ( 1 ≤ 길이 ≤ 20 )
         * @param mIndex 원소의 인덱스 ( 0 ≤ mIndex ≤ mName 리스트의 길이 - 1 )
         *
         * @return mName 리스트의 mIndex 번째 원소를 반환한다.
         * */
        int element(char[] mName, int mIndex);
    }

    private final static int CMD_INIT			= 100;
    private final static int CMD_MAKE_LIST		= 200;
    private final static int CMD_COPY_LIST		= 300;
    private final static int CMD_UNDATE_ELEMENT	= 400;
    private final static int CMD_ELEMENT		= 500;

    private final static SWEA_PRO_P08_UserSolution userSolution = new SWEA_PRO_P08_UserSolution();
//    private final static SWEA_PRO_P08_UserSolution2 userSolution = new SWEA_PRO_P08_UserSolution2();

    private static int mSeed;
    private static int pseudo_rand()
    {
        mSeed = mSeed * 214013 + 2531011;
        return (mSeed >> 16) & 0x7FFF;
    }

    private static char[] mName = new char[21];
    private static char[] mDest = new char[21];
    private static char[] mSrc = new char[21];
    private static int[] mListValue = new int[200000];

    private static void generateName(char[] name, int seed)
    {
        mSeed = seed;
        int name_len = pseudo_rand() % 20 + 1;
        for (int i = 0; i < name_len; ++i)
        {
            name[i] = (char)(pseudo_rand() % 26 + 'a');
        }
        name[name_len] = '\0';
    }

    private static int generateList(int[] listValue, int seed)
    {
        mSeed = seed;
        int length = pseudo_rand() << 15;
        length = (length + pseudo_rand()) % 200000 + 1;
        for (int i = 0; i < length; ++i)
        {
            listValue[i] = pseudo_rand();
        }
        return length;
    }

    private static boolean run(BufferedReader br, int testcase) throws Exception
    {
        StringTokenizer st;

        int numQuery;

        int seed;
        int mIndex, mValue, mCopy, mLength;
        int userAns, ans;
        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
            {
                case CMD_INIT:
                    userSolution.initTestCase(testcase);
                    userSolution.init();
                    isCorrect = true;
                    break;
                case CMD_MAKE_LIST:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    seed = Integer.parseInt((st.nextToken()));
                    mLength = generateList(mListValue, seed);
                    userSolution.makeList(mName, mLength, mListValue);
                    break;
                case CMD_COPY_LIST:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mDest, seed);
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mSrc, seed);
                    mCopy = Integer.parseInt((st.nextToken()));
                    userSolution.copyList(mDest, mSrc, (mCopy != 0));
                    break;
                case CMD_UNDATE_ELEMENT:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    mIndex = Integer.parseInt((st.nextToken()));
                    mValue = Integer.parseInt((st.nextToken()));
                    userSolution.updateElement(mName, mIndex, mValue);
                    break;
                case CMD_ELEMENT:
                    seed = Integer.parseInt((st.nextToken()));
                    generateName(mName, seed);
                    mIndex = Integer.parseInt((st.nextToken()));
                    userAns = userSolution.element(mName, mIndex);
                    ans = Integer.parseInt((st.nextToken()));
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
//                    log("    ★ CMD_ELEMENT: Ans=%d, user=%d\n", ans, userAns);
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
//        userSolution.apiInfo();
        return isCorrect;
    }

    private static void log(String s, Object ... args) {
        System.out.printf(s+"\n", args);
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        System.setIn(new java.io.FileInputStream(ProInputFile.number(8)));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

//        TC = 1;
        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br,testcase) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}
