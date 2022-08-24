package swExpertAcademy.practicalTraining;

import java.util.Map;
import java.util.TreeMap;

/**
 * [No. 2] 14614. [Pro] 긴 사다리 게임 (8/11)<br/>
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초 <br/>
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내 <br/>
 *
 * <br/>[제약사항]<br/>
 *
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.<br/>
 * 2. 각 테스트 케이스에서 add() 함수의 호출 횟수는 200,000 이하이다.<br/>
 * 3. 각 테스트 케이스에서 remove() 함수의 호출 횟수는 5,000 이하이다.<br/>
 * 4. 각 테스트 케이스에서 numberOfCross() 함수의 호출 횟수는 500 이하이다.<br/>
 * 5. 각 테스트 케이스에서 participant() 함수의 호출 횟수는 500 이하이다.<br/>
 * 6. 사다리 게임을 진행했을 때, 한 참가자가 지나게 되는 가로줄의 개수는 항상 5,000개 이하이다.<br/>
 * */
public class SWEA_PRO_P02_UserSolution implements SWEA_PRO_P02.UserSolution {

    private static final int MAX_ID = 100;

    /** TreeMap[X]<Y, +1 or -1> */
    private TreeMap<Integer, Byte>[] verLine;

    @Override
    public void init() {
        verLine = new TreeMap[MAX_ID + 1];

        for (int i = 1; i <= MAX_ID; i++) {
            verLine[i] = new TreeMap<>();
        }
    }

    @Override
    public void add(int mX, int mY) {
        verLine[mX].put(mY, (byte) 1);
        verLine[mX + 1].put(mY, (byte) -1);
    }

    @Override
    public void remove(int mX, int mY) {
        verLine[mX].remove(mY);
        verLine[mX + 1].remove(mY);
    }

    @Override
    public int numberOfCross(int mID) {
        int count = 0;
        int x = mID;
        int y = 0;

        while (true) {
            Map.Entry<Integer, Byte> entry = verLine[x].ceilingEntry(y);

            if (entry == null)
                break;

            x += entry.getValue();
            y = entry.getKey() + 1;

            count += 1;
        }

        return count;
    }

    @Override
    public int participant(int mX, int mY) {
        int x = mX;
        int y = mY;

        while (y > 0) {
            Map.Entry<Integer, Byte> entry = verLine[x].floorEntry(y);

            if (entry == null)
                break;

            x += entry.getValue();
            y = entry.getKey() - 1;
        }

        return x;
    }
}
