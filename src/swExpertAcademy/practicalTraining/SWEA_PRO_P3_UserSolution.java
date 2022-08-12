package swExpertAcademy.practicalTraining;


import java.util.*;

/**
 * [No. 3] 14612. [Pro] 메모리 시스템 (8/12)
 *
 * 시간 : 25개 테스트케이스를 합쳐서 C++의 경우 3초 / Java의 경우 3초
 * 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
 *
 * [제약사항]
 * 1. 각 테스트 케이스 시작 시 init() 함수가 호출된다.
 * 2. 각 테스트 케이스에서 모든 함수의 호출 횟수 총합은 30,000 이하이다.
 *
 * TODO: 트립(Treap) 자료구조를 직접 구현해서 풀어보기
 * */
public class SWEA_PRO_P3_UserSolution {

    /** Memory Size */
    private int memorySize;

    /** (Key, Value) = (Address, Size) */
    private TreeMap<Integer, Integer> allocTree;

    /** (Key, Value) = (Size, Address Ascending List) */
    private TreeMap<Integer, LinkedList<Integer>> emptyTree;

    /**
     * 각 테스트 케이스의 처음에 호출된다.<br/>
     * 전체 메모리의 크기 N이 주어진다. 모두 빈 공간이다.
     *
     * @param N 메모리의 크기 (30 ≤ N ≤ 100,000,000)
     * */
    public void init(int N) {
        memorySize = N;
        allocTree = new TreeMap<>();
        emptyTree = new TreeMap<>();

        LinkedList<Integer> addresses = new LinkedList<>();
        addresses.add(0);
        emptyTree.put(N, addresses);
    }

    /**
     * mSize 크기의 메모리 할당이 요청된다.<br/>
     * 수용할 수 있는 빈 공간 중에서, 가장 작은 빈 공간에 할당한다. 즉, 최적 적합 할당(Best Fit Allocation) 방식을 사용한다.<br/>
     * 가장 작은 빈 공간이 여러 개 있을 경우에는, 가장 앞쪽에 있는 빈 공간에 할당한다.<br/>
     *
     * @param mSize 할당이 요청된 메모리 크기 (1 ≤ mSize ≤ N)
     *
     * @return  할당된 메모리 공간의 시작 주소 값을 반환한다.
     *          할당에 실패한 경우에는 -1을 반환한다.
     * */
    public int allocate(int mSize) {
        if (emptyTree.isEmpty())
            return -1;

        Map.Entry<Integer, LinkedList<Integer>> entry = emptyTree.ceilingEntry(mSize);

        if (entry == null)
            return -1;

        int size = entry.getKey();
        int allocAddress = entry.getValue().removeFirst();

        allocTree.put(allocAddress, mSize);

        if (entry.getValue().isEmpty()) {
            emptyTree.remove(entry.getKey());
        }

        if (mSize < size) {
            addNewEmptyLocation(size - mSize, allocAddress + mSize);
        }

        return allocAddress;
    }

    /**
     * mAddr 시작 주소 값을 갖는 메모리의 해제가 요청된다.<br/>
     * mAddr 값이 시작 주소 값이 아니거나, 이미 빈 공간일 경우에는 해제에 실패하고 -1을 반환한다.<br/>
     * 그렇지 않을 경우, 해당 메모리 공간을 빈 공간으로 바꾸고, 인접한 빈 공간이 있다면 병합한다. 그리고 해제된 메모리 공간의 크기를 반환한다.
     *
     * @param mAddr 해제가 요청된 메모리 주소 ( 0 ≤ mAddr ≤ N - 1 )
     *
     * @return  해제된 메모리 공간의 크기를 반환한다.
     *          해제에 실패한 경우에는 -1을 반환한다.
     * */
    public int release(int mAddr) {
        Integer size = allocTree.remove(mAddr);

        if (size == null)
            return -1;

        Map.Entry<Integer, Integer> left = allocTree.floorEntry(mAddr);
        Map.Entry<Integer, Integer> right = allocTree.ceilingEntry(mAddr);

        int newEmptyAddr = mAddr;
        int newEmptySize = size;

        if (left != null) {
            if (left.getKey() + left.getValue() != mAddr) {
                newEmptyAddr = left.getKey() + left.getValue();
                newEmptySize += mAddr - newEmptyAddr;

                removeEmptyLocation(mAddr - newEmptyAddr, newEmptyAddr);
            }
        } else if (mAddr != 0) {
            newEmptyAddr = 0;
            newEmptySize += mAddr;

            removeEmptyLocation(mAddr, 0);
        }

        if (right != null) {
            if (mAddr + size != right.getKey()) {
                newEmptySize += right.getKey() - (mAddr + size);

                removeEmptyLocation(right.getKey() - (mAddr + size), mAddr + size);
            }
        } else if (mAddr + size < memorySize) {
            newEmptySize += memorySize - (mAddr + size);

            removeEmptyLocation(memorySize - (mAddr + size), mAddr + size);
        }

        addNewEmptyLocation(newEmptySize, newEmptyAddr);

        return size;
    }

    private void addNewEmptyLocation(int size, int address) {
        LinkedList<Integer> list = emptyTree.get(size);

        if (list == null) {
            list = new LinkedList<>();
            list.add(address);

            emptyTree.put(size, list);
        } else if (list.getLast() <= address) {
            list.addLast(address);
        } else {
            ListIterator<Integer> it = list.listIterator();

            while (it.hasNext()) {
                if (address < it.next()) {
                    it.previous();
                    it.add(address);
                    break;
                }
            }
        }
    }

    private void removeEmptyLocation(int size, int address) {
        LinkedList<Integer> addresses = emptyTree.get(size);
        addresses.remove(new Integer(address));

        if (addresses.isEmpty()) {
            emptyTree.remove(size);
        }
    }

    private void logln(String s, Object ... args) {
        System.out.printf(s + "\n", args);
    }

    public void printInfo() {
        logln("    allocTree ▷▷ %s", allocTree.toString());
        logln("    emptyTree ▷▷ %s", emptyTree.toString());
    }

}